package test.store.modelbasedtesting;

public enum CurrentPage {
    HOME_PAGE("https://www.klikk.com.mt/"),
    PRODUCTS_VIEW_PAGE("https://www.klikk.com.mt/shop"),
    PRODUCT_DETAILS_PAGE("https://www.klikk.com.mt/product"),
    PURCHASE_PAGE("https://www.klikk.com.mt/cart");

    public final String url;

    private CurrentPage(String url) {
        this.url = url;
    }
}
