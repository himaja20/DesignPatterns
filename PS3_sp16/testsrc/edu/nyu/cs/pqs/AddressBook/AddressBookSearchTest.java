package edu.nyu.cs.pqs.AddressBook;

/*
 * A new class is created for Search functionality 
 * as it has many cases to be tested.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import edu.nyu.cs.pqs.AddressBook.EntryAttribute;

//TODO get the indentation right
public class AddressBookSearchTest {

  private AddressBook addressBook; 
  private AddressBook emptyStringAb;
  private AddressBook emptyAb;

  private String filename = null;
  private String filenameEmptyStringAb = null;
  private String filenameEmptyAb = null;

  private AddressBookEntry entry1;
  private AddressBookEntry same1;
  private AddressBookEntry same2;
  private AddressBookEntry emptyStr;

  private File fileAb;
  private File fileEmptyStringAb;
  @Before
  public void setUp() throws Exception {
    addressBook = new AddressBook();
    emptyStringAb = new AddressBook();
    emptyAb = new AddressBook();

    filename = "addressbook.txt";
    filenameEmptyStringAb = "abEmptyStrings.txt";
    filenameEmptyAb = "emptyab.txt";

    fileAb = new File(filename);
    fileEmptyStringAb = new File(filenameEmptyStringAb);

    // creating some contacts for the address book
    addressBook.addAnEntry("Test1", "104 Romaine"
        , 123456, "test@gmail.com", "testnote");
    addressBook.addAnEntry("Test4", "107 Romaine"
        , 1234569, "test3@gmail.com", "testnote3");

    // creating contacts with almost same values in all Textfields
    addressBook.addAnEntry("Same", "Same",
        1234, "Same", "Same");
    addressBook.addAnEntry("Same", "Same",
        1234, "Same", "Same");

    //creating a null entry 
    addressBook.addAnEntry(null, null, 0, null, null);

    /*Creating a file to mimic the function
    of saving these address book contacts to a file*/
    String entryStr1 = "Test1,104 Romaine,123456,"
        + "test@gmail.com,testnote";
    String entryStr2 = "Test4,107 Romaine,1234569,"
        + "test3@gmail.com,testnote3";
    String entryStr3 = "Same,Same,1234,Same,Same";
    String entryStr4 = "Same,Same,1234,Same,Same";
    String entryStr5 = "null,null,0,null,null";

    List<String> entries = new ArrayList<String>();
    entries.add(entryStr1);
    entries.add(entryStr2);
    entries.add(entryStr3);
    entries.add(entryStr4);
    entries.add(entryStr5);

    FileUtils.writeLines(fileAb, entries);

    //creating an AddressBook with empty Strings
    emptyStringAb.addAnEntry("", "", 0, "", "");
    String emptyString = ",,0,,";
    FileUtils.writeStringToFile(fileEmptyStringAb, emptyString);

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

  /*
   * This test fails as the author catches the exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void searchWithNullEntryAttribute(){
    addressBook.searchAddressBook(filename, "Test1", null);
  }

  /*
   * This test fails as the author catches the exception
   */
  @Test(expected = IOException.class)
  public void searchWithInvalidFilename(){
    addressBook.searchAddressBook("fjksdf","test",EntryAttribute.ADDRESS);

  }

  @Test
  public void searchEmptyAddressBookShouldReturnEmptyList(){
    assertTrue("Search Result List should be empty for Email Attribute",
        (emptyAb.searchAddressBook(filenameEmptyAb, "test"
            , EntryAttribute.EMAIL)).isEmpty());
  }

  /*
   * If an empty string is passed as a search String
   * results containing empty strings should be returned
   * 
   * This test fails as such contacts are not present in the resultList
   */
  @Test
  public void searchEmptyStringValuesInEmail() {
    List<AddressBookEntry> result = emptyStringAb.searchAddressBook(
        filenameEmptyStringAb,"",EntryAttribute.EMAIL);

    AddressBookEntry expected = emptyStr;

    assertTrue("Search List(with Email Attribute)must have contacts "
        + "having Empty Strings as values",(result.size() == 1));
    assertEquals("Search Results should match",expected,result.get(0));
  }

  /*
   * If an empty string is passed as a search String
   * results containing empty strings should be returned
   * 
   * This test fails as such contacts are not present in the resultList
   */
  @Test
  public void searchContactsHavingEmptyStringValuesInPhone() {
    List<AddressBookEntry> result = emptyStringAb.searchAddressBook(
        filenameEmptyStringAb, "",EntryAttribute.PHONE);

    AddressBookEntry expected = emptyStr;

    assertTrue("Search List(with Phone Attribute)must have "
        + "contacts having Empty Strings as values",result.size() == 1);
    assertEquals("Search Results should match",expected,result.get(0));
  }

  /*
   * If an empty string is passed as a search String
   * results containing empty strings should be returned
   * 
   * This test fails as such contacts are not present in the resultList
   */
  @Test
  public void searchContactsHavingEmptyStringValuesInName() {
    List<AddressBookEntry> result = emptyStringAb.searchAddressBook(
        filenameEmptyStringAb, "",EntryAttribute.NAME);

    AddressBookEntry expected = emptyStr;

    assertTrue("Search List(with Name Attribute)must have "
        + "contacts having Empty Strings as values",result.size() == 1);
    assertEquals("Search Results should match",expected,result.get(0));
  }

  /*
   * If an empty string is passed as a search String
   * results containing empty strings should be returned
   * 
   * This test fails as such contacts are not present in the resultList
   */
  @Test
  public void searchContactsHavingEmptyStringValueInNote() {
    List<AddressBookEntry> result = emptyStringAb.searchAddressBook(
        filenameEmptyStringAb, "",EntryAttribute.NOTE);

    AddressBookEntry expected = emptyStr;

    assertTrue("Search List(with Note Attribute)must "
        + "have contacts having Empty Strings as values",result.size() == 1);
    assertEquals("Search Results should match",expected,result.get(0));
  }

  /*
   * If an empty string is passed as a search String
   * results containing empty strings should be returned
   * 
   * This test fails as such contacts are not present in the resultList
   */
  @Test
  public void searchContactsHavingEmptyStringInAddress() {
    List<AddressBookEntry> result = emptyStringAb.searchAddressBook(
        filenameEmptyStringAb, "",EntryAttribute.ADDRESS);

    AddressBookEntry expected = emptyStr;

    assertTrue("Search List(with Address Attribute)must "
        + "have contacts having Empty Strings as values",result.size() == 1);
    assertEquals("Search Results should match",expected,result.get(0));
  }

  /*
   * Test for multiple matchings of the search text across the fields of an entry
   */
  @Test
  public void searchForMultipleMatchingsAcrossAddresses(){

    List<AddressBookEntry> result = addressBook.searchAddressBook(
        filename, "Same", EntryAttribute.ADDRESS);
    List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
    expected.add(same1);
    expected.add(same2);

    assertTrue("Result list should contain 2",
        (result.size() == 2));
    assertEquals("Results should match",expected,result);
  }

  @Test
  public void searchForMultipleMatchingsAcrossNames(){
    List<AddressBookEntry> result = addressBook.searchAddressBook(
        filename, "Same", EntryAttribute.NAME);
    List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
    expected.add(same1);
    expected.add(same2);

    assertTrue("Result list should contain 2",
        (result.size() == 2));
    assertEquals("Results should match",expected,result);
  }

  @Test
  public void searchForMultipleMatchingsAcrossEmails(){
    List<AddressBookEntry> result = addressBook.searchAddressBook(
        filename, "Same", EntryAttribute.EMAIL);
    List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
    expected.add(same1);
    expected.add(same2);

    assertTrue("Result list should contain 2",result.size() == 2);
    assertEquals("Results should match",expected,result);
  }

  @Test
  public void searchForMultipleMatchingsAcrossPhones(){
    List<AddressBookEntry> result = addressBook.searchAddressBook(
        filename, "1234", EntryAttribute.PHONE);
    List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
    expected.add(same1);
    expected.add(same2);

    assertTrue("Result list should contain 2",result.size() == 2);
    assertEquals("Results should match",expected,result);
  }

  @Test
  public void searchForMultipleMatchingsAcrossNotes(){
    List<AddressBookEntry> result = addressBook.searchAddressBook(
        filename, "Same", EntryAttribute.NOTE);
    List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
    expected.add(same1);
    expected.add(same2);

    assertTrue("Result list should contain 2",result.size() == 2);
    assertEquals("Results should match",expected,result);
  }

  /*
   * Test for searching null values in an entry. 
   * Test passes if result is empty.
   */
  @Test
  public void searchNullQueryShouldReturnNull(){
    assertTrue("Result list should NOT be empty",
        (addressBook.searchAddressBook(
            filename, null, EntryAttribute.NAME)).isEmpty());
    assertTrue("Result list should NOT be empty",
        (addressBook.searchAddressBook(
            filename, null, EntryAttribute.PHONE)).isEmpty());
    assertTrue("Result list should NOT be empty",
        (addressBook.searchAddressBook(
            filename, null, EntryAttribute.ADDRESS)).isEmpty());
    assertTrue("Result list should NOT be empty",
        (addressBook.searchAddressBook(
            filename, null, EntryAttribute.EMAIL)).isEmpty());
    assertTrue("Result list should NOT be empty",
        (addressBook.searchAddressBook(
            filename, null, EntryAttribute.NOTE)).isEmpty());
  }


  @Test
  public void searchNonExistentValuesInName(){
    assertTrue("Result list should be empty",
        (addressBook.searchAddressBook(
            filename, "IDontExist", EntryAttribute.NAME)).isEmpty());
  }

  @Test
  public void searchNonExistentValuesInAddress(){
    assertTrue("Result list should be empty",
        (addressBook.searchAddressBook(
            filename, "IDontExist", EntryAttribute.ADDRESS)).isEmpty());
  }

  @Test
  public void searchNonExistentValuesInEmail(){
    assertTrue("Result list should be empty",
        (addressBook.searchAddressBook(
            filename, "IDontExist", EntryAttribute.EMAIL)).isEmpty());
  }

  @Test
  public void searchNonExistentValuesInNote(){
    assertTrue("Result list should be empty",
        (addressBook.searchAddressBook(
            filename, "IDontExist", EntryAttribute.NOTE)).isEmpty());
  }

  @Test
  public void searchNonExistentValuesInPhone(){
    assertTrue("Result list should be empty",
        (addressBook.searchAddressBook(
            filename, "1234599489358", EntryAttribute.PHONE)).isEmpty());
  }

  @Test
  public void searchNameWithValidEntryAttribute(){
    List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
    expected.add(entry1);

    List<AddressBookEntry> result = addressBook.searchAddressBook(
        filename,entry1.getName(),EntryAttribute.NAME);

    assertTrue("Size of the searched list should be 1",result.size() == 1);
    assertEquals("Results should match",expected,result);
  }

  @Test
  public void searchPhoneWithValidEntryAttribute(){
    List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
    expected.add(entry1);

    List<AddressBookEntry> result = addressBook.searchAddressBook(
        filename,String.valueOf(
            entry1.getPhoneNumber()),EntryAttribute.PHONE);

    assertTrue("Size of the searched list should be 1",result.size() == 1);
    assertEquals("Results should match",expected,result);
  }

  @Test
  public void searchEmailWithValidEntryAttribute(){
    List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
    expected.add(entry1);

    List<AddressBookEntry> result = addressBook.searchAddressBook
        (filename,entry1.getEmail(),EntryAttribute.EMAIL);

    assertTrue("Size of the searched list should be 1",result.size() == 1);
    assertEquals("Results should match",expected,result);
  }

  @Test
  public void searchNoteWithValidEntryAttribute(){
    List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
    expected.add(entry1);

    List<AddressBookEntry> result = addressBook.searchAddressBook
        (filename,entry1.getNote(),EntryAttribute.NOTE);

    assertTrue("Size of the searched list should be 1",result.size() == 1);
    assertEquals("Results should match",expected,result);
  }

  @Test
  public void searchAddressWithValidEntryAttribute(){
    List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>();
    expected.add(entry1);

    List<AddressBookEntry> result = addressBook.searchAddressBook
        (filename,entry1.getPostalAddress(),EntryAttribute.ADDRESS);

    assertTrue("Size of the searched list should be 1",result.size() == 1);
    assertEquals("Results should match",expected,result);
  }
}
