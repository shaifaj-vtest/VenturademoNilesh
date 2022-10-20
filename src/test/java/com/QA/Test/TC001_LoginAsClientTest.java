package com.QA.Test;

import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.QA.Base.BaseTest;
import com.QA.Pages.LoginAsClientPage;

import com.QA.utlis.TestUtils;

public class TC001_LoginAsClientTest extends BaseTest {

	LoginAsClientPage LoginAsClientPage;
//	LoginPage loginPage;
//	ProductsPage productsPage;
//	JSONObject loginUsers;
	TestUtils utils = new TestUtils();

	@BeforeClass
	public void beforeClass() throws Exception {
		InputStream datais = null;
		try {
			String dataFileName = "data/accountOpeningDetails.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			// loginUsers = new JSONObject(tokener);
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
	}

	@AfterMethod
	public void afterMethod() {
//		closeApp();
//		launchApp();

	}

	@Test(priority = 1, enabled = true)
	public void LoginAsClient() {

		// LoginAsClientPage.ClickOnAllow();

		LoginAsClientPage.ClickOnIAgree();

		LoginAsClientPage.ClickOnGrantPermission();

//		LoginAsClientPage.ClickOnAllow();
//
//		LoginAsClientPage.ClickOnAllow();

		LoginAsClientPage.ClickOnLoginAsClient();

	}

}
