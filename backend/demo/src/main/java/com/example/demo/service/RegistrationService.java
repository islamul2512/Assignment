package com.example.demo.service;




import com.example.demo.RequestResponseDTO.RegistrationRequestResponseDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.model.Registration;

import java.util.List;

public interface RegistrationService {
    RegistrationRequestResponseDTO createRegistration(RegistrationRequestResponseDTO dto);
    RegistrationRequestResponseDTO getRegistrationById(Long id);
    List<RegistrationRequestResponseDTO> getAllRegistrations();
    RegistrationRequestResponseDTO updateRegistration(Long id, RegistrationRequestResponseDTO dto);
    void deleteRegistration(Long id);
}
