
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppiumDemo {
    private AndroidDriver driver;

    //테스트 전처리 구문 
    @BeforeTest
    public void setUp() throws Exception {
        File appDir = new File("/Users/hwangheeseon/Documents/workspace3/AppiumDemo/apps"); // 테스트하고자 하는 애플리케이션 full path
        File app = new File(appDir, "app-debug.apk"); // 애플리케이션 이름 
        
        DesiredCapabilities capabilities = new DesiredCapabilities(); //애피움 서버와의 세션 생성을 위한 설정 값 
        capabilities.setCapability("deviceName","Android");
        capabilities.setCapability("automationName", "Selendroid"); //app이 기반 테스트 프레임워크가 셀렌드로이드 기반일때만 설정(API Level 9이상 17이하일때)
        capabilities.setCapability("platformName","Android");
        //capabilities.setCapability("udid","adb devices를 통해 얻은 device ID"); //여러 디바이스가 설치된 경우, 테스트하고자 하는 디바이스를 선택 

        capabilities.setCapability("app", app.getAbsolutePath());
        driver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

  //테스트 후처리 구문 
    @AfterTest
    public void tearDown() throws Exception {
    	driver.quit(); //세션 생성을 위해 사용했던 리소스 해제 
    }

    //테스트 
    @Test
    public void loginTest()
    {
       	
       // String app_package_name = "com.example.swipetest:id/";
        By userId = By.id("idET");
        By password = By.id("pwdET");
        By login_Button = By.id("login");
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userId));
        driver.findElement(userId).sendKeys("domich@empas.com");
        driver.findElement(password).sendKeys("qwer1234");
        driver.findElement(login_Button).click(); 
        
    }	
	
}
