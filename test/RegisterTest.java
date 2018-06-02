/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Shalitha Suranga
 */

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class RegisterTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

@Test(dataProvider = "data")
  public void testLogin(String firstname, String lastname, String regNo, String telephone, String nic, String experience, String email, String password, String cpassword, String status) throws Exception {
    driver.get("http://localhost/sqa/");
    driver.findElement(By.linkText("Register")).click();
    driver.findElement(By.id("inputEmail")).click();
    driver.findElement(By.id("inputEmail")).clear();
    driver.findElement(By.id("inputEmail")).sendKeys(firstname);
    driver.findElement(By.name("lastname")).click();
    driver.findElement(By.name("lastname")).clear();
    driver.findElement(By.name("lastname")).sendKeys(lastname);
    driver.findElement(By.name("regNo")).click();
    driver.findElement(By.name("regNo")).clear();
    driver.findElement(By.name("regNo")).sendKeys(regNo);
    driver.findElement(By.name("tp")).click();
    driver.findElement(By.name("tp")).clear();
    driver.findElement(By.name("tp")).sendKeys(telephone);
    driver.findElement(By.name("nic")).click();
    driver.findElement(By.name("nic")).clear();
    driver.findElement(By.name("nic")).sendKeys(nic);
    driver.findElement(By.name("pstExp")).click();
    driver.findElement(By.name("pstExp")).clear();
    driver.findElement(By.name("pstExp")).sendKeys(experience);
    driver.findElement(By.xpath("(//input[@id='inputEmail'])[7]")).click();
    driver.findElement(By.xpath("(//input[@id='inputEmail'])[7]")).clear();
    driver.findElement(By.xpath("(//input[@id='inputEmail'])[7]")).sendKeys(email);
    driver.findElement(By.id("inputConfirmPws")).click();
    driver.findElement(By.id("inputConfirmPws")).clear();
    driver.findElement(By.id("inputConfirmPws")).sendKeys(password);
    driver.findElement(By.name("confpwd")).click();
    driver.findElement(By.name("confpwd")).clear();
    driver.findElement(By.name("confpwd")).sendKeys(cpassword);
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    
    System.out.println(driver.getCurrentUrl());
    String outcome = driver.getCurrentUrl().equals("http://localhost/sqa/index.php?varify=try_login") ? "success" : "fail";
    System.out.println(outcome + " --------------------- " + status);
    Assert.assertTrue(outcome.equals(status));
  }

  @AfterClass(alwaysRun = true)
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
  
  
  @DataProvider(name = "data")
  String[][] data () throws IOException {
      String[][] a = new String[1][];
      try (BufferedReader br = new BufferedReader(new FileReader(new File("register.csv")))) {
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
           a[i++] = (line.split(","));
           
        }
      }
      return a;
  }
}

