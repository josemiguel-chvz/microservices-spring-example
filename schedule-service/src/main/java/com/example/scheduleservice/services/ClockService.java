package com.example.scheduleservice.services;

import org.springframework.stereotype.Service;

import com.example.scheduleservice.entities.ClockEntity;
import com.example.scheduleservice.repositories.ClockRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

@Service
public class ClockService {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    ClockRepository clockRepository;

    public void uploadClockFile(String filename) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            // Cargar archivo guardado
            Resource data =  fileUploadService.load(filename);
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(data.getInputStream())
            );
            // Lectura de archivo
            String line = reader.readLine();
            while (line != null) {
                String[] values = line.split(";");
                ClockEntity clock = new ClockEntity();
                clock.setDate(LocalDate.parse(values[0], formatter));
                clock.setTime(values[1]);
                clock.setWorker_identification(values[2]);

                // Guardar en BD
                clockRepository.save(clock);

                // Siguiente linea
                line = reader.readLine();
            }
            // Cerrar archivo
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
