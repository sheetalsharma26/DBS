package dbs.stepDefination;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import dbs.utility.*;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePageStepDef {
	WebDriver driver;
	WebDriverWait wait;
//	public ExcelDataProvider excelDataProvider;

	@Given("^User launches DBS url$")
	public void User_launches_DBS_url() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.dbs.com.sg/index/default.page");
		driver.manage().window().maximize();
	}

	@Given("^clicks on Learn More$")
	public void clicks_on_Learn_More() throws Throwable {
		wait = new WebDriverWait(driver, 30);
//		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@class='btn btn-primary']"))));
		// driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();

	}

	@Given("^Scrolls Down$")
	public void Scrolls_Down() throws Throwable {
		driver.findElement(By.linkText("Learn More")).click();
	}

	@When("^User clicks on Singapore on the left panel$")
	public void User_clicks_on_Singapore_on_the_left_panel() throws Throwable {
		driver.findElement(By.linkText("Singapore")).click();
	}

	@Then("^A table is displayed$")
	public void A_table_is_displayed() throws Throwable {
		// ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		List<WebElement> tabRow = driver.findElements(By.xpath("//table//tr"));
		List<WebElement> tabCol = driver.findElements(By.xpath("//table//td"));

		System.out.println("row............." + tabRow.size());
		System.out.println("col............." + tabCol.size());
		File src = new File("D:\\TestData.xlsx");
		System.out.println("before.............");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook currentWorkbook = new XSSFWorkbook(fis);
		System.out.println("before......***.......");
		XSSFSheet sheet = currentWorkbook.getSheet("LoginDetails");


		for (int i = 1; i < tabRow.size()+1; i++) {
			List<WebElement> r = driver.findElements(By.xpath("(//table//tr)[" + i + "]"));//(//table//tr)[2]/td
			System.out.println("col no= "+ i + driver.findElements(By.xpath("(//table//tr)[" + i + "]/td")).size());
			System.out.println("row no=" + r.size());
			XSSFRow row = sheet.createRow(i);
			for (int j = 1; j < 5; j++) {
				String s = driver.findElement(By.xpath("((//table//tr)[" + i + "]/td)[" + j + "]")).getText();
				System.out.println("before.String..........." + s);
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(s);
//				cell.setCellValue(tabCol.get(j).getText());

			}
		}
		FileOutputStream fos = new FileOutputStream(src);
		currentWorkbook.write(fos);
		fos.close();
		System.out.println("END OF WRITING DATA IN EXCEL");
		driver.findElement(By.linkText("About")).click();
		driver.findElement(By.linkText("Who We Are")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Our Awards & Accolades")).click();
		List<WebElement> awards= driver.findElements(By.xpath("//*[@class='mBot-20 col-md-4']"));
		System.out.println("ENDL" + awards.size());
	}

	@Given("^user navigate to About in the header tabs$")
	public void user_navigate_to_About_in_the_header_tabs() throws Throwable {
		driver.findElement(By.linkText("About")).click();
	}

	@When("^user navigate to Who we are > Our Awards & Accolades$")
	public void user_navigate_to_Who_we_are_Our_Awards_Accolades() throws Throwable {
		
	}

	@Then("^the total number of awards displayed on the page is (\\d+)$")
	public void the_total_number_of_awards_displayed_on_the_page_is(int arg1) throws Throwable {

	}
}
