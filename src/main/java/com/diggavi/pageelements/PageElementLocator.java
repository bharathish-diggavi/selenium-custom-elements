package com.diggavi.pageelements;

import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class PageElementLocator extends DefaultElementLocator implements ElementLocator {
	SearchContext searchContext;

	public PageElementLocator(SearchContext searchContext, AbstractAnnotations annotations) {
		super(searchContext, annotations);
		this.searchContext = searchContext;
	}

	@Override
	public PageElement findElement() {
		WebElement element = super.findElement();
		return new AbstractPageElement(element, ((WebDriver) searchContext));
	}

	@Override
	public List<WebElement> findElements() {
		return super.findElements();
	}

}
