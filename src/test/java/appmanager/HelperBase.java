package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public static String cleaned(String price) {
        return price.replaceAll("\\$", "").replaceAll("\n", "").replaceAll("Total:", "")
                .replaceAll(" ", "").replaceAll("Itemtotal:", "");
    }

    public boolean isElementPresent(By locator) {
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        } finally {
            wd.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        }
    }
}
