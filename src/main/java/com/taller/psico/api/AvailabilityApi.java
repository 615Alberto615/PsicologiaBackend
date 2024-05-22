package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.AvailabilityBl;
import com.taller.psico.dto.AvailabilityDTO;
import com.taller.psico.dto.ResponseDTO;
import com.taller.psico.dto.UserAvailabilitiesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/availability")
public class AvailabilityApi {
    private static final Logger logger = LoggerFactory.getLogger(AvailabilityApi.class);
    private final AvailabilityBl availabilityBl;
    private final Authbl authBl;

    @Autowired
    public AvailabilityApi(AvailabilityBl availabilityBl, Authbl authBl) {
        this.availabilityBl = availabilityBl;
        this.authBl = authBl;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<AvailabilityDTO>> createAvailability(@RequestBody AvailabilityDTO availabilityDTO, @RequestHeader("Authorization") String token) {
        if (availabilityDTO.getUser() == null || availabilityDTO.getUser().getUserId() == null) {
            logger.warn("User ID is missing in the request.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "User ID is missing"));
        }
        logger.info("Creating availability for user ID: {}", availabilityDTO.getUser().getUserId());
        if (!authBl.validateToken(token)) {
            logger.warn("Unauthorized access attempt with invalid token.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            AvailabilityDTO createdAvailability = availabilityBl.createAvailability(availabilityDTO);
            logger.info("Availability created successfully with ID: {}", createdAvailability.getAvailabilityId());
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), createdAvailability, "Availability created successfully"));
        } catch (Exception e) {
            logger.error("Failed to create availability: {}", e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Failed to create availability"));
        }
    }

    @PutMapping("/update/{availabilityId}")
    public ResponseEntity<ResponseDTO<AvailabilityDTO>> updateAvailability(@PathVariable int availabilityId, @RequestBody AvailabilityDTO availabilityDTO, @RequestHeader("Authorization") String token) {
        logger.info("Attempting to update availability with ID: {}", availabilityId);
        if (!authBl.validateToken(token)) {
            logger.error("Invalid token provided");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            AvailabilityDTO updatedAvailability = availabilityBl.updateAvailability(availabilityId, availabilityDTO);
            if (updatedAvailability != null) {
                logger.info("Availability updated successfully for ID: {}", updatedAvailability.getAvailabilityId());
                return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), updatedAvailability, "Availability updated successfully"));
            } else {
                logger.warn("No availability found with ID: {}", availabilityId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Failed to update availability with ID: {}. Error: {}", availabilityId, e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Failed to update availability"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<AvailabilityDTO>>> getAllAvailabilities(@RequestHeader("Authorization") String token) {
        logger.info("Fetching all availabilities");
        if (!authBl.validateToken(token)) {
            logger.error("Invalid token provided");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        List<AvailabilityDTO> allAvailabilities = availabilityBl.getAllAvailabilities();
        logger.info("Successfully fetched all availabilities");
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), allAvailabilities, "All availabilities fetched successfully."));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDTO<List<AvailabilityDTO>>> getAvailabilitiesByUserId(@PathVariable int userId, @RequestHeader("Authorization") String token) {
        logger.info("Fetching availabilities for user ID: {}", userId);
        if (!authBl.validateToken(token)) {
            logger.error("Invalid token provided");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        List<AvailabilityDTO> availabilities = availabilityBl.getAvailabilitiesByUserId(userId);
        logger.info("Successfully fetched availabilities for user ID: {}", userId);
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), availabilities, "Availabilities fetched successfully for user " + userId));
    }

    @GetMapping("/{availabilityId}")
    public ResponseEntity<ResponseDTO<AvailabilityDTO>> getAvailabilityById(@PathVariable int availabilityId, @RequestHeader("Authorization") String token) {
        logger.info("Fetching availability by ID: {}", availabilityId);
        if (!authBl.validateToken(token)) {
            logger.error("Invalid token provided for fetching availability.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            AvailabilityDTO availability = availabilityBl.getAvailabilityById(availabilityId);
            if (availability != null) {
                logger.info("Successfully fetched availability for ID: {}", availabilityId);
                return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), availability, "Availability fetched successfully for ID: " + availabilityId));
            } else {
                logger.warn("No availability found for ID: {}", availabilityId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching availability for ID: {}. Error: {}", availabilityId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "An error occurred while fetching the availability for ID: " + availabilityId));
        }
    }

    @DeleteMapping("/delete/{availabilityId}")
    public ResponseEntity<ResponseDTO<Void>> deleteAvailabilityLogically(@PathVariable int availabilityId, @RequestHeader("Authorization") String token) {
        logger.info("Attempting to logically delete availability with ID: {}", availabilityId);
        if (!authBl.validateToken(token)) {
            logger.error("Invalid token provided for deletion.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            availabilityBl.deleteAvailabilityLogically(availabilityId);
            logger.info("Availability logically deleted for ID: {}", availabilityId);
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), null, "Availability successfully deleted (logically)."));
        } catch (Exception e) {
            logger.error("Error occurred while deleting the availability with ID: {}. Error: {}", availabilityId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "An error occurred while deleting the availability."));
        }
    }

    @GetMapping("/grouped-by-user")
    public ResponseEntity<ResponseDTO<List<UserAvailabilitiesDTO>>> getGroupedByUser(@RequestHeader("Authorization") String token) {
        if (!authBl.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }

        List<UserAvailabilitiesDTO> groupedAvailabilities = availabilityBl.getAllGroupedByUser();
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), groupedAvailabilities, "Successfully fetched grouped availabilities by user."));
    }

    //vVer si una avalability esta disponible
    @GetMapping("/disponibilidad/{availabilityId}")
    public ResponseEntity<ResponseDTO<Boolean>> getAvailabilityById(@PathVariable Integer availabilityId, @RequestHeader("Authorization") String token) {
        logger.info("Fetching availability by ID: {}", availabilityId);
        if (!authBl.validateToken(token)) {
            logger.error("Invalid token provided for fetching availability.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            Boolean availability = availabilityBl.isAvailabilityAvailable(availabilityId);
            if (availability != null) {
                logger.info("Successfully fetched availability for ID: {}", availabilityId);
                return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), availability, "Availability fetched successfully for ID: " + availabilityId));
            } else {
                logger.warn("No availability found for ID: {}", availabilityId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching availability for ID: {}. Error: {}", availabilityId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "An error occurred while fetching the availability for ID: " + availabilityId));
        }
    }
}
