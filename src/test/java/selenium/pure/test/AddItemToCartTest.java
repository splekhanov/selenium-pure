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
import static selenium.pure.helper.cart.CartBaseTest.getCartItemByTitle;
import static selenium.pure.helper.inventory.InventoryBaseTest.getInventoryItemByTitle;

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
        logStep("Logging in with user: " + UserType.STANRARD);
        inventoryPage = loginPage.login(UserType.STANRARD);

        logStep("Looking for an item in inventory with title: " + itemTitle);
        inventoryItemElement = getInventoryItemByTitle(inventoryPage, itemTitle);

        logStep("Getting item price");
        String itemPrice = inventoryItemElement.getItemPrice();
        logStep("Preserving item price: " + itemPrice);

        logStep("Adding item to cart");
        inventoryItemElement.addItemToCart();

        logStep("Opening cart");
        cartPage = inventoryPage.openCart();

        logStep("Checking cart has only 1 item");
        assertEquals(1, cartPage.getCartItemListSize());

        logStep("Looking for an item in cart with title: " + itemTitle);
        cartItemElement = getCartItemByTitle(cartPage, itemTitle);

        logStep("Checking item in cart has the same title as the item we added: " + itemTitle);
        assertEquals(cartItemElement.getCartItemTitle(), itemTitle);

        logStep("Checking item in cart has the same price as the item we added: " + itemPrice);
        assertEquals(cartItemElement.getCartItemPrice(), itemPrice);

    }

    @AfterEach
    public void cleanUp() {
        logStep("Removing item from cart");
        cartItemElement.removeItemFromCart();
    }
}
