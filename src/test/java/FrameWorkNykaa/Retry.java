package FrameWorkNykaa;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Retry implements IRetryAnalyzer {
	private int retryCount = 0;
	private int maxRetryCount = 1;

	public boolean retry(ITestResult result) {
		Reporter.log("Retry Analyze : " + result.getMethod().getRetryAnalyzer(), true);

		if (retryCount < maxRetryCount) {
			result.setStatus(ITestResult.FAILURE); // we set the status to skipped and remove this result so that on rerun if we have dependency it uses latest result.
			result.getTestContext().getFailedTests().removeResult(result.getMethod());
			/*System.out.println("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retryCount+1) + " time(s).");
			 */
			Reporter.setCurrentTestResult(result);
			retryCount++;

			return true;
		}
		return false;

	}


}
