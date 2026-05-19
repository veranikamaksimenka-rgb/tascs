package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.HomePage;

import static org.assertj.core.api.Assertions.assertThat;
import static page.HomePage.*;

public class HomeTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setAcceptInsecureCerts(true);

        driver = new ChromeDriver(options);
        homePage = new HomePage(driver);
    }

    @ParameterizedTest
    @ValueSource(strings = {PROFILE_ICON, COOKIE_ICON, INPUT_ICON, ENTER_ICON})
    @DisplayName("PR102 - header icons are visible")
    public void test002(String locator) {
            driver.get("https://www.ozon.ru/");
        assertThat(
                homePage.isVisibleForParamTest(locator))
                .withFailMessage("Element '%S' is not visible",
                        homePage.getHeaderIconText(locator))
                .isTrue();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
