package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Common;

import java.io.IOException;



public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    Common common  = new Common();
    String BASE_URL = common.getPropertyValues("base_url");
    int DEFAULT_WAIT = 15;

    //Constructor
    public BasePage (WebDriver driver) throws IOException {
        this.driver = driver;
        wait = new WebDriverWait(driver,DEFAULT_WAIT);
    }

    // Common methods of all page classes (e.g. selenium method wrapper)
}
