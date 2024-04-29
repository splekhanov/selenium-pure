package selenium.pure.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pure.base.DriverFactory;
import selenium.pure.base.constants.Timeout;

public class BasePage {

    protected WebDriverWait actionWait;
    protected WebDriverWait defaultWait;

    public BasePage() {
        PageFactory.initElements(DriverFactory.get(), this);
        this.actionWait = new WebDriverWait(driver(), Timeout.ACTION);
        this.defaultWait = new WebDriverWait(driver(), Timeout.DEFAULT_TIMEOUT);
    }

    public WebDriver driver() {
        return DriverFactory.get();
    }

    public String getTitle() {
        return driver().getTitle();
    }

    public WebElement getElement(By locator) {
        return driver().findElement(locator);
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            //
        }
    }

    public void waitForElementVisible(WebElement webElement) {
        actionWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForVisibilityOfElementLocated(By by) {
        actionWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
