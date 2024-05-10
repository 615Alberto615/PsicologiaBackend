package com.taller.psico.bl;

import com.taller.psico.dto.AvailabilityDTO;
import com.taller.psico.dto.PeopleDTO;
import com.taller.psico.dto.UserAvailabilitiesDTO;
import com.taller.psico.dto.UseriDTO;
import com.taller.psico.entity.Availability;
import com.taller.psico.entity.People;
import com.taller.psico.entity.Useri;
import com.taller.psico.repository.AvailabilityRepotisory;
import com.taller.psico.repository.UseriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailabilityBl {

    @Autowired
    private AvailabilityRepotisory availabilityRepository;

    @Autowired
    private UseriRepository useriRepository; // Asegúrate de tener esta inyección para acceder a los usuarios

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
        return availabilityRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AvailabilityDTO getAvailabilityById(int availabilityId) {
        return availabilityRepository.findById(availabilityId)
                .map(this::convertToDTO)
                .orElse(null);
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
        UseriDTO userDto = convertUseriToUseriDTO(availability.getUserId());
        return new AvailabilityDTO(
                availability.getAvailabilityId(),
                availability.getWeekday(),
                availability.getStartTime(),
                availability.getEndTime(),
                availability.getCodeAvailability(),
                availability.isStatus(),
                userDto
        );
    }

    private void convertToEntity(Availability availability, AvailabilityDTO availabilityDTO) {
        availability.setWeekday(availabilityDTO.getWeekday());
        availability.setStartTime(availabilityDTO.getStartTime());
        availability.setEndTime(availabilityDTO.getEndTime());
        availability.setCodeAvailability(availabilityDTO.getCodeAvailability());
        availability.setStatus(availabilityDTO.isStatus());
        if (availabilityDTO.getUser() != null) {
            Useri user = useriRepository.findById(availabilityDTO.getUser().getUserId()).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
            availability.setUserId(user);
        } else {
            throw new IllegalStateException("User ID must not be null");
        }
    }

    public List<UserAvailabilitiesDTO> getAllGroupedByUser() {
        List<Useri> allUsers = useriRepository.findAll();
        List<UserAvailabilitiesDTO> grouped = new ArrayList<>();

        for (Useri user : allUsers) {
            UseriDTO userDTO = convertUseriToUseriDTO(user);
            List<AvailabilityDTO> availabilities = user.getAvailabilityCollection()
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            grouped.add(new UserAvailabilitiesDTO(userDTO, availabilities));
        }

        return grouped;
    }

    private UseriDTO convertUseriToUseriDTO(Useri user) {
        if (user == null) {
            return null;
        }
        UseriDTO dto = new UseriDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setPassword(user.getPassword()); // Considera las implicaciones de seguridad
        dto.setStatus(user.getStatus());

        // Convertir la entidad People a PeopleDTO, asumiendo que tienes un método adecuado para hacerlo
        PeopleDTO peopleDTO = convertPeopleToPeopleDTO(user.getPeopleId());
        dto.setPeople(peopleDTO); // Establecer el PeopleDTO convertido

        dto.setRolId(user.getRolId().getRolId());
        return dto;
    }

    private PeopleDTO convertPeopleToPeopleDTO(People people) {
        if (people == null) {
            return null;
        }
        PeopleDTO dto = new PeopleDTO();
        dto.setPeopleId(people.getPeopleId());
        dto.setName(people.getName());
        dto.setFirstLastname(people.getFirstLastname());
        dto.setSecondLastname(people.getSecondLastname());
        dto.setEmail(people.getEmail());
        dto.setAge(people.getAge());
        dto.setCellphone(people.getCellphone());
        dto.setAddress(people.getAddress());
        dto.setCi(people.getCi());
        dto.setStatus(people.getStatus()); // Asegúrate de tener este campo en PeopleDTO si es necesario
        return dto;
    }

}
