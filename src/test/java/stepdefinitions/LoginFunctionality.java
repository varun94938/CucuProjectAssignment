package stepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginFunctionality {

	public WebDriver driver = null;
	public Properties prop;

	@Given("User has opened the application url")
	public void user_has_opened_the_application_url() {
		// Write code here that turns the phrase above into concrete actions
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
	}

	@Given("click on Make Appointment button")
	public void click_on_make_appointment_button() {
		// Write code here that turns the phrase above into concrete actions
		WebElement makeAppointmentButton = driver.findElement(By.id("btn-make-appointment"));
		makeAppointmentButton.click();
	}

	@When("User enters valid username and valid password")
	public void user_enters_valid_username_and_valid_password() throws Exception{
		// Write code here that turns the phrase above into concrete actions
		
		loadProperties();
		
		driver.findElement(By.id("txt-username")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("txt-password")).sendKeys(prop.getProperty("password"));

	}

	private void loadProperties() throws Exception {
		// TODO Auto-generated method stub
		prop = new Properties();
		String rootFolder = System.getProperty("user.dir");
		File file = new File(rootFolder + "//src//test//resources//data.properties");
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		
	}

	@When("User enters invalid username and invalid password")
	public void user_enters_invalid_username_and_invalid_password() throws Exception {
		// Write code here that turns the phrase above into concrete actions
		loadProperties();
		
		driver.findElement(By.id("txt-username")).sendKeys(prop.getProperty("invalidUsername"));
		driver.findElement(By.id("txt-password")).sendKeys(prop.getProperty("invalidPassword"));
	}

	@When("click on login button")
	public void click_on_login_button() {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("btn-login")).click();
	}

	@Then("find page appointment within ten seconds")
	public void find_page_appointment_within_ten_seconds() {
		// Write code here that turns the phrase above into concrete actions

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-sm-12 text-center']/h2")));

		WebElement makeAppointment = driver.findElement(By.xpath("//div[@class='col-sm-12 text-center']/h2"));
		Assert.assertTrue(makeAppointment.isDisplayed());
	}

	@Then("find error message showing invalid credentials")
	public void find_error_message_showing_invalid_credentials() {
		// Write code here that turns the phrase above into concrete actions
		WebElement errorMsg = driver.findElement(By.xpath("//div[@class='col-sm-12 text-center']/p[2]"));
		Assert.assertTrue(errorMsg.isDisplayed());
	}

	@Then("close the browser")
	public void close_the_browser() {

		driver.quit();
	}

}
