package com.evajungabel.flightsearchsystem.domain;



import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
//@Data
@Getter
@Setter
@Builder
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customuser_id")
    private CustomUser customUser;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    private Flight flight;

}
