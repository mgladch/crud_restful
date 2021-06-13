package com.hladchuk.internet_Shop.controller;

import com.hladchuk.internet_Shop.model.Administrator;
import com.hladchuk.internet_Shop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:9191")
@RestController
@RequestMapping("/api")
public class AdminController {
    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/administrator")
    ResponseEntity<List<Administrator>> findAllAdministrator() {
        try {
            List<Administrator> administrators = new ArrayList<>();
            administrators.addAll(adminService.findAll());

            if (administrators.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(administrators, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/administratorById/{id}")
    public ResponseEntity<Administrator> findAdministratorById(@PathVariable("id") int id) {
        Optional<Administrator> administrator = Optional.ofNullable(adminService.findById(id).get());
        return administrator.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/administratorByName/{name}")
    public ResponseEntity<List<Administrator>> findAdministratorByName(@PathVariable("name") String name) {
        try {
            List<Administrator> administratorWithName = adminService.findAll().stream()
                    .filter(administrator -> administrator.getName().equals(name))
                    .collect(Collectors.toList());
            if (administratorWithName.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(administratorWithName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/administrators")
    public ResponseEntity<Administrator> saveAdministrator(@RequestBody Administrator administrator) {
        try {
            Administrator createdAdministrator = adminService
                    .save(administrator);
            return new ResponseEntity<>(createdAdministrator, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/administrator/{id}")
    public ResponseEntity<Administrator> updateAdministrator(@PathVariable("id") int id, @RequestBody Administrator administrator) {
        Optional<Administrator> administratorData = Optional.ofNullable(adminService.findById(id)).get();

        if (administratorData.isPresent()) {
            Administrator updatedAdministrator = administratorData.get();
            updatedAdministrator.setName(administrator.getName());
            updatedAdministrator.setSurname(administrator.getSurname());
            updatedAdministrator.setEmail(administrator.getEmail());
            return new ResponseEntity<>(adminService.save(updatedAdministrator), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/administrator/{id}")
    public ResponseEntity<HttpStatus> removeAdministrator(@PathVariable("id") int id) {
        try {
            Administrator administrator = adminService.findById(id).get();
            adminService.removeById(administrator.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
