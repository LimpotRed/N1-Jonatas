package com.senai.lucaslopes.consultasmedicas.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "CPF", nullable = false, length = 11)
    private String CPF;

    @Column(name = "dataNascimento", nullable = false, length = 10)
    private String dataNascimento;

    @Column(name = "telefone", nullable = false, length = 13)
    private String telefone;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Consultation> consultations;

    public Patient() {
    }

    public Patient(Integer id, String nome, String CPF, String dataNascimento, String telefone) {
        this.id = id;
        this.nome = nome;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Consultation> getConsultas() {
        return consultations;
    }
    public void setConsultas(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
