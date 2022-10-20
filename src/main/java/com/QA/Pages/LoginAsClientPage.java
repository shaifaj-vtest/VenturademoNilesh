package com.QA.Pages;

import org.testng.Assert;

import com.QA.Base.BaseTest;
import com.QA.utlis.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginAsClientPage extends BaseTest {
	TestUtils utils = new TestUtils();
	@AndroidFindBy(xpath = "//*[@text='Allow']")
	@iOSXCUITFindBy(id = "test-Username")
	private MobileElement allowBtn;

	@AndroidFindBy(xpath = "//*[@text='I AGREE']")
	@iOSXCUITFindBy(id = "test-Username")
	private MobileElement IAgreeBtn;

	@AndroidFindBy(xpath = "//*[@text='GRANT PERMISSION']")
	@iOSXCUITFindBy(id = "test-Username")
	private MobileElement GrantPermissionBtn;

	@AndroidFindBy(xpath = "(//*[@class='android.widget.TextView'])[2]")
	@iOSXCUITFindBy(id = "test-Username")
	private MobileElement LoginAsClientBtn;

	@AndroidFindBy(xpath = "//*[@text='User ID']")
	@iOSXCUITFindBy(id = "test-Username")
	private MobileElement UserId;

	public void ClickOnAllow() {

		click(allowBtn, "Clicked On Allow Button");

	}

	public void ClickOnIAgree() {

		click(IAgreeBtn, "Clicked on I Agree Button");

	}

	public void ClickOnGrantPermission() {
		click(GrantPermissionBtn, "Clicked On Grant Permission");

	}

	public void ClickOnLoginAsClient() {
		click(LoginAsClientBtn, "Clicked on Login As Client");

		// Assert.assertTrue(UserId.isDisplayed(), "UserId is displayed");

	}

}
