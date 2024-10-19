package com.example.demo.service.impl;

import com.example.demo.RequestResponseDTO.RegistrationRequestResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Registration;
import com.example.demo.repository.RegistrationRepository;
import com.example.demo.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public RegistrationRequestResponseDTO createRegistration(RegistrationRequestResponseDTO dto) {
        Registration registration = new Registration();
        registration.setName(dto.getName());
        registration.setEmail(dto.getEmail());
        registration.setDateOfBirth(dto.getDateOfBirth());

        registrationRepository.saveAndFlush(registration);

        dto.setId(registration.getId());
        dto.setStatusMessage("Registration created successfully");
        return dto;
    }

    @Override
    public RegistrationRequestResponseDTO getRegistrationById(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found"));

        return mapToDTO(registration);
    }

    @Override
    public List<RegistrationRequestResponseDTO> getAllRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();
        return registrations.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationRequestResponseDTO updateRegistration(Long id, RegistrationRequestResponseDTO dto) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found"));

        registration.setName(dto.getName());
        registration.setEmail(dto.getEmail());
        registration.setDateOfBirth(dto.getDateOfBirth());
        registrationRepository.saveAndFlush(registration);

        dto.setId(registration.getId());
        dto.setStatusMessage("Registration updated successfully");
        return dto;
    }

    @Override
    public void deleteRegistration(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found"));
        registrationRepository.delete(registration);
    }

    private RegistrationRequestResponseDTO mapToDTO(Registration registration) {
        RegistrationRequestResponseDTO dto = new RegistrationRequestResponseDTO();
        dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setDateOfBirth(registration.getDateOfBirth());
        dto.setStatusMessage("Success");
        return dto;
    }
}
