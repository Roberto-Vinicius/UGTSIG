package br.com.formulario.controller;



import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.formulario.DTO.CurriculoFormDto;
import br.com.formulario.model.Curriculo;
import br.com.formulario.services.CurriculoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class CurriculoController  {

    @Autowired
    private CurriculoService curriculoService;
   
    @GetMapping()
    public ModelAndView getForm() {
        ModelAndView mv = new ModelAndView();
        CurriculoFormDto curriculoFormDto = new CurriculoFormDto();
        mv.setViewName("index"); 
        mv.addObject("curriculoFormDto", curriculoFormDto);
        return mv;
    }
 
    @PostMapping("/formulario")
    public ModelAndView postFormulario(
            @RequestParam("file") MultipartFile file,
            @ModelAttribute("curriculoFormDto") @Valid CurriculoFormDto formDTO,
            BindingResult bindingResult,
            HttpServletRequest request)  {
             
        ModelAndView mv = new ModelAndView();  
        
        
        if (bindingResult.hasErrors()) {
            mv.addObject("curriculoFormDto", formDTO);
            mv.setViewName("index"); 
            return mv;
        }

        if (file.isEmpty() || !validarArquivo(file)) {
            bindingResult.rejectValue("arquivo", "file.invalid", "Por favor, envie um arquivo .doc, .docx ou .pdf.");
            mv.setViewName("index");  
            return mv;
        }

        if (file.getSize() > 1048576) { // 1MB
            bindingResult.rejectValue("arquivo", "file.size", "O tamanho máximo do arquivo é 1MB.");
            mv.setViewName("index");  
            return mv;
        }

        Curriculo curriculum = new Curriculo();
        BeanUtils.copyProperties(formDTO, curriculum);
        
        try {
            curriculum.setArquivo(file.getBytes());
        } catch (IOException e) {
            
            mv.setViewName("error");  
            return mv;
        }
        
        curriculum.setIp(request.getRemoteAddr());
       curriculum.setDataHoraEnvio(LocalDateTime.now());

        String originalFilename = file.getOriginalFilename();
        curriculum.setFileName(originalFilename);

      curriculoService.save(curriculum);

        mv.setViewName("enviado");  
        return mv;
    }

    private boolean validarArquivo(MultipartFile arquivo) {
        String[] tipo = {"doc", "docx", "pdf"};
        for (String ext : tipo) {
            if (arquivo.getOriginalFilename().toLowerCase().endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    }
}
