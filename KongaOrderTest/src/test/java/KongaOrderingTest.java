import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.velocity.tools.view.VelocityLayoutServlet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.Text;


public class KongaOrderingTest {
    private By ModalButton = By.xpath("//]/div/div[2]/div[3]/div[2]/div/button");
    private By ModalCardbutton = By.xpath("//*[@id=\"channel-template\"]/div[2]/div/div[2]/button/div/span[1]");
    private By ModalCardNumber = By.id("card-number");
    private By ModalDate = By.id("expiry");
    private By ModalCVV = By.id("id=\"cvv\"");
    private By ModalPayNowButton = By.id("validateCardForm");
    private By CloseIframe = By.xpath("/html/body/section/section/section/div[2]/div[1]/aside");

    private WebDriver driver;

    @BeforeTest
    public void setUp() throws InterruptedException {
//locate where the chrome driver is
        System.setProperty("webdriver.chrome", "resources/chromedriver");
// open chrome driver
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);
//1.input konga website URL
        driver.get("https://www.konga.com/");
        Thread.sleep(5000);
//maximize the browser
        driver.manage().window().maximize();
//click on the login button
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();

    }

    @Test(priority = 0)
    public void loginsuccessfully() throws InterruptedException {
//2.Sign in to Konga Successfully
//Test 1. verify user can login successfully with valid details on the webpage
//input email on email field
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("*****");
//input password on password field
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("******");
//click on login button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        if (driver.getCurrentUrl().contains("https://www.konga.com/"))
            //Pass
            System.out.println("login successful");
        else
            //Fail
            System.out.println("username/password incorrect");
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void verifyCategory() throws InterruptedException {
//3.from the Categories, click on the “Computers and Accessories”
//Test 2. Verify category computers and accessories is present
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        if (driver.getCurrentUrl().contains("https://www.konga.com/category/accessories-computing-5227"))
            //Pass
            System.out.println("correct computer and accessories webpage");
        else
            //Fail
            System.out.println("wrong webpage");
        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void clickLaptop() throws InterruptedException {
//4.Click on the Laptop SubCategory
// Test 3. verify that user can click on subcategory named laptop on the webpage
        WebElement Laptop = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/label/span"));
        Laptop.click();
        if (driver.getCurrentUrl().contains("https://www.konga.com/category/accessories-computing-5227"))
            //Pass
            System.out.println("correct laptop webpage");
        else
            //Fail
            System.out.println("wrong laptop webpage");

        Thread.sleep(5000);
    }

    @Test(priority = 3)
    public void clickAppleMacBooks() throws InterruptedException {
//5.Click on the Apple MacBooks
// Test 4. verify that user can interact by clicking Apple MacBooks on the webpage
        WebElement AppleMacBooks = driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/ul/li[1]/a/label/span"));
        AppleMacBooks.click();
        if (driver.getCurrentUrl().contains("https://www.konga.com/category/accessories-computing-5227"))
            //Pass
            System.out.println("correct Apple MacBooks webpage");
        else
            //Fail
            System.out.println("wrong Apple MacBooks webpage");
        Thread.sleep(5000);
    }

    @Test(priority = 4)
    public void Additem() throws InterruptedException {
//6.Add an item to the cart
//Test 5. verify user can add item to cart on the webpage
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[1]/div/div/div[2]/form/div[3]/button")).click();
        if (driver.getCurrentUrl().contains("https://www.konga.com/category/accessories-computing-5227"))
            //Pass
            System.out.println("macbook added to cart");
        else
            //Fail
            System.out.println("macbook not added to cart");
        Thread.sleep(5000);
    }

    @Test(priority = 5)
    public void clickCart() throws InterruptedException {
//click on my cart
//Test 6. Verify that user can interact with My cart option on the webpage
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]/span[1]")).click();
        String expectedUrl = "https://www.konga.com/category/accessories-computing-5227";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.contains(expectedUrl))
            //Pass
            System.out.println("macbook added to cart");
        else
            //Fail
            System.out.println("order placement failed");
        Thread.sleep(5000);
    }

    @Test(priority = 6)
    public void clickCheckout() throws InterruptedException {
//click on checkout
//Test 7. verify user can click on checkout button successfully on webpage
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
        String expectedUrl = "https://www.konga.com/category/accessories-computing-5227";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.contains(expectedUrl))
            //Pass
            System.out.println("contain checkout button");
        else
            //Fail
            System.out.println("not contain checkout button");
        Thread.sleep(5000);
    }

    @Test(priority = 7)
    public void ChangeAddress() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[1]/div[2]/div/button")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 8)
    public void AddDeliveryaddress() throws InterruptedException {
//7.click on add delivery address
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")).click();
//Select address from address book
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div[1]/form/button")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 9)
    public void UseThisAddress() throws InterruptedException {
//click on use this address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 10)
    public void selectCardPayment() throws InterruptedException {
//9.select a card payment method:"Pay Now"
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/h2")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 11)
    public void ContinueToPayment() throws InterruptedException {
//10. click on continue to payment button
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        Thread.sleep(5000);

    }
    @Test(priority = 12)
    public void ClickCard() throws InterruptedException {
        //click card
        click(ModalButton);
    }

    @Test(priority = 13)
    public void EnterCardDetails() throws InterruptedException {
//input invalid card number
//Test 8. verify that user should not input invalid card number on the field for card number for payment
        setText(ModalCardNumber, 12345678);
        setText(ModalDate, 02 / 25);
        setText(ModalCVV, 205);
        click(ModalPayNowButton);
        String expectedUrl = "https://www.konga.com/checkout/complete-order";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.contains(expectedUrl))
            //Pass
            System.out.println("invalid card number");
        else
            //Fail
            System.out.println("valid card number");
        Thread.sleep(5000);
    }

    @Test(priority = 14)
    public void closeiframe() throws InterruptedException {
//12. close the iframe that displays the input card modal
        click(CloseIframe);
        Thread.sleep(5000);
    }
    private void setText(By modalCardNumber, int i) {
    }

    private void click(By modalButton) {
    }
    @AfterTest
    public void closeBrowser() {
        //Quit browser
        driver.quit();
    }
}

