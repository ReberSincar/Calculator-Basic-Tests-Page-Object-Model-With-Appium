package core;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class Driver extends Base {

    protected AppiumDriver<AndroidElement> driver;

    public Driver() throws Exception {
        this.driver = getDriver();
    }
}
