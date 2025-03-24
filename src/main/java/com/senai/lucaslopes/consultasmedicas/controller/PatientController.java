package com.senai.lucaslopes.consultasmedicas.controller;

import com.senai.lucaslopes.consultasmedicas.entity.Patient;
import com.senai.lucaslopes.consultasmedicas.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pessoa/pacientes")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Patient> buscarTodosPacientes() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Patient buscarPacientePorId(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping("/documento/{cpf}")
    public List<Patient> buscarPacientePorCpf(@PathVariable String cpf) {
        return service.findByPacienteCPF(cpf);
    }

    @GetMapping("/busca-nome/{nome}")
    public List<Patient> pesquisarPacientesPorNome(@PathVariable String nome) {
        return service.findByPacienteNome(nome);
    }

    @PostMapping
    public Patient salvarNovoPaciente(@RequestBody Patient patient) {
        return service.save(patient);
    }

    @PutMapping
    public Patient modificarPaciente(@RequestBody Patient patient) {
        return service.save(patient);
    }

    @DeleteMapping("/{id}")
    public String removerPaciente(@PathVariable int id) {
        service.delete(id);
        return "Paciente removido com sucesso!";
    }
}
