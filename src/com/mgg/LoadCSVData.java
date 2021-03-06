package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class loads CSV files containing person, store, and item data, 
 * creates and ArrayList for each respective data type.
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
public class LoadCSVData {
	
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
	
}
