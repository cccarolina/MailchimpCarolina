
package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
	private WebDriver driver;

	@Given("I have navigate to MailChimp signup site")
	public void i_have_navigate_to_mail_chimp_signup_site() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://login.mailchimp.com/signup/");
	}

	@Given("I have entered  email {string} in the emailfield")
	public void i_have_entered_email_in_the_emailfield(String Email) {
		WebElement newemail = Wait(By.id("email"));

		if (Email.equals("validemail")) {

			Random randomEmail = new Random();
			int randomInt = randomEmail.nextInt(10000);
			newemail.sendKeys("username" + randomInt + "@gmail.com");
		} else {

			newemail.sendKeys(Email);

		}

	}

	@Given("I have entered  username {string} in the usernamefield")
	public void i_have_entered_username_in_the_usernamefield(String Username) {

		WebElement newusername = Wait(By.id("new_username"));

		if (Username.equals("validusername")) {
			Random randomuser = new Random();
			int randomsInt = randomuser.nextInt(10000);
			newusername.sendKeys("randomrandomusername" + randomsInt);
		}

		else {

			newusername.sendKeys(Username);
		}

	}

	@Given("I have entered   password {string} in the passwordfield")
	public void i_have_entered_password_in_the_passwordfield(String Password) {
		WebElement passwordbox = Wait(By.id("new_password"));
		passwordbox.sendKeys(Password);

	}

	@When("I press Signup")
	public void i_press_signup_buttom() {
		WebElement signupbutton = Wait(By.id("create-account"));
		signupbutton.click();

	}

	@Then("depending if valid or invalid inputs login status {string} appears")
	public void depending_if_valid_or_invalid_inputs_login_status_appears(String status) {

		// anv�narnamn upptaget
		if (status == "UsernameTaken") {

			WebElement usernameExist = Wait(By.className("invalid-error"));
			assertEquals(usernameExist,
					"Another user with this username already exists. Maybe it's your evil twin. Spooky.");
		}

		// email saknas
		else if (status == "NoEmailEntered") {
			WebElement NoEmail = Wait(By.className("invalid-error"));
			assertEquals(NoEmail, "Please enter a value");
		}

		// f�r l�ngt anv�narnamn
		else if (status == "LongUser") {
			WebElement LongUser = Wait(By.className("invalid-error"));
			assertEquals(LongUser, "Enter a value less than 100 characters long");
		}

		// anv�ndarekonto skapas

		else if (status == "validinput") {
			WebElement Loginpage = Wait(By.className("margin-bottom"));
			assertEquals(Loginpage, "Check your email");

		}
		driver.quit();

	}

	public WebElement Wait(By by) {
		WebElement webElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(by));
		return webElement;
	}

}
