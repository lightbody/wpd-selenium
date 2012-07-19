package net.lightbody.wpd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SimpleTest {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // page 1
        driver.get("http://twitter.com/#!/search-home");
        driver.findElement(By.xpath("//div[text() = 'Search']/../../input")).sendKeys("#WebPerfSD");

        // "page 2"
        driver.findElement(By.xpath("//div[text() = 'Search']")).click();
        driver.findElement(By.cssSelector(".tweet")); // wait for next page & tweets to appear

        driver.close();
    }
}
