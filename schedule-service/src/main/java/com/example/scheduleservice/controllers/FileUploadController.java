package com.example.scheduleservice.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.scheduleservice.services.ClockService;
import com.example.scheduleservice.services.FileUploadService;

@Controller
@RequestMapping("/schedules/files")
@CrossOrigin("http://localhost:3000")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    ClockService clockService;

    @PostMapping("/clocks/upload")
    public ResponseEntity<String> uploadClockFile(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            System.out.println("Nombre de archivo: " + filename);
            // Subir archivo
            fileUploadService.save(file);
            // A partir de archivo guardado, leer este y guardar datos en bd
            clockService.uploadClockFile(filename);

            return ResponseEntity.ok().body("Archivo subido correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
