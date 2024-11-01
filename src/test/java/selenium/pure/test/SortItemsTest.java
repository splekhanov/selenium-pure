package selenium.pure.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import selenium.pure.base.BaseTestClass;
import selenium.pure.data.user.UserType;
import selenium.pure.page.inventory.InventoryItemElement;
import selenium.pure.page.inventory.InventoryPage;
import selenium.pure.page.login.LoginPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

public class SortItemsTest extends BaseTestClass {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeEach
    public void beforeEach() {
        loginPage = new LoginPage();
        inventoryPage = loginPage.login(UserType.STANDARD);
    }

    @Test
    @DisplayName("Check sorting items by price: from high to low")
    public void sortByPriceFromHighToLow(){

        inventoryPage.selectSortTypeByName("Price (high to low)");

        List<Double> items = inventoryPage.collectInventoryItems()
                .stream()
                .map(i -> Double.valueOf(new InventoryItemElement(i)
                        .getItemPrice().substring(1)))
                .collect(Collectors.toList());

        assertEquals(items.stream().sorted((i1,i2) -> Double.compare(i2,i1)).collect(Collectors.toList()), items);
    }


}
