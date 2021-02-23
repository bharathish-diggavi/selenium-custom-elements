package com.diggavi.pageelements;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class PageElementLocatorFactory implements ElementLocatorFactory {

	private SearchContext searchContext;

	public PageElementLocatorFactory(SearchContext searchContext) {
		this.searchContext = searchContext;
	}
	
	WebDriver getDriver() {
		return (WebDriver) searchContext;
	}

	@Override
	public ElementLocator createLocator(Field field) {
		FindBy findBy = field.getAnnotation(FindBy.class);
		if (findBy == null)
			new IllegalStateException("Works only for @FindBy annotation");
		return new PageElementLocator(searchContext, new Annotations(field));
	}

}
