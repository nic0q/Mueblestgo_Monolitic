package com.tingeso.tingeso.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.Generated;

@Service
@Generated
public class UploadService {
  private String folder = "cargas"+File.separator;
  private final Logger logg = LoggerFactory.getLogger(UploadService.class);
  @Generated
  public String save(MultipartFile file) {
    if (!file.isEmpty()) {
      try {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(folder + file.getOriginalFilename());
        Files.write(path, bytes);
        logg.info("Archivo guardado");
      } catch (IOException e) {
        logg.error("No se pudo guardar", e);
      }
    }
    return "Archivo guardado correctamente";
  }
}
