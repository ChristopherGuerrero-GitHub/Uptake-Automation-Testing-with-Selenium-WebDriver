import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestRunner {

	public static void main(String[] args) {
		
		/* ****************************************************************************
		/* 																			  */
		/* 	Title: Project Uptake Automation Testing with Selenium WebDriver          */
		/* 																			  */
		/* 	Creator: Christopher Guerrero											  */
		/* 																			  */
		/* 	Date: 01/02/2018														  */
		/* 																			  */
		/* 	Purpose: To create automation testing for two of Uptake's website pages.  */
		/* 			 The first webpage being the Home page and the other being the	  */
		/* 			 News and Insights page.										  */
		/* 																			  */
		/*    		 Used Selenium WebDriver API to test each web links within the	  */
		/*    		 two above mentioned web pages and verified navigation back to 	  */
		/*    		 corresponding invocation source page (ie. Home/NewsAndInsight)	  */
		/*    		 is valid. Used Page Object Model pattern to implement object 	  */
		/*    		 oriented programming. 											  */
		/* 																			  */
		/* Current Class: TestRunner(main) purpose is to create two FireFox WebDriver */
		/* 				  objects and two Web Page objects. The two FireFox WebDriver */
		/* 				  objects will be passed to the corresponding designated 	  */
		/*				  Page object which contains the checkAllLinks method. Each   */
		/* 				  Page object will invoke their checkAllLinks method and will */
		/* 				  return back to the TestRunner(main)class where the WebDriver*/
		/* 				  quit method will end the web page browsers.				  */  	
		/* 				  	 														  */
		/* 																			  */
		/* Created Classes:  TestRunner(main), HomePage, NewsAndInsights			  */
		/* 																			  */
		/* Created FireFox Driver:	 testDriverFF, testDriverFF2					  */
		/* 																			  */
		/* Input:	N/A																  */
		/* 																			  */
		/* Output:	TestRunner will drive the program and print out the begin/end  	  */
		/* 			status and the final message that the program completed			  */
		/* 																			  */
		/* 																			  */
		/* 																			  */
		/* ****************************************************************************/
		
		// Create FireFox driver object	and string object 
		// variable for URL for Home Page.	
    	System.setProperty("webdriver.firefox","C:\\Users\\Christopher\\Documents\\WebDrivers\\FireFoxDriver\\geckodriver-v0.19.1-win64");
		WebDriver testDriverFF = new FirefoxDriver();		
		String baseUrl = "https://www.uptake.com/";
                              
        // launch Fire fox and direct it to the Base HomePage 
		// URL and maximize window.
        testDriverFF.get(baseUrl);
        testDriverFF.manage().window().maximize();
            
        // Create and initialize HomePage class object 
        // and pass FireFox driver object as argument.
        HomePage objHome = new HomePage(testDriverFF);
        
        /* Test Homepage Navigation Links and display 
         * start and stop testing messages. */
		try {
			System.out.println("\n\n\n*********************************************");
			System.out.println("UPTAKE HOMEPAGE navigation tests beginning...\n\n");			
			objHome.checkAllLinks();
			System.out.println("\nUPTAKE HOMEPAGE navigation tests completed!\n\n");
		} catch (IOException e) {
			// generated catch block
			e.printStackTrace();
		}
		
		// quit FireFox browser object
		testDriverFF.quit();
		
		/**************************************/
		/* Test Uptake's NewsAndInsights page */
		/**************************************/
		
		// Create FireFox driver object	and string object 
		// variable for URL for News And Insights Page.	
		WebDriver testDriverFF2 = new FirefoxDriver();
		String baseUrl2 = "https://www.uptake.com/blog";
        
        // launch Fire fox and direct it to the Base News 
		// And Insight URL and maximize window.
        testDriverFF2.get(baseUrl2);
        testDriverFF2.manage().window().maximize();
		
        // Create and initialize News And Insight Page class 
        // object and pass FireFox driver object as argument.
        NewsAndInsights objNews = new NewsAndInsights(testDriverFF2);
				
		/* Test NewsAndInsight page Navigation Links and display 
         * start and stop testing messages. */
		try {
			System.out.println("\n\n\n*********************************************");
			System.out.println("UPTAKE NEWSPAGE navigation tests beginning...\n\n");			
			objNews.checkAllLinks();
			System.out.println("\nUPTAKE NEWSPAGE navigation tests completed!\n\n");
		} catch (IOException e) {
			//generated catch block
			e.printStackTrace();
		}
	  
		// quit FireFox browser object
        testDriverFF2.quit(); 
        
        /* Program completed */
		System.out.println("\n\n\n**********************************************");
		System.out.println("Program done - ALL navigation tests completed!");
   
		

	}

}
