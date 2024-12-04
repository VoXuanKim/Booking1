package com.example.DST.controller;

import com.example.DST.assembler.VoucherModelAssembler;
import com.example.DST.entity.VoucherEntity;
import com.example.DST.model.VoucherModel;
import com.example.DST.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @Autowired
    private VoucherModelAssembler voucherModelAssembler;

    @Autowired
    private PagedResourcesAssembler<VoucherEntity> pagedResourcesAssembler;


    @PostMapping("/create")
    public ResponseEntity<String> createVoucher(@RequestBody VoucherEntity voucherEntity) {
        voucherService.save(voucherEntity);
        return ResponseEntity.ok("Voucher added successfully.");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateVoucher(@PathVariable int id, @RequestBody VoucherEntity voucherEntity) {
        voucherService.updateById(id, voucherEntity);
        return ResponseEntity.ok("Voucher with ID " + id + " updated successfully.");
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> deleteVoucher(@PathVariable int id) {
        voucherService.deleteById(id);
        return ResponseEntity.ok("Voucher with ID " + id + " deleted successfully.");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getVoucherById(@PathVariable int id) {
        Optional<VoucherEntity> voucher = voucherService.findVoucherById(id);
        if (voucher.isPresent()) {
            return ResponseEntity.ok(voucher.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public PagedModel<VoucherModel> fetchCustomersWithPagination(
            @RequestParam(defaultValue = "") String voucherCategoryFilter,
            @RequestParam(defaultValue = "") String voucherTitleFilter,
            @RequestParam(required = false) Integer voucherPercent,
            @RequestParam(required = false) Integer voucherAmount,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") List<String> sortList,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) {

        Page<VoucherEntity> voucherEntityPage = voucherService.fetchCustomerDataAsPageWithFilteringAndSorting(voucherCategoryFilter, voucherTitleFilter,voucherPercent,voucherAmount, page, size, sortList, sortOrder.toString());
        // Use the pagedResourcesAssembler and customerModelAssembler to convert data to PagedModel format
        return pagedResourcesAssembler.toModel(voucherEntityPage, voucherModelAssembler);
    }
}
