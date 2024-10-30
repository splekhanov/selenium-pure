package selenium.pure.page.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.pure.page.MainPage;

import java.util.List;

public class InventoryPage extends MainPage {

    private final By inventoryPageTitle = By.xpath("//*[@data-test='title'][text()='Products']");
    private final By inventoryList = By.xpath("//*[@data-test='inventory-list']");
    private final By inventoryItem = By.xpath("//*[@data-test='inventory-item']");
    private final By inventorySortSelection = By.xpath("//*[@data-test='product-sort-container']");

    public InventoryPage() {
        waitForVisibilityOfElementLocated(inventoryPageTitle);
    }

    public List<WebElement> collectInventoryItems() {
        return getElement(inventoryList).findElements(inventoryItem);
    }
    private Select getInventorySortSelect() {
        return new Select(getElement(inventorySortSelection));
    }

    public void selectSortTypeByName(String name) {
        getInventorySortSelect().selectByVisibleText(name);
    }
}
