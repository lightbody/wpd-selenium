package net.lightbody.wpd;

import net.lightbody.wpd.utils.PerformanceTiming;
import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class NavTimingTest {
    public static void main(String[] args) throws Exception {
        // start the proxy
        ProxyServer proxy = new ProxyServer(4444);
        proxy.start();

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy.seleniumProxy());

        FirefoxDriver driver = new FirefoxDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // start capture
        proxy.newHar("twitter.com");

        // page 1
        driver.get("http://twitter.com/#!/search-home");
        driver.findElement(By.xpath("//div[text() = 'Search']/../../input")).sendKeys("#WebPerfSD");

        // "page 2"
        driver.findElement(By.xpath("//div[text() = 'Search']")).click();
        driver.findElement(By.cssSelector(".tweet")); // wait for next page & tweets to appear

        // write the har file out
        new PerformanceTiming(driver, proxy.getHar());
        proxy.getHar().writeTo(new File("/tmp/twitter.com.har"));

        driver.close();

    }
}
