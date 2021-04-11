import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.CalculatorPage;

import static org.testng.Assert.assertEquals;

public class CalculatorTest extends BaseTest {
//    private WebDriver driver;

//    @BeforeClass
//    public void setUp() throws IOException {
//        this.driver = new Driver().initDriver();
//        Dimension windowSize = new Dimension(450, 600);
//        driver.manage().window().setSize(windowSize);
//    }


    @DataProvider(name="equationInput")
    public Object[][] getEquationInput(){
        return new Object[][]
            {
                { "Subtraction1", "1 + 2 = " },
                { "Subtraction2", "3 - 4 = " },
                { "Subtraction3", "5 / 6 = " }
            };
    }


    @Test(dataProvider = "equationInput")
    public void subtraction(String testCaseName, String equation) throws Exception {
        CalculatorPage calculator = new CalculatorPage(driver);
        calculator.openCalculator();
        calculator.updateCoordinators();
        calculator.input(equation);
        calculator.checkResult(testCaseName);
    }


//    @AfterClass
//    public void close() {
//        this.driver.quit();
//    }
}
