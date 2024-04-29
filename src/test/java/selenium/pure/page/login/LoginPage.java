package selenium.pure.page.login;

import org.openqa.selenium.By;
import selenium.pure.data.user.UserType;
import selenium.pure.page.BasePage;
import selenium.pure.page.inventory.InventoryPage;

public class LoginPage extends BasePage {

    private final By username = By.xpath("//input[@id='user-name']");
    private final By password = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//input[@id='login-button']");

    private void setUserName(String login) {
        getElement(username).clear();
        getElement(username).sendKeys(login);
    }

    private void setUserPassword(String pass) {
        getElement(password).clear();
        getElement(password).sendKeys(pass);
    }

    private void clickLoginButton() {
        getElement(loginButton).click();
    }

    public InventoryPage login(UserType userType) {
        setUserName(userType.getUserName());
        setUserPassword(userType.getUserPassword());
        clickLoginButton();
        InventoryPage inventoryPage = new InventoryPage();
        return inventoryPage;
    }
}
