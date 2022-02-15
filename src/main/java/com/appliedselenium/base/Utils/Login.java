package com.appliedselenium.base.Utils;

public interface Login {
    String username_XPATH = "//input[@name='username']";
    String passwd_XPATH = "//input[@name='password']";
    String loginBtn_XPATH= "//form[@id='signInForm']/div[4]/button";
    String linkText_XPATH="Tygerberg Hospitaal (DOCTOR)";
    String normalUser_XPATH="//p[contains(.,'Normal User')]";
}
