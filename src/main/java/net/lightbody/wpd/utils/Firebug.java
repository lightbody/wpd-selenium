package net.lightbody.wpd.utils;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class Firebug {
    public static void configure(File dir, DesiredCapabilities capabilities) throws IOException {
        FirefoxProfile profile = new FirefoxProfile();
        profile.addExtension(new File(dir, "firebug-1.9.2-fx.xpi"));
        profile.addExtension(new File(dir, "netExport-0.8b22.xpi"));

        profile.setPreference("extensions.firebug.defaultPanelName", "net");
        profile.setPreference("extensions.firebug.net.enableSites", true);
        profile.setPreference("extensions.firebug.allPagesActivation", "on");
        profile.setPreference("extensions.firebug.showFirstRunPage", false);
        profile.setPreference("extensions.firebug.netexport.defaultLogDir", "/tmp");
        profile.setPreference("extensions.firebug.netexport.saveFiles", true);
        profile.setPreference("extensions.firebug.netexport.alwaysEnableAutoExport", true);

        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
    }
}
