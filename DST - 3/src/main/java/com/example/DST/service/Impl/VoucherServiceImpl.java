package com.example.DST.service.Impl;

import com.example.DST.Utils.SortUtils;
import com.example.DST.entity.VoucherEntity;
import com.example.DST.repository.VoucherRepository;
import com.example.DST.service.VoucherService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    public Page<VoucherEntity> fetchCustomerDataAsPageWithFilteringAndSorting(
            String voucherCategoryFilter, String voucherTitleFilter, Integer voucherPercent, Integer voucherAmount,
            int page, int size, List<String> sortList, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(SortUtils.createSortOrder(sortList, sortOrder)));
        return voucherRepository.filterByVoucherAttributes(
                voucherCategoryFilter, voucherTitleFilter, voucherPercent, voucherAmount, pageable);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void save(VoucherEntity voucher) {
        voucherRepository.save(voucher);
    }

    @Override
    public void deleteById(int id) {
        voucherRepository.softDeletedById(id);
    }

    @Override
    public void updateById(int id, VoucherEntity voucher) {
        Optional<VoucherEntity> existingVoucherOpt = voucherRepository.findById(id);
        if (existingVoucherOpt.isPresent() && !existingVoucherOpt.get().isDeleted()) {
            VoucherEntity existingVoucher = existingVoucherOpt.get();
            existingVoucher.setVoucherAmount(voucher.getVoucherAmount());
            existingVoucher.setVoucherDate(voucher.getVoucherDate());
            existingVoucher.setVoucherCategory(voucher.getVoucherCategory());
            voucherRepository.save(existingVoucher);
        } else {
            throw new EntityNotFoundException("Voucher with ID " + id + " not found or has been deleted");
        }
    }

    @Override
    public Optional<VoucherEntity> findVoucherById(int id) {
        return voucherRepository.findById(id);
    }


}
