package br.com.formulario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


public class Validacao {
     @Autowired
   // private CurriculoService curriculoService;
     private boolean ValidarArquivo(MultipartFile file) {
        String[] allowedExtensions = {"doc", "docx", "pdf"};
        for (String ext : allowedExtensions) {
            if (file.getOriginalFilename().toLowerCase().endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    }
}