package edu.um.cps3230.pageobjects;

public enum Category {
    DESKTOPSANDLAPTOPS("pills-_desktops-laptops-tab"),
    PHONESANDTABLETS("pills-_phones-tablets-tab"),
    COMPUTING("pills-_computing-tab"),
    GAMING("pills-_gaming-tab"),
    HOMEANDLIFE("pills-_home-life-tab"),
    ACCESORIES("pills-_accessories-tab"),
    DEALS("pills-_deals-tab");
    public final String button_id;

    private Category(String button_id) {
        this.button_id = button_id;
    }

}
