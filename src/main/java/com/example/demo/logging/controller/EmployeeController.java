package com.example.demo.logging.controller;

import com.example.demo.logging.annotation.LogMethod;
import com.example.demo.logging.exception.ResourceNotFoundException;
import com.example.demo.logging.model.EmployeeEntity;
import com.example.demo.logging.model.dto.BankInfo;
import com.example.demo.logging.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@LogMethod
@Log4j2
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping("/employees")
  public List<EmployeeEntity> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable(value = "id") Long employeeId)
      throws ResourceNotFoundException {
    log.info("find User");
    log.info("find User {}", employeeId);
    EmployeeEntity employeeEntity = employeeService.getEmployeeById(employeeId);
    return ResponseEntity.ok().body(employeeEntity);
  }

  @PostMapping("/employees")
  public EmployeeEntity createEmployee(@Validated @RequestBody EmployeeEntity employeeEntity) {
    return employeeService.addEmployee(employeeEntity);
  }

  @PutMapping("/employees/{id}")
  public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable(value = "id") Long employeeId, @Validated @RequestBody EmployeeEntity employeeEntityDetails)
      throws ResourceNotFoundException {
    EmployeeEntity updatedEmployeeEntity = employeeService.updateEmployee(employeeId, employeeEntityDetails);
    return ResponseEntity.ok(updatedEmployeeEntity);
  }

  @DeleteMapping("/employees/{id}")
  public void deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
    employeeService.deleteEmployee(employeeId);
  }

  @GetMapping("/employees/{id}/bankInfo")
  public ResponseEntity<BankInfo> getBankInfo(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
    return ResponseEntity.ok(employeeService.getBankInfo(employeeId));
  }

}
