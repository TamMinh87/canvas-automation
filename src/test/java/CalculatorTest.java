import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.CalculatorPage;

import java.io.File;


public class CalculatorTest extends BaseTest {
    @DataProvider(name="subtraction")
    public Object[][] subtractionInput(){
        return new Object[][] {
                { "Sub01_HappyCase", "10 - 0 = " },
                { "Sub02_ManyVariables", "345 - 123 = " },
                { "Sub03_NegativeResult", "1234 - 567.89 = " },
                { "Sub04_FloatNumber", "1234.56 - 56.7890 = " },
                { "Sub05_NotPressEqualButton", "1234 - - " },
                { "Sub06_PressEqualManyTimes", "1234 - - = = =" },
                { "Sub07_Boundary_1", "999999999_ - 1 = " },
                { "Sub08_Boundary_2", "99999999_9 - 1001 =" },
                { "Sub09_Boundary_3", "9999999_99 - 10001 =" },
                { "Sub10_Boundary_4", "999999_999 - 100001 =" },
                { "Sub11_Boundary_5", "99999_9999 - 1000001 =" },
                { "Sub12_Boundary_6", "9999_99999 - 10000001 =" },
                { "Sub13_Boundary_7", "999_999999 - 100000001 =" },
                { "Sub14_Boundary_8", "99_9999999 - 100000001 - = = = = = =" },
                { "Sub15_Boundary_9", "9_99999999 - 1 - 500 - 500 =" },
                { "Sub16_Boundary_10", "999.123456789_ - .12345678901_ = " },
                { "Sub17_PressNegativeAtLast", "999999999 - 1_ = " },
                { "Sub18_PressNegativeButtonFirst", "_999999999 - 1_ = " },
        };
    }

    @DataProvider(name="additional")
    public Object[][] additionalInput(){
        return new Object[][] {
                { "Add01_HappyCase", "10 + 0 = " },
                { "Add02_ManyVariables", "345 + 123 = " },
                { "Add03_NegativeResult", "1234 + 567.89 = " },
                { "Add04_FloatNumber", "1234.56 + 56.7890 = " },
                { "Add05_NotPressEqualButton", "1234 / 0.1 / " },
                { "Add06_PressEqualManyTimes", "1234 / 0.1 / = = =" },
                { "Add05_NotPressEqualButton", "1234 + + " },
                { "Add06_PressEqualManyTimes", "1234 + + = = =" },
                { "Add07_Boundary_1", "999999999 + 1 = " },
                { "Add08_Boundary_2", "999999999 + 1001 =" },
                { "Add09_Boundary_3", "999999999 + 10001 =" },
                { "Add10_Boundary_4", "999999999 + 100001 =" },
                { "Add11_Boundary_5", "999999999 + 1000001 =" },
                { "Add12_Boundary_6", "999999999 + 10000001 =" },
                { "Add13_Boundary_7", "999999999 + 100000001 =" },
                { "Add14_Boundary_8", "999999999 + 100000001 + = = = = = =" },
                { "Add15_Boundary_9", "999999999 + 1 + 500 + 500 =" },
                { "Add16_Boundary_10", "999.123456789 + .12345678901_ = " },
                { "Add17_PressNegativeAtLast", "999999999 + 1_ = " },
                { "Add18_PressNegativeButtonFirst", "_999999999 + 1_ = " },
        };
    }

    @DataProvider(name="divide")
    public Object[][] devideInput(){
        return new Object[][] {
                { "Divide01_HappyCase", "10 / 1 = " },
                { "Divide02_ManyVariables", "345 / 123 = " },
                { "Divide03_NegativeResult", "1234 / 567.89 = " },
                { "Divide04_FloatNumber", "1234.56 / 56.7890 = " },
                { "Divide05_NotPressEqualButton", "1234 / 0.1 / " },
                { "Divide06_PressEqualManyTimes", "1234 / 0.1 / = = =" },
                { "Divide07_Boundary_1", "999999999 / 0.1 = " },
                { "Divide08_Boundary_2", "999999999_ / 0.1001 =" },
                { "Divide09_Boundary_3", "999999999 / 0.10001_ =" },
                { "Divide10_Boundary_4", "9_99999999 / 0.10000_1 =" },
                { "Divide11_Boundary_5", "999999999 / 0._1000001 =" },
                { "Divide12_Boundary_6", "999999999 / 0.10000001 =" },
                { "Divide13_Boundary_7", "9999_99999 / 0.100000001 =" },
                { "Divide14_Boundary_8", "999999999 / 0.100000001234 / = = = = = =" },
                { "Divide15_Boundary_9", "999999_999 / 0.1312312 / 0.321321 / 0.000000001 =" },
                { "Divide16_Boundary_10", "999.123456789 / .12345678901_ = " },
                { "Divide17_PressNegativeAtLast", "999999999 / 0.0000000122_ / / = " },
                { "Divide18_PressNegativeButtonFirst", "_999999999 / 0.00000001 = " },
                { "Divide19_Error_1", "_999999999 / 0 = " },
                { "Divide20_Error_2", "_999999999 / 0.00000000_ = " },
                { "Divide21_Error_3", "_999999999 / 0.0000000_01 = " },
                { "Divide22_Error_4", "10 / 1 / 0.1 / 0.2 / 0.000000001 = " },
        };
    }

    @DataProvider(name="mix")
    public Object[][] mixInput(){
        return new Object[][] {
                { "Mix01_HappyCase", "123 + 456 / 0.1 - 789.23 - _345 + _231 = " },
                { "Mix02_BigNumber_1", "123456 + 456789 / 0.0001 - 789.231023 - _34567 + _2310987 = " },
                { "Mix03_BigNumber_2", "987654321 + 4567890123 / 0.00000001 - 789.231023 - _34567 / 0.000000001 + _2310987 = " },
                { "Mix04_Operator_1", "1234 / + - 567.89 = " },
                { "Mix05_Operator_2", "123 + - 456 + / 0.1 - - 789.23 - + _345 / + _231 = " },
                { "Mix06_Error_1", "123 + 456 / 0 - 789.23 - _345 + _231 = " },
                { "Mix07_Error_2", "123456 + 456789 / 0.00000000_ - 789.231023 - _34567 + _2310987 = " },
                { "Mix08_Error_3", "1234 / + - / 0.000000001 - 789.23 - _345 + _231 = " },
        };
    }

    @Test(dataProvider = "subtraction")
    @Description("Subtraction test")
    public void subtraction(String testCaseName, String equation) throws Exception {
        this.calculate(testCaseName, equation);
    }

    @Test(dataProvider = "additional")
    @Description("Additional test")
    public void additional(String testCaseName, String equation) throws Exception {
        this.calculate(testCaseName, equation);
    }

    @Test(dataProvider = "divide")
    @Description("Divide test")
    public void divide(String testCaseName, String equation) throws Exception {
        this.calculate(testCaseName, equation);
    }

    @Test(dataProvider = "mix")
    @Description("Mix test")
    public void mix(String testCaseName, String equation) throws Exception {
        this.calculate(testCaseName, equation);
    }

    private void calculate(String testCaseName, String equation) throws Exception {
        CalculatorPage calculator = new CalculatorPage(driver);
        calculator.openCalculator();
        calculator.updateCoordinators();
        calculator.input(equation);
        File actualImage = calculator.captureCalculatorScreenshot(testCaseName);
        calculator.compareToBaselineImage(actualImage);
    }

}
