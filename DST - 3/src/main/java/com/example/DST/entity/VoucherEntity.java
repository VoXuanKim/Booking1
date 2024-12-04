package com.example.DST.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vouchers")
public class VoucherEntity extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vouchers_id")
    private int vouchersId;
    private String voucherTitle;
    private int voucherPercent;
    private String voucherCategory;
    private LocalDateTime voucherDate;
    private int voucherAmount;

    private String voucherPicture;
}
