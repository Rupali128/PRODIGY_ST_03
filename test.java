import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SauceDemoLoginTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // Valid login test
        WebElement user = driver.findElement(By.id("user-name"));
        WebElement pass = driver.findElement(By.id("password"));
        WebElement btn = driver.findElement(By.id("login-button"));

        user.sendKeys("standard_user");
        pass.sendKeys("secret_sauce");
        btn.click();

        if (driver.getCurrentUrl().contains("/inventory.html")) {
            System.out.println("✅ Valid Login Passed");
        } else {
            System.out.println("❌ Valid Login Failed");
        }

        driver.navigate().back();

        // Invalid credentials test
        user = driver.findElement(By.id("user-name"));
        pass = driver.findElement(By.id("password"));
        btn = driver.findElement(By.id("login-button"));

        user.sendKeys("invalid_user");
        pass.sendKeys("wrong_pass");
        btn.click();

        WebElement err = driver.findElement(By.cssSelector("h3[data-test='error']"));
        if (err.getText().toLowerCase().contains("username and password do not match any user")) {
            System.out.println("✅ Invalid Login Test Passed");
        } else {
            System.out.println("❌ Invalid Login Test Failed");
        }

        driver.navigate().back();

        // Empty credentials test
        user = driver.findElement(By.id("user-name"));
        pass = driver.findElement(By.id("password"));
        btn = driver.findElement(By.id("login-button"));

        user.sendKeys("");
        pass.sendKeys("");
        btn.click();

        err = driver.findElement(By.cssSelector("h3[data-test='error']"));
        if (err.getText().toLowerCase().contains("user name is required")) {
            System.out.println("✅ Empty Fields Test Passed");
        } else {
            System.out.println("❌ Empty Fields Test Failed");
        }

        driver.quit();
    }
}
