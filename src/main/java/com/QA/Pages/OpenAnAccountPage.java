package com.QA.Pages;

import java.util.concurrent.TimeUnit;

import com.QA.Base.BaseTest;
import com.QA.utlis.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class OpenAnAccountPage extends BaseTest {

	TestUtils utils = new TestUtils();
	@AndroidFindBy(xpath = "//*[@text='Open an account']")
	@iOSXCUITFindBy(id = "test-Username")
	private MobileElement openAnAccountBtn;

	@AndroidFindBy(xpath = "//*[@text='Name']")
	@iOSXCUITFindBy(id = "test-Username")
	private MobileElement NameTextField;

	@AndroidFindBy(xpath = "//*[@text='Mobile Number']")
	@iOSXCUITFindBy(id = "test-Username")
	private MobileElement MobileTextField;

	@AndroidFindBy(xpath = "//*[@text='Email']")
	@iOSXCUITFindBy(id = "test-Username")
	private MobileElement EmailTextField;

	@AndroidFindBy(xpath = "//*[@text='Andaman and Nicobar Islands']")
	@iOSXCUITFindBy(id = "test-Username")
	private MobileElement stateDropdownField;

	@AndroidFindBy(xpath = "//*[@text='Maharashtra']")
	@iOSXCUITFindBy(id = "test-Username")
	private MobileElement SelectedStateField;

	public void ClickOnOpenAnAccount() {

		click(openAnAccountBtn, "Clicked on Open An Account Button");

	}

	public void SetNameField(String name) {

		clear(NameTextField);
		sendKeys(NameTextField, name, "Name is:" + name);

	}

	public void SetMobileField(String mobile) {

		clear(MobileTextField);
		sendKeys(MobileTextField, mobile, "Mobile No. is:" + mobile);

	}

	public void SetEmailField(String email) {

		clear(EmailTextField);
		sendKeys(EmailTextField, email, "Email is:" + email);

	}

	public void SetStatefield() throws InterruptedException {

		click(stateDropdownField, "Clicked on State field");

		// scrollToElement("Maharashtra");
		// waitForVisibility(SelectedStateField);

	}

}
