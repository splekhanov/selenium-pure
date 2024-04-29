package selenium.pure.helper.cart;

import org.openqa.selenium.WebElement;
import selenium.pure.base.BaseTestClass;
import selenium.pure.page.cart.CartItemElement;
import selenium.pure.page.cart.CartPage;

import java.util.List;

public class CartBaseTest extends BaseTestClass {

    public static CartItemElement getCartItemByTitle(CartPage cartPage, String itemTitle) {
        List<WebElement> items = cartPage.collectCartItems();
        CartItemElement cartItemElement;
        int itemsAmount;

        if (items.isEmpty()) {
            throw new RuntimeException("Couldn't found any items. Cart item list is empty");
        } else {
            itemsAmount = items.size();
        }

        for (int i = 0; i <= itemsAmount; i++) {
            CartItemElement item = new CartItemElement(items.get(i));
            String t = item.getCartItemTitle();
            if (t.equals(itemTitle)) {
                cartItemElement = new CartItemElement(items.get(i));
                return cartItemElement;
            }
        }
        throw new RuntimeException("Couldn't found item with title " + itemTitle);
    }
}
