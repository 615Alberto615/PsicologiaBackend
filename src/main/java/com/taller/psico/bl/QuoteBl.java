package com.taller.psico.bl;

import com.taller.psico.dto.*;
import com.taller.psico.entity.*;
import com.taller.psico.repository.AvailabilityRepotisory;
import com.taller.psico.repository.QuotesRepository;
import com.taller.psico.repository.UseriRepository;
import com.taller.psico.repository.AppointmentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        /*List<Quotes> existingQuotes = quotesRepository.findByAvailabilityId_AvailabilityIdAndStatus(availability.getAvailabilityId(), true);
        if (existingQuotes.size() < 2) {
            Quotes quote = convertToEntity(new Quotes(), quoteDTO);
            Quotes savedQuote = quotesRepository.save(quote);
            return convertToDTO(savedQuote);
        } else {
            throw new Exception("No more slots available for this time.");
        }*/
        Quotes quote = convertToEntity(new Quotes(), quoteDTO);
        Quotes savedQuote = quotesRepository.save(quote);
        return convertToDTO(savedQuote);
    }

    //Mostrar si esta disponible una avalability por id y fecha de cita
    public Boolean isAvailable(IsAvailableDTO isAvailableDTO) {
        List<Quotes> quotes = quotesRepository.findByAvailabilityIdAndDate(isAvailableDTO.getAvailabilityId(), isAvailableDTO.getStartTime());
        if (quotes.size() < 2) {
            return true;
        } else {
            return false;
        }
    }

    // Get all quotes
    public List<QuotesObtenerDTO> getAllQuotes() {
        List<Quotes> quotes = quotesRepository.findAll();
        return quotes.stream().map(this::convertToQuotesDTO).collect(Collectors.toList());
    }

    //Mostrar todas citas hasta la fecha de hoy de un usuario o docente
    public List<QuotesObtenerDTO> getAllQuotesByDateToday(int usuario, Integer userId) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formattedDate = LocalDate.parse(today.format(formatter));
        Timestamp timestamp = Timestamp.valueOf(formattedDate.atStartOfDay());

        if (usuario == 1) {
            List<Quotes> quotes = quotesRepository.findByUserId(usuario, timestamp);
            return quotes.stream().map(this::convertToQuotesDTO).collect(Collectors.toList());
        } else {
            List<Quotes> quotes = quotesRepository.findByTherapistId(usuario, timestamp);
            return quotes.stream().map(this::convertToQuotesDTO).collect(Collectors.toList());
        }
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
/*
*   public boolean deleteQuote(int quotesId) {
        return quotesRepository.findById(quotesId)
                .map(quote -> {
                    quote.setStatus(false);
                    quotesRepository.save(quote);
                    return true;
                }).orElse(false);
    }
* */
    public boolean deleteQuote(int quotesId) {
        return quotesRepository.findById(quotesId)
                .map(quote -> {
                    quotesRepository.delete(quote);
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

    private QuotesObtenerDTO convertToQuotesDTO(Quotes quote) {
        QuotesObtenerDTO dto = new QuotesObtenerDTO();
        dto.setQuotesId(quote.getQuotesId());
        dto.setReason(quote.getReason());
        dto.setTypeQuotes(quote.getTypeQuotes());
        dto.setStatus(quote.isStatus());
        dto.setAppointmentRequest(quote.getAppointmentRequest());
        dto.setAppointmentStatusId(quote.getAppointmentStatusId().getAppointmentStatusId());
        dto.setAvailability(convertAvailabilityToDTO(quote.getAvailabilityId()));
        dto.setUser(convertToUseriObtenerDTO(quote.getUserId()));
        dto.setStartTime(quote.getStartTime());
        dto.setEndTime(quote.getEndTime());
        return dto;
    }

    private UseriObtenerDTO convertToUseriObtenerDTO(Useri user) {
        UseriObtenerDTO dto = new UseriObtenerDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setStatus(user.getStatus());
        dto.setRolId(user.getRolId().getRolId());
        PeopleObtenerDTO peopleDTO = convertToPeopleObtenerDTO(user.getPeopleId());
        dto.setPeople(peopleDTO);
        return dto;
    }

    private PeopleObtenerDTO convertToPeopleObtenerDTO(People people) {
        PeopleObtenerDTO dto = new PeopleObtenerDTO();
        dto.setPeopleId(people.getPeopleId());
        dto.setName(people.getName());
        dto.setFirstLastname(people.getFirstLastname());
        dto.setSecondLastname(people.getSecondLastname());
        dto.setEmail(people.getEmail());
        dto.setAge(people.getAge());
        dto.setCellphone(people.getCellphone());
        dto.setAddress(people.getAddress());
        dto.setCi(people.getCi());
        dto.setStatus(people.getStatus());
        DomainsDTO genderDTO = convertToDomainsDTO(people.getGenderId());
        dto.setGenderId(genderDTO);
        DomainsDTO occupationDTO = convertToDomainsDTO(people.getOccupationId());
        dto.setOccupationId(occupationDTO);
        DomainsDTO semesterDTO = convertToDomainsDTO(people.getSemesterId());
        dto.setSemesterId(semesterDTO);
        return dto;
    }

    private DomainsDTO convertToDomainsDTO(Domains domains) {
        DomainsDTO dto = new DomainsDTO();
        dto.setDomainsId(domains.getDomainsId());
        dto.setType(domains.getType());
        dto.setName(domains.getName());
        dto.setDescription(domains.getDescription());
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
        dto.setStatus(availability.isStatus());
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

        // Asignar disponibilidad
        if (quoteDTO.getAvailability() != null && quoteDTO.getAvailability().getAvailabilityId() != null) {
            Availability availability = availabilityRepository.findById(quoteDTO.getAvailability().getAvailabilityId())
                    .orElseThrow(() -> new RuntimeException("Availability not found"));
            quote.setAvailabilityId(availability);
        }

        // Asignar usuario
        if (quoteDTO.getUser() != null && quoteDTO.getUser().getUserId() != null) {
            Useri user = useriRepository.findById(quoteDTO.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            quote.setUserId(user);
        } else {
            throw new IllegalArgumentException("User ID is required");
        }

        // Asignar estado de la cita
        AppointmentStatus appointmentStatus = appointmentStatusRepository.findById(quoteDTO.getAppointmentStatusId())
                .orElseThrow(() -> new RuntimeException("Appointment Status not found"));
        quote.setAppointmentStatusId(appointmentStatus);

        quote.setStartTime(quoteDTO.getStartTime());
        quote.setEndTime(quoteDTO.getEndTime());
        return quote;
    }



    public Map<String, Integer> countAvailableSlots(int therapistId) {
        List<Availability> availabilities = availabilityRepository.findByUserIdUserId(therapistId);
        Map<String, Integer> slotsAvailability = new HashMap<>();

        int totalAvailable = 0;
        int totalReserved = 0;

        for (Availability availability : availabilities) {
            // Asumiendo que cada disponibilidad permite un número máximo de 2 citas
            long countReserved = quotesRepository.countByAvailabilityIdAndStatus(availability.getAvailabilityId(), true);
            totalReserved += countReserved;
            int availableSlots = 2 - (int)countReserved; // Calcula los slots disponibles
            totalAvailable += availableSlots > 0 ? availableSlots : 0; // Asegúrate de no tener slots disponibles negativos
        }

        slotsAvailability.put("disponible", totalAvailable);
        slotsAvailability.put("reservado", totalReserved);
        return slotsAvailability;
    }

    public Map<String, Object> getDashboardCounts() {
        List<Quotes> allQuotes = quotesRepository.findAll();
        Map<String, Integer> genderCounts = new HashMap<>();
        Map<String, Integer> occupationCounts = new HashMap<>();
        Map<String, Integer> semesterCounts = new HashMap<>();

        for (Quotes quote : allQuotes) {
            People people = quote.getUserId().getPeopleId();  // Actualizado para usar getUser()
            incrementCount(genderCounts, people.getGenderId().getName());
            incrementCount(occupationCounts, people.getOccupationId().getName());
            incrementCount(semesterCounts, people.getSemesterId().getName());
        }

        Map<String, Object> dashboardCounts = new HashMap<>();
        dashboardCounts.put("GenderCounts", genderCounts);
        dashboardCounts.put("OccupationCounts", occupationCounts);
        dashboardCounts.put("SemesterCounts", semesterCounts);
        return dashboardCounts;
    }


    private void incrementCount(Map<String, Integer> map, String key) {
        if (key != null) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
    }


}
