package com.ecommerce.redes.controllers;

import com.ecommerce.redes.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private ProdutoService ps;

    @RequestMapping(value = "/")
    public String index(Model model){
        model.addAttribute("produtos", ps.getAll());
        model.addAttribute("principal", ps.getAll().get(2));
        return "index";
    }

}
