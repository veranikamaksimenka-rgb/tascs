package test.home;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.home.HomePage;
import java.util.List;

public class HomeTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Проверка поиска")
    public void test001(){
        driver.get("https://7745.by/");
        homePage.closeCookieButton();
        homePage.closeCookieNextButton();
        homePage.inputText("лопата");
        homePage.clickSearch();
        List<String> results = homePage.getAllCardTexts();

        Assertions.assertAll("Проверка всех карточек",
                () -> Assertions.assertFalse(results.isEmpty(), "Список пустой"),
                () -> results.forEach(cardText ->
                        Assertions.assertTrue(cardText.toLowerCase().contains("лопата"),
                                "Слово 'лопата' не найдено в: " + cardText))
        );
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
