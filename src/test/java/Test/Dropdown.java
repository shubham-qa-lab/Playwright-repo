package Test;

import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;

import com.microsoft.playwright.Browser;

public class Dropdown {

	public static void main(String[] args) {

		String selectURL = "https://www.lambdatest.com/selenium-playground/select-dropdown-demo";
		String jqueryURL = "https://www.lambdatest.com/selenium-playground/jquery-dropdown-search-demo";

		Browser browser = Playwright.create().chromium().launch(new LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		page.navigate(selectURL);
		Locator dayLocator = page.locator("select#select-demo");

		// Select By Value
		dayLocator.selectOption("Sunday");
		Locator result = page.locator("p.selected-value ");
		System.out.println(result.textContent());
		assertThat(result).containsText("Day selected :- Sunday");

		// Select By label/text

		dayLocator.selectOption(new SelectOption().setValue("Thursday"));
		System.out.println(result);
		assertThat(result).containsText("Day selected :- Thursday");

		// Select by index

		dayLocator.selectOption(new SelectOption().setIndex(2));

		// Multiple Select

		Locator states = page.locator("select#multi-select");
		states.selectOption(new String[] { "New Jersey", "Texas" });

		Locator options = states.locator("option");
		System.out.println(options.count());
		List<String> allinnerText = options.allInnerTexts();
		for(int i=0;i<allinnerText.size();i++) {
			System.out.println(allinnerText.get(i));
		}
		
		// Select options from jquery likes div tags not a select tag
		page.navigate(jqueryURL);
		Locator country =page.locator("span.select2-container").first();
		country.click();
		Locator countryList = page.locator("span.select2-results",new Page.LocatorOptions().setHasText("India"));
		countryList.click();
		Locator files = page.locator("select[name='files']");
		files.selectOption("Ruby");
		browser.close();

	}

}
