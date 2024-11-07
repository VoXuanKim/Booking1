package com.example.DST.service;

import com.example.DST.entity.VoucherEntity;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;


public interface VoucherService {
    void save(VoucherEntity voucher);

    void deleteById(int id);

    void updateById(int id, VoucherEntity voucher);

    public Optional<VoucherEntity> findVoucherById(int id);

    public Page<VoucherEntity> fetchCustomerDataAsPageWithFilteringAndSorting(
            String voucherCategoryFilter, String voucherTitleFilter, Integer voucherPercent, Integer voucherAmount,
            int page, int size, List<String> sortList, String sortOrder);
}

