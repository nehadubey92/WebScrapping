import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Navigation1 {
   // public static RemoteWebDriver driver;
   static  WebDriver driver ;

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        //System.setProperty("webdriver.chrome.driver", "C:\\Utility\\BrowserDrivers\\chromedriver.exe");
        //System.setProperty("webdriver.gecko.driver", "path/to/geckodriver.exe");

        System.setProperty("webdriver.gecko.driver", "E:\\software\\firefox\\geckodriver.exe"); // 64bit version
        System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");

        /*DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.ANY);
        desiredCapabilities.setBrowserName("firefox");

        driver = new RemoteWebDriver(new URL("https://mfs.kfintech.com/mfs/FundoPaedia/NfundoPaedia.aspx?frm=iC#"), desiredCapabilities);
*/

         driver = new FirefoxDriver();

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

        List <WebElement> elementCount = oSelect.getOptions();
        List<String> ls = new ArrayList<String>();
        int iSize = elementCount.size();

        System.out.println("clicking on child item");

        for(int i =1; i<iSize ; i++){
            //if (i>1)driver.switchTo().frame(0);
           // Select oSelect1 = new Select(driver.findElement(By.className("SelectCls")));
            //List <WebElement> elementCount1 = oSelect.getOptions();
            System.out.println("searching next dropdown value");

            //refreshing page
            System.out.println("before searching refreshing page");
            if(i>1){
                driver.switchTo().parentFrame();
                refreshPage();
            };
            //if(i==2){continue;} // temp
            //driver.navigate().refresh();

            String temp="option:nth-child("+(i+1)+")";
           // String sValue = elementCount.get(i).getText();
            //System.out.println("count is "+i+"and value is"+sValue);
            //ls.add(sValue);
            driver.findElement(By.cssSelector(temp)).click();
            String dropDownName=driver.findElement(By.cssSelector(temp)).getText();
            System.out.println("count is "+i+"and value is"+  dropDownName );

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
            int count=0;

            for(int y=0;;y++){


                System.out.println("");
                //if(i==0){break;}
                // y is for tracking page for each dropdown ...
                if(y == 0 && i>1 && false) // 2nd dropdown , 1st page
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
                        Thread.sleep(5000);

                        WebElement table ;//=    driver.findElement(By.xpath("//*[@id=\"gvDividendsDetails\"]"));
                        try{
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
                    /*count++;
                    if(columnsList.size()-1 < count){ count=0;
                        System.out.println("inner"); break;}
                    else {                    System.out.print(column.getText() + ", ");
                    }*/
                                System.out.print(column.getText() + ", ");

                                //  System.out.print(columnsList.get(m).getText() + ", ");

                            }
                            //  driver.findElement(By.xpath("//a[@href='javascript:__doPostBack('gvDividendsDetails','Page$2'')")).click();

                        } // finished printing table


                    }
                }
                if(y >0) { Thread.sleep(3000);
                    System.out.println("navigating to next page");
                    System.out.println("most traversing condition , y>0");
                 //WebElement t1=   driver.findElement(By.xpath("/html/body/form/div[3]/table[2]/tbody/tr/td/div/table/tbody/tr[12]/td/table/tbody/tr/td["+y+"]/a"));
                   // WebElement t1=   driver.findElement(By.xpath("/html/body/form/div[3]/table[2]/tbody/tr/td/div/table/tbody/tr[12]/td/table/tbody/tr/td[2]/a"));
                    String pagination="//a[contains(@href, \"javascript:__doPostBack('gvDividendsDetails','Page$"+(y+1)+"')\")]";

                    //String pagination="//*[@id=\"gvDividendsDetails\"]/tbody/tr[12]/td/table/tbody/tr/td["+(y+1)+"]/a";
                    String oldPagination="/html/body/form/div[3]/table[2]/tbody/tr/td/div/table/tbody/tr[12]/td/table/tbody/tr/td[" + (y+1) + "]/a";
                    String ltext=(y+1)+"";
                    try {
                        driver.findElement(By.xpath(pagination));
                       // driver.findElement(By.linkText(ltext));

                        // driver.findElement(By.linkText("+y+"));

                    }catch (Exception e){
                        System.out.println("next page is not available for dropdown "+dropDownName+", page number "+(y+1));

                        break;

                    }
                    //WebElement t1 =driver.findElement(By.xpath("/html/body/form/div[3]/table[2]/tbody/tr/td/div/table/tbody/tr[12]/td/table/tbody/tr/td["+y+"]/a"));
                    WebElement t1 =driver.findElement(By.xpath(pagination));
                    //WebElement t1= driver.findElement(By.linkText("+(y+1)+"));
                    //WebElement t1= driver.findElement(By.linkText(ltext));

                    if(t1==null ){
                        System.out.println("page is N/A");break;}
                    if( t1 !=null && t1.isEnabled() && t1.isDisplayed()){
                       // Thread.sleep(3000);
                        //t1.click();
                        JavascriptExecutor executor = (JavascriptExecutor)driver;
                        executor.executeScript("arguments[0].click();", t1);
                        System.out.println("page number -"+t1.getText());
                        System.out.println("next page is available ");
                        Thread.sleep(5000);

                        WebElement table ;//=    driver.findElement(By.xpath("//*[@id=\"gvDividendsDetails\"]"));
                        try{
                            table =    driver.findElement(By.xpath("//*[@id=\"gvDividendsDetails\"]"));

                            System.out.println("table found ");
                        }
                        catch(Exception e)
                        {
                            System.out.println("table not fund ,trying again");
                            //table =    driver.findElement(By.className("TableCls"));
                            Thread.sleep(4000);
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
                    /*count++;
                    if(columnsList.size()-1 < count){ count=0;
                        System.out.println("inner"); break;}
                    else {                    System.out.print(column.getText() + ", ");
                    }*/
                                System.out.print(column.getText() + ", ");

                                //  System.out.print(columnsList.get(m).getText() + ", ");

                            }
                            //  driver.findElement(By.xpath("//a[@href='javascript:__doPostBack('gvDividendsDetails','Page$2'')")).click();

                        } // finished printing table


                 }
                }
           // if(y==0 && i==1){ Thread.sleep(2000);
                if(y==0 ){  // for first page , there is no pagination
                    Thread.sleep(2000);

                    System.out.println("page number -"+ 1);

                WebElement table =    driver.findElement(By.xpath("//*[@id=\"gvDividendsDetails\"]"));
                List<WebElement> rowsList = table.findElements(By.tagName("tr"));
                for (WebElement row : rowsList) {
                    System.out.println();
                    // WebElement row = rowsList.get()
                    count++;
                    if(rowsList.size()-2 < count){ count=0;
                        System.out.println("outer , y=0 "); break;}

                    columnsList = row.findElements(By.tagName("td"));
                    System.out.print(dropDownName+" ,");
                    //for(int m=0;m<columnsList.size()-1;i++){
                    ;

                    for (WebElement column : columnsList) {
                    /*count++;
                    if(columnsList.size()-1 < count){ count=0;
                        System.out.println("inner"); break;}
                    else {                    System.out.print(column.getText() + ", ");
                    }*/
                        System.out.print(column.getText() + ", ");

                        //  System.out.print(columnsList.get(m).getText() + ", ");

                    }
                    //  driver.findElement(By.xpath("//a[@href='javascript:__doPostBack('gvDividendsDetails','Page$2'')")).click();

                } // finish printing table
            }
                //System.out.println("alternate");     driver.findElement(By.linkText("2")).click();


            } // page traversing
            System.out.println("going for next drop down element");
            Thread.sleep(10000);
        }


        Thread.sleep(5000);
        System.out.println("selecting from dropdown");



        for(int i =0; i<iSize ; i++){
            String sValue = elementCount.get(i).getText();
            System.out.println("count is "+i+"and value is"+sValue);
            ls.add(sValue);
           // oSelect.selectByVisibleText(sValue);

            Thread.sleep(300);
            //driver.switchTo().frame(0);
            //oSelect = new Select(driver.findElement(By.className("SelectCls")));
        }
        for(String dropDownValue:ls)
        {
            System.out.println("clicking on element - "+dropDownValue);

            oSelect.selectByVisibleText(dropDownValue); Thread.sleep(7000);
        }

       // WebElement ele = driver.findElement(By.id("ddlSchemes")); // Get control of select tag


        /*Select dropdown = new Select(ele);
        List<WebElement> allOptions = dropdown.getOptions();
        ele.sendKeys(Keys.CONTROL); // to hold CTRL button once and then click on all options
        for (WebElement webElement : allOptions) {
            System.out.println("click on dropdown");
            webElement.click(); Thread.sleep(5000);
        }*/


    }


    public static void refreshPage() throws InterruptedException {
        String dividendLink="/html/body/form/div[6]/div/table/tbody/tr/td[2]/table/tbody/tr/td/div[1]/table/tbody/tr/td[1]/div/table/tbody/tr[2]/td/div/div[2]/div/table/tbody/tr[3]/td/a";
        driver.findElement(By.xpath(dividendLink)).click();
        Thread.sleep(5000);
        System.out.println(" searchingddlschema");
        // driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"form1\"]")));
        driver.switchTo().frame(0);

        Thread.sleep(3000);

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
