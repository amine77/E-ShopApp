package com.zarzisdev.EShopApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class EShopAppApplication implements CommandLineRunner {

//	 CommandLineRunner : cette interface sert à tester l'application pour insérer qwuelques données en DB

	public static void main(String[] args) {
		SpringApplication.run(EShopAppApplication.class, args);
	}
	// CommandLineRunner : cette interface sert à tester l'application pour insérer qwuelques données en DB

	@Autowired    // pour faire de l'injection de dépendences on utilise cette annotation
	private TermRepository termRepository;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/terms").allowedOrigins("http://localhost:8383");
				registry.addMapping("/100-terms").allowedOrigins("http://localhost:8383");
				registry.addMapping("/100-terms").allowedOrigins("http://localhost:4200");
			}
		};
	}

	@Override
	public void run(String... arg0) throws Exception {

//		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		termRepository.save(new Term("faire", "do", "machen", df.parse("27/11/2017")));
//		termRepository.save(new Term("grand", "tall", "hoch", df.parse("27/11/2017")));
//		termRepository.save(new Term("gros", "big", "groß", df.parse("27/11/2017")));
//		termRepository.findAll().forEach(t->{
//			System.out.println("En français: "+t.getFr());
//		});
	}
}