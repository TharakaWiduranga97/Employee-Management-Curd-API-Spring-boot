package com.example.employeems.controller;

import com.example.employeems.dto.EmployeeDto;
import com.example.employeems.dto.ResponseDto;
import com.example.employeems.service.EmployeeService;
import com.example.employeems.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ResponseDto responseDto;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            String res = employeeService.saveEmployee(employeeDto);
            if (res.equals("00")) {
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);


            } else if (res.equals("06")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Already Registred");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            } else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDto.setCode(VarList.RSP_FAIL);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   @PutMapping(value = "/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDto employeeDto) {
       try {
           String res = employeeService.updateEmployee(employeeDto);
           if (res.equals("00")) {
               responseDto.setCode(VarList.RSP_SUCCESS);
               responseDto.setMessage("Success");
               responseDto.setContent(employeeDto);
               return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);


           } else if (res.equals("01")) {
               responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
               responseDto.setMessage("Employee Not Found");
               responseDto.setContent(employeeDto);
               return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
           } else {
               responseDto.setCode(VarList.RSP_FAIL);
               responseDto.setMessage("Error");
               responseDto.setContent(null);
               return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
           }
       } catch (Exception e) {
           responseDto.setCode(VarList.RSP_FAIL);
           responseDto.setMessage(e.getMessage());
           responseDto.setContent(null);
           return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
       }

   }
@GetMapping(value="/getAllEmployee")
   public ResponseEntity getAllEmployee(){

        try{
            List<EmployeeDto> employeeDtoList = employeeService.findAllEmployee();
            responseDto.setCode(VarList.RSP_SUCCESS);
            responseDto.setMessage("Success");
            responseDto.setContent(employeeDtoList);
            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);


        }catch(Exception e){
            responseDto.setCode(VarList.RSP_FAIL);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

   }
   @GetMapping("/searchEmployee/{empId}")
   public ResponseEntity searchEmployee(@PathVariable int empId){
       try{
           EmployeeDto employeeDto = employeeService.searchEmployee(empId);

           if(employeeDto!=null){
               responseDto.setCode(VarList.RSP_SUCCESS);
               responseDto.setMessage("Success");
               responseDto.setContent(employeeDto);
               return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
           }else{
               responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
               responseDto.setMessage("Employee Not Found");
               responseDto.setContent(null);
               return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);

           }



       }catch(Exception e){
           responseDto.setCode(VarList.RSP_FAIL);
           responseDto.setMessage(e.getMessage());
           responseDto.setContent(null);
           return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
       }

   }
    @DeleteMapping("/deleteEmployee/{empId}")
    public ResponseEntity deleteEmployee(@PathVariable int empId){
        try{
            String res = employeeService.deleteEmployee(empId);

            if(res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Employee deleted");
                responseDto.setContent(empId);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else{
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Employee Not Found");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);

            }



        }catch(Exception e){
            responseDto.setCode(VarList.RSP_FAIL);
            responseDto.setMessage(e.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
