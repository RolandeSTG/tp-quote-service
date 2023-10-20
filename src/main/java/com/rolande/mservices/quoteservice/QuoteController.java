package com.rolande.mservices.quoteservice;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;				 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rolande.mservices.quoteservice.beans.Quote;

/**
 * Controller for the Quote microservice. Offers a single service at this time, which is to retrieve the latest quote
 * for a given security' symbol.
 * 
 * @author Rolande St-Gelais
 */

@RestController
public class QuoteController {
	
	@Autowired
	private Environment environment;

	@Autowired
	private QuoteRepository repository;
	
	private static Random random = new Random(); 

	private Map<String, Quote> quoteCache = new HashMap<>();
	
	@GetMapping("/quote/{symbol}")
	public Quote retrieveStockQuote(@PathVariable String symbol) {
		
		System.out.println("--> Getting quote for: " + symbol);
			
		Quote quote = quoteCache.get(symbol.toUpperCase());
		
		// if 1st time a quote is requested for this symbol, fetch it from DB			
		
		if (quote == null) {  
			quote = repository.findBySymbol(symbol.toUpperCase());   // pass symbol in uppercase, the way they have been loaded in DB
			
			if (quote == null) {
				String errorMsg = "Symbol NOT found (" + symbol + ")";			
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMsg);   
			}		
		
		}
		else {  // quote has been requested before, generated a new one to simulate real-time markets

			generateQuote(quote);        // modify the existing quote object only
			
		}			
	
		String port = environment.getProperty("local.server.port");
		quote.setEnvironment(port);
		
		quoteCache.put(symbol.toUpperCase(), quote);
		
		System.out.println("    " + quote);
		
		return quote;
	}
	
	/**
	 * Generate a new quote for the given quote object. 
	 * 
	 * @param quote Last quote for a given symbol, which will be directly modified.
	 * 
	 * The method generates a new quote the following way:
	 * 
	 * 1- Determine the new 'last trading price':
	 *    Picks a random integer: If even, we set the prices to go down, setting last trading price/volume to 
	 *    the last bid price/volume; if random number is odd, we go up, and last trading price/volume will
	 *    be set to the last ask price/volume;
	 * 2- Determine new bid/ask prices:
	 *    Generate a spread from the last trade price between 1 and 10 cents, then set the bid/ask prices 
	 *    using this spread;
	 * 3- Determine new bid/ask volumes:
	 * 	  Generate bid volume between 1 and 9 lots (of 100 shares) and ask volume between 1 and 7 lots.
	 * 
	 *    This is done completely arbitrarily.
	 */

	private void generateQuote(Quote quote) {
		
		// No change for these properties: id, symbol, previousClodePrice and openPrice...
	
		quote.setLastTradeTimestamp(new Timestamp(System.currentTimeMillis()));
		
		// Get a random integer: If even number, make the quote price go down; if odd, go up...	
		
		int randomInt = Math.abs(random.nextInt());                 // get a positive number
		
		if ((randomInt % 2) == 0) {    
		    // System.out.println("    Even --> Going DOWN");
		   
		    // Going down, set last trade at last bid price & volume
			quote.setLastTradePrice(quote.getBidPrice());
			quote.setLastTradeVolume(quote.getBidVolume());				
		}
		else { 
			//System.out.println("    Odd --> Going UP");

			// Going up: Set last trade at ask price & volume
			quote.setLastTradePrice(quote.getAskPrice());
			quote.setLastTradeVolume(quote.getAskVolume());
		}
		
		// Generate a spread from the last trade price we just set between 1 and 10 cents,
		// then set the bid and ask price accordingly...	
		
		int spread = (randomInt % 10) + 1;         
		//System.out.println("    Spread = " + spread + " cents");
		
		quote.setBidPrice( (int) ((quote.getLastTradePrice() * 100 - spread)) / 100.0 );
		quote.setAskPrice( (int) ((quote.getLastTradePrice() * 100 + spread)) / 100.0 );
		
		// Generate bid volume between 1 and 9 lots (of 100 shares)		
		
		int bidVolume = (randomInt % 9) + 1;    
		quote.setBidVolume(bidVolume * 100);		
		
		// Generate ask volume between 1 and 7 lots (of 100 shares)
		
		int askVolume = (randomInt % 7) + 1;    
		quote.setAskVolume((int) (askVolume * 100));
	
		//System.out.println("    bidVolume Lots = " + bidVolume + ", askVolume Lots = " + askVolume);		

		return;
	}
}
