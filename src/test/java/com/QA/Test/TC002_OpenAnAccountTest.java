package com.QA.Test;

import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.QA.Base.BaseTest;
import com.QA.Pages.LoginAsClientPage;
import com.QA.Pages.OpenAnAccountPage;
import com.QA.utlis.TestUtils;

public class TC002_OpenAnAccountTest extends BaseTest {

	LoginAsClientPage LoginAsClientPage;
	OpenAnAccountPage openAnAccountPage;
	JSONObject accountOpeningDetails;
	TestUtils utils = new TestUtils();

	@BeforeClass
	public void beforeClass() throws Exception {
		InputStream datais = null;
		try {
			String dataFileName = "data/accountOpeningDetails.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			accountOpeningDetails = new JSONObject(tokener);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (datais != null) {
				datais.close();
			}
		}
		closeApp();
		launchApp();
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		LoginAsClientPage = new LoginAsClientPage();
		openAnAccountPage = new OpenAnAccountPage();
	}

	@AfterMethod
	public void afterMethod() {
//		closeApp();
//		launchApp();

	}

	@Test(priority = 1, enabled = true)
	public void OpenAnAccount() throws InterruptedException {

		LoginAsClientPage.ClickOnIAgree();

		LoginAsClientPage.ClickOnGrantPermission();

		openAnAccountPage.ClickOnOpenAnAccount();

		openAnAccountPage.SetNameField(accountOpeningDetails.getJSONObject("AccountOpeningDetails").getString("name"));

		openAnAccountPage
				.SetMobileField(accountOpeningDetails.getJSONObject("AccountOpeningDetails").getString("mobileNumber"));

		openAnAccountPage
				.SetEmailField(accountOpeningDetails.getJSONObject("AccountOpeningDetails").getString("email"));

		openAnAccountPage.SetStatefield();

	}

}
