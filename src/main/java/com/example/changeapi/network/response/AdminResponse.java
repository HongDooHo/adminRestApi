package com.example.changeapi.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponse {

    private Long id;

    private String adminEmail;

    private String userName;

    private String userEmail;

    private Integer donationPrice;

    private String donationPlace;

    private LocalDate donationDate;

    private LocalDateTime createdAt;

    private String cratedBy;
}
