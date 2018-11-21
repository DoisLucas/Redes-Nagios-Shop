package com.ecommerce.redes.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tab_produto")
public class Produto {

    @Id
    @GeneratedValue
    Integer id;
    @NotNull
    String nome;
    @NotNull
    String descricao;
    @NotNull @NegativeOrZero
    Double preco;
    @NotNull
    String path_img;

    public Produto() {
    }

    public Produto(Integer id, String nome, String descricao, Double preco, String path_img) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.path_img = path_img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getPath_img() {
        return path_img;
    }

    public void setPath_img(String path_img) {
        this.path_img = path_img;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", path_img='" + path_img + '\'' +
                '}';
    }
}
