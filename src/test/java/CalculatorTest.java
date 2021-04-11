import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.CalculatorPage;


//@Listeners({ TestListener.class })
public class CalculatorTest extends BaseTest {
    @DataProvider(name="equationInput")
    public Object[][] getEquationInput(){
        return new Object[][]
            {
                { "Sub01_HappyCase", "1 + 2 = " },
                { "Sub02_ManyVariables", "345 - 123 = " },
                { "Sub03_NegativeResult", "1234 - 56789 = " },
                { "Sub04_FloatNumber", "1234.56 - 56.7890 = " },
                { "Sub05_NotPressEqualButton", "1234 - - " },
                { "Sub06_PressEqualManyTimes", "1234 - - = = =" }
            };
    }


    @Test(dataProvider = "equationInput")
    @Description("Subtraction test")
    public void subtraction(String testCaseName, String equation) throws Exception {
        CalculatorPage calculator = new CalculatorPage(driver);
        calculator.openCalculator();
        calculator.updateCoordinators();
        calculator.input(equation);
        calculator.checkResult(testCaseName);
    }

}
