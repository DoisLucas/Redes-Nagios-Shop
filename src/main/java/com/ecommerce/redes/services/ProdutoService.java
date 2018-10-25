package com.ecommerce.redes.services;

import com.ecommerce.redes.models.Produto;
import com.ecommerce.redes.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository pr;

    public List<Produto> getAll(){
        return pr.findAll();
    }

    public Produto getOne(Integer id){
        return pr.findById(id).get();
    }

}
