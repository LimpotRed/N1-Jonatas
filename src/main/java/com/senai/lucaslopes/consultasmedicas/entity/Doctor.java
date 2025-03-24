package com.senai.lucaslopes.consultasmedicas.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "medicos")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nomeMedico", nullable = false, length = 100)
    private String nomeMedico;

    @Column(name = "especialidade", nullable = false, length = 100)
    private String especialidade;

    @Column(name = "crm", nullable = false, length = 6)
    private Integer crm;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Consultation> consultations;

    public Doctor() {
    }

    public Doctor(Integer id, String nomeMedico, String especialidade, Integer crm) {
        this.id = id;
        this.nomeMedico = nomeMedico;
        this.especialidade = especialidade;
        this.crm = crm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNome(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public List<Consultation> getConsultas() {
        return consultations;
    }

    public void setConsultas(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}