package com.diggavi.pageelements;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

public class PageElementFileDecorator extends DefaultFieldDecorator {

	public PageElementFileDecorator(ElementLocatorFactory factory) {
		super(factory);
	}

	public PageElementFileDecorator(WebDriver driver) {
		this(new PageElementLocatorFactory(driver));
	}

	@Override
	public Object decorate(ClassLoader loader, Field field) {
		if (!(PageElements.class.isAssignableFrom(field.getType())
				|| PageElement.class.isAssignableFrom(field.getType())
				|| WebElement.class.isAssignableFrom(field.getType()) || isDecoratableList(field))) {
			return null;
		}

		ElementLocator locator = factory.createLocator(field);
		if (locator == null) {
			return null;
		}
		if (WebElement.class.isAssignableFrom(field.getType())) {
			return pageElementProxyForLocator(loader, locator);
		} else if (WebElement.class.isAssignableFrom(field.getType())) {
			return proxyForLocator(loader, locator);
		} else if (PageElements.class.isAssignableFrom(field.getType())) {
			return proxyforPageElementsLocator(loader, locator);
		} else if (List.class.isAssignableFrom(field.getType())) {
			return proxyForListLocator(loader, locator);
		} else {
			return null;
		}

	}

	private WebElement pageElementProxyForLocator(ClassLoader loader, ElementLocator locator) {
		WebElement element = proxyForLocator(loader, locator);
		return new AbstractPageElement(element, ((PageElementLocatorFactory) factory).getDriver());
	}

	@SuppressWarnings("unchecked")
	private PageElements proxyforPageElementsLocator(ClassLoader loader, ElementLocator locator) {
		InvocationHandler handler = new LocatingElementListHandler(locator);

		List<WebElement> proxy;
		proxy = (List<WebElement>) Proxy.newProxyInstance(loader, new Class[] { List.class }, handler);
		return new PageElements(proxy, ((PageElementLocatorFactory) factory).getDriver());
	}

}
