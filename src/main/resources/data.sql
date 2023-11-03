/* Only 'data.sql' is auto-executed, not the others -- so keeping this one for testing */
 
 /*	public Quote(Long id, String symbol, double previousClosePrice, double openPrice, Timestamp lastTradeTimestamp,
			double lastTradePrice, int lastTradeVolume, double bidPrice, int bidVolume, double askPrice,
			int askVolume, String environment) {
 */
 
insert into quote(environment, symbol, previous_close_price, open_price, last_trade_timestamp, 
			      last_trade_price, last_trade_volume, bid_price, bid_volume, ask_price,
			      ask_volume)
values
('8000', 'AAPL', 173.0, 176.0, CURRENT_TIMESTAMP(), 177.5, 100, 177.25, 100, 177.55, 200),
('8000', 'AMZN', 132.0, 130.0, CURRENT_TIMESTAMP(), 129.5, 400, 129.45, 100, 129.47, 900),
('8000', 'BABA',  88.0,  89.0, CURRENT_TIMESTAMP(),  89.5, 100,  89.40, 100,  89.50, 200),
('8000', 'GOOG', 140.0, 138.0, CURRENT_TIMESTAMP(), 139.5, 100, 139.40, 100, 139.50, 200),
('8000', 'META', 303.0, 301.0, CURRENT_TIMESTAMP(), 301.5, 100, 301.40, 100, 301.50, 200),
('8000', 'MSFT', 325.0, 326.0, CURRENT_TIMESTAMP(), 327.5, 200, 327.40, 100, 327.50, 500),
('8000', 'NFLX', 395.0, 392.0, CURRENT_TIMESTAMP(), 392.5, 100, 392.40, 100, 392.50, 200),
('8000', 'NVDA', 435.0, 436.0, CURRENT_TIMESTAMP(), 437.5, 100, 437.40, 100, 437.50, 200),
('8000', 'SHOP',  53.0, 53.43, CURRENT_TIMESTAMP(),  54.1, 100,  54.08, 400,  54.14, 100),
('8000', 'TSLA', 269.0, 268.0, CURRENT_TIMESTAMP(), 268.1, 400, 268.09, 300, 268.15, 700),
('8000', 'ZM'  ,  64.0, 64.34, CURRENT_TIMESTAMP(),  65.1, 100,  65.05, 300,  65.20, 200)
;


