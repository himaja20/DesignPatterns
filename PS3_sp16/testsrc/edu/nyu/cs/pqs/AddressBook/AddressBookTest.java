package edu.nyu.cs.pqs.AddressBook;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.pqs.AddressBook.AddressBook;
import edu.nyu.cs.pqs.AddressBook.AddressBookEntry;

public class AddressBookTest {

	private AddressBook ab; 
	private AddressBook emptyAb;

	private String filename = null;
	private String filenameEmptyAb = null;
	private AddressBookEntry entry1;
	private AddressBookEntry entry2;
	private AddressBookEntry entry3;
	private AddressBookEntry NonExistentObject;
	private String entryString1;
	private String entryString2;

	List<AddressBookEntry> entryList = new ArrayList<AddressBookEntry>();
	List<String> entries = new ArrayList<String>();
	File fileAb;
	File fileEmptyAb;

	@Before
	public void setUp() throws Exception {
		ab = new AddressBook();
		emptyAb = new AddressBook();

		filename = "addressbook.txt";
		filenameEmptyAb = "emptyab.txt";

		// creating some contacts for the address book
		entryString1 = "Test1,104 Romaine,123456,test@gmail.com,testnote";
		entryString2 = "Test4,107 Romaine,1234569,test3@gmail.com,testnote3";
		//String entryString3 = "Test6,110 Romaine,4403423,tester45@gmail.com,notes";

		entries.add(entryString1);
		entries.add(entryString2);
		//entries.add(entryString3);

		fileEmptyAb = new File(filenameEmptyAb);

		//Creating AddressBook Entries for validation
		entry1 = new AddressBookEntry();
		entry1.setName("Test1");
		entry1.setPostalAddress("104 Romaine");
		entry1.setPhoneNumber(123456);
		entry1.setEmail("test@gmail.com");
		entry1.setNote("testnote");

		entry2 = new AddressBookEntry();
		entry2.setName("Test4");
		entry2.setPostalAddress("107 Romaine");
		entry2.setPhoneNumber(1234569);
		entry2.setEmail("test3@gmail.com");
		entry2.setNote("testnote3");

		//		entry3 = new AddressBookEntry();
		//		entry3.setName("Test6");
		//		entry3.setPostalAddress("110 Romaine");
		//		entry3.setPhoneNumber(4403423);
		//		entry3.setEmail("tester45@gmail.com");
		//		entry3.setNote("notes");

		NonExistentObject = new AddressBookEntry();
		NonExistentObject.setName("This");
		NonExistentObject.setPostalAddress("object does not");
		NonExistentObject.setPhoneNumber(123456);
		NonExistentObject.setEmail("exist@gmail.com");
		NonExistentObject.setNote("in the addressbook");

		//creating an entryList of these objects for validation
		entryList.add(entry1);
		entryList.add(entry2);
		//entryList.add(entry3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void removeWithNullEntryShouldReturnFalse(){
		generateFile(entries);
		assertFalse("Null look up entry should return false",
				ab.removeAddressBookEntry(filename, null));
	}

	@Test
	public void removeEmptyStringEntryShouldReturnTrue(){
		generateFile(entries);
		ab.removeAddressBookEntry(filename, entry3);
	}

	@Test
	public void removeExistingEntryShouldReturnTrue(){
		generateFile(entries);
		assertTrue("Entry should be successfully removed by returning true",
				ab.removeAddressBookEntry(filename, entry1));
	}

	@Test
	public void removeNonExistentEntryShouldReturnFalse(){
		generateFile(entries);
		assertFalse("Should return false when trying to remove a non-existent object",
				ab.removeAddressBookEntry(filename, NonExistentObject));
	}

	/**
	 * This text expects to receive an IOException on providing 
	 * an invalid  filename
	 * 
	 * This test fails
	 */
	@Test(expected = IOException.class)
	public void removeEntryWithInvalidFileNameShouldCatchException(){
		ab.removeAddressBookEntry("invalidFilename",entry1);
	}

	@Test
	public void removeEntryWithEmptyAddressBookShouldReturnFalse() {
		assertFalse("Should return false",emptyAb.removeAddressBookEntry(filenameEmptyAb, entry3));
	}

	//Cannot test this method, as no object is returned
	@Test
	public void addEntry(){
		ab.addAnEntry("TestName", "Test address 104", 74823, "test@gmai.com", "notes");
	}

	@Test
	public void addEntryWithNullValues(){
		ab.addAnEntry("TestName", "Test address 104", 74823, "test@gmai.com", null);
	}

	@Test
	public void addEntryWithEmptyString(){
		ab.addAnEntry("","",0,"","");
	}

	@Test
	public void saveAbValidCase() throws IOException{
		String file = "savetest.txt";
		ab.addAnEntry("Test1", "104 Romaine", 123456, "test@gmail.com", "testnote");
		ab.addAnEntry("Test4", "107 Romaine", 1234569, "test3@gmail.com", "testnote3");
		ab.saveAddressListToFile(file);

		File result = new File(file);

		generateFile(entries);
		File expected = fileAb;
		assertEquals(FileUtils.readLines(expected),FileUtils.readLines(result));
	}

	/**
	 * This tests the functionlaity when a null is passed for a
	 * string value of a file path. The function should ideally throw
	 * a null pointer exception. The test does not run as it encounters
	 * the error of null pointer exception.
	 * @throws IOException
	 */
	@Test(expected = IOException.class)
	public void saveAbWithInvalidFileNameNullShouldCatchException() throws IOException{
		String file = null;
		ab.addAnEntry("Test1","104 Romaine", 123456, "test@gmail.com", "testnote");
		ab.addAnEntry("Test4","107 Romaine", 1234569, "test3@gmail.com", "testnote3");
		ab.addAnEntry("Test6","110 Romaine",4403423,"tester45@gmail.com","notes");
		ab.saveAddressListToFile(file);
	}

	@Test(expected = IOException.class)
	public void saveAbWithInvalidFileNameEmptyStringShouldCatchException() throws IOException{
		String file = "";
		ab.addAnEntry("Test1", "104 Romaine", 123456, "test@gmail.com", "testnote");
		ab.addAnEntry("Test4", "107 Romaine", 1234569, "test3@gmail.com", "testnote3");
		ab.addAnEntry("Test6","110 Romaine",4403423,"tester45@gmail.com","notes");
		ab.saveAddressListToFile(file);
	}

	@Test
	public void saveEmptyAddressBook() throws IOException{
		String file = "saveEmptyTest.txt";
		ab.saveAddressListToFile(file);

		File result = new File(file);
		File expected = fileEmptyAb;
		assertEquals(FileUtils.readLines(result),FileUtils.readLines(expected));
	}

	@Test
	public void saveAbContainingNullValues() throws IOException{
		String file = "savetest.txt";
		ab.addAnEntry("Test1", "104 Romaine", 123456, "test@gmail.com", "testnote");
		ab.addAnEntry("Test4", "107 Romaine", 1234569, "test3@gmail.com", "testnote3");
		ab.addAnEntry("Test5", null, 1234569, null, "testnote4");
		ab.saveAddressListToFile(file);

		String newEntry = "Test5,null,1234569,null,testnote4";
		entries.add(newEntry);
		File result = new File(file);

		generateFile(entries);
		File expected = fileAb;
		assertEquals(FileUtils.readLines(expected),FileUtils.readLines(result));
	}

	@Test
	public void saveAbContainingEmptyStrings() throws IOException{
		String file = "savetest.txt";
		ab.addAnEntry("Test5", "", 1234569, "", "testnote4");
		ab.addAnEntry("","",0,"","");
		ab.saveAddressListToFile(file);

		entries.clear();
		String newEntry = "Test5,,1234569,,testnote4";
		String newEntry1 = ",,0,,";
		entries.add(newEntry);
		entries.add(newEntry1);
		File result = new File(file);

		generateFile(entries);
		File expected = fileAb;
		assertEquals(FileUtils.readLines(expected),FileUtils.readLines(result));
	}

	@Test
	public void readAbFromFile() throws IOException{
		generateFile(entries);
		List<AddressBookEntry> expected = entryList;
		List<AddressBookEntry> result = ab.readAddressesFromFile(filename);
		assertEquals(expected,result);
	}

	@Test(expected = IOException.class)
	public void readAbFromNonExistentFile() throws IOException{
		String file = "DoesNotExist";
		ab.readAddressesFromFile(file);
	}

	@Test
	public void readAbFromFileWithEmptyStringValues() throws IOException{
		List<AddressBookEntry> expected = entryList;
		AddressBookEntry entry = new AddressBookEntry();
		entry.setName("");
		entry.setEmail("test@gmai.com");
		entry.setPhoneNumber(0);
		entry.setPostalAddress("");
		entry.setNote("notes");

		expected.add(entry);

		String emptyString = ",,0,test@gmai.com,notes";
		entries.add(emptyString);

		generateFile(entries);

		List<AddressBookEntry> result = ab.readAddressesFromFile(filename);
		assertEquals(result,expected);
	}

	/**
	 * Failed case -
	 * A saved addressbook with a null entry in an object
	 * is read as a string "null" from file 
	 * @throws IOException
	 */
	//
	@Test
	public void readAbFromFileWithNullValues() throws IOException{
		List<AddressBookEntry> expected = entryList;
		AddressBookEntry entry = new AddressBookEntry();
		entry.setName(null);
		entry.setEmail("test@gmai.com");
		entry.setPhoneNumber(0);
		entry.setPostalAddress(null);
		entry.setNote("notes");

		expected.add(entry);

		String emptyString = "null,null,0,test@gmai.com,notes";
		entries.add(emptyString);

		generateFile(entries);

		List<AddressBookEntry> result = ab.readAddressesFromFile(filename);
		assertEquals(result,expected);

	}

	@Test
	public void readAbFromFileHavingDuplicateEntries() throws IOException{
		entryList.add(entry1);
		entryList.add(entry2);
		entries.add(entries.get(0));
		entries.add(entries.get(1));
		generateFile(entries);

		List<AddressBookEntry> expected = entryList;
		List<AddressBookEntry> result = ab.readAddressesFromFile(filename);
		assertEquals(expected,result);

	}

	//Test Fails with an error as the implementation is wrong
	@Test
	public void equalsReflexiveCheck(){
		ab.addAnEntry("Test1", "104 Romaine", 123456, "test@gmail.com", "testnote");
		ab.addAnEntry("Test4", "107 Romaine", 1234569, "test3@gmail.com", "testnote3");

		assertTrue(ab.equals(ab));
	}

	//Test fails with an error as the implementation is wrong
	@Test
	public void equalsSymmetricCheck(){

		AddressBook ab2 = new AddressBook();

		ab2.addAnEntry("Test1", "104 Romaine", 123456, "test@gmail.com", "testnote");
		ab2.addAnEntry("Test4", "107 Romaine", 1234569, "test3@gmail.com", "testnote3");

		assertTrue(ab.equals(ab2) == ab2.equals(ab));
	}

	//Test fails with an error as the implementation is wrong
	@Test
	public void equalsTransitiveCheck(){

		AddressBook ab2 = new AddressBook();

		ab2.addAnEntry("Test1", "104 Romaine", 123456, "test@gmail.com", "testnote");
		ab2.addAnEntry("Test4", "107 Romaine", 1234569, "test3@gmail.com", "testnote3");

		AddressBook ab3 = new AddressBook();

		ab3.addAnEntry("Test1", "104 Romaine", 123456, "test@gmail.com", "testnote");
		ab3.addAnEntry("Test4", "107 Romaine", 1234569, "test3@gmail.com", "testnote3");

		if(ab.equals(ab2) && ab2.equals(ab)){
			assertTrue(ab.equals(ab3));
		}
	}

	//Test fails with an error as the implementation is wrong
	@Test
	public void equalsConsistencyCheck(){
		ab.addAnEntry("Test1", "104 Romaine", 123456, "test@gmail.com", "testnote");
		ab.addAnEntry("Test4", "107 Romaine", 1234569, "test3@gmail.com", "testnote3");
		assertTrue(ab.equals(ab) == ab.equals(ab));
	}

	//Test fails with an error as the implementation is wrong
	@Test
	public void equalsNotNullProperty(){
		ab.addAnEntry("Test1", "104 Romaine", 123456, "test@gmail.com", "testnote");
		ab.addAnEntry("Test4", "107 Romaine", 1234569, "test3@gmail.com", "testnote3");
		assertFalse(ab.equals(null));
	}

	@Test
	public void toStringcheck(){
		ab.addAnEntry("Test1", "104 Romaine", 123456, "test@gmail.com", "testnote");
		String expected = "{ [Test1,104 Romaine,123456,test@gmail.com,testnote] }";
		String result = ab.toString();
		assertEquals(result,expected);
	}
	private void generateFile(List<String> entries){
		try{
			fileAb = new File(filename);
			FileUtils.writeLines(fileAb, entries);	
		}
		catch (IOException e){
			e.printStackTrace();
		}

	}
}
