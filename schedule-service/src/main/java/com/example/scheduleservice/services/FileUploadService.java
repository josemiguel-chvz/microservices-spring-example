package com.example.scheduleservice.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    // Ruta en raiz de proyecto para guardar archivos
    private final Path root = Paths.get("uploads");

    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Guarda archivo en carpeta root (uploads)
    public void save(MultipartFile file) {
        try {
            Files.copy(
                file.getInputStream(), // Copiar desde archivo que se sube
                root.resolve(file.getOriginalFilename()) // Se copia en carpeta root
            );
        } catch (Exception e) {
            throw new RuntimeException("Archivo ya existe en " + e.getMessage());
        }
    }

    // Comprueba si archivo existe y lo devuelve como un recurso
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se puede leer el archivo");
            }
        } catch (Exception e) {
            throw new RuntimeException("No se puede leer el archivo" + e.getMessage());
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }
}
