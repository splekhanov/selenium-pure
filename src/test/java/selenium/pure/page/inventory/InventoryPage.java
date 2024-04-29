package selenium.pure.page.inventory;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.pure.page.MainPage;

import java.util.List;

public class InventoryPage extends MainPage {

    private final By inventoryPageTitle = By.xpath("//*[@data-test='title'][text()='Products']");
    private final By inventoryList = By.xpath("//*[@data-test='inventory-list']");
    private final By inventoryItem = By.xpath("//*[@data-test='inventory-item']");
    private final By itemTitle = By.xpath("//*[@data-test='inventory-item-name']");

    public InventoryPage() {
        waitForVisibilityOfElementLocated(inventoryPageTitle);
    }

    private List<WebElement> collectInventoryItems() {
        return getElement(inventoryList).findElements(inventoryItem);
    }

    @Step("Getting inventory item with title {0}")
    public InventoryItemElement getInventoryItemByTitle(String itemTitle) {
        List<WebElement> items = collectInventoryItems();
        InventoryItemElement inventoryItemElement;
        int itemsAmount;

        if(items.isEmpty()) {
            throw new RuntimeException("Couldn't found any items. Inventory item list is empty");
        } else {
            itemsAmount = items.size();
        }

        for (int i = 0; i <= itemsAmount; i++) {
            String t = items.get(i).findElement(this.itemTitle).getText();
            if(t.equals(itemTitle)) {
                inventoryItemElement = new InventoryItemElement(items.get(i));
                return inventoryItemElement;
            }
        }
        throw new RuntimeException("Couldn't found item with title " + itemTitle);
    }

}
