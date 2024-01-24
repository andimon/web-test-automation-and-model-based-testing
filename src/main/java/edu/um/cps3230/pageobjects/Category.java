package edu.um.cps3230.pageobjects;

public enum Category {
    DESKTOPS_AND_LAPTOPS("pills-_desktops-laptops-tab","_desktops-laptops"),
    PHONES_AND_TABLETS("pills-_phones-tablets-tab","_phones-tablets"),
    COMPUTING("pills-_computing-tab","_computing"),
    GAMING("pills-_gaming-tab","_gaming"),
    HOME_AND_LIFE("pills-_home-life-tab","_home-life"),
    ACCESSORIES("pills-_accessories-tab","_accessories"),
    DEALS("pills-_deals-tab","_deals");
    public final String buttonId;
    public final String queryParam;

    private Category(String button_id,String queryParam) {
        this.buttonId = button_id;
        this.queryParam=queryParam;
    }

}
