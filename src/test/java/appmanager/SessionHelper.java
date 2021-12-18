package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class SessionHelper extends HelperBase {
    private final WebDriverWait wait;
    private final Properties properties;

    public SessionHelper(WebDriver wd, WebDriverWait wait, Properties properties) {
        super(wd);
        this.wait = wait;
        this.properties = properties;
    }

    public void login(String user) {
        // sometimes the page dont open correctly and needed to be refresh:-. its a bug
        wd.navigate().refresh();
        if (user.equals("standard_user")) {
            wd.findElement(By.cssSelector("div.login-box input#user-name")).sendKeys(properties.getProperty("username"));
            wd.findElement(By.cssSelector("div.login-box input#password")).sendKeys(properties.getProperty("password"));
        }
        wd.findElement(By.cssSelector("div.login-box input#login-button")).click();
    }

    public boolean is_logged() {
        try {
            if (isElementPresent(By.cssSelector("button#react-burger-menu-btn"))) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
