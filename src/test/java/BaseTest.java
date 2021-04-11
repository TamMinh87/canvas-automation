import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverManager;

import java.io.IOException;

public class BaseTest {
    public WebDriver driver;

    @BeforeClass
    public void setUp() throws IOException {
        this.driver = new DriverManager().initDriver();
        Dimension windowSize = new Dimension(450, 600);
        driver.manage().window().setSize(windowSize);
    }

    @AfterClass
    public void close() {
        this.driver.quit();
    }
}
