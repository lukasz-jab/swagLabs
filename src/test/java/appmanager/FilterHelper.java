package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class FilterHelper {
    private final WebDriver wd;

    public FilterHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void selectFilterMethod(String filterMethod) {
        Select filter = new Select(wd.findElement(By.cssSelector("select.product_sort_container")));
        filter.selectByVisibleText(filterMethod);
    }
}
