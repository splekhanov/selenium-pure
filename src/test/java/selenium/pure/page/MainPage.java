package selenium.pure.page;

import org.openqa.selenium.By;
import selenium.pure.page.cart.CartPage;

public class MainPage extends BasePage {

    private final By cartButton = By.xpath("//*[@data-test='shopping-cart-link']");

    public CartPage openCart() {
        getElement(cartButton).click();
        return new CartPage();
    }
}
