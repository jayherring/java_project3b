//Jeremy Herring
//IT313
//Project 3a
//Oct 21 2017

import java.io.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.ChartUtilities; 
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class BarChart {

	public static void main(String[] args) throws MalformedURLException,IOException, FileNotFoundException {
		
		try {
			 // Define data for line chart.
	        DefaultCategoryDataset barChartDataset = 
	            new DefaultCategoryDataset();
		
		 Scanner s = new Scanner(new File("currencies.txt"));
		    String prefix = "http://download.finance.yahoo.com/d/quotes.csv?s=";
		    String suffix = "=X&f=sl1d1t1ba&e=.csv";
		    String targetCurrency = "USD";
		    while(s.hasNextLine( )) {
		        String sourceCurrency = s.nextLine( );
		        String url = prefix + sourceCurrency + targetCurrency + suffix;
		        Scanner fromWeb = new Scanner((new URL(url)).openStream( ));
		        String line = fromWeb.nextLine( );
		        barChartDataset.addValue(Double.parseDouble(line.split(",")[1]), "total", sourceCurrency);
		        fromWeb.close( );
		    }
		    s.close();
		    
			
	
	
	        
	  
	            
	        // Define JFreeChart object that creates line chart.
	        JFreeChart barChartObject = ChartFactory.createBarChart(
	            "Exchange Rates to USD", "Currency", "Rate (% of USD)", barChartDataset,
	            PlotOrientation.VERTICAL, 
	            false,  // Include legend.
	            false,  // Include tooltips.
	            false); // Include URLs.               
	                      
	         // Write line chart to a file.               
	         int imageWidth = 700;
	         int imageHeight = 500;                
	         File barChart = new File("CurrencyExchange.png");              
	         ChartUtilities.saveChartAsPNG(
	             barChart, barChartObject, imageWidth, imageHeight); 
	    }
	  
	    catch (Exception i)
	    {
	        System.out.println(i);
	    }
	
	}
}


