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

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Consultation> findAllPaciente() {
        return consultationRepository.findAll();
    }

    public Consultation findByIdPaciente(int id) {
        return consultationRepository.findById(id).orElseThrow(() -> new RuntimeException("Não possui consulta"));
    }

    public List<Consultation> consultasPaciente(String nome){
        return consultationRepository.buscaConsultaPaciente(nome);
    }


    public Consultation salvarConsulta(Consultation consultation) {
        boolean consultaExiste = consultationRepository.existsByPacienteIdAndHorarioConsultaAndDataConsulta(
                consultation.getPaciente().getId(),
                consultation.getHorarioConsulta(),
                consultation.getDataConsulta()
        );
        if (consultaExiste) {
            throw new RuntimeException("Paciente ja possui consulta nessa data");
        }

        consultation.getPaciente().getConsultas().add(consultation);
        consultation.getMedico().getConsultas().add(consultation);
        consultation.setStatus(ConsultationStatus.CONSULTA_AGENDADA);

        return consultationRepository.save(consultation);
    }


    public void delete(Integer id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi encontrada nenhuma consulta"));

        consultation.getPaciente().getConsultas().remove(consultation);
        patientRepository.save(consultation.getPaciente());

        consultation.getMedico().getConsultas().remove(consultation);
        doctorRepository.save(consultation.getMedico());

        if (consultation.getStatus() == ConsultationStatus.CONSULTA_REALIZADA) {
            throw new RuntimeException("Essa consulta ja foi realizada");
        }
        consultation.setStatus(ConsultationStatus.CONSULTA_CANCELADA);
        consultationRepository.deleteById(id);
    }


    public Consultation atualizarConsulta(Consultation consultation) {
        Consultation consultationAntiga = consultationRepository.findById(consultation.getId())
                .orElseThrow(() -> new RuntimeException("Não possui consulta"));

        consultationAntiga.getPaciente().getConsultas().remove(consultationAntiga);
        patientRepository.save(consultationAntiga.getPaciente());

        consultationAntiga.getMedico().getConsultas().remove(consultationAntiga);
        doctorRepository.save(consultationAntiga.getMedico());

        if (consultation.getStatus() == ConsultationStatus.CONSULTA_CANCELADA) {
            throw new RuntimeException("Não é permitido fazer alteraçãp em uma consulta cancelada.");
        }

        if (LocalDate.parse(consultation.getDataConsulta()).isAfter(LocalDate.now()) ||
                (LocalDate.parse(consultation.getDataConsulta()).isEqual(LocalDate.now()) &&
                        LocalTime.parse(consultation.getHorarioConsulta()).isAfter(LocalTime.now()))) {
            throw new RuntimeException("Não é permitido fazer alteraçãp em uma consulta antes do horario.");
        }
        consultation.setStatus(ConsultationStatus.CONSULTA_REALIZADA);

        return consultationRepository.save(consultation);
    }

}
