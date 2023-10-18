package com.example.employeems.service;

import com.example.employeems.dto.EmployeeDto;
import com.example.employeems.entity.Employee;
import com.example.employeems.repo.EmployeeRepo;
import com.example.employeems.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired

    private ModelMapper modelMapper;
    public String saveEmployee(EmployeeDto employeeDto){
        if(employeeRepo.existsById(employeeDto.getEmpId())){
            return VarList.RSP_DUPLICATED;
        }else{
            employeeRepo.save(modelMapper.map(employeeDto, Employee.class));
            return  VarList.RSP_SUCCESS;
        }
    }
    public String updateEmployee(EmployeeDto employeeDto) {
        if(employeeRepo.existsById(employeeDto.getEmpId())){
            employeeRepo.save(modelMapper.map(employeeDto, Employee.class));
            return  VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<EmployeeDto> findAllEmployee(){
        List<Employee> allEmployee = employeeRepo.findAll();
        return modelMapper.map(allEmployee,new TypeToken<ArrayList<EmployeeDto>>(){}.getType());
    }

    public EmployeeDto searchEmployee(int empId){
        Employee employee =employeeRepo.findById(empId).orElse(null);
        return modelMapper.map(employee,EmployeeDto.class);
    }

    public String deleteEmployee(int empId){

        if(employeeRepo.existsById(empId)){
            employeeRepo.deleteById(empId);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }

    }

}
