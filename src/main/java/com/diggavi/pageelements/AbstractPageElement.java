package com.diggavi.pageelements;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class AbstractPageElement implements PageElement {

	protected WebElement element;
	protected FluentWait<WebDriver> waiter;
	private WebDriver driver;

	public AbstractPageElement(WebElement webElement, WebDriver driver) {
		super();
		this.element = webElement;
		this.driver = driver;
		this.waiter = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1));
	}

	@Override
	public void click() {
		try {
			element.click();
		} catch (Exception e) {
			waiter.until(ExpectedConditions.elementToBeClickable(element));
		}
	}

	@Override
	public void submit() {
		element.submit();
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		try {
			element.sendKeys(keysToSend);
		} catch (Exception e) {
			waiter.until(ExpectedConditions.visibilityOf(element)).sendKeys(keysToSend);
		}
	}

	@Override
	public void clear() {
		element.clear();
	}

	@Override
	public String getTagName() {
		return element.getTagName();
	}

	@Override
	public String getAttribute(String name) {
		return element.getAttribute(name);
	}

	@Override
	public boolean isSelected() {
		return element.isSelected();
	}

	@Override
	public boolean isEnabled() {
		return element.isEnabled();
	}

	@Override
	public String getText() {
		return element.getText();
	}

	@Override
	public List<WebElement> findElements(By by) {
		return element.findElements(by);
	}

	public PageElements findPageElements(By by) {
		return new PageElements(element.findElements(by), driver);
	}

	@Override
	public WebElement findElement(By by) {
		return element.findElement(by);
	}
	
	public PageElement findPageElement(By by) {
		return new AbstractPageElement(element.findElement(by), driver);
	}

	@Override
	public boolean isDisplayed() {
		return element.isDisplayed();
	}

	@Override
	public Point getLocation() {
		return element.getLocation();
	}

	@Override
	public Dimension getSize() {
		return element.getSize();
	}

	@Override
	public Rectangle getRect() {
		return element.getRect();
	}

	@Override
	public String getCssValue(String propertyName) {
		return element.getCssValue(propertyName);
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return element.getScreenshotAs(target);
	}

}
