package selenium.pure.helper.inventory;

import org.openqa.selenium.WebElement;
import selenium.pure.base.BaseTestClass;
import selenium.pure.page.inventory.InventoryItemElement;
import selenium.pure.page.inventory.InventoryPage;

import java.util.List;

public class InventoryBaseTest extends BaseTestClass {

    public static InventoryItemElement getInventoryItemByTitle(InventoryPage inventoryPage, String itemTitle) {
        List<WebElement> items = inventoryPage.collectInventoryItems();

        if (items.isEmpty()) {
            throw new RuntimeException("Couldn't found any items. Inventory item list is empty");
        }

        for (WebElement itemWebElement : items) {
            InventoryItemElement item = new InventoryItemElement(itemWebElement);
            String t = item.getInventoryItemTitle();
            if (t.equals(itemTitle)) {
                return item;
            }
        }
        throw new RuntimeException("Couldn't found item with title " + itemTitle);
    }
}
