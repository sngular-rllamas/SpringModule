package com.hugojuradogarcia.commons;

import com.hugojuradogarcia.entity.Employee;
import com.hugojuradogarcia.model.output.Response;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Utils {

    public static String statusTextOk;
    public static String messageOk;
    public static String messageOkSave;

    @Value("${httpStatus.ok.statusText}")
    public void setStatusTextOk(String statusText) {
        Utils.statusTextOk = statusText;
    }
    @Value("${httpStatus.ok.messages}")
    public void setMessageOk(String messages) {
        Utils.messageOk = messages;
    }

    @Value("${httpStatus.ok.messagesSave}")
    public void setMessageOkSave(String messagesSave) {
        Utils.messageOkSave = messagesSave;
    }

    public final Response RESPONSE_OK_FIND_EMPLOYEE(String status, Employee employee) {
        return Response.builder()
                .status(status)
                .statusText(statusTextOk)
                .message(messageOkSave)
                .employee(employee)
                .build();
    }

    public final Response RESPONSE_OK_FIND_ALL_EMPLOYEE(String status, List<Employee> employees) {
        return Response.builder()
                .status(status)
                .statusText(statusTextOk)
                .message(messageOkSave)
                .employees(employees)
                .build();
    }

    public final Response RESPONSE_OK_PDF(String status, byte[] bytes) {
        return Response.builder()
                .status(status)
                .statusText(statusTextOk)
                .message(messageOkSave)
                .bytes(bytes)
                .build();
    }
}
