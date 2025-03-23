package com.example.Client_Lms.ServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path; // For file operations
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Client_Lms.Entity.Employee;
import com.example.Client_Lms.Entity.Role;
import com.example.Client_Lms.Repoisitory.EmployeeRepoisitory;
import com.example.Client_Lms.Repoisitory.RoleRepoisitory;
import com.example.Client_Lms.Service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
public class EmployeeImpl implements EmployeeService {

	@Autowired
	private RoleRepoisitory roleRepoisitory;

	@Autowired
	private EmployeeRepoisitory employeeRepoisitory;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@Override
	public String initRoleAndAdmin() {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		roleRepoisitory.save(adminRole);

		Role employeeRole = new Role();
		employeeRole.setRoleName("Employee");
		roleRepoisitory.save(employeeRole);

		return "Success";
	}

	@Override
	public Employee addAdmin(Employee admin) {

		Role role = roleRepoisitory.findById("Admin").get();
		Set<Role> adminRole = new HashSet<>();
		adminRole.add(role);
		admin.setRoles(adminRole);
		String encodedPassword = encoder.encode(admin.getPassword());
		admin.setPassword(encodedPassword);
		return employeeRepoisitory.save(admin);
	}

	@Override
	public Employee addEmployee(Employee employee, String roleName) throws Exception {
		Role role = roleRepoisitory.findById(roleName).get();
		Set<Role> employeeRole = new HashSet<>();
		employeeRole.add(role);
		employee.setRoles(employeeRole);

		// Store the original password before encoding
		String originalPassword = employee.getPassword();

		// Encode the password and set it to the employee object
		String encodedPassword = encoder.encode(originalPassword);
		employee.setPassword(encodedPassword);

		return employeeRepoisitory.save(employee);
	}

	@Override
	public List<Employee> gellAllEmployess() {
		List<Employee> allempoyes = employeeRepoisitory.findAll();
		return allempoyes;
	}

}
