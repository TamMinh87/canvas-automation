package page;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import static org.testng.Assert.assertEquals;


public class CalculatorPage extends BasePage {
    Common common  = new Common();
    String PAGE_URL = BASE_URL + "/";

    float DEFAULT_CANVAS_WIDTH = 549;
    float DEFAULT_CANVAS_HEIGHT = 710;
    Point NUMBER_0 = new Point(-205, 255);
    Point NUMBER_1 = new Point(-205, 155);
    Point NUMBER_2 = new Point(-105, 155);
    Point NUMBER_3 = new Point(0, 155);
    Point NUMBER_4 = new Point(-205, 55);
    Point NUMBER_5 = new Point(-105, 55);
    Point NUMBER_6 = new Point(0, 55);
    Point NUMBER_7 = new Point(-205, -55);
    Point NUMBER_8 = new Point(-105, -55);
    Point NUMBER_9 = new Point(0, -55);
    Point DOT      = new Point(-105, 255);
    Point DIVIDE   = new Point(105, -55);
    Point MINUS    = new Point(105, 155);
    Point ADD      = new Point(105, 255);
    Point EQUAL    = new Point(205, 255);
    Point CLEAR    = new Point(205, -155);
    Point NEGATIVE = new Point(0, 255);


    @FindBy(id="fullframe")
    WebElement calculatorFrame;

    @FindBy(id="canvas")
    WebElement calculatorCanvas;

    public CalculatorPage(WebDriver driver) throws IOException {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("open calculator")
    public void openCalculator() {
        this.driver.get(PAGE_URL);
    }

    /**
     * Update coordinators for all elements inside canvas based on its size
     */
    @Step("check resolution and update coordinators")
    public void updateCoordinators() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorFrame));
        wait.until(ExpectedConditions.elementToBeClickable(calculatorCanvas));

        float currentHeight = calculatorCanvas.getSize().getHeight();
        float currentWidth = calculatorCanvas.getSize().getWidth();

        DecimalFormat df = new DecimalFormat("###.##");
        String currentRatio = df.format(currentHeight / currentWidth);
        String defaultRatio = df.format(DEFAULT_CANVAS_HEIGHT / DEFAULT_CANVAS_WIDTH);

        if (currentRatio.equals(defaultRatio)) {
            float diff = currentWidth / DEFAULT_CANVAS_WIDTH;

            if (diff != 1.0) {
                NUMBER_9.setLocation((int) (NUMBER_9.x * diff), (int) (NUMBER_9.y * diff));
                NUMBER_6.setLocation((int) (NUMBER_6.x * diff), (int) (NUMBER_6.y * diff));
                NUMBER_3.setLocation((int) (NUMBER_3.x * diff), (int) (NUMBER_3.y * diff));
                NEGATIVE.setLocation((int) (NEGATIVE.x * diff), (int) (NEGATIVE.y * diff));
                NUMBER_8.setLocation((int) (NUMBER_8.x * diff), (int) (NUMBER_8.y * diff));
                NUMBER_5.setLocation((int) (NUMBER_5.x * diff), (int) (NUMBER_5.y * diff));
                NUMBER_2.setLocation((int) (NUMBER_2.x * diff), (int) (NUMBER_2.y * diff));
                DOT.setLocation((int) (DOT.x * diff), (int) (DOT.y * diff));
                NUMBER_7.setLocation((int) (NUMBER_7.x * diff), (int) (NUMBER_7.y * diff));
                NUMBER_4.setLocation((int) (NUMBER_4.x * diff), (int) (NUMBER_4.y * diff));
                NUMBER_1.setLocation((int) (NUMBER_1.x * diff), (int) (NUMBER_1.y * diff));
                NUMBER_0.setLocation((int) (NUMBER_0.x * diff), (int) (NUMBER_0.y * diff));
                DIVIDE.setLocation((int) (DIVIDE.x * diff), (int) (DIVIDE.y * diff));
                MINUS.setLocation((int) (MINUS.x * diff), (int) (MINUS.y * diff));
                ADD.setLocation((int) (ADD.x * diff), (int) (ADD.y * diff));
                EQUAL.setLocation((int) (EQUAL.x * diff), (int) (EQUAL.y * diff));
                CLEAR.setLocation((int) (CLEAR.x * diff), (int) (CLEAR.y * diff));
            }
        }  // in this exercise, the canvas always maintains the ratio, so not spend time to different ratio case yet
    }

    /**
     * click on calculator based on user input
     * @param equation user input
     * @throws Exception when key is not defined yet
     */
    @Step("click on calculator based on user input")
    public void input(String equation) throws Exception {
//        equation = "123 + 456 : 0.1 - 789.23 - _345 + _231 = "; // 5,114.77
        char[] keys = equation.replaceAll("\\s", "").toCharArray();
        Actions actions = new Actions(driver);

        for(char key : keys) {
            switch (key) {
                case '1':
                    actions.moveToElement(calculatorCanvas, NUMBER_1.x, NUMBER_1.y).click().build().perform();
                    break;
                case '2':
                    actions.moveToElement(calculatorCanvas, NUMBER_2.x, NUMBER_2.y).click().build().perform();
                    break;
                case '3':
                    actions.moveToElement(calculatorCanvas, NUMBER_3.x, NUMBER_3.y).click().build().perform();
                    break;
                case '4':
                    actions.moveToElement(calculatorCanvas, NUMBER_4.x, NUMBER_4.y).click().build().perform();
                    break;
                case '5':
                    actions.moveToElement(calculatorCanvas, NUMBER_5.x, NUMBER_5.y).click().build().perform();
                    break;
                case '6':
                    actions.moveToElement(calculatorCanvas, NUMBER_6.x, NUMBER_6.y).click().build().perform();
                    break;
                case '7':
                    actions.moveToElement(calculatorCanvas, NUMBER_7.x, NUMBER_7.y).click().build().perform();
                    break;
                case '8':
                    actions.moveToElement(calculatorCanvas, NUMBER_8.x, NUMBER_8.y).click().build().perform();
                    break;
                case '9':
                    actions.moveToElement(calculatorCanvas, NUMBER_9.x, NUMBER_9.y).click().build().perform();
                    break;
                case '0':
                    actions.moveToElement(calculatorCanvas, NUMBER_0.x, NUMBER_0.y).click().build().perform();
                    break;
                case '.':
                    actions.moveToElement(calculatorCanvas, DOT.x, DOT.y).click().build().perform();
                    break;
                case '/':
                    actions.moveToElement(calculatorCanvas, DIVIDE.x, DIVIDE.y).click().build().perform();
                    break;
                case '-':
                    actions.moveToElement(calculatorCanvas, MINUS.x, MINUS.y).click().build().perform();
                    break;
                case '+':
                    actions.moveToElement(calculatorCanvas, ADD.x, ADD.y).click().build().perform();
                    break;
                case '=':
                    actions.moveToElement(calculatorCanvas, EQUAL.x, EQUAL.y).click().build().perform();
                    break;
                case 'C':
                    actions.moveToElement(calculatorCanvas, CLEAR.x, CLEAR.y).click().build().perform();
                    break;
                // subtract button and positive/negative_convert button is the same, so I use '_' for positive/negative_convert button
                case '_':
                    actions.moveToElement(calculatorCanvas, NEGATIVE.x, NEGATIVE.y).click().build().perform();
                    break;
                default:
                    throw new Exception(String.format("Key '%s' is not defined yet", key));
            }

        }
    }

    @Step("capture calculator screenshot")
    public File captureCalculatorScreenshot(String expectedImageName) throws Exception {
        File actualImage = common.captureElementScreenshot(calculatorCanvas, expectedImageName);
        Allure.addAttachment("Actual image", new ByteArrayInputStream(FileUtils.readFileToByteArray(actualImage)));
        return actualImage;
    }

    @Step("compare to baseline image")
    public void compareToBaselineImage(File actualImage) throws Exception {
        String browser = common.getPropertyValues("browser");
        double diffValue = Double.parseDouble(common.getPropertyValues("percent_of_difference"));
        File diffImage = new File(String.format("screenshot\\diff\\%s_%s.png", browser, actualImage.getName()));

        BufferedImage bufferedExpectedImage = ImageComparisonUtil.readImageFromResources(String.format("baseline\\%s\\%s", browser, actualImage.getName()));
        BufferedImage bufferedActualImage = ImageIO.read(actualImage);

        ImageComparison imageComparison = new ImageComparison(bufferedExpectedImage, bufferedActualImage, diffImage);

        imageComparison.setAllowingPercentOfDifferentPixels(diffValue);

        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();

        //Image can be saved after comparison, using ImageComparisonUtil.
        ImageComparisonUtil.saveImage(diffImage, imageComparisonResult.getResult());

        Allure.addAttachment("Difference image", new ByteArrayInputStream(FileUtils.readFileToByteArray(diffImage)));

        //Check the result
        assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());
    }
}
