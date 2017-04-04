package com.excilys.formation.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumTest {
    private static WebDriver driver;
    private final String homeDir = "localhost:8080/ComputerDatabase";
    private WebElement element;

    /**
     */
    @BeforeClass
    public static void beforeEachTest() {
        System.setProperty("webdriver.chrome.driver", "WebContent/WEB-INF/lib/chromedriver");

        driver = new ChromeDriver();
        //permet de laisser un temps dans le cas d'une erreur.
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    /**
     */
    @AfterClass
    public static void afterEachTest() {
        driver.close();
    }

    /**
     */
    @Test
    public void testOpenDashboard() {
        driver.navigate().to(homeDir + "/dashboard");

        element = driver.findElement(By.id("homeTitle"));
        Assert.assertNotNull(element);
    }

    /**
     */
    @Test
    public void testOpenAddComputer() {
        driver.navigate().to(homeDir + "/add");

        element = driver.findElement(By.id("main"));
        Assert.assertNotNull(element);
    }

    /**
     */
    @Test
    public void testOpenEdit() {
        driver.navigate().to(homeDir + "/edit");

        element = driver.findElement(By.id("main"));
        Assert.assertNotNull(element);
    }
    /**
     */
    @Test
    public void testNavigateToDashboard() {

        driver.get(homeDir + "/dashboard");
        driver.findElement(By.id("dashboard_button")).click();
        element = driver.findElement(By.id("homeTitle"));
        Assert.assertNotNull(element);

        driver.get(homeDir + "/add");
        driver.findElement(By.id("dashboard_button")).click();
        element = driver.findElement(By.id("homeTitle"));
        Assert.assertNotNull(element);

        driver.get(homeDir + "/edit");
        driver.findElement(By.id("dashboard_button")).click();
        element = driver.findElement(By.id("homeTitle"));
        Assert.assertNotNull(element);
    }

    /**
     */
    @Test
    public void testNavigateToAdd() {

        driver.get(homeDir + "/dashboard");
        driver.findElement(By.id("addComputer")).click();
        element = driver.findElement(By.id("main"));
        Assert.assertNotNull(element);

    }

    /**
     */
    @Test
    public void testAddComputer() {

        driver.navigate().to(homeDir + "/add");
        driver.findElement(By.id("computerName")).sendKeys("Test");
        driver.findElement(By.id("introduced")).sendKeys("01-01-2000");
        driver.findElement(By.id("discontinued")).sendKeys("01-01-2001");
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        driver.findElement(By.id("submit")).click();
        driver.navigate().to(homeDir + "/dashboard");
        driver.findElement(By.id("alast")).click();
        driver.navigate().to(homeDir + "/dashboard");
        List<WebElement> tableRows = driver.findElements(By.tagName("tr"));
        element = tableRows.get((tableRows.size() - 1)).findElements(By.tagName("td")).get(1);
        Assert.assertEquals("Test", element.getText());
        driver.findElement(By.id("editComputer")).click();
        tableRows.get((tableRows.size() - 1)).findElements(By.tagName("td")).get(0).findElement(By.className("cb")).click();
        driver.findElement(By.id("deleteSelected")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();






    }


}
