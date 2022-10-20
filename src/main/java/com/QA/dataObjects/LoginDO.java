package com.QA.dataObjects;

import java.io.IOException;

import com.QA.utlis.ExcelParserUtil;



public class LoginDO {

	private String emailAddress;
	private String password;

	/**
	 * This method is used to read the test data from Alias sheet in the
	 * testdata.xlsx file
	 * 
	 * @param excelParserUtil
	 * @param rowNumber
	 * @throws IOException
	 */

	public void loadLogin(ExcelParserUtil excelParserUtil, int rowNumber) throws IOException {

		this.setEmailAddress(excelParserUtil.getExcelData(rowNumber, 0));
		this.setPassword(excelParserUtil.getExcelData(rowNumber, 1));
	}

	/**
	 * This method is used to return the emailAddress value
	 * 
	 * @return
	 */

	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * This method is used to set the emailAddress value
	 * 
	 * @param emailAddress
	 */

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * This method is used to return the password value
	 * 
	 * @return
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * This method is used to set the password value
	 * 
	 * @param password
	 */

	public void setPassword(String password) {
		this.password = password;
	}

}
