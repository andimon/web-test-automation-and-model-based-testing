package test.store.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SectionsComponent {
    private WebDriver webDriver;
    public SectionsComponent(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void clickSectionById(Section section){
        webDriver.findElement(By.xpath("//button[@id='"+section.button_id+"']")).click();
        webDriver.findElement(By.xpath("//button[@onclick=\"location.href='/shop?c=1049&t=_computing';\"]")).click();
    }
}
