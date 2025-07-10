package com_test_utilites;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com_luma_tsetcases.Luma_BaseClass;


public class Reports implements ITestListener{

	public ExtentSparkReporter report;
	public ExtentReports extent;
	public ExtentTest test;
	
	//pre defined Methods(onStart,OnTestSuccess,onTestFailue,onTestSkiped,onFinish)
	
	public void onStart(ITestContext tr) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repname = "Luma_TestReport" + timeStamp + ".html";
		report = new ExtentSparkReporter("D:\\mastaniworkspace\\LUMA1\\repoerts" + repname);
		
		report.config().setReportName("Luma_registration");
		report.config().setDocumentTitle("Luma_Registration_signin_Actions");
		report.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Username", "Ram");
		extent.setSystemInfo("HostName", "Remote Host");
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "Stage Environment");
		
	}
	
	public void onTestSuccess(ITestResult tr) {
		test= extent.createTest(tr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr) {
		test= extent.createTest(tr.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		try {
		String implocation = new Luma_BaseClass().CaptureScreenShots(tr.getName());
		test.addScreenCaptureFromPath(implocation);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult tr) {
		test= extent.createTest(tr.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREY));
	
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
