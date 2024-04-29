package selenium.pure.page.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.pure.page.MainPage;

import java.util.List;

public class InventoryPage extends MainPage {

    private final By inventoryPageTitle = By.xpath("//*[@data-test='title'][text()='Products']");
    private final By inventoryList = By.xpath("//*[@data-test='inventory-list']");
    private final By inventoryItem = By.xpath("//*[@data-test='inventory-item']");

    public InventoryPage() {
        waitForVisibilityOfElementLocated(inventoryPageTitle);
    }

    public List<WebElement> collectInventoryItems() {
        return getElement(inventoryList).findElements(inventoryItem);
    }
}
