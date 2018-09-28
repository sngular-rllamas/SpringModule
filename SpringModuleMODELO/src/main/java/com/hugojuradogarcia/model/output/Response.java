package com.hugojuradogarcia.model.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hugojuradogarcia.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String status;
    private String statusText;
    private String message;
    private Employee employee;
    private List<Employee> employees;
    private byte[] bytes;
}
