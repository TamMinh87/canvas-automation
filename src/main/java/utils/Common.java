package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Common extends DriverManager {
    private static InputStream inputStream;

    public File captureElementScreenshot(WebElement element, String screenshotName) throws Exception {

        if(WrapsElement.class.isAssignableFrom(element.getClass()))
            driver = ((WrapsDriver)((WrapsElement)element).getWrappedElement()).getWrappedDriver();
        else
            driver = ((WrapsDriver)element).getWrappedDriver();

        //Get the entire Screenshot from the driver of passed WebElement
        File screenImage = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        //Create an instance of Buffered Image from captured screenImage
        BufferedImage bufferedScreenImage = ImageIO.read(screenImage);

        //Create a rectangle using Width and Height of element
        Rectangle rect = new Rectangle(element.getSize().getWidth(), element.getSize().getHeight());

        //Get the Location of WebElement in a Point.
        org.openqa.selenium.Point p = element.getLocation();

        //Create image by for element using its location and size.
        BufferedImage bufferedElementImage = bufferedScreenImage.getSubimage(p.getX(), p.getY(), rect.width, rect.height);

        //Save file
        ImageIO.write(bufferedElementImage, "png", screenImage);
        File elementImage = new File(String.format("screenshot\\%s_%s.png", this.getPropertyValues("browser"), screenshotName));
        FileUtils.copyFile(screenImage, elementImage);
        return elementImage;
    }

    public static String getCurrentTimestamp() {
        return ZonedDateTime
                .now(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("uuuu_MM_dd_HH_mm_ss"));
    }

    public String getPropertyValues(String propertyName) throws IOException {
        String result = "";
        try {
            Properties prop = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream("data\\config.properties");

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file not found");
            }
            result = prop.getProperty(propertyName);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return result;
    }
}
