package org.testing;


import org.testing.Assertion.AssertionHolding;
import org.testng.annotations.Test;

import java.io.IOException;

public class StockCompareTest extends BaseTest{
    AssertionHolding assertionHolding = new AssertionHolding();

    @Test
    public void asUserOpenRegisterForm() throws InterruptedException, IOException {
        //Open the link  and assert homepage
        assertionHolding.assertHomePage();
        //Click the search box
        WebUtil.click("(//input[@class='Ax4B8 ZAGvjd' and @type='text'])[2]");
        //Write the name of the stock
        WebUtil.setTextAndEnter("(//input[@class='Ax4B8 ZAGvjd' and @type='text'])[2]", "BBRI");
        //click tab YTD
        WebUtil.click("//button[@id='ytdTab']");
        //Click the another recommendation stock in horizontal share list
        WebUtil.click("(//div[@class='bF2Mne']/div)[1]");
        //capture the differences between the two shares
        WebUtil.takeScreenshot();

        //Compare the content value the data shown
        //price value
        String price1 = WebUtil.getText("//div[@data-tab-number='0']//div[contains(@class, 'Z63m9d')]/span");
        String price2 = WebUtil.getText("//div[@data-tab-number='1']//div[contains(@class, 'Z63m9d')]/span");
        WebUtil.compareString(price1, price2);
        //Stock name
        String stock1 = WebUtil.getText("//div[@data-tab-number='0']//div[contains(@class, 'qIEjSe')]");
        String stock2 = WebUtil.getText("//div[@data-tab-number='1']//div[contains(@class, 'qIEjSe')]");
        WebUtil.compareString(price1, price2);
    }

}