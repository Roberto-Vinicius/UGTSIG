package br.com.formulario.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorMessage", "Ocorreu um erro ao processar sua solicitação,veja se seu arquivo é valido!..");
        return "error";
    }

}
