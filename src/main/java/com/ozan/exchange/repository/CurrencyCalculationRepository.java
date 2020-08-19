package com.ozan.exchange.repository;

import com.ozan.exchange.model.entity.CurrencyCalculationEntity;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CurrencyCalculationRepository
        extends PagingAndSortingRepository<CurrencyCalculationEntity, String> {

    @Query(
            "SELECT c FROM currency_calculations c WHERE (:transactionId is null or c.transactionId = :transactionId) and "
                    + "(:date is null or c.date = :date)")
    Page<CurrencyCalculationEntity> findByTransactionIdAndDate(
            @Param("transactionId") String transactionId,
            @Param("date") Date date,
            Pageable pageable);
}
