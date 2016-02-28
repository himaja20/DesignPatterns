package edu.nyu.hr970.pqshw1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * @author himaja
 * Address Book class will facilitate operations of 
 * 	- Creating an Address book
 * 	- Adding a contact to the address book
 * 	- Removing a contact from the address book
 * 	- Search for a contact with by any of the contact properties.
 * 	- Save the address to a file
 * 	- Retrieve an address book from a file
 * 
 * Address Book returns a singleton object as any application should have only one address book entity
 *
 */
public class AddressBook {
	
	private static final AddressBook abInstance = new AddressBook(); // creates a singleton instance of address book
	ArrayList<Contact> contactList = new ArrayList<Contact>(); // List to store all contacts of this address book 
	
	/**
	 * Gets a singleton instance of Address Book
	 * @return
	 */
	public static AddressBook getInstance(){
		return abInstance;
	}
	
	/**
	 * Gets the list of all contacts in the address book
	 * @return An ArrayList containing the contact objects in the address book
	 */
	public ArrayList<Contact> getContactList(){
		return contactList;
	}
	
	/**
	 * Adds a contact to Address book. Address book allows duplicate contacts.
	 * @param contact - a contact object that should be added to the address book
	 */
	public void addContact(Contact contact){
		if(contact == null){
			return;
		}
		contactList.add(contact);
	}
	
	/**
	 * Removes a contact from the address book. Removes only one contact at a time. Does not remove duplicate contacts
	 * @param contact - a contact that should be removed. 
	 */
	public void removeContact(Contact contact){
		if (contact == null){
			return;
		}
		contactList.remove(contact);
	}
	
	/**
	 * Allows the user to retrieve an address book from a csv file
	 * @param file - location of the file from which the address book should be retrieved
	 * @return - returns an address book with populated entries
	 * @throws IOException - Throws an exception if file object cannot be resolved 
	 */
	public AddressBook retrieveAddressBookFromFile(File file) throws IOException{
		CSVReader reader = new CSVReader(new FileReader(file), ',');
		String [] nextLine;
		
		Name name;
		Phone phone;
		Email email;
		Address address;
		Contact contact;
					
		Map<String,String> contactInfoMapper = getContactInfoMapper();
		ArrayList<String> contactAttrList = getContactAttributesList();

		//Loading ContactInfoMapper (Hashmap of Contact Attributes and Entries) with corresponding entries from csv
		while((nextLine = reader.readNext()) != null){
			int i = 0;
			for (String elem : contactAttrList){
				contactInfoMapper.put(elem, nextLine[i]);
				i++;
			}
			
			// Building Contact Info objects from Hash map
			name = new Name.nameBuilder().firstName(contactInfoMapper.get("firstName")).lastName(contactInfoMapper.get("lastName")).
					middleName(contactInfoMapper.get("middleName")).nickname(contactInfoMapper.get("nickname")).
					namePrefix(contactInfoMapper.get("namePrefix")).nameSuffix(contactInfoMapper.get("nameSuffix")).build();

			phone = new Phone.phoneBuilder().phoneHome(contactInfoMapper.get("phoneHome")).
					phoneMobile(contactInfoMapper.get("phoneMobile")).phoneWork(contactInfoMapper.get("phoneWork")).
					phoneFax(contactInfoMapper.get("phoneFax")).build();

			email = new Email.emailBuilder().emailHome(contactInfoMapper.get("emailHome")).emailWork(contactInfoMapper.get("emailWork")).
					emailOther(contactInfoMapper.get("emailOther")).build();
			
			address = new Address.addressBuilder().addressHome(contactInfoMapper.get("addressHome")).
					addressWork(contactInfoMapper.get("addressWork")).addressOther(contactInfoMapper.get("addressOther")).build();
			
			//Building contact object
			contact = new Contact.contactBuilder().cName(name).cPhone(phone).cEmail(email).cAddress(address).build();
			
			this.addContact(contact);
		}	
	reader.close();
	return this;
 }
	
	/**
	 * Allows the user to Search for a Contact entry by any of the contact properties.
	 * @param searchString - String that should be searched across all contacts and their properties
	 * @return returns a list of contact for which the search is successful
	 */
	public ArrayList<Contact> searchAddressBook(String searchString){
		// ArrayList to store contacts for which the search was successful
		ArrayList<Contact> searchedContactList = new ArrayList<Contact>();
		Iterator<Contact> abIterator = contactList.iterator();
		
		// Iterator over the contact List to search for the string in every object in the list
		while(abIterator.hasNext()){
			Contact contact = abIterator.next();
			boolean searchResult = contact.search(searchString);
			if(searchResult){
				searchedContactList.add(contact);
			}
		}
		return searchedContactList;
	}
	
	/**
	 * Saves the address book to a file location specified.
	 * @param file - a file object to which the address book should to be saved.
	 * @throws IOException - Throws an Exception if file object is not found
	 */
	public void saveAddressBook(File file) throws IOException{
		Iterator<Contact> abIterator = contactList.iterator();
		CSVWriter writer = new CSVWriter(new FileWriter(file), ',');
    while(abIterator.hasNext()){
    	Contact contact = abIterator.next();
    	// Get the corresponding entries of each contact object to serialize into CSV
    	ArrayList<String> contactEntries = contact.getContactEntries();
    	System.out.println("Size of output is "+ contactEntries.size());
      String[] contactEntryArr = new String[contactEntries.size()];
      contactEntries.toArray(contactEntryArr);
      writer.writeNext(contactEntryArr);
   }
   writer.close();
	}
	
	/**
	 * Gets the Attribute names of a contact object. For example, a name has attributes - first name, last name, middle name with some entries as values. 
	 * @return An ArrayList of attributes of a contact object in the following order -
	 * 				 - Name,Phone,Email,Address,Notes
	 */
	private ArrayList<String> getContactAttributesList(){
		ArrayList<String> contactAttributes = new ArrayList<String>();
		
		// Build an empty object to get the possible attributes of each class
		Name name = new Name.nameBuilder().build();
		Phone phone = new Phone.phoneBuilder().build();
		Email email = new Email.emailBuilder().build();
		Address address = new Address.addressBuilder().build();
		
		//Collect all attributes from each sub-class into a single list
		contactAttributes.addAll(name.getNameTypes());
		contactAttributes.addAll(phone.getPhoneTypes());
		contactAttributes.addAll(email.getEmailTypes());
		contactAttributes.addAll(address.getAddressTypes());
		contactAttributes.add("Notes");
		
		return contactAttributes;
	}
	
	/**
	 * Creates an empty hash map of contact attributes with keys as Attribute names(eg. firstName, lastName, etc.) and values as null.
	 * @return a hashMap with contactAttributes as keys and values null
	 */
	private HashMap<String,String> getContactInfoMapper(){
		HashMap<String,String> mapper = new HashMap<String,String>();
		ArrayList<String> contactAttrList = getContactAttributesList();
		
		// Iterate through the contactAttrList to populate the map with keys as attribute names and values as null
		Iterator<String> caIterator = contactAttrList.iterator();
		while (caIterator.hasNext()){
			String key = caIterator.next();
			mapper.put(key, null);
		}
		return mapper;
	}
} 