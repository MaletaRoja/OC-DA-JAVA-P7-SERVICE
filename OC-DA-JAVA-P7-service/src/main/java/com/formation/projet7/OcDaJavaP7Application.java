package com.formation.projet7;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.formation.projet7.model.Emprunt;
import com.formation.projet7.proxy.MicroServiceMail;
import com.formation.projet7.service.jpa.EmpruntService;

@SpringBootApplication
@EnableFeignClients("com.formation.projet7")
public class OcDaJavaP7Application {
	
	@Autowired
	EmpruntService empruntService;

	@Autowired
	MicroServiceMail microServiceMail;
	
	public static void main(String[] args) {
		SpringApplication.run(OcDaJavaP7Application.class, args);
	/*	
		Timer timer = new Timer();
		TimerTask scrutation = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Bonjour");
			}
		};
		
		timer.schedule(scrutation, 0, 2000);
		
	*/
		
	}
	
	@Scheduled(initialDelay = 1000L, fixedDelay = 2000L)
	void scrutation() throws InterruptedException {
		
		System.out.println("Now is " + new Date());
		List<Emprunt> empruntsActifs = empruntService.obtenirEmpruntsActif();
		System.out.println("Nbre d'emprunts actifs: " + empruntsActifs.size());
		Emprunt em = empruntsActifs.get(0);
		if (em.getFin().isBefore(LocalDateTime.now()) ){
			
			System.out.println("Alerte!");
			microServiceMail.sendSimpleEmail();
		}
				
		Thread.sleep(5000L);
	
	}
	
	@Configuration
	@EnableScheduling
	@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
	class SchedulingConfiguration{
	
	}
}
