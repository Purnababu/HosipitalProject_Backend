package com.example.Client_Lms.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Client_Lms.Entity.EmployeeResult;
import com.example.Client_Lms.Repoisitory.EmployeeResultRepository;
import com.example.Client_Lms.Service.EmployeeResultService;

@Service
public class EmployeeResultServiceImpl implements EmployeeResultService {

	@Autowired
	private EmployeeResultRepository employeeResultRepository;

//	@Override
//	public EmployeeResult saveEmployeeResult(EmployeeResult employeeResult) {
//		return employeeResultRepository.save(employeeResult);
//	}

	public EmployeeResult saveEmployeeResult(EmployeeResult employeeResult) {
		// Check if the employee email and course name already exist
		if (employeeResultRepository.existsByEmployeeEmailAndCourseName(employeeResult.getEmployeeEmail(),
				employeeResult.getCourseName())) {
			// If exists, return null or throw an exception
			throw new IllegalArgumentException("Result already exists for this employee and course.");
		}
		// Save new result
		return employeeResultRepository.save(employeeResult);
	}

	@Override
	public List<EmployeeResult> getAllEmployeeResults() {
		return employeeResultRepository.findAll();
	}

	@Override
	public EmployeeResult getEmployeeResultById(Long id) {
		Optional<EmployeeResult> result = employeeResultRepository.findById(id);
		return result.orElse(null);
	}

	@Override
	public String checkIfAlreadySubmitted(String employeeEmail, String courseName) {
		// Check if a result already exists for the given employee email and course name
		Optional<EmployeeResult> result = employeeResultRepository.findByEmployeeEmailAndCourseName(employeeEmail,
				courseName);

		// Return appropriate message based on presence of the result
		if (result.isPresent()) {
			return "Already Done";
		} else {
			return "Not Submitted";
		}
	}

}