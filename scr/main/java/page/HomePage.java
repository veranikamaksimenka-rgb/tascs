package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public static final String PROFILE_ICON = "//div[@data-widget='profileMenuAnonymous']";
    public static final String COOKIE_ICON = "//div[@data-widget='orderInfo']";
    public static final String INPUT_ICON = "//a[@data-widget='favoriteCounter']";
    public static final String ENTER_ICON = "//a[@data-widget='headerIcon']";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean isVisibleForParamTest(String locator) {
        return isElementDisplayedWithWait(locator);
    }

    public boolean isElementDisplayedWithWait(String locator) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getHeaderIconText(String locator) {
        return this.driver.findElement(By.xpath(locator)).getText();
    }

}
