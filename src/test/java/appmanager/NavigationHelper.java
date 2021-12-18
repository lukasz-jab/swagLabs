package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationHelper {
    private final WebDriver wd;
    private final WebDriverWait wait;

    public NavigationHelper(WebDriver wd, WebDriverWait wait) {
        this.wd = wd;
        this.wait = wait;
    }

    public void continueShopping() {

        wd.findElement(By.cssSelector("div#cart_contents_container button#continue-shopping")).click();
    }

    public void openChart() {
        wd.findElement(By.cssSelector("div.shopping_cart_container a.shopping_cart_link")).click();
    }
}
