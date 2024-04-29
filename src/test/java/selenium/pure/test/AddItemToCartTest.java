package selenium.pure.test;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import selenium.pure.base.BaseTestClass;
import selenium.pure.data.user.UserType;
import selenium.pure.page.cart.CartItemElement;
import selenium.pure.page.cart.CartPage;
import selenium.pure.page.inventory.InventoryItemElement;
import selenium.pure.page.inventory.InventoryPage;
import selenium.pure.page.login.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Item can be added to the cart")
public class AddItemToCartTest extends BaseTestClass {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private InventoryItemElement inventoryItemElement;
    private CartPage cartPage;
    private CartItemElement cartItemElement;
    private String itemTitle;

    @BeforeEach
    public void beforeAll() {
        loginPage = new LoginPage();
        itemTitle = "Sauce Labs Backpack";
    }

    @Test
    public void AddingItemToCartTest() {
        inventoryPage = loginPage.login(UserType.STANRARD);
        inventoryItemElement = inventoryPage.getInventoryItemByTitle(itemTitle);

        String itemPrice = inventoryItemElement.getItemPrice();

        inventoryItemElement.addItemToCart();
        cartPage = inventoryPage.openCart();

        Allure.step("Checking cart has only 1 item", step -> {
            assertEquals(1, cartPage.getCartItemListSize());
        });
        cartItemElement = cartPage.getCartItemByTitle(itemTitle);
        Allure.step("Checking item in cart has the same title as the item we added", step -> {
            assertEquals(cartItemElement.getCartItemTitle(), itemTitle);
        });
        Allure.step("Checking item in cart has the same price as the item we added", step -> {
            assertEquals(cartItemElement.getCartItemPrice(), itemPrice);
        });
    }

    @AfterEach
    public void cleanUp() {
        cartItemElement.removeItemFromCart();
    }
}
