// UserAvailabilitiesDTO.java
package com.taller.psico.dto;

import java.io.Serializable;
import java.util.List;

public class UserAvailabilitiesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private UseriDTO user;
    private List<AvailabilityDTO> availabilities;

    // Constructor vacío
    public UserAvailabilitiesDTO() {}

    // Constructor con todos los campos
    public UserAvailabilitiesDTO(UseriDTO user, List<AvailabilityDTO> availabilities) {
        this.user = user;
        this.availabilities = availabilities;
    }

    // Getters y setters
    public UseriDTO getUser() {
        return user;
    }

    public void setUser(UseriDTO user) {
        this.user = user;
    }

    public List<AvailabilityDTO> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<AvailabilityDTO> availabilities) {
        this.availabilities = availabilities;
    }
}
