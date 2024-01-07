package edu.um.cps3230.pageobjects;

public enum StockStatus {
    IN_STOCK("In stock"),
    OUT_OF_STOCK("Out of stock"),
    COMING_SOON("Coming Soon."),
    CHECK_FOR_AVAILABILITY("Check for availability");
    public final String stock_status_message;
    private StockStatus(String stock_status_message) {
        this.stock_status_message = stock_status_message;
    }
}
