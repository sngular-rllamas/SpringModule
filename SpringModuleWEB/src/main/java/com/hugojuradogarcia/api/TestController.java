package com.hugojuradogarcia.api;

import com.hugojuradogarcia.EmployeeService;
import com.hugojuradogarcia.JasperReportService;
import com.hugojuradogarcia.commons.Utils;
import com.hugojuradogarcia.entity.Employee;
import com.hugojuradogarcia.model.output.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JasperReportService jasperReportService;

    @Autowired
    private Utils utils;

    @GetMapping("/getMessage")
    public String getMessage(){
        return "Hello controller";
    }

    @GetMapping("/getEmployeeById")
    public ResponseEntity getEmployeeById(@RequestParam(value = "id") int id){
        Response response = utils.RESPONSE_OK_FIND_EMPLOYEE(HttpStatus.OK.toString(), employeeService.findEmplyoeeByID(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getNumberEmployeeLike")
    public int getNumberEmployeeLike(@RequestParam(value = "lastName") String lastName){
        return employeeService.findEmployeeByFirstNameLike(lastName);
    }

    @GetMapping("/getAllEmployeeById")
    public ResponseEntity getAllEmployeeById(@RequestParam(value = "id") int id){
        Response response = utils.RESPONSE_OK_FIND_ALL_EMPLOYEE(HttpStatus.OK.toString(), employeeService.findAllEmplyoeeByID(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/getPDF")
    public ResponseEntity getPDF(@RequestBody Employee employee){
        Response response = utils.RESPONSE_OK_PDF(HttpStatus.OK.toString(), jasperReportService.getFormatoSolicitudIsssteEPR(employee));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
