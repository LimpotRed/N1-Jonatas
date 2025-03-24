package com.senai.lucaslopes.consultasmedicas.controller;

import com.senai.lucaslopes.consultasmedicas.entity.Consultation;
import com.senai.lucaslopes.consultasmedicas.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consultas")
public class ConsultationController {

    private final ConsultationService consultationService;

    @Autowired
    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @GetMapping
    public List<Consultation> listarTodasConsultas() {
        return consultationService.obterConsultas();
    }

    @GetMapping("/{id}")
    public Consultation obterConsultaPorId(@PathVariable Integer id) {
        return consultationService.buscarConsultaPorId(id);
    }

    @PostMapping
    public Consultation criarConsulta(@RequestBody Consultation consultation) {
        return consultationService.agendarConsulta(consultation);
    }

    @GetMapping("/paciente/{nome}")
    public List<Consultation> listarConsultasPorPaciente(@PathVariable String nome) {
        return consultationService.buscarConsultasPorPaciente(nome);
    }

    @PutMapping
    public Consultation modificarConsulta(@RequestBody Consultation consultation) {
        return consultationService.modificarConsulta(consultation);
    }

    @DeleteMapping("/{id}")
    public void removerConsulta(@PathVariable Integer id) {
        consultationService.cancelarConsulta(id);
    }
}
