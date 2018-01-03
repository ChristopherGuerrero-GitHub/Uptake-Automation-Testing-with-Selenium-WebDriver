import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.interactions.HasInputDevices;

public class NewsAndInsights {
	
	/* ****************************************************************************
	/* 																			  */
	/* 	Title: Project Uptake Automation Testing with Selenium WebDriver          */
	/* 																			  */
	/* 	Creator: Christopher Guerrero											  */
	/* 																			  */
	/* 	Date: 01/02/2018														  */
	/* 																			  */
	/* Purpose of Current Class: 												  */
	/* 				  NewsAndInsight purpose is find all web Elements with tag    */
	/* 				  name "a" on the home website and test if each link is valid */
	/* 				  and clickable and then return to source NewAndInsight Page. */
	/* 																			  */
	/* 				  Used a For loop to evaluate each of the Web Elements to see */
	/* 				  if they are invisible "" and clickable or visible and 	  */
	/* 				  clickable. Once either scenario is determined navigation 	  */
	/* 				  to the source NewAndInsight Page is invoked and statistics  */
	/* 				  are recorded and printing out on the console.				  */
	/* 																			  */
	/* 				  Testing for invisible Web Element:						  */
	/* 																			  */
	/* 				  If the Web Element is invisible then check to see if it is  */
	/* 				  valid if the Web Element belong to either Industries or 	  */
	/* 				  Expertise sub-Menu by using a Mouse object to hover to check*/
	/* 				  for visibility. If the Web Element is visible then test	  */
	/* 				  clickable. If the Web Element is still not visible during	  */
	/* 				  mouse hover and does not belong to either sub menu then the */
	/* 				  link is recorded as invalid. 								  */
	/* 																			  */
	/* 				  Testing for visible Web Element:						      */
	/* 					  														  */
	/*			      If the Web Element link is visible then test if it is 	  */
	/* 				  enabled and then check if the link is clickable. Once		  */
	/* 				  the target website is verified return to source 			  */
	/* 				  NewAndInsight Page. Record statistics and print results 	  */
	/* 				  on the display.		  									  */
	/* 																			  */
	/* 				  If an exception is to occur then record the Web Element	  */
	/* 				  and screen shot and place into designated file location.	  */
	/* 																			  */
	/* 																			  */
	/* Created Classes:  N/A													  */
	/* 																			  */
	/* Created FireFox Driver:	 testDriver										  */
	/* 																			  */
	/* Input:	WebDriver wb													  */
	/* 																			  */
	/* Output:	Print the statistics for each Web Element it's href, class name,  */
	/* 			the results of clickable action status.							  */
	/* 																			  */
	/* 																			  */
	/* 																			  */
	/* ****************************************************************************/
	
	
	WebDriver testDriver;
	
	// Create By for webpage anchor links
	By anchorTagname = By.tagName("a");
	
	/* Caller of this method will reference
	   this class and its functions */
	public NewsAndInsights (WebDriver wd) {
		
		this.testDriver = wd;
	}
		
	/* Test navigation of anchor web elements */
	public void checkAllLinks() throws IOException {
		
		int wdeCount = 0; // Track total webdriver exceptions
		int rteCount = 0; // Track total runtime exceptions
		int successCount = 0; // Track total successful navigations
		int invisibleCount = 0; // Track total invisible web elements
		int invisibleResolvedCount = 0; // Track total resolved invisible web elements
		int disableCount = 0; // Track total disabled web elements
		
		/* PRIMING -- Find anchor web elements for total number of elements for size */
		List<WebElement> listTemp = testDriver.findElements(anchorTagname); // Temp list of anchor web elems
		int listSize = listTemp.size(); 
		
		/* Loop through anchor web elements */
		for (int i=0; i<listSize; i++){
		
			/* Create Main web elements List for tagNames = anchor */
			List<WebElement> listAnchors = testDriver.findElements(anchorTagname);

			WebElement w = listAnchors.get(i); // Load web element from list for evaluation

			/************** Get ANCHOR web element details ************/
			System.out.println("********************************");
			System.out.println("Evaluating ANCHOR Web Element "+ i);
			System.out.println("  web elem: " + w.toString());
			System.out.println("  href attr: " + w.getAttribute("href"));
			System.out.println("  className attr: " + w.getAttribute("class"));
			System.out.println("  text: " + w.getText() + "\n");
			System.out.println("    ****************************\n");
			
						try {
							
							/* Check if web element is invisible, or else */
							if (!w.isDisplayed()) {
								
								/* navigation test for invisible web elements */
								invisibleCount = invisibleCount + 1;
								
								/* Is this web elem, submenu to Industries */
								/* Is this web elem, submenu to Expertise  */
								/* Let's try to resolve ********************/
								
								while(!w.isDisplayed()){
									
									/* Create mouse obj for testDriver */
									Mouse m = ((HasInputDevices) testDriver).getMouse();
								
									/* Attempt Industries submenu resolution */
									WebElement displayIndustriesMenu = listAnchors.get(3); // Position 3 in List, where Industries elem is
									Locatable hoverIndustriesMenu = (Locatable) displayIndustriesMenu;
									m.mouseMove(hoverIndustriesMenu.getCoordinates());
									
									/* Invisible Industries Submenu is now displayed */
									
									/* Is Industries submenu web elem visible */
									if (w.isDisplayed()) {
										
										w.click();
										System.out.println("\n  >>> Industries submenu resolution invoked.");
										System.out.println("  Webpage Title: " + testDriver.getTitle()); //verifies success
										System.out.println("\n  >>> Navigation TARGET click successful.\n");
										invisibleResolvedCount = invisibleResolvedCount + 1;
										break;
									}
										
									
									/* Attempt Expertise submenu resolution */
									WebElement displayExpertiseMenu = listAnchors.get(18); // Position 18 in List, where Expertise elem is
									Locatable hoverExpertiseMenu = (Locatable) displayExpertiseMenu;
									m.mouseMove(hoverExpertiseMenu.getCoordinates());
								
									/* Invisible Expertise Submenu is now displayed */
									
									/* Is Expertise submenu web elem visible */
									if (w.isDisplayed()) {
										
										w.click();
										System.out.println("\n  >>> Expertise submenu resolution invoked.");
										System.out.println("  Webpage Title: " + testDriver.getTitle()); //verifies success
										System.out.println("\n  >>> Navigation TARGET click successful.\n");
										invisibleResolvedCount = invisibleResolvedCount + 1;
										break;
									}
									
									/* If Resolutions unsuccessful, then no navigation attempt */
									/* ...could be mobile or video link */
									System.out.println("NAVIGATION Issue: web elem is still invisible...");
									break;
									
								}
								
								/* Navigate back to News And Insights page */
								testDriver.navigate().to("https://www.uptake.com/blog/");
								testDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
								System.out.println("\n  >>> Navigation to News And Insights click successful.\n");
								
							} else if (!w.isEnabled()) {
								
								System.out.println("NAVIGATION Issue: web elem is disabled...");
								disableCount = disableCount + 1;
								
							} else {
								
								/* navigation test for non-invisible web elements */
								w.click(); //test link
								successCount = successCount + 1;
								
								/* Get Title of webpage */
								System.out.println("\n  Webpage Title: " + testDriver.getTitle()); //verifies success
								
								System.out.println("\n  >>> Navigation TARGET click successful.\n");
								
								/* Navigate back to News And Insights page */
								testDriver.navigate().to("https://www.uptake.com/blog/");
								testDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
								System.out.println("\n  >>> Navigation to News And Insights click successful.\n");
								
							} //end if else if
							
						} catch (WebDriverException wde) {

							System.out.println("\n***Error - WebDriver Exception caught...Screenshot taken.");
					    	
					    	// Take screenshot and store as a file format
					    	File src= ((TakesScreenshot)testDriver).getScreenshotAs(OutputType.FILE);
					    	
					    	try {
					    		// now copy the  screenshot to desired location using copyFile //method
					    		FileUtils.copyFile(src, new File("C:\\SeleniumData\\Errors\\webDriverErrorOnWebElement_" + i +"_Newspage.png"));
					    	
					    	} catch (IOException e) {
					    	  
					    		System.out.println(e.getMessage());
					    		
					    	}
					    	
					    	wdeCount = wdeCount + 1;
					    	
					    	System.out.println("  ***Error Msg: " + ((WebDriverException) wde).getSystemInformation());
					    	System.out.println("  ***Error Msg: " + ((WebDriverException) wde).getSupportUrl());
					    	System.out.println("  ***Error Msg: " + ((WebDriverException) wde).getAdditionalInformation() + "\n");
							
					    } catch (RuntimeException rte) {
					    	
					    	System.out.println("\n***Error - Runtime Exception caught...Screenshot taken.");
					    	
					    	// Take screenshot and store as a file format
					    	File src= ((TakesScreenshot)testDriver).getScreenshotAs(OutputType.FILE);
					    	try {
					    		// now copy the  screenshot to desired location using copyFile //method
					    		FileUtils.copyFile(src, new File("C:\\SeleniumData\\Errors\\runtimeErrorOnWebElement_" + i +"_Newspage.png"));
					    	
					    	} catch (IOException e) {
					    	  
					    		System.out.println("  ***Error Msg: " + e.getMessage() + "\n");
					    	}
					    	
					    	rteCount = rteCount + 1;
							
					    } finally {
					    	
					    	//Place-holder
					    	
					    } //end finally catch
						
					
			
		}//end for loop
		
		/* calculate web element ANCHOR totals */
		System.out.println("\n\n------------------");
		System.out.println("Web Element Totals");
		System.out.println("------------------\n");
		System.out.println(listSize + " -- total ANCHOR web elements to test.");
		System.out.println(successCount + " -- total successful navigations (may includ. java errs or no navigation detected).");
		System.out.println(invisibleResolvedCount + " -- total RESOLVED navigationss of invisible web elements.");
		System.out.println(invisibleCount - invisibleResolvedCount + " -- total UN-Resolved invisible web elements.");
		System.out.println(disableCount + " -- total disabled web elements.");
		System.out.println(wdeCount + " -- total exceptions for webdriver.");
		System.out.println(rteCount + " -- total exceptions for runtime.");
	}

	
	

}
