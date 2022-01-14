package com.calculator.coin.repository;

import com.calculator.coin.domain.CurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, Long> {

    List<CurrencyConversion> findByTransactionIdxNo(Integer idxNo);
}
