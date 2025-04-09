package com.example.hello_again_admin.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.hello_again_admin.model.Storage;

@Service
public class FileStorageService implements Storage {

    private final Path uploadLocation = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectory(uploadLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.uploadLocation.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.uploadLocation, 1).filter(path -> !path.equals(this.uploadLocation))
                    .map(this.uploadLocation::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filename) {
        return uploadLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException(String.format("Failed to read file: %s", filename));
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not load file as a resource");
        }
    }

    @Override
    public void deleteAll() {
        try {
            Files.walk(this.uploadLocation)
                    .map(Path::toFile)
                    .forEach(file -> {
                        if (!file.delete()) {
                            throw new RuntimeException("Failed to delete file: " + file);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete files", e);
        }
    }

}
