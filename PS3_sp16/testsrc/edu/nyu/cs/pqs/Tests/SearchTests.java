package edu.nyu.cs.pqs.Tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.pqs.AddressBook.AddressBook;
import edu.nyu.cs.pqs.AddressBook.EntryAttribute;


public class SearchTests {

	private AddressBook ab; 
	private AddressBook emptyStringAb;
	private AddressBook emptyAb;

	private String filename = null;
	private String filenameEmptyStringAb = null;
	private String filenameEmptyAb = null;
	
	@Before
	public void setUp() throws Exception {
		ab = new AddressBook();
		emptyStringAb = new AddressBook();
		emptyAb = new AddressBook();
		
		filename = "addressbook.txt";
		filenameEmptyStringAb = "abEmptyStrings.txt";
		filenameEmptyAb = "emptyab.txt";
		
		// creating some contacts for the address book
		ab.addAnEntry("Test1", "104 Romaine", 123456, "test@gmail.com", "testnote");
		ab.addAnEntry("Test4", "107 Romaine", 1234569, "test3@gmail.com", "testnote3");
		
		// creating contacts with almost same values in all Textfields
		ab.addAnEntry("Same", "Same", 1234, "Same", "Same");
		ab.addAnEntry("Same", "Same", 1234, "Same", "Same");
		
		//creating a null entry 
		ab.addAnEntry(null, null, 0, null, null);
		
		//creating entry with empty strings
		ab.addAnEntry("Test1", "104 Romaine", 123456, "test@gmail.com", "testnote");
		ab.addAnEntry("Test3", "106 Romaine", 1234568, "test2@gmail.com", "testnote2");
		ab.addAnEntry("Test4", "107 Romaine", 1234569, "test3@gmail.com", "testnote3");
		
		ab.saveAddressListToFile(filename);
		
		//creating an AddressBook with empty Strings
		emptyStringAb.addAnEntry("", "", 0, "", "");
		emptyStringAb.saveAddressListToFile(filenameEmptyStringAb);
		emptyAb.saveAddressListToFile(filenameEmptyAb);
	} 

	@After
	public void tearDown() throws Exception {
	}

	//TODO ...how to deal with this case???
	@Test(expected = IOException.class)
	public void searchWithInvalidFilename(){
		assertTrue("Search Result List should be empty for Address Attribute",
				(ab.searchAddressBook("fjksdf","test",EntryAttribute.ADDRESS)).isEmpty() == true);
		assertTrue("Search Result List should be empty for Email Attribute",
				(ab.searchAddressBook("fjksdf","test",EntryAttribute.EMAIL)).isEmpty() == true);
		assertTrue("Search Result List should be empty for Name Attribute",
				(ab.searchAddressBook("fjksdf","test",EntryAttribute.NAME)).isEmpty() == true);
		assertTrue("Search Result List should be empty for Phone Attribute",
				(ab.searchAddressBook("fjksdf","test",EntryAttribute.PHONE)).isEmpty() == true);
		assertTrue("Search Result List should be empty for Note Attribute",
				(ab.searchAddressBook("fjksdf","test",EntryAttribute.NOTE)).isEmpty() == true);
	}
	
	@Test
	public void searchEmptyAddressBook(){
		assertTrue("Search Result List should be empty for Email Attribute",
				(emptyAb.searchAddressBook(filenameEmptyAb, "test", EntryAttribute.EMAIL)).isEmpty() == true);
		assertTrue("Search Result List should be empty for Phone Attribute",
				(emptyAb.searchAddressBook(filenameEmptyAb, "test", EntryAttribute.PHONE)).isEmpty() == true);
		assertTrue("Search Result List should be empty for name Attribute",
				(emptyAb.searchAddressBook(filenameEmptyAb, "test", EntryAttribute.NAME)).isEmpty() == true);
		assertTrue("Search Result List should be empty for note Attribute",
				(emptyAb.searchAddressBook(filenameEmptyAb, "test", EntryAttribute.NOTE)).isEmpty() == true);
		assertTrue("Search Result List should be empty for phone Attribute",
				(emptyAb.searchAddressBook(filenameEmptyAb, "test", EntryAttribute.PHONE)).isEmpty() == true);
	}
	
	@Test
	public void searchForContactsHavingEmptyStringValues() {
		assertTrue("Search List(with Email Attribute)must have contacts having Empty Strings as values",
			(emptyStringAb.searchAddressBook(filenameEmptyStringAb, "",
			EntryAttribute.EMAIL).size() != 0));
		
		assertTrue("Search List(with Phone Attribute)must have contacts having Empty Strings as values",
				(emptyStringAb.searchAddressBook(filenameEmptyStringAb, "",
				EntryAttribute.PHONE).size() != 0));
		
		assertTrue("Search List(with Name Attribute)must have contacts having Empty Strings as values",
				(emptyStringAb.searchAddressBook(filenameEmptyStringAb, "",
				EntryAttribute.NAME).size() != 0));
		
		assertTrue("Search List(with Note Attribute)must have contacts having Empty Strings as values",
				(emptyStringAb.searchAddressBook(filenameEmptyStringAb, "",
				EntryAttribute.NOTE).size() != 0));
		
		assertTrue("Search List(with Address Attribute)must have contacts having Empty Strings as values",
				(emptyStringAb.searchAddressBook(filenameEmptyStringAb, "",
				EntryAttribute.ADDRESS).size() != 0));
	}
	
	/**
	 * Test for multiple matchings of the search text across the fields of an entry
	 */
	@Test
	public void searchForMultipleMatchingsAcrossAddresses(){
		assertTrue("Result list should contain 2",
				(ab.searchAddressBook(filename, "Same", EntryAttribute.ADDRESS)).size() == 2);
	}
	
	@Test
	public void searchForMultipleMatchingsAcrossNames(){
		assertTrue("Result list should contain 2",
				(ab.searchAddressBook(filename, "Same", EntryAttribute.NAME)).size() == 2);
	}
	
	@Test
	public void searchForMultipleMatchingsAcrossEmails(){
		assertTrue("Result list should contain 2",
				(ab.searchAddressBook(filename, "Same", EntryAttribute.EMAIL)).size() == 2);
	}
	
	@Test
	public void searchForMultipleMatchingsAcrossPhones(){
		assertTrue("Result list should contain 2"
				,(ab.searchAddressBook(filename, "1234", EntryAttribute.PHONE)).size() == 2);
	}
	
	@Test
	public void searchForMultipleMatchingsAcrossNotes(){
		assertTrue("Result list should contain 2",
				(ab.searchAddressBook(filename, "Same", EntryAttribute.NOTE)).size() == 2);
	}
	
	/**
	 * Test for searching null values in an entry. 
	 * Test passes if the entries with null values(while creation) are returned.
	 */
	@Test
	public void searchNullValues_ShouldReturnContactsHavingNulls(){
		assertTrue("Result list should NOT be empty",
				(ab.searchAddressBook(filename, null, EntryAttribute.NAME)).isEmpty() == false);
		assertTrue("Result list should NOT be empty",
				(ab.searchAddressBook(filename, null, EntryAttribute.PHONE)).isEmpty() == false);
		assertTrue("Result list should NOT be empty",
				(ab.searchAddressBook(filename, null, EntryAttribute.ADDRESS)).isEmpty() == false);
		assertTrue("Result list should NOT be empty",
				(ab.searchAddressBook(filename, null, EntryAttribute.EMAIL)).isEmpty() == false);
		assertTrue("Result list should NOT be empty",
				(ab.searchAddressBook(filename, null, EntryAttribute.NOTE)).isEmpty() == false);
	}
	
	
	@Test
	public void searchNonExistentValuesInName(){
		assertTrue("Result list should be empty",
				(ab.searchAddressBook(filename, "IDontExist", EntryAttribute.NAME)).isEmpty() == true);
	}
	
	@Test
	public void searchNonExistentValuesInAddress(){
		assertTrue("Result list should be empty",
				(ab.searchAddressBook(filename, "IDontExist", EntryAttribute.ADDRESS)).isEmpty() == true);
	}
	
	@Test
	public void searchNonExistentValuesInEmail(){
		assertTrue("Result list should be empty",
				(ab.searchAddressBook(filename, "IDontExist", EntryAttribute.EMAIL)).isEmpty() == true);
	}
	
	@Test
	public void searchNonExistentValuesInNote(){
		assertTrue("Result list should be empty",
				(ab.searchAddressBook(filename, "IDontExist", EntryAttribute.NOTE)).isEmpty() == true);
	}
	
	@Test
	public void searchNonExistentValuesInPhone(){
		assertTrue("Result list should be empty",
				(ab.searchAddressBook(filename, "1234599489358", EntryAttribute.PHONE)).isEmpty() == true);
	}

	/**
	 * Search for a name with Invalid Entry attributes like PHONE,ADDRESS etc.
	 * 
	 */
	@Test
	public void searchNameWithInvalidEntryAttribute(){
		assertTrue("Size of the searched list with Phone Attribute should be zero",
				(ab.searchAddressBook(filename,"Test1",EntryAttribute.PHONE)).isEmpty());
		assertTrue("Size of the searched list with Address attribute should be zero",
				(ab.searchAddressBook(filename,"Test1",EntryAttribute.ADDRESS)).isEmpty());
		assertTrue("Size of the searched list with note attribute should be zero",
				(ab.searchAddressBook(filename,"Test1",EntryAttribute.NOTE)).isEmpty());
		assertTrue("Size of the searched list with Email attribute should be zero",
				(ab.searchAddressBook(filename,"Test1",EntryAttribute.EMAIL)).isEmpty());
	}
	
	@Test
	public void searchNameWithValidEntryAttribute(){
		assertTrue("Size of the searched list should be 2",
				(ab.searchAddressBook(filename,"Test1",EntryAttribute.NAME)).size() == 2);
	}
	
	@Test
	public void searchPhoneWithInvalidEntryAttribute(){
		assertTrue("Size of the searched list with Name attribute should be zero",
				(ab.searchAddressBook(filename,"123456",EntryAttribute.NAME)).isEmpty());
		assertTrue("Size of the searched list with Address Attribute should be zero",
				(ab.searchAddressBook(filename,"123456",EntryAttribute.ADDRESS)).isEmpty());
		assertTrue("Size of the searched list with Note attribute should be zero",
				(ab.searchAddressBook(filename,"123456",EntryAttribute.NOTE)).isEmpty());
		assertTrue("Size of the searched list with Email attribute should be zero",
				(ab.searchAddressBook(filename,"123456",EntryAttribute.EMAIL)).isEmpty());
	}
	
	@Test
	public void searchPhoneWithValidEntryAttribute(){
		assertTrue("Size of the searched list should be 2",
				(ab.searchAddressBook(filename,"123456",EntryAttribute.PHONE)).size() == 2);
	}
	
	@Test
	public void searchEmailWithInvalidEntryAttribute(){
		assertTrue("Size of the searched list with name attribute should be zero",
				(ab.searchAddressBook(filename,"test2@gmail.com",EntryAttribute.NAME)).isEmpty());
		assertTrue("Size of the searched list with address attribute should be zero",
				(ab.searchAddressBook(filename,"test2@gmail.com",EntryAttribute.ADDRESS)).isEmpty());
		assertTrue("Size of the searched list with note attribute should be zero",
				(ab.searchAddressBook(filename,"test2@gmail.com",EntryAttribute.NOTE)).isEmpty());
		assertTrue("Size of the searched list with phone attribute should be zero",
				(ab.searchAddressBook(filename,"test2@gmail.com",EntryAttribute.PHONE)).isEmpty());
	}
	
	@Test
	public void searchEmailWithValidEntryAttribute(){
		assertTrue("Size of the searched list should be 1",
				(ab.searchAddressBook(filename,"test2@gmail.com",EntryAttribute.EMAIL)).size() == 1);
	}

	@Test
	public void searchNoteWithInvalidEntryAttribute(){
		assertTrue("Size of the searched list with name attribute should be zero",
				(ab.searchAddressBook(filename,"testnote2",EntryAttribute.NAME)).isEmpty());
		assertTrue("Size of the searched list with address attribute should be zero",
				(ab.searchAddressBook(filename,"testnote2",EntryAttribute.ADDRESS)).isEmpty());
		assertTrue("Size of the searched list with email attribute should be zero",
				(ab.searchAddressBook(filename,"testnote2",EntryAttribute.EMAIL)).isEmpty());
		assertTrue("Size of the searched list with phone attribute should be zero",
				(ab.searchAddressBook(filename,"testnote2",EntryAttribute.PHONE)).isEmpty());
	}
	
	@Test
	public void searchNoteWithValidEntryAttribute(){
		assertTrue("Size of the searched list should be 1",
				(ab.searchAddressBook(filename,"testnote2",EntryAttribute.NOTE)).size() == 1);
	}
	
	@Test
	public void searchAddressWithInvalidEntryAttribute(){
		assertTrue("Size of the searched list with name attribute should be zero",
				(ab.searchAddressBook(filename,"106 Romaine",EntryAttribute.NAME)).isEmpty());
		assertTrue("Size of the searched list with note attribute should be zero",
				(ab.searchAddressBook(filename,"106 Romaine",EntryAttribute.NOTE)).isEmpty());
		assertTrue("Size of the searched list with email attribute should be zero",
				(ab.searchAddressBook(filename,"106 Romaine",EntryAttribute.EMAIL)).isEmpty());
		assertTrue("Size of the searched list with phone attribute should be zero",
				(ab.searchAddressBook(filename,"106 Romaine",EntryAttribute.PHONE)).isEmpty());
	}
	
	@Test
	public void searchAddressWithValidEntryAttribute(){
		assertTrue("Size of the searched list should be 1",
				(ab.searchAddressBook(filename,"106 Romaine",EntryAttribute.ADDRESS)).size() == 1);
	}
}
