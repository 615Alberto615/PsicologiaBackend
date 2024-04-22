package com.taller.psico.bl;

import com.taller.psico.dto.UseriDTO;
import com.taller.psico.entity.Useri;
import com.taller.psico.repository.AvailabilityOfficeRepository;
import com.taller.psico.repository.AvailabilityRepotisory;
import com.taller.psico.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taller.psico.dto.AvailabilityDTO;
import com.taller.psico.entity.Availability;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AvailabilityBl {

    @Autowired
    private AvailabilityRepotisory availabilityRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private AvailabilityOfficeRepository availabilityOfficeRepository;

    public AvailabilityDTO createAvailability(AvailabilityDTO availabilityDTO) {
        Availability availability = new Availability();
        convertToEntity(availability, availabilityDTO);
        availability = availabilityRepository.save(availability);
        return convertToDTO(availability);
    }

    public AvailabilityDTO updateAvailability(int availabilityId, AvailabilityDTO availabilityDTO) {
        Availability availability = availabilityRepository.findById(availabilityId).orElse(null);
        if (availability != null) {
            convertToEntity(availability, availabilityDTO);
            availability = availabilityRepository.save(availability);
            return convertToDTO(availability);
        }
        return null;
    }

    public List<AvailabilityDTO> getAllAvailabilities() {
        return availabilityRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public AvailabilityDTO getAvailabilityById(int availabilityId) {
        return availabilityRepository.findById(availabilityId).map(this::convertToDTO).orElse(null);
    }

    public void deleteAvailabilityLogically(int availabilityId) {
        Availability availability = availabilityRepository.findById(availabilityId).orElse(null);
        if (availability != null) {
            availability.setStatus(false);
            availabilityRepository.save(availability);
        }
    }

    public List<AvailabilityDTO> getAvailabilitiesByUserId(int userId) {
        List<Availability> availabilities = availabilityRepository.findByUserIdUserId(userId);
        return availabilities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }





    private AvailabilityDTO convertToDTO(Availability availability) {
        return new AvailabilityDTO(
                availability.getAvailabilityId(),
                availability.getWeekday(),
                availability.getStartTime(),
                availability.getEndTime(),
                availability.getCodeAvailability(),
                availability.getStatus(),
                availability.getUserId().getUserId()
        );
    }

    private void convertToEntity(Availability availability, AvailabilityDTO availabilityDTO) {
        availability.setWeekday(availabilityDTO.getWeekday());
        availability.setStartTime(availabilityDTO.getStartTime());
        availability.setEndTime(availabilityDTO.getEndTime());
        availability.setCodeAvailability(availabilityDTO.getCodeAvailability());
        availability.setStatus(availabilityDTO.isStatus());
        if (availabilityDTO.getUserId() != null) {
            Useri user = new Useri(); // Solo crear una instancia si es necesario manejar relaciones.
            user.setUserId(availabilityDTO.getUserId());
            availability.setUserId(user); // Asegúrate de que el Useri exista en la base de datos o maneja esto con lógica adicional.
        } else {
            throw new IllegalStateException("User ID must not be null");
        }
    }
}