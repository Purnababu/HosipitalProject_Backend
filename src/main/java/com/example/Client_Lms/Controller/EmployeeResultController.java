package com.example.Client_Lms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Client_Lms.Entity.EmployeeResult;
import com.example.Client_Lms.Service.EmployeeResultService;

@RestController
public class EmployeeResultController {

	@Autowired
	private EmployeeResultService employeeResultService;

	@PostMapping("saveReslu")
	public EmployeeResult saveEmployeeResult(@RequestBody EmployeeResult employeeResult) {
		return employeeResultService.saveEmployeeResult(employeeResult);
	}

	@GetMapping("/getallresults")
	public List<EmployeeResult> getAllEmployeeResults() {
		return employeeResultService.getAllEmployeeResults();
	}

	@GetMapping("/{id}")
	public EmployeeResult getEmployeeResultById(@PathVariable Long id) {
		return employeeResultService.getEmployeeResultById(id);
	}

}
