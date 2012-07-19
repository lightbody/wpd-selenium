package net.lightbody.wpd;

import net.lightbody.wpd.utils.Firebug;
import net.lightbody.wpd.utils.JavaScriptErrors;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ErrorTest {
    public static void main(String[] args) throws Exception {
        // **************************************
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Firebug.configure(new File("."), capabilities);
        // **************************************

        FirefoxDriver driver = new FirefoxDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // page 1
        driver.get("http://twitter.com/#!/search-home");
        //*********************
        JavaScriptErrors.wire(driver);
        //*********************
        driver.findElement(By.xpath("//div[text() = 'Search']/../../input")).sendKeys("#WebPerfSD");

        // "page 2"
        driver.findElement(By.xpath("//div[text() = 'Search']")).click();
        driver.findElement(By.cssSelector(".tweet")); // wait for next page & tweets to appear

        JavaScriptErrors errors = new JavaScriptErrors(driver);
        if (!errors.getErrors().isEmpty()) {
            System.out.println("FOUND ERRORS!");
            for (JavaScriptErrors.JavaScriptError error : errors.getErrors()) {
                System.out.println(error);
            }
        }

        driver.close();

    }

}
