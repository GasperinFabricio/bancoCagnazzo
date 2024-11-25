package com.example.bancoproposta.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@Table(name = "banco")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Proposta> propostas = new ArrayList<>();

    public Banco() {}

    public Banco(Long id) {
        this.id = id;
    }

    public Banco(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(List<Proposta> propostas) {
        this.propostas = propostas;
    }

    public void addProposta(Proposta proposta) {
        propostas.add(proposta);
        proposta.setBanco(this);
    }

    public void removeProposta(Proposta proposta) {
        propostas.remove(proposta);
        proposta.setBanco(null);
    }

    @PrePersist
    @PreUpdate
    private void ensurePropostasHaveBanco() {
        for (Proposta proposta : propostas) {
            proposta.setBanco(this);
        }
    }
}
