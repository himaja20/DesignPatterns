package edu.nyu.hr970.pqshw1;

import java.util.ArrayList;

/**
 * Contact class contains the type of information that a contact can have in an address book.
 * A contact can have the following information -
 * 	- Name
 * 	- Phone
 * 	- Email
 * 	- Address
 * 	- Notes
 * @author himaja
 *
 */
public class Contact {
	private final Name cName;
	private final Phone cPhone;
	private final Address cAddress;
	private final Email cEmail;
	private final String cNotes;
	
	/**
	 * Builder class (Inner) to create an object. This class is used to create a Address object which has to be constructed based on 
	 * number of optional parameters passed.
	 * 
	 * Examples to create a Address object -
	 * 
	 * contact = new Contact.contactBuilder().cName(name).cPhone(phone).cEmail(email).cAddress(address).build();
	 * contact = new Contact.contactBuilder().cName(name).cPhone(phone).build();
	 */
	public static class contactBuilder{
		private Name cName;
		private Phone cPhone;
		private Email cEmail;
		private Address cAddress;
		private String cNotes;

		public contactBuilder cName(Name cName){
			this.cName = cName;
			return this;
		}
		
		public contactBuilder cPhone(Phone cPhone){
			this.cPhone = cPhone;
			return this;
		}
		
		public contactBuilder cEmail(Email cEmail){
			this.cEmail = cEmail;
			return this;
		}
		
		public contactBuilder cAddress(Address cAddress){
			this.cAddress = cAddress;
			return this;
		}
		
		public contactBuilder cNotes(String cNotes){
			this.cNotes = cNotes;
			return this;
		}
		
		public Contact build(){
			return new Contact(this);
		}
	}
	
	private Contact(contactBuilder builder){
		this.cName = builder.cName;
		this.cPhone = builder.cPhone;
		this.cAddress = builder.cAddress;
		this.cEmail = builder.cEmail;
		this.cNotes = builder.cNotes;
	}
	
	public Name getName(Contact contact){
		return this.cName;
	}
	
	public Phone getPhone(Contact contact){
		return this.cPhone;
	}
	
	public Email getEmail(Contact contact){
		return this.cEmail;
	}
	
	public Address getAddress(Contact contact){
		return this.cAddress;
	}
	
	public String getNotes(Contact contact){
		return this.cNotes;
	}
	
	/**
	 * Search for a contact which contains a specified string in any of its contact entries 
	 * @param searchString - String that should be searched for.
	 * @return returns True if the search is successful, otherwise returns false
	 */
	boolean search(String searchString){
		boolean retVal = false;
	// Check the string in the following order and return if string is found in at least one field
		if(this.cName != null){
			retVal = this.cName.search(searchString.toLowerCase());
			if (retVal){
				return retVal;
			}
		}
		
		if(this.cPhone != null){
			retVal = this.cPhone.search(searchString.toLowerCase());
			if (retVal){
				return retVal;
			}
		}
		
		if(this.cEmail != null){
			retVal = this.cEmail.search(searchString.toLowerCase());
			if(retVal){
				return retVal;
			}
		}
		
		if(this.cAddress != null){
			retVal = this.cAddress.search(searchString.toLowerCase());
			if(retVal){
				return retVal;
			}
		}	
		
		if(this.cNotes != null){
			retVal = this.cNotes.toLowerCase().contains(searchString.toLowerCase());
			if(retVal){
				return true;
			}
		}
		
		return retVal;
	}
	
/**
 * Gets the list of contact entries(values stored for each contact)  
 * @return an ArrayList of values stored in the contact object
 */
	public ArrayList<String> getContactEntries(){
		ArrayList<String> retList = new ArrayList<String>();
	// if object is null, get default entry values
	// else get the entries of the current object
		if (cName == null){  
			Name cName = new Name.nameBuilder().build();
			retList.addAll(cName.getNameEntries());
		}
		else{
			retList.addAll(this.cName.getNameEntries());
		}
		if(cPhone == null){  
			Phone cPhone = new Phone.phoneBuilder().build();
			retList.addAll(cPhone.getPhoneEntries());
		}
		else{
			retList.addAll(this.cPhone.getPhoneEntries());
		}
		if (cEmail == null){
			Email cEmail = new Email.emailBuilder().build();
			retList.addAll(cEmail.getEmailEntries());
		}
		else{
			retList.addAll(this.cEmail.getEmailEntries());
		}
		if (cAddress ==  null){
			Address address = new Address.addressBuilder().build();
			retList.addAll(address.getAddressEntries());
		}
		else{
			retList.addAll(this.cAddress.getAddressEntries());
		}
		if (cNotes == null){
			retList.add("");
		}
		else{
			retList.add(this.cNotes);
		}
		return retList;
	}
	
	@Override public boolean equals(Object o){
		if (o == null){
			return false;
		}
		if (o == this){
			return true;
		}
		if(!(o instanceof Contact)){
			return false;
		}
		boolean retVal = true;
		Contact contact = (Contact)o;
		retVal = retVal && (cName == null ? contact.cName == null : cName.equals(contact.cName));
		retVal = retVal && (cPhone == null ? contact.cPhone == null : cPhone.equals(contact.cPhone));
		retVal = retVal && (cEmail == null ? contact.cEmail == null : cEmail.equals(contact.cEmail));
		retVal = retVal && (cAddress == null ? contact.cAddress == null : cAddress.equals(contact.cAddress));
		retVal = retVal && (cNotes == null ? contact.cNotes == null : cNotes.equals(contact.cNotes));
		return retVal;
	}
	
	@Override public int hashCode(){
		int result = 17;
		result = 31 * result + cName.hashCode();
		result = 31 * result + cPhone.hashCode();
		result = 31 * result + cEmail.hashCode();
		result = 31 * result + cAddress.hashCode();
		result = 31 * result + cNotes.hashCode();
		return result;
	}

	/**
	 * Prints the name,phone,email,address and notes values of a contact object
	 */
	@Override public String toString(){
		String name = cName == null ? "null" : cName.toString();
		String phone = cPhone == null ? "null" : cPhone.toString();
		String email = cEmail == null ? "null" : cEmail.toString();
		String address = cAddress == null ? "null" : cAddress.toString();
		String notes = cNotes == null ? "null" : cNotes.toString();
		
		return name+"\t\t"+phone+"\t\t"+email+"\t\t"+address+"\t\t"+"Notes:" +notes;
	}
}
