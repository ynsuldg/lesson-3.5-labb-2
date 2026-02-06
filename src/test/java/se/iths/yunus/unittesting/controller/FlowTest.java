package se.iths.yunus.unittesting.controller;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlowTest {

    @LocalServerPort
    private int port;
    private String baseUrl;
    static Playwright playwright;
    static Browser browser;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void createPage() {
        baseUrl = "http://localhost:" + port;
        page = browser.newPage();
    }

    // BALANCE SHOWS CORRECTLY
    @Test
    @DisplayName("Balance should be displayed")
    public void balanceDisplayed() {
        page.navigate(baseUrl + "/balance");
        String balanceText = page.locator("#balance").innerText();

        assertNotNull(balanceText);
    }

    // PAGE IS ACCESSABLE
    @Test
    @DisplayName("Balance page should be reachable")
    public void balancePageReachable() {
        Response response = page.navigate(baseUrl + "/balance");
        assertEquals(200, response.status());
    }

    // PAGE LOADS CORRECTLY
    @Test
    @DisplayName("Balance page should load content")
    public void balancePageLoads() {
        page.navigate(baseUrl + "/balance");
        String title = page.title();
        assertEquals("Account balance", title);
    }
}