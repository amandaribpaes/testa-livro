import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestaLivros {
  private WebDriver driver;
  
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  

  @Before
  public void setUp() throws Exception {	 
	 driver = new ChromeDriver();
       
  }

  @Test
  public void TestaLivros() throws Exception {
	  	driver.get("https://www.submarino.com.br/");
	    driver.findElement(By.id("h_search-input")).click();
	    driver.findElement(By.id("h_search-input")).clear();
	    driver.findElement(By.id("h_search-input")).sendKeys("livros");
	    driver.findElement(By.id("h_search-btn")).click();
	    
	    //selecionando o primeiro livro da pagina
	    driver.findElement(By.xpath("//img[@alt='Livro - Dois a Dois']")).click();
	    
	    //gravando o nome do autor do livro no site submarino
	    String LivroSub = driver.findElement(By.xpath("//div[@id='content']/div/main/div[5]/div/section/section[2]/div/table/tbody/tr[3]/td[2]")).getText();
	    
	    //gravando o ISBN do livro 
	    String ISBNSub = driver.findElement(By.xpath("//div[@id='content']/div/main/div[5]/div/section/section[2]/div/table/tbody/tr[5]/td[2]")).getText();
	    
	    //acessando o site americanas para buscar o livro
	    driver.get("https://www.americanas.com.br/");
	    driver.findElement(By.id("h_search-input")).click();
	    driver.findElement(By.id("h_search-input")).clear();
	    
	    //usando a variavel ISBNSub para encontrar o livro
	    driver.findElement(By.id("h_search-input")).sendKeys(ISBNSub);
	    driver.findElement(By.id("h_search-input")).sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("//img[@alt='Livro - Dois a Dois']")).click();
	    
	    //gravando o nome do autor do livro no site americanas
	    String LivroAme = driver.findElement(By.xpath("//div[@id='content']/div/main/div[5]/div/section/section[2]/div/table/tbody/tr[3]/td[2]")).getText();
	   
	    //acessando o site da saraiva 
	    driver.get("https://www.saraiva.com.br/");
	    driver.findElement(By.id("q")).clear();
	    
	    //usando a variavel ISBNSub para encontrar o livro
	    driver.findElement(By.id("q")).sendKeys(ISBNSub);
	    driver.findElement(By.id("search_mini_form")).submit();
	    driver.findElement(By.xpath("//img[@alt='//www.saraiva.com.br/dois-a-dois-9430059.html']")).click();
	    driver.findElement(By.xpath("//article[@id='product-view']/div")).click();
	    //gravando o nome do autor do livro no site da saraiva
	    String LivroSar = driver.findElement(By.xpath("//form[@id='product_addtocart_form']/section[2]/section/h2")).getText();
	  
	    //primeira comparação do nome do autor no site Submarino e Americanas
	    if(LivroSub.equals(LivroAme)) {  
	    	System.out.println("O nome do autor do livro é igual nos sites do Submarino e Americanas.");
	    }
	    else {
	    	System.out.println("O nome do autor do livro é diferente nos sites do Submarino e Americanas.");
	    }
	    
	    //segunda verificação do nome do autor no site submarino e saraiva
	    if(LivroSub.equals(LivroSar)) {
	    	System.out.println("O nome do autor do livro é igual nos sites do Submarino e Saraiva.");
	    }
	    else {
	    	System.out.println("O nome do autor do livro é diferente nos sites do Submarino e Saraiva.");
	    }
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	}
