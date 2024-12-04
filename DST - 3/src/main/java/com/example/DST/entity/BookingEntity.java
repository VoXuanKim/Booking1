package com.example.DST.entity;

import com.example.DST.enums.BookingStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookings_id")
    private int bookingsId;
    private LocalDateTime bookingDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Enumerated(EnumType.STRING)
    private BookingStatusEnum bookingStatus;
    private int totalPrice;
    // N-1 User
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;
    // N-1 Resource
    @ManyToOne
    @JoinColumn(name = "resources_id")
    private ResourceEntity resource;
    // 1-N Payment
    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PaymentsEntity> payments;

    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ReviewEntity> reviews;
}
