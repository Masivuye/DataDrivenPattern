package com.appliedselenium.base;
import com.appliedselenium.base.Utils.ElementFetch;
import com.appliedselenium.base.Utils.Login;
import org.apache.log4j.Logger;

public class CheckLogin {
    ElementFetch element = new ElementFetch();
    public static Logger logs = Logger.getLogger("devpinoyLogger");

    public void testLogin(String username, String password){

                logs.debug("Inside testLogin()");

                     element.getWebElement("XPATH", Login.username_XPATH).sendKeys(username);
                     logs.debug("user name entered: " + username);

                      element.getWebElement("XPATH",Login.passwd_XPATH).sendKeys(password);
                        logs.debug("Password entered: " + password);

                      element.getWebElement("XPATH",Login.loginBtn_XPATH).click();
                    logs.debug("Log in button clicked");

                    element.getWebElement("LINK",Login.linkText_XPATH).click();
                    logs.debug("Hospital Selected");
                    logs.debug("Login Successful");
        }

    }

