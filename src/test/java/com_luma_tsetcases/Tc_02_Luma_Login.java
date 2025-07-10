package com_luma_tsetcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com_page_object.Luma_LoginPage;
import com_test_utilites.XLUtils;




public class Tc_02_Luma_Login extends Luma_BaseClass{
	public Luma_LoginPage ll;
	@Test(dataProvider="Luma")
	public void LoginTest(String Email,String pass) throws InterruptedException {
		ll = new Luma_LoginPage(driver);
		ll.setEmail(Email);
		ll.setpassword(pass);
		ll.clicksigninbtn();
		ll.clearEmail();
		Thread.sleep(2000);
		ll.clearpassword();
		Thread.sleep(2000);
	}
	@DataProvider(name="Luma")
	String [][]getData() throws IOException{
//		String path = System.getProperty(("user.dir")+"\\ com_Luma_TestData\\"+"Luma.XLSX");
		String path = "D:\\mastaniworkspace\\LUMA1\\src\\test\\java\\com_luma_testdata\\Luma.xlsx";
		//identify the row count
		int rownum=XLUtils.getRowcount(path, "Sheet1");
		
		//identify the cell count
		int cellnum=XLUtils.getcellcount(path, "Sheet1", 1);
		
		String LoginData [][] = new String[rownum][cellnum];
		for(int i=1;i<= rownum;i++) {	//it represent the rows
			for(int j=0; j<cellnum;j++) {
				LoginData[i-1][j]=XLUtils.getcelldata(path, "Sheet1", i, j);
			}
		}
		return LoginData;
	}
}
