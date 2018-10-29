package com.ecommerce.redes.controllers;

import com.ecommerce.redes.models.Produto;
import com.ecommerce.redes.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ProdutoService ps;

    @RequestMapping(value = "/")
    public String index(Model model){

        Produto banner_principal = ps.getOne(1);

        List<Produto> produtos_list = ps.getAll();
        produtos_list.remove(banner_principal);

        model.addAttribute("produtos", produtos_list);
        model.addAttribute("principal", banner_principal);

        return "index";
    }

    @RequestMapping(value = "/produto/{id}")
    public String produto_detalhe(Model model, @PathVariable("id") Integer id){
        model.addAttribute("produto", ps.getOne(id));
        return "produto_detalhe";
    }

}
