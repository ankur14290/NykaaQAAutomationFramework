
package FrameWorkNykaa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import TestCaseSuite.Z_SanitySuite_New;
import android.R.string;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;


/**
 * @author nevil
 */
public class DataBaseActivity {

	Logger log = Logger.getLogger(DataBaseActivity.class.getName());
	static Connection conn = null;
	static List<XSSFSheet> listOfshits;
	GoogleSheets GWorkSheet;


	/**
	 * dumps Excel Data into Derby database which is virtually exist (EmbededDatabase)
	 * Sheets as Database table
	 *
	 * @throws Throwable
	 */


	public static void dumpDataIntoDatabase() throws Throwable {

		StartConnection();

		listOfshits = new ArrayList<XSSFSheet>();
		//	String Path = "C:/Users/nevil/Desktop/ExcelWorkbook";
		String Path = new File("Files").getAbsolutePath();

		File[] files = getAllExcelsFromFolder(Path);

		for (File file : files) {
			FileInputStream fileInput = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
			int sheetNumber = workbook.getNumberOfSheets();
			for (int x = 0; x < sheetNumber; x++) {
				XSSFSheet sheet = workbook.getSheetAt(x);
				listOfshits.add(sheet);
			}
			for (XSSFSheet sheet1 : listOfshits) {
				String Tablename = sheet1.getSheetName();
				StringBuilder HeaderCellValue = new StringBuilder();
				int physicalrows = sheet1.getPhysicalNumberOfRows();
				XSSFRow header = sheet1.getRow(0);
				int Physicalcolumns = header.getPhysicalNumberOfCells();
				HeaderCellValue = getAppendedHeaderData(HeaderCellValue, header);
				creatHeader(HeaderCellValue, Tablename);
				int NoOfPhysicalRow = sheet1.getPhysicalNumberOfRows();

				for (int x1 = 1; x1 < NoOfPhysicalRow; x1++) {
					XSSFRow Row = sheet1.getRow(x1);
					StringBuilder RowCellValue = new StringBuilder();
					RowCellValue = getAppendedRowData(RowCellValue, Row, Physicalcolumns);
					insertDataIntoTable(RowCellValue, Tablename);

				}

			}

			// create table "+sheet+" ();

		}
	}


	public static void StartConnection() throws SQLException {
		try {
			conn = DriverManager.getConnection("jdbc:derby:testmdb1;create=true", "test", "test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getCause().getMessage());
			if (e.getCause().getMessage().equalsIgnoreCase("Failed to start database"))
				conn = DriverManager.getConnection("jdbc:derby:;shutdown=true");//closing database
			conn = DriverManager.getConnection("jdbc:derby:testmdb1;create=true", "test", "test");//starting database
		}
	}


	List<XSSFSheet> getSheetsFromExcel() throws IOException {

		listOfshits = new ArrayList<XSSFSheet>();
		String Path = new File("Files").getAbsolutePath();

		File[] files = getAllExcelsFromFolder(Path);

		for (File file : files) {
			FileInputStream fileInput = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
			int sheetNumber = workbook.getNumberOfSheets();
			for (int x = 0; x < sheetNumber; x++) {
				XSSFSheet sheet = workbook.getSheetAt(x);
				listOfshits.add(sheet);
			}
		}
		return listOfshits;
	}


	/**
	 * Closing Database connections
	 *
	 * @throws Throwable
	 */
	public void afterTest() throws Throwable {
		for (XSSFSheet sheet1 : listOfshits) {
			String sheetname = sheet1.getSheetName();
			dropTable(sheetname);
		}
		closeConnection();
	}

	private static StringBuilder getAppendedHeaderData(StringBuilder headerNameOfsheet, XSSFRow header) {
		int NoOfcells = header.getPhysicalNumberOfCells();
		headerNameOfsheet = headerNameOfsheet.append(header.getCell(0).getStringCellValue() + " varchar(4000)");
		for (int x = 1; x < NoOfcells; x++) {
			String headerName = "" + header.getCell(x).getStringCellValue() + " varchar(4000)";
			headerNameOfsheet = headerNameOfsheet.append("," + headerName);
		}
		return headerNameOfsheet;
	}

	private static String getAppendedGHeaderData(StringBuilder headerNameOfsheet, ListEntry headers) {
		Set<String> tags = headers.getCustomElements().getTags();

		for (String tag : tags) {
			String headerName = "" + tag + " varchar(30000)";
			headerNameOfsheet = headerNameOfsheet.append(headerName + ",");
		}
		return headerNameOfsheet.substring(0, headerNameOfsheet.length() - 1);
	}

	private static StringBuilder getAppendedRowData(StringBuilder rowNameOfsheet, XSSFRow row, int physicalcolumns) {

		DataFormatter formatter = new DataFormatter();
		rowNameOfsheet = rowNameOfsheet.append("'" + formatter.formatCellValue(row.getCell(0)) + "'");
		for (int x = 1; x < physicalcolumns; x++) {
			String rowName = formatter.formatCellValue(row.getCell(x));

			rowNameOfsheet = rowNameOfsheet.append(",'" + rowName + "'");

		}
		return rowNameOfsheet;
	}

	private static String getAppendedGRowData(StringBuilder rowNameOfsheet, ListEntry row) {
		rowNameOfsheet = new StringBuilder();
		for (String tag : row.getCustomElements().getTags()) {
			rowNameOfsheet = rowNameOfsheet.append("'" + row.getCustomElements().getValue(tag) + "',");
		}


		return rowNameOfsheet.substring(0, rowNameOfsheet.length() - 1);
	}


	private static void creatHeader(StringBuilder headerCellValue, String Tablename) throws SQLException {
		// TODO Auto-generated method stub
		String createSQL = "create table " + Tablename + " (" + headerCellValue + ")";
		System.out.println(createSQL);

		try {
			conn.createStatement().execute(createSQL);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private static void insertDataIntoTable(StringBuilder rowCellValue, String Tablename) throws SQLException {

		//String x = rowCellValue.toString().replace("'", "");
		String insertTabledata = "insert into " + Tablename + " values(" + rowCellValue + ")";
		System.out.println(insertTabledata);
		conn.createStatement().execute(insertTabledata);

	}

	public static void dropTable(String tableName) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = "drop table " + tableName + "";
		try {
			statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	private static void closeConnection() throws SQLException {
		// TODO Auto-generated method stub

		try {
			// DriverManager.getConnection("jdbc:derby:testdb1;drop=true");
			conn = DriverManager.getConnection("jdbc:derby:;shutdown=true");

		} catch (SQLException ex) {

			if (((ex.getErrorCode() == 50000) && ("XJ015".equals(ex.getSQLState())))) {
				System.out.println("Derby shut down normally");
			} else {
				System.err.println("Derby did not shut down normally");
				System.err.println(ex.getMessage());
			}

		}
	}

	private static File[] getAllExcelsFromFolder(String dirName) {

		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".xlsx");
			}
		});

	}

	public void dropTableFromGsheet() throws IOException, URISyntaxException, Throwable {
		GWorkSheet = new GoogleSheets();
		SpreadsheetService service = GWorkSheet.getService();
		SpreadsheetEntry GWorkbook = GWorkSheet.getSpreadSheet("Automation Data");
		List<WorksheetEntry> Sheets = GWorkbook.getWorksheets();
		for (WorksheetEntry sheet : Sheets) {
			String Tablename = sheet.getTitle().getPlainText();
			dropTable(Tablename);
		}
		SpreadsheetEntry TWorkbook = GWorkSheet.getSpreadSheet("Object_Repository_For_Automation");
		List<WorksheetEntry> sheets = TWorkbook.getWorksheets();
		for (WorksheetEntry sheet : sheets) {
			String Tablename = sheet.getTitle().getPlainText();
			dropTable(Tablename);
		}
	}


	@Test
	public void createTableFromGSpreadsheets() throws Throwable {
		try {
			StartConnection();

			GWorkSheet = new GoogleSheets();
			SpreadsheetService service = GWorkSheet.getService();
			SpreadsheetEntry GWorkbook = GWorkSheet.getSpreadSheet("Automation Data");
			List<WorksheetEntry> Sheets = GWorkbook.getWorksheets();

			for (WorksheetEntry sheet : Sheets) {
				String Tablename = sheet.getTitle().getPlainText();
				System.out.println(Tablename);
				URL SheetlistFeedUrl = sheet.getListFeedUrl();
				ListFeed RowList = service.getFeed(SheetlistFeedUrl, ListFeed.class);
				ListEntry headers = RowList.getEntries().get(0);
				StringBuilder HeaderCellValue = new StringBuilder();
				String headerrow = getAppendedGHeaderData(HeaderCellValue, headers);
				StringBuilder headerrows = new StringBuilder(headerrow);
				creatHeader(headerrows, Tablename);
				StringBuilder RowCellsheet = new StringBuilder();

				for (ListEntry row : RowList.getEntries()) {

					String Row = getAppendedGRowData(RowCellsheet, row);

					StringBuilder rowValue = new StringBuilder(Row);
					insertDataIntoTable(rowValue, Tablename);
				}
			}

			SpreadsheetEntry TWorkbook = GWorkSheet.getSpreadSheet("Object_Repository_For_Automation");
			List<WorksheetEntry> locator = TWorkbook.getWorksheets();

			boolean flag = true;
			for (WorksheetEntry sheet : locator) {
				String Tablename ="ObjectRepository";
				//                String Tablename = sheet.getTitle().getPlainText();

				URL SheetlistFeedUrl = sheet.getListFeedUrl();
				ListFeed RowList = service.getFeed(SheetlistFeedUrl, ListFeed.class);

				/** execute this method only once --> that means if there are multiple sheets in ObjectRepository workbook
				 * then no need to create table for each sheet, just dump all sheets in ObjectRepository table */
				if(flag) {
					ListEntry headers = RowList.getEntries().get(0);
					StringBuilder HeaderCellValue = new StringBuilder();
					String headerrow = getAppendedGHeaderData(HeaderCellValue, headers);
					StringBuilder headerrows = new StringBuilder(headerrow);
					creatHeader(headerrows, Tablename);

					flag = false;
				}

				StringBuilder RowCellsheet = new StringBuilder();

				for (ListEntry row : RowList.getEntries()) {

					String Row = getAppendedGRowData(RowCellsheet, row);

					StringBuilder rowValue = new StringBuilder(Row);
					insertDataIntoTable(rowValue, Tablename);

				}

			}

			log.info("created table for object locators ");

		} catch (Exception e) {
			log.info(e);
		}
	}


}
