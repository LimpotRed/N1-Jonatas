package com.senai.lucaslopes.consultasmedicas.repository;

import com.senai.lucaslopes.consultasmedicas.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query("select dn from Doctor dn where dn.nomeMedico like concat('%', :DoctorName, '%')")
    List<Doctor> findByDoctorName(@Param("DoctorName") String doctorName);

    @Query("select dc from Doctor dc where dc.crm = :crm")
    List<Doctor> findByCrm(@Param("crm") int crm);

    @Query("select ds from Doctor ds where ds.especialidade like concat('%', :specialty, '%')")
    List<Doctor> findBySpecialty(@Param("specialty") String specialty);
}

