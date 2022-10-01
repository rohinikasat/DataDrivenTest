package ApachePOITests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDriven_HardCodedTest {
	
	WebDriver driver;
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/Users/rishimalani/BrowserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	@DataProvider(name = "LoginData")
	public String[][] getData() {
		String loginData[][]= {
				
				{"admin@yourstore.com",	"admin",	"Valid"},
				{"admin@yourstore.com",	"admn",	"Invalid"},
				{"adm@yourstore.com",	"admin",	"Invalid"},
				{"adm@yourstore.com",	"adm",	"Invalid"}
		};
		
		return loginData;
	}

	@Test(dataProvider = "LoginData")
	public void loginTest(String user, String pwd, String exp) {
		System.out.println(user + pwd + exp);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
