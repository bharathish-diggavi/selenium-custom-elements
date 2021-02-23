package com.diggavi;

import org.openqa.selenium.support.FindBy;

import com.diggavi.pageelements.PageElement;
import com.diggavi.pageelements.PageElements;

public class GooglePage {

	@FindBy(name = "q")
	PageElement searchInput;

	@FindBy(css = "div.g")
	public PageElements elements;

	public void enterSearchText(String text) {
		searchInput.sendKeys(text);
	}
}
