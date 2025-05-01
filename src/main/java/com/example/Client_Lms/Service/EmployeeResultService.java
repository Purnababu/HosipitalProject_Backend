package com.example.Client_Lms.Service;

import java.util.List;

import com.example.Client_Lms.Entity.EmployeeResult;

public interface EmployeeResultService {

	EmployeeResult saveEmployeeResult(EmployeeResult employeeResult);

	List<EmployeeResult> getAllEmployeeResults();

	EmployeeResult getEmployeeResultById(Long id);

	String checkIfAlreadySubmitted(String employeeEmail, String courseName);
}
