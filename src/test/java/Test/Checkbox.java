package Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Checkbox {

	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
		
		Page page = browser.newPage();	
		page.navigate("https://www.lambdatest.com/selenium-playground/checkbox-demo");
		Locator isCheckbox = page.getByText("Click on check box");
		System.out.println(isCheckbox.textContent());
		assertThat(isCheckbox).not().isChecked();
		isCheckbox.check();
		assertThat(isCheckbox).isChecked();
		
		//playwright.close();

	}

}
