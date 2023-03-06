package com.tutrit.storestapiservice.controller;

import com.tutrit.storestapiservice.exception.DummyNotFoundException;
import com.tutrit.storestapiservice.exception.EntityNotFoundException;
import com.tutrit.storestapiservice.exception.EntityNotFoundExceptionResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DummyController {

    @GetMapping("/dummy")
    public String dummy() {
        throw new EntityNotFoundException("dummy exception message", "Car");
    }

    @GetMapping("/dummy2")
    public String dummy2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/dummy3")
    public String dummy3() {
        throw new EntityNotFoundExceptionResponseStatus();
    }

    @GetMapping("/dummy4")
    public String dummy4() {
        String[] args = {"additional", "arguments"};
        throw new EntityNotFoundExceptionResponseStatus(HttpStatus.NOT_FOUND, "dummy exception reason", new Throwable("root cause if exists"), "Detailed message code", args);
    }

    @GetMapping("/dummy5")
    public String dummy5() {
        throw new DummyNotFoundException("Dummy is not a joke!");
    }

    @GetMapping("/dummy6")
    public String dummy6() {
        throw new IllegalArgumentException("DUMMY_6: That could be any other exception");
    }
}
