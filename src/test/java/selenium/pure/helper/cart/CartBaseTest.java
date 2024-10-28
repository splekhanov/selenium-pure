package selenium.pure.helper.cart;

import org.openqa.selenium.WebElement;
import selenium.pure.base.BaseTestClass;
import selenium.pure.page.cart.CartItemElement;
import selenium.pure.page.cart.CartPage;

import java.util.List;

public class CartBaseTest extends BaseTestClass {

    public static CartItemElement getCartItemByTitle(CartPage cartPage, String itemTitle) {
        List<WebElement> items = cartPage.collectCartItems();

        if (items.isEmpty()) {
            throw new RuntimeException("Couldn't found any items. Cart item list is empty");
        }

        for (WebElement itemWebElement : items) {
            CartItemElement item = new CartItemElement(itemWebElement);
            String t = item.getCartItemTitle();
            if (t.equals(itemTitle)) {
                return item;
            }
        }
        throw new RuntimeException("Couldn't found item with title " + itemTitle);
    }
}
