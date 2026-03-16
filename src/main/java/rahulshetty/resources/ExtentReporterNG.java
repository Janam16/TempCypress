package rahulshetty.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class ExtentReporterNG {
    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "index1.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setDocumentTitle("Shopping & User Details Form");
        reporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Jan");
        return extent;
    }
}
