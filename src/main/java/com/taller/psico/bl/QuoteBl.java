package com.taller.psico.bl;

import com.taller.psico.dto.AvailabilityDTO;
import com.taller.psico.dto.PeopleDTO;
import com.taller.psico.dto.QuotesDTO;
import com.taller.psico.dto.UseriDTO;
import com.taller.psico.entity.*;
import com.taller.psico.repository.AvailabilityRepotisory;
import com.taller.psico.repository.QuotesRepository;
import com.taller.psico.repository.UseriRepository;
import com.taller.psico.repository.AppointmentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteBl {

    @Autowired
    private QuotesRepository quotesRepository;

    @Autowired
    private AvailabilityRepotisory availabilityRepository;

    @Autowired
    private UseriRepository useriRepository;

    @Autowired
    private AppointmentStatusRepository appointmentStatusRepository;

    // Método para crear una nueva cita considerando la disponibilidad
    public QuotesDTO createQuote(QuotesDTO quoteDTO) throws Exception {
        Availability availability = availabilityRepository.findById(quoteDTO.getAvailability().getAvailabilityId())
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        List<Quotes> existingQuotes = quotesRepository.findByAvailabilityId_AvailabilityIdAndStatus(availability.getAvailabilityId(), true);
        if (existingQuotes.size() < 2) {
            Quotes quote = convertToEntity(new Quotes(), quoteDTO);
            Quotes savedQuote = quotesRepository.save(quote);
            return convertToDTO(savedQuote);
        } else {
            throw new Exception("No more slots available for this time.");
        }
    }

    // Get all quotes
    public List<QuotesDTO> getAllQuotes() {
        List<Quotes> quotes = quotesRepository.findAll();
        return quotes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get user specific quotes
    public List<QuotesDTO> getUserQuotes(int userId) {
        Useri user = useriRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Quotes> quotes = quotesRepository.findByUser(user);
        return quotes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a single quote by ID
    public QuotesDTO getQuoteById(int quotesId) {
        return quotesRepository.findById(quotesId)
                .map(this::convertToDTO)
                .orElse(null);
    }

    // Update a quote
    public QuotesDTO updateQuote(int quotesId, QuotesDTO quoteDTO) {
        return quotesRepository.findById(quotesId)
                .map(quote -> {
                    convertToEntity(quote, quoteDTO);
                    quotesRepository.save(quote);
                    return convertToDTO(quote);
                }).orElse(null);
    }

    // Soft delete a quote by setting its status to false
    public boolean deleteQuote(int quotesId) {
        return quotesRepository.findById(quotesId)
                .map(quote -> {
                    quote.setStatus(false);
                    quotesRepository.save(quote);
                    return true;
                }).orElse(false);
    }

    public List<QuotesDTO> getQuotesByTherapistId(int therapistId) {
        List<Quotes> quotes = quotesRepository.findByTherapistId(therapistId);
        return quotes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Convert from entity to DTO
    private QuotesDTO convertToDTO(Quotes quote) {
        QuotesDTO dto = new QuotesDTO();
        dto.setQuotesId(quote.getQuotesId());
        dto.setReason(quote.getReason());
        dto.setTypeQuotes(quote.getTypeQuotes());
        dto.setStatus(quote.isStatus());
        dto.setAppointmentRequest(quote.getAppointmentRequest());
        dto.setAppointmentStatusId(quote.getAppointmentStatusId().getAppointmentStatusId());
        dto.setAvailability(convertAvailabilityToDTO(quote.getAvailabilityId()));
        dto.setUser(convertUseriToUseriDTO(quote.getUserId()));
        dto.setStartTime(quote.getStartTime());
        dto.setEndTime(quote.getEndTime());
        return dto;
    }

    private AvailabilityDTO convertAvailabilityToDTO(Availability availability) {
        if (availability == null) {
            return null;
        }
        AvailabilityDTO dto = new AvailabilityDTO();
        dto.setAvailabilityId(availability.getAvailabilityId());
        dto.setWeekday(availability.getWeekday());
        dto.setStartTime(availability.getStartTime());
        dto.setEndTime(availability.getEndTime());
        dto.setCodeAvailability(availability.getCodeAvailability());
        dto.setStatus(availability.getStatus());
        dto.setUser(convertUseriToUseriDTO(availability.getUserId()));
        return dto;
    }

    private UseriDTO convertUseriToUseriDTO(Useri user) {
        if (user == null) {
            return null;
        }
        UseriDTO dto = new UseriDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setPassword(user.getPassword()); // Consider security implications
        dto.setStatus(user.getStatus());

        // Convertir la entidad People a PeopleDTO
        PeopleDTO peopleDTO = convertPeopleToPeopleDTO(user.getPeopleId());
        dto.setPeople(peopleDTO);

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



    // Convert from DTO to entity
    private Quotes convertToEntity(Quotes quote, QuotesDTO quoteDTO) {
        quote.setReason(quoteDTO.getReason());
        quote.setTypeQuotes(quoteDTO.getTypeQuotes());
        quote.setStatus(quoteDTO.isStatus());
        quote.setAppointmentRequest(quoteDTO.getAppointmentRequest());

        // Asegurarte de que los objetos y sus IDs no sean null
        if (quoteDTO.getAvailability() != null && quoteDTO.getAvailability().getAvailabilityId() != null) {
            Availability availability = availabilityRepository.findById(quoteDTO.getAvailability().getAvailabilityId())
                    .orElseThrow(() -> new RuntimeException("Availability not found"));
            quote.setAvailabilityId(availability);
        }

        if (quoteDTO.getUser() != null && quoteDTO.getUser().getUserId() != null) {
            Useri user = useriRepository.findById(quoteDTO.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            quote.setUserId(user);
        }

        AppointmentStatus appointmentStatus = appointmentStatusRepository.findById(quoteDTO.getAppointmentStatusId())
                .orElseThrow(() -> new RuntimeException("Appointment Status not found"));
        quote.setAppointmentStatusId(appointmentStatus);
        quote.setStartTime(quoteDTO.getStartTime());
        quote.setEndTime(quoteDTO.getEndTime());
        return quote;
    }

}
