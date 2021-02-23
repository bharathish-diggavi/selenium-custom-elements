package com.diggavi;

import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.diggavi.pageelements.FilterType;
import com.diggavi.pageelements.PageElementFileDecorator;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void shouldAnswerWithTrue() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = null;
		try {
			driver = new ChromeDriver();
			driver.get("https://www.google.co.in/");
			GooglePage page = new GooglePage();
			PageFactory.initElements(new PageElementFileDecorator(driver), page);
			page.enterSearchText("hello" + Keys.ENTER);
			System.out.println(page.elements.filter(FilterType.VISIBLE).filter(FilterType.CLICKABLE).getSize());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			if (driver != null)
//				driver.quit();
		}
	}
}
