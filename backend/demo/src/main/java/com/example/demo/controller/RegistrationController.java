package com.example.demo.controller;
import com.example.demo.RequestResponseDTO.RegistrationRequestResponseDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationRequestResponseDTO> createRegistration(
            @Valid @RequestBody RegistrationRequestResponseDTO dto) {
        RegistrationRequestResponseDTO createdRegistration = registrationService.createRegistration(dto);
        return ResponseEntity.ok(createdRegistration);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationRequestResponseDTO> getRegistrationById(@PathVariable Long id) {
        RegistrationRequestResponseDTO dto = registrationService.getRegistrationById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationRequestResponseDTO>> getAllRegistrations() {
        List<RegistrationRequestResponseDTO> dtos = registrationService.getAllRegistrations();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationRequestResponseDTO> updateRegistration(
            @PathVariable Long id,
            @Valid @RequestBody RegistrationRequestResponseDTO dto) {
        RegistrationRequestResponseDTO updatedRegistration = registrationService.updateRegistration(id, dto);
        return ResponseEntity.ok(updatedRegistration);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    }
}

