package edu.nyu.hr970.pqshw1;

import java.util.ArrayList;

/**
 * Address class contains information that a contact entry can have about the postal addresses of a person
 * Address can contain the following information -
 * 	- Home
 * 	- Work
 * 	- Other
 * @author himaja
 *
 */
public class Address {

	private final String addressHome;
	private final String addressWork;
	private final String addressOther;
	
	/**
	 * Builder class (Inner) to create an object. This class is used to create a Address object which has to be constructed based on 
	 * number of optional parameters passed.
	 * 
	 * Examples to create a Address object -
	 * 
	 * address = new Address.addressBuilder().addressHome("addressHome").
					addressWork("addressWork").addressOther("addressOther").build();
					
	 * address = new Address.addressBuilder().addressOther("addressOther").build();
	 *
	 */
	public static class addressBuilder{
		private String addressHome = "";
		private String addressWork = "";
		private String addressOther = "";
		
		public addressBuilder addressHome(String addressHome){
		  this.addressHome = addressHome;
			return this;
		}
		
		public addressBuilder addressWork(String addressWork){
			this.addressWork = addressWork;
			return this;
		}
		
		public addressBuilder addressOther(String addressOther){
			this.addressOther = addressOther;
			return this;
		}
		
		public Address build(){
			return new Address(this);
		}
	}
	
	private Address(addressBuilder builder){
		this.addressHome = builder.addressHome;
		this.addressWork = builder.addressWork;
		this.addressOther = builder.addressOther;
	}
	
	public String getaddressHomeAddress(Address address){
		return address.addressHome;
	}
	
	public String getaddressWorkAddress(Address address){
		return address.addressWork;
	}
	
	public String getaddressOtherAddress(Address address){
		return address.addressOther;
	}
	
	/**
	 * Gets the list of types of Address a contact can have
	 * @return returns an ArrayList of types of postal Addresses that a contact can have
	 */
	public ArrayList<String> getAddressTypes(){
		ArrayList<String> retList = new ArrayList<String>();
		retList.add("addressHome");
		retList.add("addressWork");
		retList.add("addressOther");
		return retList;
	}
	
	/**
	 * Gets the list of entries that an address object contains within a contact object
	 * @param address - Takes the address object within a contact
	 * @return - returns an ArrayList of values which are contained by this Address object
	 */
	public ArrayList<String> getAddressEntries(){
		ArrayList<String> retList = new ArrayList<String>();
		retList.add(this.addressHome);
		retList.add(this.addressWork);
		retList.add(this.addressOther);
		return retList;
	}
	
	/**
	 * Check if address object contains a specified string in any of its entries 
	 * @param searchString - String to be searched for
	 * @return True if the string is found, otherwise return false
	 */
	boolean search(String searchString){
		boolean retVal = false;
		retVal = retVal || (addressHome != null ? addressHome.toLowerCase().contains(searchString) : retVal);
		retVal = retVal || (addressWork != null ? addressHome.toLowerCase().contains(searchString) : retVal);
		retVal = retVal || (addressOther != null ? addressOther.toLowerCase().contains(searchString) : retVal);
		
		return retVal;
	}
	
	@Override public boolean equals(Object o){
		if (o == null){
			return false;
		}
		if (o == this){
			return true;
		}
		if (!(o instanceof Address)){
			return false;
		}
		boolean retVal = true;
		Address address = (Address)o;
		retVal = retVal && (addressHome == null ? address.addressHome == null : addressHome.equals(address.addressHome));
		retVal = retVal && (addressWork == null ? address.addressWork == null : addressWork.equals(address.addressWork));
		retVal = retVal && (addressOther == null ? address.addressOther == null : addressOther.equals(address.addressOther));
		return retVal;
		}
	
	@Override public int hashCode(){
		int result = 17;
		result = 31 * result + (addressHome == null ? 0 : addressHome.hashCode());
		result = 31 * result + (addressWork == null ? 0 : addressWork.hashCode());
		result = 31 * result + (addressOther == null? 0 : addressOther.hashCode());
		return result;
	}
	
	/**
	 * returns the contents of an address object
	 */
	@Override public String toString(){
		return "addressHome : "+addressHome+"  addressWork :  "+addressWork+"  addressOther :  "+addressOther;
		
	}
}