package net.lightbody.wpd;

import net.lightbody.wpd.utils.Firebug;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FirebugTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        // **************************************
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Firebug.configure(new File("."), capabilities);
        // **************************************

        WebDriver driver = new FirefoxDriver(capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // page 1
        driver.get("http://twitter.com/#!/search-home");
        driver.findElement(By.xpath("//div[text() = 'Search']/../../input")).sendKeys("#WebPerfSD");

        // "page 2"
        driver.findElement(By.xpath("//div[text() = 'Search']")).click();
        driver.findElement(By.cssSelector(".tweet")); // wait for next page & tweets to appear

        // **************************************
        Thread.sleep(5000); // chill
        // **************************************

        driver.close();

    }
}
