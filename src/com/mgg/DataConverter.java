package com.mgg;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This is the main driver program that loads CSV files containing person, store, and item data, 
 * creates and ArrayList for each respective data type, and uses these ArrayLists to output XML 
 * files containing the data from the original CSV input files.
 * 
 * @author kauman<br \>
 * Kyle Auman<br \>
 * kauman3@huskers.unl.edu<br \>
 * CSCE156<br \><br \>
 * @author zmain<br \>
 * Zach Main<br \>
 * zmain2@huskers.unl.edu<br \>
 * CSCE156<br \>
 *
 */
public class DataConverter {
	
	/**
	 * Takes a CSV file with Person data and stores it in an ArrayList of Persons
	 * @param file
	 * @return
	 */
    public static List<Person> loadPersonData(String file) {

        List<Person> persons = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File(file));
            String firstLine = s.nextLine();
            String token[] = firstLine.split(",");
            int n = Integer.parseInt(token[0]);
            
            for(int i =0; i<n; i++) {
                String line = s.nextLine();
                String tokens[] = line.split(",");
                Address address = new Address(tokens[4], tokens[5], tokens[6], tokens[7], tokens[8]);
                if(tokens.length <= 9) {
                    persons.add(new Person(tokens[0], tokens[1], tokens[2], tokens[3], address));
                } else { 
                	List<String> emailList = new ArrayList<>();
                    for(int j=9; j<tokens.length; j++) {
                        emailList.add(tokens[j]);
                    }
                    persons.add(new Person(tokens[0], tokens[1], tokens[2], tokens[3], address, emailList));
                }
            }
            s.close();
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        }
        return persons;
    }
    
    /**
     * Takes a CSV file with Store data and an ArrayList of Persons and stores it in an ArrayList of Stores
     * @param file
     * @return
     */
    public static List<Store> loadStoreData(String file, List<Person> persons) {
    	
    	List<Store> stores = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File(file));
            String firstLine = s.nextLine();
            String token[] = firstLine.split(",");
            int n = Integer.parseInt(token[0]);
            
            for(int i =0; i<n; i++) {
                String line = s.nextLine();
                String tokens[] = line.split(",");
                Address address = new Address(tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                for(Person p : persons) {
                	if(tokens[1].contentEquals(p.getCode())) {
                		stores.add(new Store(tokens[0], p, address));
                	}
                }
            }
            s.close();
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        }
        return stores;
    }
    
    /**
     * Takes a CSV file with Sales Item data and stores it in an ArrayList of Sales Items
     * @param file
     * @return
     */
    public static List<Item> loadItemData(String file) {
    	
    	List<Item> items = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File(file));
            String firstLine = s.nextLine();
            String token[] = firstLine.split(",");
            int n = Integer.parseInt(token[0]);
            
            for(int i =0; i<n; i++) {
                String line = s.nextLine();
                String tokens[] = line.split(",");
                if(tokens[1] == "SV") {
                    items.add(new Service(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3])));
                } else if(tokens[1] == "SB") {
                	items.add(new Subscription(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3])));
                } else if(tokens.length < 4) {
                	items.add(new Product(tokens[0], tokens[1], tokens[2]));
                } else {
                	items.add(new Product(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3])));
                }
            }
            s.close();
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        }
        return items;
    }
    
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

    public static void main(String[] args) {

        String personsFile = "data/Persons.csv";
        List<Person> persons = loadPersonData(personsFile);
        String storesFile = "data/Stores.csv";
        List<Store> stores = loadStoreData(storesFile, persons);
        String itemsFile = "data/Items.csv";
        List<Item> items = loadItemData(itemsFile);
        
        personsToXML(persons);
        storesToXML(stores);
        itemsToXML(items);
    }
    
}
