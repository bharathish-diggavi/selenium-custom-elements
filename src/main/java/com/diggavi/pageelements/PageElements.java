package com.diggavi.pageelements;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

public class PageElements {

	private List<WebElement> list;
	private WebDriver driver;
	protected FluentWait<WebDriver> waiter;

	public PageElements(List<WebElement> list, WebDriver driver) {
		super();
		this.list = list;
		this.driver = driver;
		this.waiter = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1));
	}

	public int getSize() {
		return list.size();
	}

	public PageElements filter(Predicate<WebElement> filters) {
		return new PageElements(list.stream().collect(Collectors.toList()), driver);
	}
}
