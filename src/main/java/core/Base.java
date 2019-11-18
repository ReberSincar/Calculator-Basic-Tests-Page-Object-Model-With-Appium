package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Base {
    private AppiumDriver<AndroidElement> driver;
    private static final String serverIP = "http://127.0.0.1:4723/wd/hub";
    private DesiredCapabilities capabilities = new DesiredCapabilities();

    public Base(){
        try {
            initDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initDriver() throws Exception {
        System.out.println("Driver Starting");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"ONEPLUS A6000");
        capabilities.setCapability(MobileCapabilityType.UDID,"3af392dd");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.oneplus.calculator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.oneplus.calculator.Calculator");
        capabilities.setCapability(MobileCapabilityType.NO_RESET,"true");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET,"false");

        try{
            driver = new AndroidDriver<AndroidElement>(new URL(serverIP),capabilities);
            System.out.println("Driver Started");
        }
        catch (Exception e){
            throw new Exception("Appium Driver Failed "+ e.getMessage());
        }

    }

    public AppiumDriver<AndroidElement> getDriver(){
        return driver;
    }

    public void WaitSecond(int second){
        driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
    }
}
