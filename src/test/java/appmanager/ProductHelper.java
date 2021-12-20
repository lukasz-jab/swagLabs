package appmanager;

import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class ProductHelper extends HelperBase {
    private final WebDriverWait wait;

    public ProductHelper(WebDriver wd, WebDriverWait wait) {
        super(wd);
        this.wait = wait;
    }

    public List<Product> getProducts() throws InterruptedException {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.inventory_list div.inventory_item"), 5));
        Thread.sleep(4000);
        List<Product> products = new ArrayList<>();
        List<WebElement> webProducts = new ArrayList<>();
        webProducts = wd.findElements(By.cssSelector("div.inventory_list div.inventory_item"));
        for (WebElement e : webProducts) {
            String name = e.findElement(By.cssSelector("div.inventory_item_name")).getText();
            String description = e.findElement(By.cssSelector("div.inventory_item_desc")).getText();
            String price = e.findElement(By.cssSelector("div.inventory_item_price")).getText();
            String id = e.findElement(By.cssSelector("div.inventory_item_label a")).getAttribute("id");
            products.add(new Product().withId(id).withName(name).withPrice(cleaned(price)).withDescription(description));
        }
        return products;
    }

    public void addToChart(int no) {
        wd.findElements(By.cssSelector("div.inventory_list div.inventory_item button[id^='add-to-cart']")).get(no - 1).click();
    }

}
