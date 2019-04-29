package FrameWorkNykaa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * @author nevil
 */
public class Email {
    public static String failureReason = "Login";
    public static String failureReasonMessage = "User was not able to login from home page as home page is taking too long to load completely.";

    /*
     * public static void main(String[] args) { email ema =new email();
     * ema.sendMail(); }
     */
    public void sendMail(TestListenerAdapter tla) throws IOException {
        PropertyConfiguration pf = new PropertyConfiguration();
        Properties prop = pf.getInstance();
        List<ITestResult> testcases = tla.getFailedTests();
        List<ITestResult> skippedTescases = tla.getFailedButWithinSuccessPercentageTests();
        boolean isFailed;
        if (tla.getFailedTests().isEmpty() && tla.getFailedButWithinSuccessPercentageTests().isEmpty()) {
            isFailed = false;
        } else {
            isFailed = true;
        }
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        Session mailSession = null;

        mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("live.nykaa.automate@gmail.com", "Automation@2016");
                // return new
                // PasswordAuthentication("dibragede2@gmail.com","monsoon098");

            }
        });

        try {

            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);
            java.util.Date date = new java.util.Date();
            if (prop.getProperty("environmentName").equalsIgnoreCase("www")) {
                if (isFailed) {
                    message.setSubject(
                            "LIVE DESKTOP " + prop.getProperty("EmailType").toUpperCase() + ": Checkout Test Failed !");
                } else {
                    message.setSubject(
                            "LIVE DESKTOP " + prop.getProperty("EmailType").toUpperCase() + ": Tests Passed !");

                }
            } else {
                if (isFailed) {
                    message.setSubject(failureReason + " Failed on LIVE !  (" + prop.getProperty("environmentName") + ")");
                } else {
                    message.setSubject(prop.getProperty("environmentName").toUpperCase() + " : Tests Passed !");
                }
            }
            message.setFrom(new InternetAddress("qa@Nykaa.com"));
            // String []to = new String[]{"nevil.panchal@nykaa.com"};
            /*
             * String []to = new String[]{"nevil.panchal@nykaa.com"};
             * message.addRecipient(Message.RecipientType.TO, new
             * InternetAddress(to[0]));
             */
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("qa@nykaa.com"));
            String address = prop.getProperty("EmailNotificationTo");
            InternetAddress[] iAdressArray = InternetAddress.parse(address);
            message.setRecipients(Message.RecipientType.TO, iAdressArray);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart("mixed");

            for (ITestResult testcase : testcases) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                MimeBodyPart attachmentBodyPart1 = new MimeBodyPart();
                String filepath3 = "src/test/resources/ExtentReport.html";
                String snapFilePath = "test-output" + File.separator + testcase.getMethod().getMethodName() + ".jpg";
                DataSource source = new FileDataSource(new File(filepath3).getAbsolutePath());
                DataSource source1 = new FileDataSource(new File(snapFilePath).getAbsolutePath());
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(testcase.getMethod().getMethodName());
                multipart.addBodyPart(attachmentBodyPart);
                attachmentBodyPart1.setDataHandler(new DataHandler(source1));
                attachmentBodyPart1.setFileName(testcase.getMethod().getMethodName());
                multipart.addBodyPart(attachmentBodyPart1);

            }

            for (ITestResult testcase : skippedTescases) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                String filepath3 = "src/test/resources/ExtentReport.html";
                DataSource source = new FileDataSource(new File(filepath3).getAbsolutePath());
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(testcase.getMethod().getMethodName());
                multipart.addBodyPart(attachmentBodyPart);
            }

            messageBodyPart = new MimeBodyPart();
            String htmlFilePath = "test-output/Command line suite/Command line test.html";
            // String htmlFilePath = "test-output/emailable-report.html";
            StringBuilder contentBuilder = new StringBuilder();
            String content1 = Files.toString(new File(htmlFilePath), Charsets.UTF_8);
            int TotalTestCases = tla.getFailedTests().size() + tla.getPassedTests().size()
                    + tla.getFailedButWithinSuccessPercentageTests().size();
            int FailedTestCount = tla.getFailedButWithinSuccessPercentageTests().size() + tla.getFailedTests().size();
            // int FailedTestCount = tla.getFailedTests().size();
            int PassedTestCount = tla.getPassedTests().size();
            MimeBodyPart messageBodyPart3 = new MimeBodyPart();
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            MimeBodyPart messageBodyPart4 = new MimeBodyPart();

            String Header = "<html><header><h1 align=\"center\">Live Checkout Test</h1></header></html>";
			/*String MainBody = "<html><body><table  cellspacing=\"4\" cellpadding=\"4\" border=\"1\" align=\"center\"><tr><td>PassedTests</td><td>"
					+ PassedTestCount + "/" + TotalTestCases + "</td></tr><tr><td>FailedTests</td><td>"
					+ FailedTestCount + "/" + TotalTestCases + "</td></tr></table></body></html>";*/
            String MainBody = "<html><body>"
                    + "<table  cellspacing=\"4\" cellpadding=\"4\" border=\"1\" align=\"center\"><tr><td>STEPS: </td></tr>"
                    + "<tr><td>1. Login to Nykaa.com with credentials: testlive@nykaa.com / Password@1</td></tr>"
                    + "<tr><td>2. Navigate to any category (HAIR) and add a product to cart.</td></tr>"
                    + "<tr><td>3. If item price is less than Rs. 500 then again add a product till cart value becomes > 500 </td></tr>"
                    + "<tr><td>4. Apply coupon testqa987 on sliding cart and click checkout button. </td></tr>"
                    + "<tr><td>5. Enter or select Shipping address and place COD order.</td></tr>"
                    + "<tr><td>6. Navigate to My Orders and cancel the last order (order placed in above step).</td></tr>"
                    + "</table></body></html>" + System.getProperty("BUILD_NUMBER");

            contentBuilder.append(
                    "FailedTests :  " + FailedTestCount + " Out of " + TotalTestCases + " " + System.lineSeparator()
                            + "PassedTest Count :  " + PassedTestCount + "Out Of " + TotalTestCases + "");
            List<ITestResult> Failedtestcases = tla.getFailedTests();
            StringBuilder strblder = new StringBuilder();
            for (ITestResult failtest : Failedtestcases) {
                String strfailtest = failtest.getMethod().getMethodName();
                strblder.append("<tr><td>" + strfailtest.replace("_", " ")
                        + "</td><td><b style='color:red;'>Fail</b></td></tr>");

            }
            for (ITestResult failtest : skippedTescases) {
                String strfailtest = failtest.getMethod().getMethodName();
                strblder.append("<tr><td>" + strfailtest.replace("_", " ")
                        + "</td><td><b style='color:red;'>Fail</b></td></tr>");

            }
            List<ITestResult> PassedTestcases = tla.getPassedTests();

            for (ITestResult passtest : PassedTestcases) {
                String strpasstest = passtest.getMethod().getMethodName();
                System.out.println(strpasstest);
                strblder.append("<tr><td>" + strpasstest.replace("_", " ")
                        + "</td><td><b style='color:green;'>Pass</b></td></tr>");

            }
            String str = "";
            //String str = "<b style='color:red;'> <h2>Failure Reason: </h2>"+ failureReasonMessage +"</b>";
            if (!Framework.webPageLoadTestResultUrl.equals("")) {
                str = "<html><body><table  cellspacing=\"4\" cellpadding=\"4\" align=\"centre\"><tr><td><b style='color:red;'>Failure reason: </b></td><td><b style='color:red;'>" + failureReasonMessage + "</b></td><td><a href=" + Framework.webPageLoadTestResultUrl + ">Check Page Load Report here</a></td></tr></table></body></html>";
            } else {
                str = "<html><body><table  cellspacing=\"4\" cellpadding=\"4\" align=\"centre\"><tr><td><b style='color:red;'>Failure reason: </b></td><td><b style='color:red;'>" + failureReasonMessage + "</b></td></tr></table></body></html>";
            }
            //String str2 = "<html><body><table  cellspacing=\"4\" cellpadding=\"4\" border=\"1\" align=\"center\"><tr style=\"background-color:gray;\"><td><b style='color:white;'>Test Cases</b></td><td><b style='color:white;'>Status</b></td></tr>"
            //		+ strblder.toString() + "</table></body></html>";
            contentBuilder.append(str);
            //contentBuilder.append(str2);
            // contentBuilder.append("PassedTest Count : "+PassedTestCount+"\r\n
            // ");
            String content2 = contentBuilder.toString();
            // messageBodyPart3.setText(content2);
            messageBodyPart1.setText(Header, "utf-8", "html");
            messageBodyPart2.setText(str, "utf-8", "html");
            //messageBodyPart3.setText(str2, "utf-8", "html");
            messageBodyPart3.setText(MainBody, "utf-8", "html");
            if (isFailed) {
                messageBodyPart4.setText("Note : Please find Attached ScreenShot(s) and report for More information. Download and open the report in browser.");
            } else {
                messageBodyPart4.setText(" ");
            }
            // multipart.addBodyPart(messageBodyPart3);
            String content = contentBuilder.toString();
            // message.setContent(content, "text/html");
            // messageBodyPart.setText(content1, "utf-8", "html"); //uncomment
            // open this when done
            // multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);
            multipart.addBodyPart(messageBodyPart3);
            multipart.addBodyPart(messageBodyPart4);
            message.setContent(multipart);
            // message.setContent(content1,"text/html");
            // message.setText(content1, "utf-8", "html");

            // String body = "Sample text";
            // message.setContent(source.toString(),"text/html");
            transport.connect();
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void sendMail(List<String> output) {
        // TODO Auto-generated method stub
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        Session mailSession = null;

        mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("dibragede2@gmail.com", "monsoon098");
            }
        });

        try {

            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);

            message.setSubject("Automation Smoke Suite");
            message.setFrom(new InternetAddress("qa@Nykaa.com"));
            // String []to = new String[]{"nevil.panchal@nykaa.com"};
            String[] to = new String[]{"@nykaa.com"};
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[0]));

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String filepath = "test-output/Command line suite/Command line test.html";
            // String filepath = "test-output/index.html";
            String fileName = "Smokesuite";
            DataSource source = new FileDataSource(new File(filepath).getAbsolutePath());
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            int i = 1;
            StringBuilder stringBuilder = new StringBuilder();
            for (String reporterOutput : output) {

                if (reporterOutput.contains("Failled"))
                    stringBuilder.append("<h5 style=\"color:#FF0000\">" + i + ". " + reporterOutput + "</h5>" + "<Br>");
                else
                    stringBuilder.append(i + ". " + reporterOutput + "<Br>");
                i++;
            }
            message.setText(stringBuilder.toString(), "utf-8", "html");
            Multipart multipart;
            // multipart.addBodyPart(messageBodyPart);

            // String body = "Sample text";
            // message.setContent(source.toString(),"text/html");
            transport.connect();
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (Exception exception) {

        }

    }
}
