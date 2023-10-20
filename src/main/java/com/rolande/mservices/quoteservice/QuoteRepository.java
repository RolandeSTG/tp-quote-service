package com.rolande.mservices.quoteservice;


import org.springframework.data.jpa.repository.JpaRepository;
import com.rolande.mservices.quoteservice.beans.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
	Quote findBySymbol(String symbol);	
}
