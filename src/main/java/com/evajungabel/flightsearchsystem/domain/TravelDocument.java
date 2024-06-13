package com.evajungabel.flightsearchsystem.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "travel_document")
public class TravelDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_document_id")
    private Long travelDocumentId;
    @Column(name = "document_number")
    private Integer documentNumber;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "issuing_country")
    private String issuingCountry;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "nationality")
    private String nationality;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customuser_id")
    private CustomUser customUser;



}
