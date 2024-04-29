package selenium.pure.helper.inventory;

import org.openqa.selenium.WebElement;
import selenium.pure.base.BaseTestClass;
import selenium.pure.page.inventory.InventoryItemElement;
import selenium.pure.page.inventory.InventoryPage;

import java.util.List;

public class InventoryBaseTest extends BaseTestClass {

    public static InventoryItemElement getInventoryItemByTitle(InventoryPage inventoryPage, String itemTitle) {
        List<WebElement> items = inventoryPage.collectInventoryItems();
        InventoryItemElement inventoryItemElement;
        int itemsAmount;

        if (items.isEmpty()) {
            throw new RuntimeException("Couldn't found any items. Inventory item list is empty");
        } else {
            itemsAmount = items.size();
        }

        for (int i = 0; i <= itemsAmount; i++) {
            InventoryItemElement item = new InventoryItemElement(items.get(i));
            String t = item.getInventoryItemTitle();
            if (t.equals(itemTitle)) {
                inventoryItemElement = new InventoryItemElement(items.get(i));
                return inventoryItemElement;
            }
        }
        throw new RuntimeException("Couldn't found item with title " + itemTitle);
    }
}
