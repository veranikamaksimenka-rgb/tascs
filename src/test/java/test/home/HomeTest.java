package test.home;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
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
        String searchItem = "лопата";
        driver.get("https://7745.by/");
        homePage.closeCookieButton();
        homePage.closeCookieNextButton();
        homePage.inputText(searchItem);
        homePage.clickSearch();
        List<String> results = homePage.getAllCardTexts();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(results)
                .as("Список результатов поиска")
                .isNotEmpty();

        results.forEach(cardText -> {
            softly.assertThat(cardText.toLowerCase())
                    .as("Текст карточки товара")
                    .contains(searchItem.toLowerCase());
        });

        softly.assertAll();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
