package br.com.siberius.projeto.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Endereco {

    @Column(name = "ENDERECO_CEP")
    private String cep;

    @Column(name = "ENDERECO_LOGRADOURO")
    private String logradouro;

    @Column(name = "ENDERECO_NUMERO")
    private String numero;

    @Column(name = "ENDERECO_COMPLEMENTO")
    private String complemento;

    @Column(name = "ENDERECO_BAIRRO")
    private String bairro;

    @ManyToOne
    @JoinColumn(name = "ENDERECO_CIDADE_ID")
    private Cidade cidade;

}