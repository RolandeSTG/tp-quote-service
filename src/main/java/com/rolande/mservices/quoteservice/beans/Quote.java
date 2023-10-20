package com.rolande.mservices.quoteservice.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

/**
 * A class/bean to define a record from the quotes table.
 * @author Rolande 
 * 
 * Note: Beside traditional id primary key, we also create a secondary index on column 'symbol', 
 *       to speed up symbol lookup.
 *       
 */
@Entity
@Table(indexes = @Index(name="idx_symbol", columnList="symbol", unique=false))

public class Quote {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)    // to have primary key 'id' autoincrement
	@JsonProperty(value="_id")
	private Long id;
	
	@JsonProperty(value="symbol")
	private String symbol;	

	@JsonProperty(value="previous_close_price")
	private double previousClosePrice;             // in quotes table, previous day's last price

	@JsonProperty(value="open_price")
	private double openPrice;				  // in quotes table, 1st price of the day

	@JsonProperty(value="last_trade_timestamp")
	private Timestamp lastTradeTimestamp;
	
	@JsonProperty(value="last_trade_price")
	private double lastTradePrice;
	
	@JsonProperty(value="last_trade_volume")
	private int lastTradeVolume;
	
	@JsonProperty(value="bid_price")
	private double bidPrice;

	@JsonProperty(value="bid_volume")
	private int bidVolume;

	@JsonProperty(value="ask_price")
	private double askPrice;
	
	@JsonProperty(value="ask_volume")
	private int askVolume;

	@JsonProperty(value="environment")
	private String environment;
	
	public Quote() {
		super();
	}

	public Quote(Long id, String symbol, double previousClosePrice, double openPrice, Timestamp lastTradeTimestamp,
			double lastTradePrice, int lastTradeVolume, double bidPrice, int bidVolume, double askPrice,
			int askVolume, String environment) {
		super();
		this.id = id;
		this.symbol = symbol;
		this.previousClosePrice = previousClosePrice;
		this.openPrice = openPrice;
		this.lastTradeTimestamp = lastTradeTimestamp;
		this.lastTradePrice = lastTradePrice;
		this.lastTradeVolume = lastTradeVolume;
		this.bidPrice = bidPrice;
		this.bidVolume = bidVolume;
		this.askPrice = askPrice;
		this.askVolume = askVolume;
		this.environment = environment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getPreviousClosePrice() {
		return previousClosePrice;
	}

	public void setPreviousClosePrice(double previousClosePrice) {
		this.previousClosePrice = previousClosePrice;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	public Timestamp getLastTradeTimestamp() {
		return lastTradeTimestamp;
	}

	public void setLastTradeTimestamp(Timestamp lastTradeTimestamp) {
		this.lastTradeTimestamp = lastTradeTimestamp;
	}

	public double getLastTradePrice() {
		return lastTradePrice;
	}

	public void setLastTradePrice(double lastTradePrice) {
		this.lastTradePrice = lastTradePrice;
	}

	public int getLastTradeVolume() {
		return lastTradeVolume;
	}

	public void setLastTradeVolume(int lastTradeVolume) {
		this.lastTradeVolume = lastTradeVolume;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public int getBidVolume() {
		return bidVolume;
	}

	public void setBidVolume(int bidVolume) {
		this.bidVolume = bidVolume;
	}

	public double getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
	}

	public int getAskVolume() {
		return askVolume;
	}

	public void setAskVolume(int askVolume) {
		this.askVolume = askVolume;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "Quote [id=" + id + ", symbol=" + symbol + ", previousClosePrice=" + previousClosePrice + ", openPrice="
				+ openPrice + ", lastTradeTimestamp=" + lastTradeTimestamp + ", lastTradePrice=" + lastTradePrice
				+ ", lastTradeVolume=" + lastTradeVolume + ", bidPrice=" + bidPrice + ", bidVolume=" + bidVolume
				+ ", askPrice=" + askPrice + ", askVolume=" + askVolume + ", environment=" + environment + "]";
	}

}
