package selenium.pure.test;

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

public class AddItemToCartTestPage extends BaseTestClass {

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
    @DisplayName("Item can be added to the cart")
    public void SearchTest() {
        inventoryPage = loginPage.login(UserType.STANRARD);
        inventoryItemElement = inventoryPage.getInventoryItemByTitle(itemTitle);

        String itemPrice = inventoryItemElement.getItemPrice();

        inventoryItemElement.addItemToCart();
        cartPage = inventoryPage.openCart();
        assertEquals(1, cartPage.getCartItemListSize(), "Cart has 1 item");
        cartItemElement = cartPage.getCartItemByTitle(itemTitle);
        assertEquals(cartItemElement.getCartItemTitle(), itemTitle, "Item in cart has the same title as item we added");
        assertEquals(cartItemElement.getCartItemPrice(), itemPrice, "Item in cart has the same price as item we added");
    }

    @AfterEach
    public void cleanUp() {
        cartItemElement.removeItemFromCart();
    }
}
