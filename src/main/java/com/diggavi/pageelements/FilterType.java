package com.diggavi.pageelements;

import java.util.function.Predicate;

import org.openqa.selenium.WebElement;

public class FilterType {
	public static final Predicate<WebElement> VISIBLE = e -> e.isDisplayed();
	public static final Predicate<WebElement> CLICKABLE = e -> e.isDisplayed() && e.isEnabled();
}
