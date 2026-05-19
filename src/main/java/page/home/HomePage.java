package page.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final String COOKIE_BUTTON = "//button[@id='dldkdDecline']";
    private final String COOKIE_NEXT_BUTTON = "//button[@id='dldkdDeclineAll']";
    private final String INPUT_SEARCH = "//input[@id='search']";
    private final String ENTER_SEARCH = "//span[@class='search-icon']";
    private final String ALL_CARD = "//div[@itemprop='itemListElement']";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void closeCookieButton(){
        try {
            driver.findElement(By.xpath(COOKIE_BUTTON)).click();
        } catch (Exception e) {
            System.out.println("Куки не найдены или уже закрыты");
        }
    }

    public void closeCookieNextButton(){
        try {
            driver.findElement(By.xpath(COOKIE_NEXT_BUTTON)).click();
        } catch (Exception e) {
            System.out.println("Куки не найдены или уже закрыты");
        }
    }

    public void inputText(String text) {
        WebElement element = driver.findElement(By.xpath(INPUT_SEARCH));
        element.clear();
        element.sendKeys(text);
    }

    public void clickSearch(){
        driver.findElement(By.xpath(ENTER_SEARCH)).click();
    }

    public List<String> getAllCardTexts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(ALL_CARD)));

        List<WebElement> elements = driver.findElements(By.xpath(ALL_CARD));
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(element.getText());
        }
        return texts;
    }
}
