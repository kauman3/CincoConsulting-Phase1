package com.mgg;

/**
 * This abstract class models a sales item with a code, type, name, and basePrice. Items can be
 * Products, Services, or Subscriptions.
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
public abstract class Item {
	
	private String code;
	private String type;
	private String name;
	private double basePrice;
	
	public Item(String code, String type, String name, double basePrice) {
		this.code = code;
		this.type = type;
		this.name = name;
		this.basePrice = basePrice;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the name
	 */
	public double getBasePrice() {
		return this.basePrice;
	}
	
}
