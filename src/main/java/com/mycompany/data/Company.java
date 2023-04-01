package com.mycompany.data;

public class Company {
  String symbol;
  String companyName;
  long marketCap;
  String sector;
  String industry;
  double beta;
  double price;
  double lastAnnualDividend;
  long volume;
  String exchange;
  String exchangeShortName;
  String country;
  boolean isEtf;
  boolean isActivelyTrading;
  double earningsPerShare;
  double bookValuePerShare;
  double salesPerShare;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public long getMarketCap() {
    return marketCap;
  }

  public void setMarketCap(long marketCap) {
    this.marketCap = marketCap;
  }

  public String getSector() {
    return sector;
  }

  public void setSector(String sector) {
    this.sector = sector;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public double getBeta() {
    return beta;
  }

  public void setBeta(double beta) {
    this.beta = beta;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getLastAnnualDividend() {
    return lastAnnualDividend;
  }

  public void setLastAnnualDividend(double lastAnnualDividend) {
    this.lastAnnualDividend = lastAnnualDividend;
  }

  public long getVolume() {
    return volume;
  }

  public void setVolume(long volume) {
    this.volume = volume;
  }

  public String getExchange() {
    return exchange;
  }

  public void setExchange(String exchange) {
    this.exchange = exchange;
  }

  public String getExchangeShortName() {
    return exchangeShortName;
  }

  public void setExchangeShortName(String exchangeShortName) {
    this.exchangeShortName = exchangeShortName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public boolean getIsEtf() {
    return isEtf;
  }

  public void setIsEtf(boolean etf) {
    isEtf = etf;
  }

  public boolean getIsActivelyTrading() {
    return isActivelyTrading;
  }

  @Override
  public String toString() {
    return "Company{"
        + "symbol='"
        + symbol
        + '\''
        + ", companyName='"
        + companyName
        + '\''
        + ", marketCap="
        + marketCap
        + ", sector='"
        + sector
        + '\''
        + ", industry='"
        + industry
        + '\''
        + ", beta="
        + beta
        + ", price="
        + price
        + ", lastAnnualDividend="
        + lastAnnualDividend
        + ", volume="
        + volume
        + ", exchange='"
        + exchange
        + '\''
        + ", exchangeShortName='"
        + exchangeShortName
        + '\''
        + ", country='"
        + country
        + '\''
        + ", isEtf="
        + isEtf
        + ", isActivelyTrading="
        + isActivelyTrading
        + ", earningsPerShare="
        + earningsPerShare
        + ", bookValuePerShare="
        + bookValuePerShare
        + ", salesPerShare="
        + salesPerShare
        + '}';
  }

  public void setIsActivelyTrading(boolean activelyTrading) {
    isActivelyTrading = activelyTrading;
  }

  public double getEarningsPerShare() {
    return earningsPerShare;
  }

  public void setEarningsPerShare(double earningsPerShare) {
    this.earningsPerShare = earningsPerShare;
  }

  public double getBookValuePerShare() {
    return bookValuePerShare;
  }

  public void setBookValuePerShare(double bookValuePerShare) {
    this.bookValuePerShare = bookValuePerShare;
  }

  public double getSalesPerShare() {
    return salesPerShare;
  }

  public void setSalesPerShare(double salesPerShare) {
    this.salesPerShare = salesPerShare;
  }
}
