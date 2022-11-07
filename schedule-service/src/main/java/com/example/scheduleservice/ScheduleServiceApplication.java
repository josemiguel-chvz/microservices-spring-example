package com.example.scheduleservice;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.scheduleservice.services.FileUploadService;

@SpringBootApplication
public class ScheduleServiceApplication implements CommandLineRunner {

	@Resource
	FileUploadService fileUploadService;
	public static void main(String[] args) {
		SpringApplication.run(ScheduleServiceApplication.class, args);
	}

	// Al momento de inicializar la aplicación
	// Se borran las subidas
	// y se genera la carpeta uploads en la raiz del proyecto/contenedor
	@Override
	public void run(String... args) throws Exception {
		fileUploadService.deleteAll();
		fileUploadService.init();
	}

}
