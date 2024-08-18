package org.testing.Assertion;

import org.testing.WebUtil;

public class AssertionHolding {
    public void assertHomePage(){
        WebUtil.verifyElementVisible("//*[contains(@id, 'sdgBod') and @title='Finance']");
    }
}