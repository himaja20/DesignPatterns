package edu.nyu.hr970.pqshw1;

import java.util.ArrayList;

/**
 * Phone class contains the information a contact entry can have about the phone numbers of a person
 * Phone can have following information-
 * 	- Mobile
 * 	- Work
 * 	- Home
 * 	- Fax
 * @author himaja
 *
 */
public class Phone {
	private final String phoneMobile;
	private final String phoneWork;
	private final String phoneHome;
	private final String phoneFax;

	/**
	 * Builder class (Inner) to create an object. This class is used to create a Phone object which has to be constructed based on 
	 * number of optional parameters passed.
	 * 
	 * Examples to create a phone object -
	 * 
	 * phone = new Phone.phoneBuilder().phoneHome("12345")).
					phoneMobile("995234")).phoneWork("657")).
					phoneFax("1343")).build();
					
		 phone = new Phone.phoneBuilder().phoneHome("12345")).build();
		 phone = new Phone.phoneBuilder().phoneHome("12345")).phoneWork("657").build();
	 *
	 */
	public static class phoneBuilder {
		private String phoneMobile = "";
		private String phoneWork = "";
		private String phoneHome = "";
		private String phoneFax = "";

		public phoneBuilder phoneMobile(String phoneMobile) {
			this.phoneMobile = phoneMobile;
			return this;
		}

		public phoneBuilder phoneWork(String phoneWork) {
			this.phoneWork = phoneWork;
			return this;
		}

		public phoneBuilder phoneHome(String phoneHome) {
			this.phoneHome = phoneHome;
			return this;
		}

		public phoneBuilder phoneFax(String phoneFax) {
			this.phoneFax = phoneFax;
			return this;
		}

		public Phone build() {
			if (!(isValid(this))) {
				throw new IllegalStateException("Phone number should only contain numbers");
			}
			return new Phone(this);
		}

		/**
		 * Function to check if the phone number is a valid entry. Valid phone numbers should only consist of numbers between 0-9.
		 * A check is performed on the object just before it is sent to build function in the parent class.
		 * @param builder - Pass the builder object built using Phone builder class 
		 * @return True if the check passes, otherwise false
		 */
		private boolean isValid(phoneBuilder builder) {
			boolean retVal = true;
			if (!builder.phoneHome.equals("")) {
				retVal = retVal && builder.phoneHome.matches("^[0-9]*$") ? true : false;
			}
			if (!builder.phoneMobile.equals("")) {
				retVal = retVal && builder.phoneMobile.matches("^[0-9]*$") ? true : false;
			}
			if (!builder.phoneWork.equals("")) {
				retVal = retVal && builder.phoneWork.matches("^[0-9]*$") ? true : false;
			}
			if (!builder.phoneFax.equals("")) {
				retVal = retVal && builder.phoneFax.matches("^[0-9]*$") ? true : false;
			}
			return retVal;
		}
	}

	private Phone(phoneBuilder builder) {
		this.phoneMobile = builder.phoneMobile;
		this.phoneWork = builder.phoneWork;
		this.phoneFax = builder.phoneFax;
		this.phoneHome = builder.phoneHome;
	}

	public String getphoneMobilePhone(Phone phone) {
		return phone.phoneMobile;
	}

	public String getphoneWorkPhone(Phone phone) {
		return phone.phoneWork;
	}

	public String getphoneFax(Phone phone) {
		return phone.phoneFax;
	}

	public String getphoneHomePhone(Phone phone) {
		return phone.phoneHome;
	}
	
	/**
	 * Check if phone object contains a specified string in any of its entries 
	 * @param searchString - String to be searched for
	 * @return True if the string is found, otherwise return false
	 */
	boolean search(String searchString){
		boolean retVal = false;
		retVal = retVal || (phoneHome != null ? phoneHome.toLowerCase().contains(searchString) : retVal);
		retVal = retVal || (phoneMobile != null ? phoneMobile.toLowerCase().contains(searchString) : retVal);
		retVal = retVal || (phoneWork != null ? phoneWork.toLowerCase().contains(searchString) : retVal);
		retVal = retVal || (phoneFax != null ? phoneFax.toLowerCase().contains(searchString) : retVal);
		
		return retVal;
	}
	
	/**
	 * Gets the list of types of Phone a contact can have
	 * @return returns an ArrayList of Phone types that a contact can have
	 */
	public ArrayList<String> getPhoneTypes() {
		ArrayList<String> retList = new ArrayList<String>();
		retList.add("phoneHome");
		retList.add("phoneMobile");
		retList.add("phoneWork");
		retList.add("phoneFax");
		return retList;
	}

	/**
	 * Gets the list of entries that a phone object contains within a contact object
	 * @param Email - Takes the phone object within a contact as a parameter
	 * @return - returns an ArrayList of values which are contained by this phone object
	 */
	public ArrayList<String> getPhoneEntries(){
		ArrayList<String> retList = new ArrayList<String>();
		retList.add(this.phoneHome);
		retList.add(this.phoneMobile);
		retList.add(this.phoneWork);
		retList.add(this.phoneFax);
		
		return retList;
	}

	@Override public boolean equals(Object o) {
		if (o == null){
			return false;
		}
		if (o == this) {
			return true;
		}
		if (!(o instanceof Phone)) {
			return false;
		}
		Phone phone = (Phone) o;
		boolean retVal = true;
		retVal = retVal && (phoneHome == null ? phone.phoneHome == null : phoneHome.equals(phone.phoneHome));
		retVal = retVal && (phoneWork == null ? phone.phoneWork == null : phoneWork.equals(phone.phoneWork));
		retVal = retVal && (phoneFax == null ? phone.phoneFax == null : phoneFax.equals(phone.phoneFax));
		retVal = retVal && (phoneMobile == null ? phone.phoneMobile == null : phoneMobile.equals(phone.phoneMobile));
		return retVal;
	}

	@Override public int hashCode() {
		int result = 17;
		result = 31 * result + (phoneHome == null ? 0 : phoneHome.hashCode());
		result = 31 * result + (phoneMobile == null ? 0 : phoneMobile.hashCode());
		result = 31 * result + (phoneWork == null ? 0 : phoneWork.hashCode());
		result = 31 * result + (phoneFax == null ? 0 : phoneFax.hashCode());
		return result;
	}

	/**
	 * Returns the contents of a phone object
	 */
	@Override public String toString() {
		return "phoneHome Phone  " + phoneHome + " phoneMobile Phone " + phoneMobile + "  phoneWork phone: " + phoneWork
				+ "  phoneFax: " + phoneFax;
	}
}