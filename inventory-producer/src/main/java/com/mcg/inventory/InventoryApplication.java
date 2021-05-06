package com.mcg.inventory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
	
	@Autowired
	private KafkaTemplate<Object,Object> template;
	
	@Value("${inventory.filename}")
	private String filename;
	
	@Bean
	public CommandLineRunner readInventory() {
		
		return (args) -> {
	    try {
	      // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
	    	FileReader fReader = new FileReader(filename);
	      // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
	    	BufferedReader bReader = new BufferedReader(fReader);
	      // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
	    String thisLine = bReader.readLine();  
	    	// start a while loop that loops while line isn't null
	    
	    while (thisLine != null) {
	    		//System.out.println(thisLine);
	    		String[] cLine = thisLine.split("\\,");
	    		Inventory inv = new Inventory();
	    		inv.setInvId(Long.parseLong(cLine[0]));
	    		inv.setProductId(Integer.parseInt(cLine[1]));
	    		inv.setQuantity(Integer.parseInt(cLine[2]));
	    		inv.setThreshold(Integer.parseInt(cLine[3]));
	    		this.template.executeInTransaction(t -> t.send("mcg-inventory",inv));
	    		thisLine = bReader.readLine();
	    }
	    		// call the close method of the BufferedReader
	    bReader.close();
	    //customers.forEach(c->System.out.println(c.getCustomerName()+c.getCustomerAddress()));
	    }
	    catch(FileNotFoundException ex) {
	      System.out.println("unable to open file " + filename );
	    }
	    catch(IOException ex) {
	      System.out.println("error reading file " + filename);
	    }

		};
	}

}
