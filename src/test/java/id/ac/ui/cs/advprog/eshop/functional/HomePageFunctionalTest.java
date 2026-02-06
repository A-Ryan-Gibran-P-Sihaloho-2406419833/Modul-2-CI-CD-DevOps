package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class HomePageFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        //buka halaman utama
        driver.get(baseUrl);
        // ambiljudul dari tab browser (<title>)
        String pageTitle = driver.getTitle();
        // Verifikasi apakah judulnya "ADV Shop"
        assertEquals("ADV Shop", pageTitle);
    }

    @Test
    void welcomeMessage_homePage_isCorrect(ChromeDriver driver) throws Exception {
        // Buka halaman utama
        driver.get(baseUrl);
        //cari elemen <h3> di halaman
        String welcomeMessage = driver.findElement(By.tagName("h3")).getText();
        // Verifikasi apakah teksnya benar-benar "Welcome"
        assertEquals("Welcome", welcomeMessage);
    }
}