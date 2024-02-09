import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TestScenarios {
    String _browserName;
    WebDriver driver;
    @BeforeTest
    @Parameters({"BrowserName"})
    public void SetBrowser()
    {
        _browserName="BrowserName";
        if(_browserName.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver(getChromeOptions());
        } else if (_browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver(getSafariOptions());

        }
        else
        {
            driver = new ChromeDriver();
        }
    }
    @AfterTest
    public void Close()
    {
        driver.quit();
    }
    @Test
    public void TestScenario1(){
        String URL;
        String welText = "Welcome to LambdaTest";
        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[normalize-space()='Simple Form Demo']")).click();
        URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("simple-form-demo"));
        driver.findElement(By.xpath("//input[@id='user-message']")).sendKeys(welText);
        driver.findElement(By.xpath("//button[@id='showInput']")).click();
        String msg = driver.findElement(By.xpath("//p[@id='message']")).getText();
        Assert.assertEquals(msg,welText);

    }
    @Test
    public void TestScenario2()
    {
        int x=10;
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.findElement(By.xpath("//a[normalize-space()='Drag & Drop Sliders']")).click();
        WebElement slider = driver.findElement(By.xpath("//input[@value='15']"));
        Actions action= new Actions(driver);
        int width = slider.getSize().getWidth();
        Actions act= new Actions(driver);
        System.out.println(slider.getSize());
        //act.moveToElement(slider, ((width*x)/100), 0).click();
        act.moveToElement(slider, 215, 0).click();
        act.build().perform();
        String value = driver.findElement(By.xpath("//input[@value='15']/following-sibling::output")).getText();
        Assert.assertEquals(value,"95");

    }
    @Test
    public void TestScenario3()
    {

        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.findElement(By.xpath("//a[normalize-space()='Input Form Submit']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
        //Form validation is done through browser and not html on the webpage, hence can not be accessible through DOM
        //validation is opened in another window which is active, hence below code should work
        WebElement activeElement = driver.switchTo().activeElement();
        String messageStr = activeElement.getAttribute("validationMessage");
        Assert.assertEquals(messageStr,"Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Firstname");
        driver.findElement(By.xpath("//input[@id='inputEmail4']")).sendKeys("test@test.com");
        driver.findElement(By.xpath("//input[@id='inputPassword4']")).sendKeys("TestPassword1");
        driver.findElement(By.xpath("//input[@id='company']")).sendKeys("ItestCompany");
        driver.findElement(By.xpath("//input[@id='websitename']")).sendKeys("www.ItestCompany.com");
        Select clst = new Select(driver.findElement(By.xpath("//select[@name='country']")));
        clst.selectByVisibleText("United States");
        driver.findElement(By.xpath("//input[@id='inputCity']")).sendKeys("Pittsburgh");
        driver.findElement(By.xpath("//input[@id='inputAddress1']")).sendKeys("Greentree Rd.");
        driver.findElement(By.xpath("//input[@id='inputAddress2']")).sendKeys("1200");
        driver.findElement(By.xpath("//input[@id='inputState']")).sendKeys("PA");
        driver.findElement(By.xpath("//input[@id='inputZip']")).sendKeys("15220");
        driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
        String msg= driver.findElement(By.xpath("//p[@class='success-msg hidden']")).getText() ;
        Assert.assertEquals(msg,"Thanks for contacting us, we will get back to you shortly.");


    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("122.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "lrmahajan");
        ltOptions.put("accessKey", "2xvtB2P4DIAivMjILOAp841ZsSX0j4aqYD8p27kmSVzQyvQpuo");
        ltOptions.put("project", "Selenium101");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("seCdp", true);
        ltOptions.put("network",true);
        ltOptions.put("visual", true);
        ltOptions.put("video",true);
        ltOptions.put("console",true);
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);
        return browserOptions;
    }
    private static SafariOptions getSafariOptions() {
        SafariOptions browserOptions = new SafariOptions();
        browserOptions.setPlatformName("macOS Sonoma");
        browserOptions.setBrowserVersion("17.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "lrmahajan");
        ltOptions.put("accessKey", "2xvtB2P4DIAivMjILOAp841ZsSX0j4aqYD8p27kmSVzQyvQpuo");
        ltOptions.put("project", "Selenium101");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);
        return browserOptions;
    }

}
