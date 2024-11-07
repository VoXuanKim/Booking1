package com.example.DST.entity;


import com.example.DST.enums.PaymentMethodEnum;
import com.example.DST.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "payments")
public class PaymentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentsId;
    private String paymentType;
    private double amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethodEnum;
    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum paymentStatusEnum;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;
}
