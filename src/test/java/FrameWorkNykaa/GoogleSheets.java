package FrameWorkNykaa;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;

public class GoogleSheets {
    private static volatile SpreadsheetService service = null;

    public GoogleSheets() throws Throwable, IOException, URISyntaxException {
        if (service == null) {
            service = getService();
        }

    }

    private static final String Response = null;
    // Generate a service account and P12 key:
    // https://developers.google.com/identity/protocols/OAuth2ServiceAccount
    private final String CLIENT_ID = "automationdata@automation-193805.iam.gserviceaccount.com";
    // Add requested scopes.
    private final List<String> SCOPES = Arrays
            .asList("https://spreadsheets.google.com/feeds");
    // The name of the p12 file you created when obtaining the service account
    private final String P12FILE = "./Automation-c78cfc6f1fe7.p12";

    @Test
    public static SpreadsheetEntry getSpreadSheet(String SpreadSheetName) throws GeneralSecurityException,
            IOException, ServiceException, URISyntaxException {
        CellFeed cellFeed = null;
        SpreadsheetEntry spreadSheet = null;
        URL SPREADSHEET_FEED_URL = null;

        try {
            SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
            //SPREADSHEET_FEED_URL = new URL("https://sheets.googleapis.com/v4/spreadsheets/1JwcVW5AW4XSM7zwqypx6PqxjMa3fO--QgUKUtG3nMsM");
            //://docs.google.com/spreadsheets/d
            //https://spreadsheets.google.com/feeds/worksheets/1hLTDrzuVz9ls0OPnxCIa3RLofF35W9xOl_89A2begl4/private/basic
            SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,
                    SpreadsheetFeed.class);
            List<SpreadsheetEntry> spreadsheets = feed.getEntries();


            for (SpreadsheetEntry GspreadSheet : spreadsheets) {
                String spreadsheetName = GspreadSheet.getTitle().getPlainText().trim();
                if (spreadsheetName.equalsIgnoreCase(SpreadSheetName)) {
                    spreadSheet = GspreadSheet;
                    break;
                }
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

        return spreadSheet;
    }


    public SpreadsheetService getService() throws GeneralSecurityException, IOException, URISyntaxException {
        SpreadsheetService service;
        service = new SpreadsheetService(
                "google-spreadsheet");
        service.setProtocolVersion(SpreadsheetService.Versions.V3);
        GoogleCredential credential = getCredentials();
        service.setOAuth2Credentials(credential);
        return service;
    }


    public String getFieldValue(String CaseID, SpreadsheetEntry spreadsheet, String sheetName, String FieldName) throws Throwable, Throwable {
        String Value = null;
        URL listFeedUrl = getFeedFromSheet(spreadsheet, sheetName);
        ListFeed listOfRow = service.getFeed(listFeedUrl, ListFeed.class);

        for (ListEntry row : listOfRow.getEntries()) {
            if (row.getCustomElements().getValue("TestCaseID") != null) {
                if (row.getCustomElements().getValue("TestCaseID").trim().equalsIgnoreCase(CaseID)) {
                    //System.out.println("Result updated in gooogle sheet");
                    Value = row.getCustomElements().getValue(FieldName);

                    break;
                }
            }

        }

        return Value;
    }

    public void updateResultField(String CaseID, SpreadsheetEntry spreadsheet, String sheetName, String Status, String resultFieldName) throws Throwable, IOException, URISyntaxException {

        URL listFeedUrl = getFeedFromSheet(spreadsheet, sheetName);
        ListFeed listOfRow = service.getFeed(listFeedUrl, ListFeed.class);

        for (ListEntry row : listOfRow.getEntries()) {
            if (row.getCustomElements().getValue("TestCaseID") != null) {
                if (row.getCustomElements().getValue("TestCaseID").trim().equalsIgnoreCase(CaseID)) {
                    System.out.println("Result updated in gooogle sheet");
                    row.getCustomElements().setValueLocal(resultFieldName, Status);

                    //row.getCustomElements().setValueLocal(resultFieldName, "=HYPERLINK(\"http://stackoverflow.com\",\"SO label\")");
                    row.update();
                    break;
                }
            }

        }

    }


    public URL getFeedFromSheet(SpreadsheetEntry spreadsheet, String Sheetname) throws IOException, ServiceException {
        WorksheetEntry sheet = null;
        for (WorksheetEntry sheetEntry : spreadsheet.getWorksheets()) {
            if (sheetEntry.getTitle().getPlainText().equalsIgnoreCase(Sheetname)) {
                sheet = sheetEntry;
            }

        }

        return sheet.getListFeedUrl();


    }

    public void clearField(SpreadsheetEntry spreadsheet, String SheetName, String FieldName) throws IOException, Throwable {
        URL listFeedUrl = getFeedFromSheet(spreadsheet, SheetName);
        ListFeed listOfRow = service.getFeed(listFeedUrl, ListFeed.class);

        for (ListEntry row : listOfRow.getEntries()) {
            if (row.getCustomElements().getValue("TestCaseID") != null && row.getCustomElements().getValue(FieldName) != null) {
                row.getCustomElements().setValueLocal(FieldName, "");
                row.update();

            }


        }
    }

    public String getExecutionalTestcases(SpreadsheetEntry spreadsheet, String Sheetname) throws IOException, ServiceException {
        String Testcases;
        URL listFeedUrl = getFeedFromSheet(spreadsheet, Sheetname);
        ListFeed listOfRow = service.getFeed(listFeedUrl, ListFeed.class);
        List<String> tescaseidslist = new ArrayList<String>();
        for (ListEntry row : listOfRow.getEntries()) {
            if (row.getCustomElements().getValue("Executable") != null) {
                if (row.getCustomElements().getValue("Executable").equalsIgnoreCase("yes")) {
                    String testcaseID = row.getCustomElements().getValue("ParentTestCaseID");
                    tescaseidslist.add(testcaseID);
                }
            }
        }
        HashSet<String> hs = new HashSet();
        hs.addAll(tescaseidslist);
        tescaseidslist.clear();
        tescaseidslist.addAll(hs);
        StringBuffer TestcaseIDs = new StringBuffer();
        for (String testcaseid : tescaseidslist) {
            TestcaseIDs.append(testcaseid + ",");
        }

        String testacaseId = TestcaseIDs.toString();

        testacaseId = testacaseId.substring(0, testacaseId.length() - 1);
        System.out.println(testacaseId);
        return testacaseId;


    }


    public void updateRemarkField(String CaseID, SpreadsheetEntry spreadsheet, String SheetName, String Status) throws Throwable, IOException, URISyntaxException {

        URL listFeedUrl = getFeedFromSheet(spreadsheet, SheetName);
        ListFeed listOfRow = service.getFeed(listFeedUrl, ListFeed.class);

        for (ListEntry row : listOfRow.getEntries()) {

            if (row.getCustomElements().getValue("TestCaseID") != null) {
                if (row.getCustomElements().getValue("TestCaseID").equalsIgnoreCase(CaseID)) {
                    row.getCustomElements().setValueLocal("Remark", Status);
                    row.update();
                    break;
                }
            }

        }

    }

    //updateExcelValue(SpreadsheetEntry Spreadsheet,String SheetNAme , String UpdateValue)

    /*
            if (spreadsheets.size() == 0) {
                // // TODO: There were no spreadsheets, act accordingly.
            }
            //
            //SpreadsheetEntry spreadsheet = spreadsheets.get(0);

            SpreadsheetEntry spreadsheet = spreadsheets.get(1);
            System.out.println(spreadsheet.getTitle().getPlainText());

            // Get the first worksheet of the first spreadsheet.
            // TODO: Choose a worksheet more intelligently based on your
            // app's needs.
            WorksheetFeed worksheetFeed = service.getFeed(
                spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
            List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
            WorksheetEntry worksheet = worksheets.get(0);
            System.out.println(worksheet.getTitle().getPlainText());
             URL cellFeedUrl = new URL(worksheet.getCellFeedUrl().toString()
                        );





             //+ "?min-row=2&min-col=4&max-col=4"
                //     cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
             URL listFeedUrl = spreadsheet.getWorksheets().get(0).getListFeedUrl();

                // Print entries
                ListFeed wfeed = service.getFeed(listFeedUrl, ListFeed.class);

                for (ListEntry entry : wfeed.getEntries()) {
                    System.out.println("new row");
                    for (String tag : entry.getCustomElements().getTags()) {

                        System.out.println("     " + tag + ": "
                                + entry.getCustomElements().getValue(tag));
                        entry.getCustomElements().setValueLocal(tag, "pass");



                        String status = entry.getCustomElements().getValue(tag);
                        System.out.println(status);

                    }
                    entry.update();
                }

                     CellEntry cellEntry= new CellEntry (1, 1, "Pass");
                     cellFeed.insert (cellEntry);
                     cellEntry= new CellEntry (2, 1, "Fail");
                     cellFeed.insert (cellEntry);

                    for (CellEntry cell : cellFeed.getEntries()) {
                        // Print the cell's address in A1 notation
                        System.out.print(cell.getTitle().getPlainText() + "\t");
                        // Print the cell's address in R1C1 notation
                        System.out.print(cell.getId().substring(cell.getId().lastIndexOf('/') + 1) + "\t");
                        // Print the cell's formula or text value
                        System.out.print(cell.getCell().getInputValue() + "\t");
                        cell.changeInputValueLocal("");
                        cell.update();
                        // Print the cell's calculated value if the cell's value is numeric
                        // Prints empty string if cell's value is not numeric
                        System.out.print(cell.getCell().getNumericValue() + "\t");
                        // Print the cell's displayed value (useful if the cell has a formula)
                        System.out.println(cell.getCell().getValue() + "\t");
                      }
            //int rowcount = WorksheetEntry.getColCount();
             //System.out.println(rowcount);



 we = new WorksheetEntry();
            we.getRowCount();
            System.out.println(rowcount);

        //return cellFeed;



    }*/
    void writeInExcel(String url, String Response) {


    }

    private GoogleCredential getCredentials() throws GeneralSecurityException,
            IOException, URISyntaxException {
        JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        HttpTransport httpTransport = GoogleNetHttpTransport
                .newTrustedTransport();

        // URL fileUrl = this.getClass().getResource(P12FILE);
        URL fileUrl = getClass().getClassLoader().getResource(P12FILE);
        Reporter.log("verifying path");
        Reporter.log(fileUrl.toURI().getPath());
        System.out.println(fileUrl.toURI());

        // URL fileUrl = file:/C:/Users/nevil/Desktop/P12Folder/NeoProject-680b4448d369.p12;
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId(CLIENT_ID)
                .setServiceAccountPrivateKeyFromP12File(
                        new File(fileUrl.toURI()))
                .setServiceAccountScopes(SCOPES).build();

        return credential;
    }

}