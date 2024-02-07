package edu.um.cps3230.pageobjects;

/**
 * Enum representing the main categories of the web store.
 */
public enum Category {
    /**
     * Represents desktop and laptops category.
     */
    DESKTOPS_AND_LAPTOPS("pills-_desktops-laptops-tab", "_desktops-laptops"),
    /**
     * Represents phones and tablets category.
     */
    PHONES_AND_TABLETS("pills-_phones-tablets-tab", "_phones-tablets"),
    /**
     * Represents category computing.
     */
    COMPUTING("pills-_computing-tab", "_computing"),
    /**
     * Represents computing category.
     */
    GAMING("pills-_gaming-tab", "_gaming"),
    /**
     * Represents home and life category.
     */
    HOME_AND_LIFE("pills-_home-life-tab", "_home-life"),
    /**
     * Represents accessories category.
     */
    ACCESSORIES("pills-_accessories-tab", "_accessories"),
    /**
     * Represents deals category.
     */
    DEALS("pills-_deals-tab", "_deals");

    public final String buttonId;
    public final String queryParam;

    private Category(String button_id, String queryParam) {
        this.buttonId = button_id;
        this.queryParam = queryParam;
    }
}
