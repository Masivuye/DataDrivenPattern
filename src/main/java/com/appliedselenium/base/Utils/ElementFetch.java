package com.appliedselenium.base.Utils;
import com.appliedselenium.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementFetch {
    public WebElement getWebElement(String identifierType, String identifierValue){
        switch (identifierType) {
            case "ID":
                return TestBase.driver.findElement(By.id(identifierValue));
            case "XPATH":
                return TestBase.driver.findElement(By.xpath(identifierValue));
            case "CSS":
                return TestBase.driver.findElement(By.cssSelector(identifierValue));
            case "TAGNAME":
                return TestBase.driver.findElement(By.tagName(identifierValue));
            case "LINK":
                return TestBase.driver.findElement(By.linkText(identifierValue));
            default:
                return null;
        }
    }
        public List<WebElement> getListWebElements(String identifierType, String identifierValue){
            switch (identifierType){
                case"ID":
                    return TestBase.driver.findElements(By.id(identifierValue));
                case"XPATH":
                    return TestBase.driver.findElements(By.xpath(identifierValue));
                case"CSS":
                    return TestBase.driver.findElements(By.cssSelector(identifierValue));
                case"TAGNAME":
                    return TestBase.driver.findElements(By.tagName(identifierValue));
                default:
                    return null;

            }
    }
}
