import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Navigation3 {
   // public static RemoteWebDriver driver;
   static  WebDriver driver ;
    static  WebDriverWait wait;

    public static void main(String[] args) throws InterruptedException, IOException {
        //System.setProperty("webdriver.chrome.driver", "C:\\Utility\\BrowserDrivers\\chromedriver.exe");
        //System.setProperty("webdriver.gecko.driver", "path/to/geckodriver.exe");

        System.setProperty("webdriver.gecko.driver", "E:\\software\\firefox\\geckodriver.exe"); // 64bit version
        System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");

        /*DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.ANY);
        desiredCapabilities.setBrowserName("firefox");

        driver = new RemoteWebDriver(new URL("https://mfs.kfintech.com/mfs/FundoPaedia/NfundoPaedia.aspx?frm=iC#"), desiredCapabilities);
*/

        Path path = Paths.get("E:\\op\\invescoMF_5d_8_324.....txt");

//Use try-with-resource to get auto-closeable writer instance

        /*try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            writer.write("Hello World !!");
        }
*/


        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            FirefoxBinary firefoxBinary = new FirefoxBinary();
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(firefoxBinary);
            options.setHeadless(true);  // <-- headless set her

          driver = new FirefoxDriver(options);
          wait = new WebDriverWait(driver, 60);
          //WebDriver driver1 = new FirefoxDriver();
          //WebDriver driver =  new FirefoxDriver();
          driver.get("https://mfs.kfintech.com/mfs/FundoPaedia/NfundoPaedia.aspx?frm=iC#");

          Thread.sleep(5000);
          System.out.println("calling refresh");
          refreshPage();
          System.out.println("refresh page called");
        /*String dividendLink="/html/body/form/div[6]/div/table/tbody/tr/td[2]/table/tbody/tr/td/div[1]/table/tbody/tr/td[1]/div/table/tbody/tr[2]/td/div/div[2]/div/table/tbody/tr[3]/td/a";
        driver.findElement(By.xpath(dividendLink)).click();
        Thread.sleep(5000);
        System.out.println("searching ddlschema");
       // driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"form1\"]")));
        driver.switchTo().frame(0);

        Thread.sleep(3000);

        WebElement ele = driver.findElement(By.id("ddlSchemes"));
        if(ele !=null)
        {
            System.out.println("ele is not null");
            if(ele.isDisplayed()){
                System.out.println("isdaily"+ele.isEnabled());
            }
        }
*/
          // Select oSelect = new Select(driver.findElement(By.className("SelectCls")));
          Select oSelect = new Select(driver.findElement(By.xpath("//*[@id=\"ddlSchemes\"]")));

          List<WebElement> elementCount = oSelect.getOptions();
          List<String> ls = new ArrayList<String>();
          int iSize = elementCount.size();

          System.out.println("clicking on child item");

          for (int i = 127; i < iSize; i++) {
              //if (i>1)driver.switchTo().frame(0);
              // Select oSelect1 = new Select(driver.findElement(By.className("SelectCls")));
              //List <WebElement> elementCount1 = oSelect.getOptions();
              System.out.println("searching next dropdown value");

              //refreshing page
              System.out.println("before searching refreshing page");
              if (i > 1) {
                  driver.switchTo().parentFrame();
                  refreshPage();
              }
              ;
              //if(i==2){continue;} // temp
              //driver.navigate().refresh();

              String temp = "option:nth-child(" + (i + 1) + ")";
              // String sValue = elementCount.get(i).getText();
              //System.out.println("count is "+i+"and value is"+sValue);
              //ls.add(sValue);

              // driver.findElement(By.cssSelector(temp)).click();

              // adding explicit wait
              wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(temp)));
              // click on the compose button as soon as the "compose" button is visible
              driver.findElement(By.cssSelector(temp)).click();


              String dropDownName = driver.findElement(By.cssSelector(temp)).getText();
              System.out.println("count is " + i + " and value is" + dropDownName);


              if (dropDownName.contains("Daily") || dropDownName.toLowerCase().indexOf("daily") != -1 ? true : false) {
                  System.out.println("daily drop down to be ignore - " + dropDownName);
                  continue;
              }

/*
           System.out.println("testing pagination");
             //driver.findElement(By.linkText("+y+"));

            WebElement t11 =driver.findElement(By.xpath("//a[contains(@href, \"javascript:__doPostBack('gvDividendsDetails','Page$2')\")]"));

            JavascriptExecutor executor11 = (JavascriptExecutor)driver;
            executor11.executeScript("arguments[0].click();", t11);
            System.out.println("done");*/
            /*WebElement table =    driver.findElement(By.xpath("//*[@id=\"gvDividendsDetails\"]"));
                    List<WebElement> rowsList = table.findElements(By.tagName("tr"));*/

              List<WebElement> columnsList = null;
              int count = 0;

              for (int y = 0; ; y++) {


                  System.out.println("");
                  //if(i==0){break;}
                  // y is for tracking page for each dropdown ...
                /*if(y == 0 && i>1 && false) // 2nd dropdown , 1st page
                { Thread.sleep(5000);
                    System.out.println("navigating to next page");
                    //WebElement t1=   driver.findElement(By.xpath("/html/body/form/div[3]/table[2]/tbody/tr/td/div/table/tbody/tr[12]/td/table/tbody/tr/td["+y+"]/a"));
                    // WebElement t1=   driver.findElement(By.xpath("/html/body/form/div[3]/table[2]/tbody/tr/td/div/table/tbody/tr[12]/td/table/tbody/tr/td[2]/a"));
                    String pagination="//a[contains(@href, \"javascript:__doPostBack('gvDividendsDetails','Page$"+(y+1)+"')\")]";
                    System.out.println("pagination "+ pagination);
                    //String pagination="//*[@id=\"gvDividendsDetails\"]/tbody/tr[12]/td/table/tbody/tr/td["+(y+1)+"]/a";
                    String oldPagination="/html/body/form/div[3]/table[2]/tbody/tr/td/div/table/tbody/tr[12]/td/table/tbody/tr/td[" + (y+1) + "]/a";
                    String ltext=(y+1)+"";

                    try {
                        driver.findElement(By.xpath(pagination));
                      //  driver.findElement(By.linkText(ltext));

                    }catch (Exception e){
                        System.out.println("next page is not available for dropdown "+dropDownName+", page number "+(y+1));

                        break;

                    }
                    //WebElement t1 =driver.findElement(By.xpath("/html/body/form/div[3]/table[2]/tbody/tr/td/div/table/tbody/tr[12]/td/table/tbody/tr/td["+y+"]/a"));
                    WebElement t1 =driver.findElement(By.xpath(pagination));
                    //WebElement t1=         driver.findElement(By.linkText(ltext));

                    if(t1==null ){
                        System.out.println("page is N/A");break;}
                    if( t1 !=null && t1.isEnabled() && t1.isDisplayed()){
                        // Thread.sleep(3000);
                        //t1.click();
                        JavascriptExecutor executor = (JavascriptExecutor)driver;
                        executor.executeScript("arguments[0].click();", t1);
                        System.out.println("page number -"+t1.getText());
                        System.out.println("next page is available ");
                        //Thread.sleep(5000); not

                        WebElement table ;//=    driver.findElement(By.xpath("//*[@id=\"gvDividendsDetails\"]"));
                        try{

                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("TableCls")));
                            //driver.findElement(By.xpath(dividendLink)).click();
                            table =    driver.findElement(By.className("TableCls"));
                            System.out.println("table found ");
                        }
                        catch(Exception e)
                        {
                            System.out.println("table not fund ,trying again");
                            table =    driver.findElement(By.xpath("//*[@id=\"gvDividendsDetails\"]"));

                        }

                        List<WebElement> rowsList = table.findElements(By.tagName("tr"));
                        Thread.sleep(5000);
                        for (WebElement row : rowsList) {
                            System.out.println();
                            // WebElement row = rowsList.get()
                            count++;
                            if(rowsList.size()-2 < count){ count=0;
                                System.out.println("outer ,y>1"); break;}

                            columnsList = row.findElements(By.tagName("td"));
                            System.out.print(dropDownName+" ,");
                            //for(int m=0;m<columnsList.size()-1;i++){
                            ;

                            for (WebElement column : columnsList) {
                    *//*count++;
                    if(columnsList.size()-1 < count){ count=0;
                        System.out.println("inner"); break;}
                    else {                    System.out.print(column.getText() + ", ");
                    }*//*
                                System.out.print(column.getText() + ", ");

                                //  System.out.print(columnsList.get(m).getText() + ", ");

                            }
                            //  driver.findElement(By.xpath("//a[@href='javascript:__doPostBack('gvDividendsDetails','Page$2'')")).click();

                        } // finished printing table


                    }
                }*/
                  if (y > 0) {
                      Thread.sleep(3000);
                      System.out.println("navigating to next page");
                      System.out.println("most traversing condition , y>0");
                      //WebElement t1=   driver.findElement(By.xpath("/html/body/form/div[3]/table[2]/tbody/tr/td/div/table/tbody/tr[12]/td/table/tbody/tr/td["+y+"]/a"));
                      // WebElement t1=   driver.findElement(By.xpath("/html/body/form/div[3]/table[2]/tbody/tr/td/div/table/tbody/tr[12]/td/table/tbody/tr/td[2]/a"));
                      String pagination = "//a[contains(@href, \"javascript:__doPostBack('gvDividendsDetails','Page$" + (y + 1) + "')\")]";

                      //String pagination="//*[@id=\"gvDividendsDetails\"]/tbody/tr[12]/td/table/tbody/tr/td["+(y+1)+"]/a";
                      String oldPagination = "/html/body/form/div[3]/table[2]/tbody/tr/td/div/table/tbody/tr[12]/td/table/tbody/tr/td[" + (y + 1) + "]/a";
                      String ltext = (y + 1) + "";
                      try {
                          driver.findElement(By.xpath(pagination));
                          // driver.findElement(By.linkText(ltext));

                          // driver.findElement(By.linkText("+y+"));

                      } catch (Exception e) {
                          System.out.println("next page is not available for dropdown " + dropDownName + ", page number " + (y + 1));

                          break;

                      }
                      //WebElement t1 =driver.findElement(By.xpath("/html/body/form/div[3]/table[2]/tbody/tr/td/div/table/tbody/tr[12]/td/table/tbody/tr/td["+y+"]/a"));
                      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pagination)));
                      //for pagination ,i am not putting wait , as end of page ,it has to wait lot
                      WebElement t1 = driver.findElement(By.xpath(pagination));
                      //WebElement t1= driver.findElement(By.linkText("+(y+1)+"));
                      //WebElement t1= driver.findElement(By.linkText(ltext));

                      if (t1 == null) {
                          System.out.println("page is N/A");
                          break;
                      }
                      if (t1 != null && t1.isEnabled() && t1.isDisplayed()) {
                          // Thread.sleep(3000);
                          //t1.click();
                          JavascriptExecutor executor = (JavascriptExecutor) driver;
                          executor.executeScript("arguments[0].click();", t1);
                          System.out.println("page number -" + t1.getText());
                          System.out.println("next page is available ");
                          //Thread.sleep(5000);

                          WebElement table;//=    driver.findElement(By.xpath("//*[@id=\"gvDividendsDetails\"]"));
                          try {
                              wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gvDividendsDetails\"]")));
                              Thread.sleep(5000);

                              table = driver.findElement(By.xpath("//*[@id=\"gvDividendsDetails\"]"));

                              System.out.println("table found ");
                          } catch (Exception e) {
                              System.out.println("table not fund ,trying again");
                              //table =    driver.findElement(By.className("TableCls"));
                              Thread.sleep(6000);
                              table = driver.findElement(By.xpath("//*[@id=\"gvDividendsDetails\"]"));

                          }

                          wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("tr")));
                          Thread.sleep(5000);

                          List<WebElement>  rowsList;
                          try{rowsList = table.findElements(By.tagName("tr"));}
                          catch (Exception e){
                              System.out.println("searching again");
                              Thread.sleep(5000);
                              rowsList = table.findElements(By.tagName("tr"));
                          }


                          Thread.sleep(2000);
                          for (WebElement row : rowsList) {
                              System.out.println();
                              //writer.write("\n");
                              // WebElement row = rowsList.get()
                              count++;
                              if (rowsList.size() - 2 < count) {
                                  count = 0;
                                  System.out.println("outer ,y>1");
                                  break;
                              }
                              wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("td")));
                              columnsList = row.findElements(By.tagName("td"));


                             /////################## System.out.print(dropDownName + " ; ");
                                writer.write(dropDownName + " ; ");
                              //for(int m=0;m<columnsList.size()-1;i++){
                              ;

                              for (WebElement column : columnsList) {
                         //     for (WebElement column : columnsList.subList(1,columnsList.size())) {
                    /*count++;
                    if(columnsList.size()-1 < count){ count=0;
                        System.out.println("inner"); break;}
                    else {                    System.out.print(column.getText() + ", ");
                    }*/
                                  /////##################     System.out.print(column.getText() + ";");
                                  writer.write(column.getText() + ";");
                                  //  System.out.print(columnsList.get(m).getText() + ", ");

                              }
                              /////################## System.out.print((y + 1));
                              writer.write(""+(y+1));
                              //  driver.findElement(By.xpath("//a[@href='javascript:__doPostBack('gvDividendsDetails','Page$2'')")).click();
                              writer.write("\n");
                          } // finished printing table

                          //
                      }
                  }
                  // if(y==0 && i==1){ Thread.sleep(2000);
                  if (y == 0) {  // for first page , there is no pagination
                      //Thread.sleep(2000);

                      System.out.println("page number -" + 1);
                      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gvDividendsDetails\"]")));

                      WebElement table = driver.findElement(By.xpath("//*[@id=\"gvDividendsDetails\"]"));
                      wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("tr")));

                      List<WebElement> rowsList = table.findElements(By.tagName("tr"));
                      for (WebElement row : rowsList) {
                          //writer.write("\n");
                          System.out.println();
                          // WebElement row = rowsList.get()
                          count++;
                          if (rowsList.size() - 2 < count) {
                              count = 0;
                              System.out.println("outer , y=0 ");
                              break;
                          }
                          wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("td")));

                          columnsList = row.findElements(By.tagName("td"));
                          System.out.print(dropDownName + " ; ");
                          writer.write(dropDownName + " ; ");

                          //for(int m=0;m<columnsList.size()-1;i++){
                          ;

                          for (WebElement column : columnsList) {
                         // for (WebElement column : columnsList.subList(1,columnsList.size())) {

                    /*count++;
                    if(columnsList.size()-1 < count){ count=0;
                        System.out.println("inner"); break;}
                    else {                    System.out.print(column.getText() + ", ");
                    }*/
                              System.out.print(column.getText() + " ; ");
                              writer.write(column.getText() + " ; ");

                              //  System.out.print(columnsList.get(m).getText() + ", ");

                          }
                          System.out.print((y + 1));
                          writer.write(""+(y+1));
                          writer.write("\n");

                          //  driver.findElement(By.xpath("//a[@href='javascript:__doPostBack('gvDividendsDetails','Page$2'')")).click();

                      } // finish printing table
                  }
                  //System.out.println("alternate");     driver.findElement(By.linkText("2")).click();
                  writer.flush();
              } // page traversing
              System.out.println("going for next drop down element");
             // writer.flush();
             // Thread.sleep(10000);
          }


         // Thread.sleep(5000);
          System.out.println("selecting from dropdown");



        /*for(int i =0; i<iSize ; i++){
            String sValue = elementCount.get(i).getText();
            System.out.println("count is "+i+"and value is"+sValue);
            ls.add(sValue);
           // oSelect.selectByVisibleText(sValue);

            Thread.sleep(300);
            //driver.switchTo().frame(0);
            //oSelect = new Select(driver.findElement(By.className("SelectCls")));
        }*/

      }catch (Exception e)
      {
          System.out.println("---------------exception occured ----------------------"+e.getMessage());
          e.printStackTrace();
      }
    }


    public static void refreshPage() throws InterruptedException {
        String dividendLink="/html/body/form/div[6]/div/table/tbody/tr/td[2]/table/tbody/tr/td/div[1]/table/tbody/tr/td[1]/div/table/tbody/tr[2]/td/div/div[2]/div/table/tbody/tr[3]/td/a";
       // driver.findElement(By.xpath(dividendLink)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dividendLink)));
        driver.findElement(By.xpath(dividendLink)).click();

        //Thread.sleep(5000);
        System.out.println(" searchingddlschema");
        // driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"form1\"]")));
        driver.switchTo().frame(0);

        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ddlSchemes")));

        WebElement ele = driver.findElement(By.id("ddlSchemes"));
        if(ele !=null)
        {
            //System.out.println("ele is not null");
            if(ele.isDisplayed()){
               // System.out.println("isdaily"+ele.isEnabled());
                System.out.println("page refreshed ######################");

            }
        }

    }
}
