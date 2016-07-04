package ca.trigon.mongo.template;


public class BookPrices {
	
	String title;
	String author;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	Double netPricePlus1;
	Double netPriceMinus1;
	Double netPriceDiv2;
	Double grossPrice;
	Double grossPriceIncludingDiscountAndCharge;
	
	
	
	
	@Override
	public String toString() {
		return "BookPrices [title=" + title + ", author=" + author + ", netPricePlus1="
				+ netPricePlus1 + ", netPriceMinus1=" + netPriceMinus1
				+ ", netPriceDiv2=" + netPriceDiv2 + ", grossPrice="
				+ grossPrice + ", grossPriceIncludingDiscountAndCharge="
				+ grossPriceIncludingDiscountAndCharge + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getNetPricePlus1() {
		return netPricePlus1;
	}
	public void setNetPricePlus1(Double netPricePlus1) {
		this.netPricePlus1 = netPricePlus1;
	}
	public Double getNetPriceMinus1() {
		return netPriceMinus1;
	}
	public void setNetPriceMinus1(Double netPriceMinus1) {
		this.netPriceMinus1 = netPriceMinus1;
	}
	public Double getNetPriceDiv2() {
		return netPriceDiv2;
	}
	public void setNetPriceDiv2(Double netPriceDiv2) {
		this.netPriceDiv2 = netPriceDiv2;
	}
	public Double getGrossPrice() {
		return grossPrice;
	}
	public void setGrossPrice(Double grossPrice) {
		this.grossPrice = grossPrice;
	}
	public Double getGrossPriceIncludingDiscountAndCharge() {
		return grossPriceIncludingDiscountAndCharge;
	}
	public void setGrossPriceIncludingDiscountAndCharge(
			Double grossPriceIncludingDiscountAndCharge) {
		this.grossPriceIncludingDiscountAndCharge = grossPriceIncludingDiscountAndCharge;
	}
	
	
	
}
