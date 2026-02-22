package Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

public class fisrtTest {

	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();
			page.navigate("https://ecommerce-playground.lambdatest.io/");
			Locator myaccount = page.locator("//a[contains(.,'My account')][@role='button']");
			myaccount.hover();
			page.locator("//a[contains(.,'Login')]").click();
			System.out.println(page.title());
			assertThat(page).hasTitle("Account Login");
			page.getByPlaceholder("E-Mail Address").fill("bigcommerce55@gmail.com");
			page.getByPlaceholder("Password").fill("Pass@154");
			page.locator("//input[@value='Login']").click();
			Locator errorMsg = page.locator("//div[@class='alert alert-danger alert-dismissible']");
			System.out.println(errorMsg.innerText());
			assertThat(errorMsg).hasText(" Warning: No match for E-Mail Address and/or Password.");
			browser.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
