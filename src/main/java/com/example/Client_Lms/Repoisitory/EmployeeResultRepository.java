package com.example.Client_Lms.Repoisitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Client_Lms.Entity.EmployeeResult;

@Repository
public interface EmployeeResultRepository extends JpaRepository<EmployeeResult, Long> {

	boolean existsByEmployeeEmailAndCourseName(String employeeEmail, String courseName);

	Optional<EmployeeResult> findByEmployeeEmailAndCourseName(String employeeEmail, String courseName);
}
