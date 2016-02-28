package edu.nyu.hr970.pqshw1;

import java.util.ArrayList;

/**
 * Email class contains information that a contact entry can have about the email addresses of a person
 * Email can contain the following information
 * 	- Home
 * 	- Work
 *  - Other
 * @author himaja
 *
 */
public class Email {
	private final String emailHome;
	private final String emailWork;
	private final String emailOther;

	/**
	 * Builder class (Inner) to create an object. This class is used to create a Email object which has to be constructed based on 
	 * number of optional parameters passed.
	 * 
	 * Examples to create a Email object -
	 * 
	 * email = new Email.emailBuilder().emailHome("abc@def.com").emailWork("shf@asdf.com").
					emailOther("abc@def.com").build();
	 * email = new Email.emailBuilder().emailOther("abc@def.com").build();
	 */
	public static class emailBuilder {
		private String emailHome = "";
		private String emailWork = "";
		private String emailOther = "";

		public emailBuilder emailHome(String emailHome) {
			this.emailHome = emailHome;
			return this;
		}

		public emailBuilder emailWork(String emailWork) {
			this.emailWork = emailWork;
			return this;
		}

		public emailBuilder emailOther(String emailOther) {
			this.emailOther = emailOther;
			return this;
		}

		/**
		 * This function builds the object of the class Email. A check is performed to check if the object built is valid. If the check fails
		 * an Illegal state exception is thrown.
		 * @return Email Object
		 */
		public Email build() {
			if (!(isValid(this))) {
				throw new IllegalStateException("Not a valid Email address");
			}
			return new Email(this);
		}
		
		/**
		 * Function to check if the email address is a valid entry. Email addresses should have an @ in the string.
		 * A check is performed on the object just before it is sent to build function in the parent class.
		 * @param builder - Pass the builder object built using Email builder class 
		 * @return True if the check passes, otherwise false
		 */
		private boolean isValid(emailBuilder builder){
			boolean retVal = true;
			if(!builder.emailHome.equals("")){
				retVal = retVal && (builder.emailHome.contains("@") ? true : false);
			}
			if(!builder.emailOther.equals("")){
				//System.out.println();
				retVal = retVal && (builder.emailOther.contains("@") ? true : false);
			}
			if(!builder.emailWork.equals("")){
				retVal = retVal && (builder.emailWork.contains("@") ? true : false);
			}
			return retVal;
			
		}
	}
	
	private Email(emailBuilder builder) {
		this.emailHome = builder.emailHome;
		this.emailOther = builder.emailOther;
		this.emailWork = builder.emailWork;
	}

	public String getemailHomeEmail(Email email) {
		return email.emailHome;
	}

	public String getemailOtherEmail(Email email) {
		return email.emailOther;
	}

	public String getemailWorkEmail(Email email) {
		return email.emailWork;
	}

	/**
	 * Check if Email object contains a specified string in any of its entries 
	 * @param searchString - String to be searched for
	 * @return True if the string is found, otherwise return false
	 */
	boolean search(String searchString){
		boolean retVal = false;
		retVal = retVal || (emailHome != null ? emailHome.toLowerCase().contains(searchString) : retVal);
		retVal = retVal || (emailWork != null ? emailWork.toLowerCase().contains(searchString) : retVal);
		retVal = retVal || (emailOther != null ? emailOther.toLowerCase().contains(searchString) : retVal);
		
		return retVal;
	}
	
	/**
	 * Gets the list of types of Email a contact can have
	 * @return returns an ArrayList of Email address types that a contact can have
	 */
	public ArrayList<String> getEmailTypes() {
		ArrayList<String> retList = new ArrayList<String>();
		retList.add("emailHome");
		retList.add("emailWork");
		retList.add("emailOther");
		return retList;
	}

	/**
	 * Gets the list of entries that an Email object contains within a contact object
	 * @param Email - Takes the Email object within a contact as a parameter
	 * @return - returns an ArrayList of values which are contained by this email object
	 */
	public ArrayList<String> getEmailEntries(){
		ArrayList<String> retList = new ArrayList<String>();
		retList.add(this.emailHome);
		retList.add(this.emailWork);
		retList.add(this.emailOther);
		return retList;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null){
			return false;
		}
		if (o == this) {
			return true;
		}
		if (!(o instanceof Email)) {
			return false;
		}
		boolean retVal = true;
		Email email = (Email) o;
		retVal = retVal && (emailHome == null ? email.emailHome == null : emailHome.equals(email.emailHome));
		retVal = retVal && (emailWork == null ? email.emailWork == null : emailWork.equals(email.emailWork));
		retVal = retVal && (emailOther == null ? email.emailOther == null : emailOther.equals(email.emailOther));
		return retVal;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (emailHome == null ? 0 : emailHome.hashCode());
		result = 31 * result + (emailWork == null ? 0 : emailWork.hashCode());
		result = 31 * result + (emailOther == null ? 0 : emailOther.hashCode());
		return result;
	}

	/**
	 * returns the contents of an email object
	 */
	@Override
	public String toString() {
		return "emailHome : " + emailHome + " emailWork : " + emailWork + " emailOther : " + emailOther;
	}
}