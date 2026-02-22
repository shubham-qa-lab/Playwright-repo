package Test;

import java.nio.file.Paths;
import java.util.Arrays;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ScreenshotCaret;

public class Screenshot {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");

		// Screenshot for visible area
		ScreenshotOptions screenshotoption = new ScreenshotOptions();
		page.screenshot(screenshotoption.setPath(Paths.get("./snaps/scr.png")));

		// Screenshot for fullpage
		page.screenshot(screenshotoption.setFullPage(true).setPath(Paths.get("./snaps/fullpage.jpg")));

		// Locator Screenshot

		Locator bookbtn = page.locator("button.chfw-header_demo_btn");
		bookbtn.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snaps/locator.png")));

		// Masking locator
		Locator input = page.getByPlaceholder("Please enter your Message");
		input.fill("Something");
		input.scrollIntoViewIfNeeded();
		page.screenshot(screenshotoption.setFullPage(false).setPath(Paths.get("./snaps/masking.jpg"))
				.setMask(Arrays.asList(input, bookbtn)));

		// Caret(Cursor blinking in any input field) hide/show in screenshot

		input.click();
		page.screenshot(
				new ScreenshotOptions().setCaret(ScreenshotCaret.HIDE).setPath(Paths.get("./snaps/caretHIDE.png")));
		page.screenshot(
				new ScreenshotOptions().setCaret(ScreenshotCaret.INITIAL).setPath(Paths.get("./snaps/caretinit.png")));

		page.close();
		playwright.close();

	}

}
