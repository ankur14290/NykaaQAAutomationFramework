/**
 *
 */
package FrameWorkNykaa;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestResult;

/**
 * @author nevil
 */
public class TestUtil {

    public static void main(String[] args) throws IOException {
        Framework framework = new Framework();
        framework.killDriverProcess();

    }

    public static int getId(ITestResult result) {
        int id = result.getTestClass().getName().hashCode();
        id = 31 * id + result.getMethod().getMethodName().hashCode();
        id = 31 * id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
        return id;
    }
}
