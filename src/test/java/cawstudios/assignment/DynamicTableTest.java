package cawstudios.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import java.time.Duration;
import org.json.JSONArray;  

public class DynamicTableTest  {
	
	@Test
	public void sampleTest() {

		WebDriver driver = new ChromeDriver();

		driver.get("https://testpages.eviltester.com/styled/tag/dynamic-table.html");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));



		driver.findElement(By.xpath("//summary")).click();

		driver.findElement(By.id("jsondata")).clear();

		String names[] = {"Bob","George","Sara","Conor","Jennifer"};

		int[] ages = {20,42,42,40,42};

		String[] genders = {"male","male","female","male","female"};

		String inputjson = "[";

		for(int i = 0;i<names.length;i++)

		{

			inputjson += "{\"name\":\"" +names[i] + "\",\"age\":" + ages[i] + ",\"gender\":\"" + genders[i] + "\"},";

		}

		inputjson = inputjson.substring(0,inputjson.length()-1) + "]";

		JSONArray array = new JSONArray(inputjson); 

		System.out.println(inputjson);



		driver.findElement(By.id("jsondata")).sendKeys(inputjson);

		driver.findElement(By.id("refreshtable")).click();

		for(int j = 0;j<names.length;j++)

		{

			String name = driver.findElement(By.xpath("//td[text()='" + names[j] + "']")).getText();
			String age = driver.findElement(By.xpath("//td[text()='" + ages[j] + "']")).getText();
			String gender = driver.findElement(By.xpath("//td[text()='" + genders[j] + "']")).getText();

			assert array.getJSONObject(j).getString("name").equals(name);
			assert array.getJSONObject(j).optString("age").equals(age);
			assert array.getJSONObject(j).getString("gender").equals(gender);

		}

	}

}