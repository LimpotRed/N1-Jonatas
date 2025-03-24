package com.senai.lucaslopes.consultasmedicas.service;

import com.senai.lucaslopes.consultasmedicas.entity.Consultation;
import com.senai.lucaslopes.consultasmedicas.entity.ConsultationStatus;
import com.senai.lucaslopes.consultasmedicas.repository.ConsultationRepository;
import com.senai.lucaslopes.consultasmedicas.repository.DoctorRepository;
import com.senai.lucaslopes.consultasmedicas.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Consultation> obterConsultas() {
        return consultationRepository.findAll();
    }

    public Consultation buscarConsultaPorId(int id) {
        Optional<Consultation> consulta = consultationRepository.findById(id);
        return consulta.orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }

    public List<Consultation> buscarConsultasPorPaciente(String nomePaciente) {
        return consultationRepository.buscaConsultaPaciente(nomePaciente);
    }

    public Consultation agendarConsulta(Consultation consulta) {

        if (isConsultaExistente(consulta)) {
            throw new RuntimeException("O paciente já possui uma consulta agendada nesta data e horário.");
        }


        adicionarConsultaPacienteEMedico(consulta);


        consulta.setStatus(ConsultationStatus.CONSULTA_AGENDADA);


        return salvarConsultaEAtualizarRelacionados(consulta);
    }

    public void cancelarConsulta(Integer id) {

        Consultation consulta = buscarConsultaPorId(id);


        if (consulta.getStatus() == ConsultationStatus.CONSULTA_REALIZADA) {
            throw new RuntimeException("Não é possível cancelar uma consulta já realizada.");
        }

        removerConsultaPacienteEMedico(consulta);
        consulta.setStatus(ConsultationStatus.CONSULTA_CANCELADA);

        consultationRepository.deleteById(id);
    }

    public Consultation modificarConsulta(Consultation consultaAtualizada) {
        Consultation consultaExistente = buscarConsultaPorId(consultaAtualizada.getId());

        if (consultaAtualizada.getStatus() == ConsultationStatus.CONSULTA_CANCELADA) {
            throw new RuntimeException("Não é possível alterar uma consulta cancelada.");
        }

        validarHorarioConsulta(consultaAtualizada);

        removerConsultaPacienteEMedico(consultaExistente);

        consultaAtualizada.setStatus(ConsultationStatus.CONSULTA_REALIZADA);

        return salvarConsultaEAtualizarRelacionados(consultaAtualizada);
    }

    private boolean isConsultaExistente(Consultation consulta) {
        return consultationRepository.existsByPacienteIdAndHorarioConsultaAndDataConsulta(
                consulta.getPaciente().getId(),
                consulta.getHorarioConsulta(),
                consulta.getDataConsulta()
        );
    }

    private void adicionarConsultaPacienteEMedico(Consultation consulta) {
        consulta.getPaciente().getConsultas().add(consulta);
        consulta.getMedico().getConsultas().add(consulta);
    }

    private void removerConsultaPacienteEMedico(Consultation consulta) {
        consulta.getPaciente().getConsultas().remove(consulta);
        consulta.getMedico().getConsultas().remove(consulta);
        patientRepository.save(consulta.getPaciente());
        doctorRepository.save(consulta.getMedico());
    }

    private void validarHorarioConsulta(Consultation consulta) {
        LocalDate dataConsulta = LocalDate.parse(consulta.getDataConsulta());
        LocalTime horarioConsulta = LocalTime.parse(consulta.getHorarioConsulta());

        if (dataConsulta.isAfter(LocalDate.now()) ||
                (dataConsulta.isEqual(LocalDate.now()) && horarioConsulta.isAfter(LocalTime.now()))) {
            throw new RuntimeException("Não é permitido alterar uma consulta antes do horário agendado.");
        }
    }

    private Consultation salvarConsultaEAtualizarRelacionados(Consultation consulta) {
        consultationRepository.save(consulta);
        patientRepository.save(consulta.getPaciente());
        doctorRepository.save(consulta.getMedico());
        return consulta;
    }
}

