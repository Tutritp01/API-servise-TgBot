package com.tutrit.storestapiservice.exception;

import jakarta.servlet.RequestDispatcher;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class GlobalErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public ResponseEntity<ExceptionDto> error(EntityNotFoundException ex, WebRequest request) {
        var dto = new ExceptionDto(LocalDateTime.now().toString(), null, ex.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURI(), ex.entityName);
        final List<ResponseEntity> resultHolder = new ArrayList<>();
        Optional.ofNullable(HttpStatus.valueOf((Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE, 0))).ifPresent(
                status -> {
                    var result = switch (status) {
                        case NOT_FOUND -> new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
                        case INTERNAL_SERVER_ERROR -> new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
                        case FORBIDDEN -> new ResponseEntity<>(dto, HttpStatus.FORBIDDEN);
                        default -> new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
                    };
                    resultHolder.add(result);
                }
        );
        return resultHolder.get(0);
    }
}
