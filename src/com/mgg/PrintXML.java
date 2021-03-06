package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This class takes lists of <code>Person</code>s, <code>Store</code>s, and <code>Item</code>s
 * and outputs the data they contain as an XML file. 
 * 
 * @author kauman<br \>
 * Kyle Auman<br \>
 * kauman3@huskers.unl.edu<br \>
 * CSCE156<br \><br \>
 * @author zmain<br \>
 * Zach Main<br \>
 * zmain2@huskers.unl.edu<br \>
 * CSCE156<br \>
 */
public class PrintXML {
	
	/**
     * Outputs the given Person List to an XML file
     * @param persons
     */
    public static void personsToXML(List<Person> persons) {
    	
    	XStream xstream = new XStream(new DomDriver());
        xstream.alias("Person", Person.class);
        String xml = new String();
        
        for(Person p : persons) {
        	if(p.getType().contentEquals("C")) {
        		xstream.alias("Customer", Person.class);
        		xml = xml + xstream.toXML(p) + "\n";
        	} else if(p.getType().contentEquals("G")) {
        		xstream.alias("GoldCustomer", Person.class);
        		xml = xml + xstream.toXML(p) + "\n";
        	} else if(p.getType().contentEquals("P")) {
        		xstream.alias("PlatinumCustomer", Person.class);
        		xml = xml + xstream.toXML(p) + "\n";
        	} else {
        		xstream.alias("Employee", Person.class);
        		xml = xml + xstream.toXML(p) + "\n";
        	}
        }
        File personsXMLFile = new File("data/Persons.xml");
        try {
        	PrintWriter pw = new PrintWriter(personsXMLFile);
        	pw.print(xml);
        	pw.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
        return;
    }
    
    /**
     * Outputs the given Store List to an XML file
     * @param stores
     */
    public static void storesToXML(List<Store> stores) {
    	
    	XStream xstream = new XStream(new DomDriver());
        xstream.alias("Store", Store.class);
        String xml = new String();
        
        for(Store s : stores) {
        	xml = xml + xstream.toXML(s) + "\n";
        }
        File storessXMLFile = new File("data/Stores.xml");
        try {
        	PrintWriter pw = new PrintWriter(storessXMLFile);
        	pw.print(xml);
        	pw.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
        return;
    }
    
    /**
     * Outputs the given Item List to an XML file
     * @param items
     */
    public static void itemsToXML(List<Item> items) {
    	
    	XStream xstream = new XStream(new DomDriver());
    	xstream.alias("Items", Item.class);
        String xml = new String();
        
        for(Item i : items) {
        	if(i.getType().contentEquals("PN")) {
        		xstream.alias("NewProduct", Product.class);
        		xml = xml + xstream.toXML(i) + "\n";
        	} else if(i.getType().contentEquals("PU")) {
        		xstream.alias("UsedProduct", Product.class);
        		xml = xml + xstream.toXML(i) + "\n";
        	} else if(i.getType().contentEquals("PG")) {
        		xstream.alias("GiftCard", Product.class);
        		xml = xml + xstream.toXML(i) + "\n";
        	} else if(i.getType().contentEquals("SV")) {
        		xstream.alias("Service", Product.class);
        		xml = xml + xstream.toXML(i) + "\n";
        	} else {
        		xstream.alias("Subscription", Product.class);
        		xml = xml + xstream.toXML(i) + "\n";
        	}
        }
        File itemsXMLFile = new File("data/Items.xml");
        try {
        	PrintWriter pw = new PrintWriter(itemsXMLFile);
        	pw.print(xml);
        	pw.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
        return;
    }
	
}
