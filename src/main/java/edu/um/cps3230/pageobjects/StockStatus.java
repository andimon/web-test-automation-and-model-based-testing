package edu.um.cps3230.pageobjects;

/**
 * Enum representing the possible stock statuses of the web store.
 */
public enum StockStatus {
    /**
     * Represents that a product is in stock.
     */
    IN_STOCK("In stock"),
    /**
     * Represents that a product is out of stock,
     */
    OUT_OF_STOCK("Out of stock"),
    /**
     * Represents that a product is coming soon.
     */
    COMING_SOON("Coming Soon."),
    /**
     * Represents that a product may be available.
     */
    CHECK_FOR_AVAILABILITY("Check for availability");
    public final String stock_status_message;

    private StockStatus(String stock_status_message) {
        this.stock_status_message = stock_status_message;
    }
}
