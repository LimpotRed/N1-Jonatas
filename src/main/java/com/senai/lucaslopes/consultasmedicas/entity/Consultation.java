package com.senai.lucaslopes.consultasmedicas.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "consultas")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dataConsulta", length = 100)
    private String dataConsulta;

    @Column(name = "horarioConsulta", length = 100)
    private String horarioConsulta;

    @Enumerated(EnumType.STRING)
    private ConsultationStatus status;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Patient patient;

    public Consultation() {
    }

    public Consultation(Integer id, String dataConsulta, String horarioConsulta, ConsultationStatus status, Doctor doctor, Patient patient) {
        this.id = id;
        this.dataConsulta = dataConsulta;
        this.horarioConsulta = horarioConsulta;
        this.status = status;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(String horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    public ConsultationStatus getStatus() {
        return status;
    }

    public void setStatus(ConsultationStatus status) {
        this.status = status;
    }

    public Doctor getMedico() {
        return doctor;
    }

    public void setMedico(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPaciente() {
        return patient;
    }

    public void setPaciente(Patient patient) {
        this.patient = patient;
    }
}
