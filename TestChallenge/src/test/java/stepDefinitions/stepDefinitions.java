package stepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class stepDefinitions {

    private WebDriver driver;
    private String orderId;

    @Before
    public void browserSetup(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://aweui.aspnetawesome.com/");
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    @Given("that the user is on the landing page")
    public void that_the_user_is_on_the_landing_page(){
        String h1Value = driver.findElement(By.xpath("//*[@id=\"maincont\"]/h1")).getText();
        String expectedH1Value = "aweui - awesome js UI components library";
        Assert.assertEquals(h1Value,expectedH1Value);
    }

    @When("the user input {string} as the ID")
    public void the_user_input_an_id(String orderId){
        this.orderId = orderId;
    }

    @Then("the row of data that corresponds with that ID will be displayed")
    public void the_row_of_data_that_corresponds_with_that_ID_will_be_displayed(){
        String cssId = "tr[data-k="+ "\"" + orderId + "\"]";
        WebElement rowElement = driver.findElement(By.cssSelector(cssId));
        List<WebElement> tdElements = rowElement.findElements(By.tagName("td"));
        String[] columnHeaders = {"ID", "Person", "Food", "Country","Date","Meals","Chef"};

        int i = 0;
        for (WebElement tdElement : tdElements) {
            String tdValue = tdElement.getText();
            System.out.println(columnHeaders[i] + ": " + tdValue);
            i++;
        }
    }
}
