package edu.nyu.cs.pqs.AddressBook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.pqs.AddressBook.AddressBook;
import edu.nyu.cs.pqs.AddressBook.AddressBookEntry;
import edu.nyu.cs.pqs.AddressBook.EntryAttribute;

//TODO assertEquals do for objects - not for strings
public class SearchTests {

	private AddressBook ab; 
	private AddressBook emptyStringAb;
	private AddressBook emptyAb;

	private String filename = null;
	private String filenameEmptyStringAb = null;
	private String filenameEmptyAb = null;

	private AddressBookEntry entry1;
	private AddressBookEntry same1;
	private AddressBookEntry same2;
	private AddressBookEntry emptyStr;

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

		ab.saveAddressListToFile(filename);

		//creating an AddressBook with empty Strings
		emptyStringAb.addAnEntry("", "", 0, "", "");
		emptyStringAb.saveAddressListToFile(filenameEmptyStringAb);

		//an Empty address book
		emptyAb.saveAddressListToFile(filenameEmptyAb);

		//Creating AddressBook Entries for validation
		entry1 = new AddressBookEntry();
		entry1.setName("Test1");
		entry1.setPostalAddress("104 Romaine");
		entry1.setPhoneNumber(123456);
		entry1.setEmail("test@gmail.com");
		entry1.setNote("testnote");

		emptyStr = new AddressBookEntry();
		emptyStr.setName("");
		emptyStr.setPostalAddress("");
		emptyStr.setPhoneNumber(0);
		emptyStr.setEmail("");
		emptyStr.setNote("");

		same1 = new AddressBookEntry();
		same1.setName("Same");
		same1.setPostalAddress("Same");
		same1.setPhoneNumber(1234);
		same1.setEmail("Same");
		same1.setNote("Same");

		same2 = new AddressBookEntry();
		same2.setName("Same");
		same2.setPostalAddress("Same");
		same2.setPhoneNumber(1234);
		same2.setEmail("Same");
		same2.setNote("Same");
	} 

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void searchWithNullEntryAttribute(){
		ab.searchAddressBook(filename, "Test1", null);
	}

	//should we have assert here?
	@Test(expected = IOException.class)
	public void searchWithInvalidFilename(){
		assertTrue("Search Result List should be empty for Address Attribute",
				(ab.searchAddressBook("fjksdf","test",EntryAttribute.ADDRESS)).isEmpty());

	}

	@Test
	public void searchEmptyAddressBookShouldReturnEmptyList(){
		assertTrue("Search Result List should be empty for Email Attribute",
				(emptyAb.searchAddressBook(filenameEmptyAb, "test", EntryAttribute.EMAIL)).isEmpty());
	}

	@Test
	public void searchEmptyStringValuesInEmail() {
		List<AddressBookEntry> result = emptyStringAb.searchAddressBook(filenameEmptyStringAb, "",
				EntryAttribute.EMAIL);
		
		AddressBookEntry expected = emptyStr;
		
		assertTrue("Search List(with Email Attribute)must have contacts "
				+ "having Empty Strings as values",(result.size() == 1));
		assertEquals("Search Results should match",expected,result.get(0));
	}

	@Test
	public void searchContactsHavingEmptyStringValuesInPhone() {
		List<AddressBookEntry> result = emptyStringAb.searchAddressBook(
				filenameEmptyStringAb, "",EntryAttribute.PHONE);
		
		AddressBookEntry expected = emptyStr;

		assertTrue("Search List(with Phone Attribute)must have "
				+ "contacts having Empty Strings as values",result.size() == 1);
		assertEquals("Search Results should match",expected,result.get(0));
	}

	@Test
	public void searchContactsHavingEmptyStringValuesInName() {
		List<AddressBookEntry> result = emptyStringAb.searchAddressBook(
				filenameEmptyStringAb, "",EntryAttribute.NAME);

		AddressBookEntry expected = emptyStr;

		assertTrue("Search List(with Name Attribute)must have "
				+ "contacts having Empty Strings as values",result.size() == 1);
		assertEquals("Search Results should match",expected,result.get(0));
	}

	@Test
	public void searchContactsHavingEmptyStringValueInNote() {
		List<AddressBookEntry> result = emptyStringAb.searchAddressBook(
				filenameEmptyStringAb, "",EntryAttribute.NOTE);
		
		AddressBookEntry expected = emptyStr;

		assertTrue("Search List(with Note Attribute)must "
				+ "have contacts having Empty Strings as values",result.size() == 1);
		assertEquals("Search Results should match",expected,result.get(0));
	}

	@Test
	public void searchContactsHavingEmptyStringInAddress() {
		List<AddressBookEntry> result = emptyStringAb.searchAddressBook(
				filenameEmptyStringAb, "",EntryAttribute.ADDRESS);
		
		AddressBookEntry expected = emptyStr;

		assertTrue("Search List(with Address Attribute)must "
				+ "have contacts having Empty Strings as values",result.size() == 1);
		assertEquals("Search Results should match",expected,result.get(0));
	}

	/**
	 * Test for multiple matchings of the search text across the fields of an entry
	 */
	@Test
	public void searchForMultipleMatchingsAcrossAddresses(){
		
		List<AddressBookEntry> result = ab.searchAddressBook(filename, "Same", EntryAttribute.ADDRESS);
		List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
		expected.add(same1);
		expected.add(same2);
		
		assertTrue("Result list should contain 2",
				(result.size() == 2));
		assertEquals("Results should match",expected,result);
	}

	@Test
	public void searchForMultipleMatchingsAcrossNames(){
		List<AddressBookEntry> result = ab.searchAddressBook(filename, "Same", EntryAttribute.NAME);
		List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
		expected.add(same1);
		expected.add(same2);
		
		assertTrue("Result list should contain 2",
				(result.size() == 2));
		assertEquals("Results should match",expected,result);
	}

	@Test
	public void searchForMultipleMatchingsAcrossEmails(){
		List<AddressBookEntry> result = ab.searchAddressBook(filename, "Same", EntryAttribute.EMAIL);
		List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
		expected.add(same1);
		expected.add(same2);
		
		assertTrue("Result list should contain 2",result.size() == 2);
		assertEquals("Results should match",expected,result);
	}

	@Test
	public void searchForMultipleMatchingsAcrossPhones(){
		List<AddressBookEntry> result = ab.searchAddressBook(filename, "Same", EntryAttribute.PHONE);
		List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
		expected.add(same1);
		expected.add(same2);
		
		assertTrue("Result list should contain 2",result.size() == 2);
		assertEquals("Results should match",expected,result);
	}

	@Test
	public void searchForMultipleMatchingsAcrossNotes(){
		List<AddressBookEntry> result = ab.searchAddressBook(filename, "Same", EntryAttribute.NOTE);
		List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
		expected.add(same1);
		expected.add(same2);
		
		assertTrue("Result list should contain 2",result.size() == 2);
		assertEquals("Results should match",expected,result);
	}

	/**
	 * Test for searching null values in an entry. 
	 * Test passes if result is empty.
	 */
	@Test
	public void searchNullQueryShouldReturnNull(){
		assertTrue("Result list should NOT be empty",
				(ab.searchAddressBook(filename, null, EntryAttribute.NAME)).isEmpty());
		assertTrue("Result list should NOT be empty",
				(ab.searchAddressBook(filename, null, EntryAttribute.PHONE)).isEmpty());
		assertTrue("Result list should NOT be empty",
				(ab.searchAddressBook(filename, null, EntryAttribute.ADDRESS)).isEmpty());
		assertTrue("Result list should NOT be empty",
				(ab.searchAddressBook(filename, null, EntryAttribute.EMAIL)).isEmpty());
		assertTrue("Result list should NOT be empty",
				(ab.searchAddressBook(filename, null, EntryAttribute.NOTE)).isEmpty());
	}


	@Test
	public void searchNonExistentValuesInName(){
		assertTrue("Result list should be empty",
				(ab.searchAddressBook(filename, "IDontExist", EntryAttribute.NAME)).isEmpty());
	}

	@Test
	public void searchNonExistentValuesInAddress(){
		assertTrue("Result list should be empty",
				(ab.searchAddressBook(filename, "IDontExist", EntryAttribute.ADDRESS)).isEmpty());
	}

	@Test
	public void searchNonExistentValuesInEmail(){
		assertTrue("Result list should be empty",
				(ab.searchAddressBook(filename, "IDontExist", EntryAttribute.EMAIL)).isEmpty());
	}

	@Test
	public void searchNonExistentValuesInNote(){
		assertTrue("Result list should be empty",
				(ab.searchAddressBook(filename, "IDontExist", EntryAttribute.NOTE)).isEmpty());
	}

	@Test
	public void searchNonExistentValuesInPhone(){
		assertTrue("Result list should be empty",
				(ab.searchAddressBook(filename, "1234599489358", EntryAttribute.PHONE)).isEmpty());
	}

	/**
	 * Search for a name with Invalid Entry attributes like PHONE,ADDRESS etc.
	 * 
	 */
	@Test
	public void searchNameWithValidEntryAttribute(){
		List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
		expected.add(entry1);
		
		List<AddressBookEntry> result = ab.searchAddressBook(filename,entry1.getName(),EntryAttribute.NAME);
		
		assertTrue("Size of the searched list should be 1",result.size() == 1);
		assertEquals("Results should match",expected,result);
	}

	@Test
	public void searchPhoneWithValidEntryAttribute(){
		List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
		expected.add(entry1);
		
		List<AddressBookEntry> result = ab.searchAddressBook(filename,String.valueOf(entry1.getPhoneNumber()),EntryAttribute.PHONE);
		
		assertTrue("Size of the searched list should be 1",result.size() == 1);
		assertEquals("Results should match",expected,result);
	}

	@Test
	public void searchEmailWithValidEntryAttribute(){
		List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
		expected.add(entry1);
		
		List<AddressBookEntry> result = ab.searchAddressBook(filename,entry1.getEmail(),EntryAttribute.EMAIL);
		
		assertTrue("Size of the searched list should be 1",result.size() == 1);
		assertEquals("Results should match",expected,result);
	}

	@Test
	public void searchNoteWithValidEntryAttribute(){
		List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
		expected.add(entry1);
		
		List<AddressBookEntry> result = ab.searchAddressBook(filename,entry1.getNote(),EntryAttribute.NOTE);
		
		assertTrue("Size of the searched list should be 1",result.size() == 1);
		assertEquals("Results should match",expected,result);
	}

	@Test
	public void searchAddressWithValidEntryAttribute(){
		List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
		expected.add(entry1);
		
		List<AddressBookEntry> result = ab.searchAddressBook(filename,entry1.getPostalAddress(),EntryAttribute.ADDRESS);
		
		assertTrue("Size of the searched list should be 1",result.size() == 1);
		assertEquals("Results should match",expected,result);
	}
}