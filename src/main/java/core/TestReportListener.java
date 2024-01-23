package core;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class TestReportListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        try {

            System.out.println("Test report listener called -----------------------------------");
            Properties props=new Properties();
            int totalCNT=iTestContext.getAllTestMethods().length;
            int passedCNT=iTestContext.getPassedTests().size();
            int failedCNT=iTestContext.getFailedTests().size();
            int skippedCNT=iTestContext.getSkippedTests().size();
            float passPCNT=((float)passedCNT/(float)totalCNT)*100;

            props.setProperty("Total_tests",totalCNT+"");
            props.setProperty("Total_passed",passedCNT+"");
            props.setProperty("Total_failed",failedCNT+"");
            props.setProperty("Total_skipped",skippedCNT+"");
            props.setProperty("Passed_percentage",passPCNT+"");
            props.store(new FileOutputStream("results"), null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
