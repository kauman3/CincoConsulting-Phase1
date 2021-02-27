package com.mgg;

/**
 * This class models a Subscription with a code, type, name, and annualFee and implements the Item class.
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
public class Subscription extends Item {
	
	public Subscription(String code, String type, String name, double annualFee) {
		super(code, type, name, annualFee);
	}

}
