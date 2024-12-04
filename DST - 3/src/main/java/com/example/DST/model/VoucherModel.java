package com.example.DST.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
public class   VoucherModel extends RepresentationModel<VoucherModel> {
    private int vouchersId;
    private String voucherTitle;
    private int voucherPercent;
    private String voucherCategory;
    private LocalDateTime voucherDate;
    private int voucherAmount;
    private String voucherPicture;
}
