package stepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MakeAppointmentFunctionality {

	//Make Appointment functionality
	public WebDriver driver = null;
	public Properties prop = null;

	@Given("User has opened the application Url")
	public void user_has_opened_the_application_url() {
		// Write code here that turns the phrase above into concrete actions
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
	}

	@Given("clicks on Make appointment BUtton")
	public void clicks_on_make_appointment_b_utton() {
		// Write code here that turns the phrase above into concrete actions
		WebElement makeAppointmentButton = driver.findElement(By.id("btn-make-appointment"));
		makeAppointmentButton.click();
	}

	@When("user enters valid username and valid password")
	public void user_enters_valid_username_and_valid_password() throws Exception {
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

	@When("clicks on login button")
	public void clicks_on_login_button() {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("btn-login")).click();
	}

	@When("clicks on Make Appointment button")
	public void clicks_on_make_appointment_button() {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//a[@id='btn-make-appointment']")).click();
	}

	@When("enter facilty healthcare program and visit date")
	public void enter_facilty_healthcare_program_and_visit_date() throws Exception {
		// Write code here that turns the phrase above into concrete actions

		// Facility
		WebElement facility = driver.findElement(By.id("combo_facility"));

		Select s = new Select(facility);
		s.selectByVisibleText(prop.getProperty("facilityOne"));

		// Healthcare program

		List<WebElement> programs = driver.findElements(By.xpath("//input[@name='programs']"));

		for (WebElement p : programs) {
			if (p.getAttribute("value").equals(prop.getProperty("healthCareProgramTwo"))) {
				p.click();
				break;
			}
		}

		driver.findElement(By.id("txt_visit_date")).click();

		String currentMonthYear = driver.findElement(By.xpath("(//th[@class='datepicker-switch'])[1]")).getText();
		String[] str = currentMonthYear.split(" ");

		String monthToBeSelected = "September";
		String yearToBeSelected = "2023";
		String dayToBeSelected = "20";

		while (!(str[0].equals(monthToBeSelected) && str[1].equals(yearToBeSelected))) {
			driver.findElement(By.xpath("(//th[@class='next'])[1]")).click();
			currentMonthYear = driver.findElement(By.xpath("(//th[@class='datepicker-switch'])[1]")).getText();
			str = currentMonthYear.split(" ");
		}
		driver.findElement(By.xpath("(//td[@class='day'])[" + dayToBeSelected + "]")).click();
		Thread.sleep(2000);
	}

	@When("click on Book Appointment button")
	public void click_on_book_appointment_button() {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("btn-book-appointment")).click();
	}

	@Then("User should be able to find booking confirmation message")
	public void user_should_be_able_to_find_booking_confirmation_message() {
		// Write code here that turns the phrase above into concrete actions
		WebElement confirmMsg = driver.findElement(By.xpath("//div[@class='col-xs-12 text-center']/h2"));
		Assert.assertTrue(confirmMsg.isDisplayed());
	}

	@Then("verify details in confirmation msg with entered details")
	public void verify_details_in_confirmation_msg_with_entered_details() {
		// Write code here that turns the phrase above into concrete actions
		WebElement facility = driver.findElement(By.id("facility"));
		WebElement readMission = driver.findElement(By.id("hospital_readmission"));
		WebElement program = driver.findElement(By.id("program"));
		WebElement visitDate = driver.findElement(By.id("visit_date"));

		if (facility.getText().equals(prop.getProperty("facilityOne"))
				&& readMission.getText().equals(prop.getProperty("applyForHospReadmission"))
				&& program.getText().equals(prop.getProperty("healthCareProgramTwo"))
				&& visitDate.getText().equals(prop.getProperty("visitDate")))

			System.out.println("Details verified");
		else
			System.out.println("Details are wrong");
	}

	@Then("close the Browser")
	public void close_the_browser() {
		// Write code here that turns the phrase above into concrete actions
		driver.quit();
	}

}
