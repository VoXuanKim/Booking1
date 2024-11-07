package com.example.DST.repository;

import com.example.DST.entity.VoucherEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface VoucherRepository extends JpaRepository<VoucherEntity, Integer>, JpaSpecificationExecutor<VoucherEntity> {
    @Transactional
    @Modifying
    @Query(name = "softDeletedById", value = "UPDATE VoucherEntity v SET v.deleted = true where v.vouchersId = ?1")
    void softDeletedById(int id);

    String FILTER_VOUCHER_ON_QUERY = "select v from VoucherEntity v where " +
            "(:voucherCategoryFilter IS NULL OR UPPER(v.voucherCategory) like CONCAT('%', UPPER(:voucherCategoryFilter), '%')) and " +
            "(:voucherTitleFilter IS NULL OR UPPER(v.voucherTitle) like CONCAT('%', UPPER(:voucherTitleFilter), '%')) and " +
            "(:voucherPercent IS NULL OR v.voucherPercent = :voucherPercent) and " +
            "(:voucherAmount IS NULL OR v.voucherAmount = :voucherAmount)";

    @Query(FILTER_VOUCHER_ON_QUERY)
    Page<VoucherEntity> filterByVoucherAttributes(
            @Param("voucherCategoryFilter") String voucherCategoryFilter,
            @Param("voucherTitleFilter") String voucherTitleFilter,
            @Param("voucherPercent") Integer voucherPercent,
            @Param("voucherAmount") Integer voucherAmount,
            Pageable pageable);


    @Query(FILTER_VOUCHER_ON_QUERY)
    List<VoucherEntity> filterByVoucherAttributes(String voucherCategoryFilter,String voucherTitleFilter);
}
