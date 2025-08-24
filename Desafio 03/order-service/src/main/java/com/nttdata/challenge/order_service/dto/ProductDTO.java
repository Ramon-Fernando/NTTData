package com.nttdata.challenge.order_service.dto;

public class ProductDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String nome, String descricao, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }
}
