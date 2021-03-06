
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

	@Given("I have entered email {string} in the emailfield")
	public void i_have_entered_email_in_the_emailfield(String Email) {
		WebElement newemail = Wait(By.cssSelector("input[id=email]"));

		if (Email.equals("validemail")) {
			Random randomEmail = new Random();
			int randomInt = randomEmail.nextInt(10000);
			newemail.sendKeys("username" + randomInt + "@gmail.com");
		} else {
			newemail.sendKeys(Email);
		}
	}

	@Given("I have entered username {string} in the usernamefield")
	public void i_have_entered_username_in_the_usernamefield(String Username) {
		WebElement newusername = Wait(By.cssSelector("input[id=new_username]"));
		
		if (Username.equals("validusername")) {
			Random randomuser = new Random();
			int randomsInt = randomuser.nextInt(10000);
			newusername.sendKeys("randomrandomusername" + randomsInt);
		}
		else {
			newusername.sendKeys(Username);
		}
	}

	@Given("I have entered password {string} in the passwordfield")
	public void i_have_entered_password_in_the_passwordfield(String Password) {
		WebElement PasswordBox = Wait(By.cssSelector("input[id=new_password]"));
		PasswordBox.sendKeys(Password);
	}

	@When("I press signup")
	public void i_press_signup() {
		WebElement signupButton = Wait(By.id("create-account"));
		signupButton.click();
	}

	@Then("depending if valid or invalid inputs login status {string} appears")
	public void depending_if_valid_or_invalid_inputs_login_status_appears(String status) {

		// anv?ndarnamn upptaget
		if (status.equals("UsernameTaken")) {
			WebElement usernameExist = Wait(By.className("invalid-error"));
			assertEquals("Another user with this username already exists. Maybe it's your evil twin. Spooky.",
					usernameExist.getText());
			
			WebElement newUsername = Wait(By.cssSelector("input[id=new_username]"));
			assertEquals("invalid av-text", newUsername.getAttribute("class"));
		}

		// email saknas
		else if (status.equals("NoEmailEntered")) {
			WebElement noEmail = Wait(By.className("invalid-error"));
			assertEquals("Please enter a value", noEmail.getText());
		
			String URL = driver.getCurrentUrl();
			assertEquals("https://login.mailchimp.com/signup/post", URL);
		}

		// f?r l?ngt anv?narnamn
		else if (status.equals("LongUser")) {
			WebElement longUser = Wait(By.className("invalid-error"));
			assertEquals("Enter a value less than 100 characters long", longUser.getText());
			
			WebElement failMessage = Wait(By.tagName("li"));
			assertEquals("Please check your entry and try again.", failMessage.getText());
		}

		// anv?ndarkonto skapas
		else if (status.equals("validinput")) {
			WebElement loginPage = Wait(By.tagName("h1"));
			assertEquals("Check your email", loginPage.getText());
			
			String URL = driver.getCurrentUrl();
			assertEquals(true, URL.startsWith("https://login.mailchimp.com/signup/success"));
		}
		
		// om vi kommer in i else ?r "status" fel - d? ska testet faila
		else {
			assertEquals(true, false);
		}
		driver.quit();
	}

	public WebElement Wait(By by) {
		WebElement webElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(by));
		return webElement;
	}
}
