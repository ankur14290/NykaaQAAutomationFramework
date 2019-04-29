package FrameWorkNykaa;


/**
 * @author nevil
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import com.google.gdata.data.appsforyourdomain.migration.Rfc822Msg.Encoding;

public class GatewaySMS {
    public static void main(String[] args) {
        sendSms();
    }

    public static void sendSms() {


        String URL = "" + System.getenv("BUILD_URL") + "testngreports/PagesNykaa/";
        int pendingItems = 1;
        String s = String.format("Live: Automation test cases are failing, please find below link %s", URL);

        send("8097409069,9890995489,8826030870", s);
    }

    public static void send(String mobileNo, String message) {


        try {
            String data = "";
            data += "method=sendMessage";
            data += "&userid=2000107700"; // your loginId
            data += "&password=" + URLEncoder.encode("nykaa2015_fsn", "UTF-8"); // your password
            data += "&msg=" + URLEncoder.encode(message, "UTF-8");
            data += "&send_to=" + URLEncoder.encode(mobileNo, "UTF-8"); // a valid 10 digit phone no.
            data += "&v=1.1";
            data += "&msg_type=TEXT"; // Can be "FLASH" or "UNICODE_TEXT" or �BINARY�
            data += "&auth_scheme=PLAIN";

            URL url = new URL("http://enterprise.smsgupshup.com/GatewayAPI/rest?" + data);
            System.out.println("http://enterprise.smsgupshup.com/GatewayAPI/rest?" + data);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.connect();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            System.out.println(buffer.toString());
            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
