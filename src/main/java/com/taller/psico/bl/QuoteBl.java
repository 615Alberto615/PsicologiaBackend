package com.taller.psico.bl;

import com.taller.psico.dto.QuotesDTO;
import com.taller.psico.entity.AppointmentStatus;
import com.taller.psico.entity.Availability;
import com.taller.psico.entity.Quotes;
import com.taller.psico.entity.Useri;
import com.taller.psico.repository.QuotesRepository;
import com.taller.psico.repository.UseriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteBl {

    @Autowired
    private QuotesRepository quotesRepository;

    @Autowired
    private UseriRepository useriRepository; // Ensure you have this repository for fetching Useri

    // Create a new quote
    public QuotesDTO createQuote(QuotesDTO quoteDTO) {
        Quotes quote = convertToEntity(new Quotes(), quoteDTO);
        Quotes savedQuote = quotesRepository.save(quote);
        return convertToDTO(savedQuote);
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

    // Convert from entity to DTO
    private QuotesDTO convertToDTO(Quotes quote) {
        return new QuotesDTO(
                quote.getQuotesId(),
                quote.getReason(),
                quote.getTypeQuotes(),
                quote.getStatus(),
                quote.getAppointmentRequest(),
                quote.getAppointmentStatusId().getAppointmentStatusId(),
                quote.getAvailabilityId().getAvailabilityId(),
                quote.getUserId().getUserId()
        );
    }

    // Convert from DTO to entity
    private Quotes convertToEntity(Quotes quote, QuotesDTO quoteDTO) {
        quote.setReason(quoteDTO.getReason());
        quote.setTypeQuotes(quoteDTO.getTypeQuotes());
        quote.setStatus(quoteDTO.isStatus());
        quote.setAppointmentRequest(quoteDTO.getAppointmentRequest());
        quote.setAppointmentStatusId(new AppointmentStatus(quoteDTO.getAppointmentStatusId())); // Make sure you have appropriate constructors in AppointmentStatus
        quote.setAvailabilityId(new Availability(quoteDTO.getAvailabilityId())); // Make sure you have appropriate constructors in Availability
        quote.setUserId(new Useri(quoteDTO.getUserId())); // Make sure you have appropriate constructors in Useri
        return quote;
    }
}
