package appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;
    String browser;
    SessionHelper session;
    NavigationHelper navigation;
    Properties properties;
    FilterHelper filterHelper;
    ProductHelper productHelper;
    CartHelper cartHelper;
    WebDriverWait wait;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if (browser.equals("firefox")) {
            wd = new FirefoxDriver();
            //System.out.println(((HasCapabilities) wd).getCapabilities());
        } else if (browser.equals("chrome")) {
            wd = new ChromeDriver();
            //System.out.println(((HasCapabilities) wd).getCapabilities());
        } else {
            System.out.println("Unrecognized browser");
        }
        wd.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        wait = new WebDriverWait(wd, 7);
        wd.manage().window().maximize();
        session = new SessionHelper(wd, wait, properties);
        navigation = new NavigationHelper(wd, wait);
        filterHelper = new FilterHelper(wd);
        productHelper = new ProductHelper(wd, wait);
        cartHelper = new CartHelper(wd, wait);
        wd.get(properties.getProperty("web.baseUrl"));
    }

    public void stop() {
        wd.quit();
    }

    public SessionHelper session() {
        return session;
    }

    public NavigationHelper goTo() {
        return navigation;
    }

    public FilterHelper filter() {
        return filterHelper;
    }

    public ProductHelper product() {
        return productHelper;
    }

    public CartHelper cart() {
        return cartHelper;
    }
}




