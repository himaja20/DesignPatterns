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

  private AddressBook addressBook; 

  private String filename = null;
  private String filenameEmptyAb = null;
  private AddressBookEntry entry1;
  private AddressBookEntry entry2;
  private AddressBookEntry entry3;
  private AddressBookEntry NonExistentObject;
  private String entryString1;
  private String entryString2;

  List<AddressBookEntry> entryList =
      new ArrayList<AddressBookEntry>();
  List<String> entries = new ArrayList<String>();
  File fileAb;
  File fileEmptyAb;

  @Before
  public void setUp() throws Exception {
    addressBook = new AddressBook();

    filename = "addressbook.txt";
    filenameEmptyAb = "emptyab.txt";

    // creating some contacts for the address book
    entryString1 = "Test1,104 Romaine,"
        + "123456,test@gmail.com,testnote";
    entryString2 = "Test4,107 Romaine,"
        + "1234569,test3@gmail.com,testnote3";

    entries.add(entryString1);
    entries.add(entryString2);

    fileEmptyAb = new File(filenameEmptyAb);
    if (!fileEmptyAb.exists()){
      fileEmptyAb.createNewFile();
    }

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

    NonExistentObject = new AddressBookEntry();
    NonExistentObject.setName("This");
    NonExistentObject.setPostalAddress("object does not");
    NonExistentObject.setPhoneNumber(123456);
    NonExistentObject.setEmail("exist@gmail.com");
    NonExistentObject.setNote("in the addressbook");

    //creating an entryList of these objects for validation
    entryList.add(entry1);
    entryList.add(entry2);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void removeWithNullEntryShouldReturnFalse(){
    generateFile();
    assertFalse("Null look up entry should return false",
        addressBook.removeAddressBookEntry(filename, null));
  }

  @Test
  public void removeEmptyStringEntryShouldReturnTrue(){
    generateFile();
    addressBook.removeAddressBookEntry(filename, entry3);
  }

  @Test
  public void removeExistingEntryShouldReturnTrue(){
    generateFile();
    assertTrue("Entry should be successfully"
        + " removed by returning true",
        addressBook.removeAddressBookEntry(filename, entry1));
  }

  @Test
  public void removeNonExistentEntryShouldReturnFalse(){
    generateFile();
    assertFalse("Should return false when trying"
        + " to remove a non-existent object",
        addressBook.removeAddressBookEntry(filename, NonExistentObject));
  }

  /*
   * This text expects to receive an IOException on providing 
   * an invalid  filename
   * 
   * This test fails as the author catches the exception
   */
  @Test(expected = IOException.class)
  public void removeEntryWithInvalidFileNameShouldCatchException(){
    addressBook.removeAddressBookEntry("invalidFilename",entry1);
  }

  @Test
  public void removeEntryWithEmptyAddressBookShouldReturnFalse() {
    assertFalse("Should return false",addressBook.
        removeAddressBookEntry(filenameEmptyAb, entry3));
  }

  //Cannot test this method, as no object is returned
  @Test
  public void addEntry(){
    addressBook.addAnEntry("TestName", "Test address 104",
        74823, "test@gmai.com", "notes");
  }

  @Test
  public void addEntryWithNullValues(){
    addressBook.addAnEntry("TestName", "Test address 104",
        74823, "test@gmai.com", null);
  }

  @Test
  public void addEntryWithEmptyString(){
    addressBook.addAnEntry("","",0,"","");
  }

  @Test
  public void saveAbValidCase() throws IOException{
    String file = "savetest.txt";
    addressBook.addAnEntry("Test1", "104 Romaine",
        123456, "test@gmail.com", "testnote");
    addressBook.addAnEntry("Test4", "107 Romaine",
        1234569, "test3@gmail.com", "testnote3");
    addressBook.saveAddressListToFile(file);

    File result = new File(file);

    generateFile();
    File expected = fileAb;
    assertEquals(FileUtils.readLines(expected),FileUtils.readLines(result));
  }

  /*
   * This tests the functionlaity when a null is passed for a
   * string value of a file path. The function should ideally throw
   * a null pointer exception. The test does not run as it encounters
   * the error of null pointer exception.
   * @throws IOException
   */
  @Test(expected = IOException.class)
  public void saveAbWithFileNameNullShouldCatchException() throws IOException{
    String file = null;
    addressBook.addAnEntry("Test1","104 Romaine",
        123456, "test@gmail.com", "testnote");
    addressBook.addAnEntry("Test4","107 Romaine",
        1234569, "test3@gmail.com", "testnote3");
    addressBook.addAnEntry("Test6","110 Romaine",
        4403423,"tester45@gmail.com","notes");
    addressBook.saveAddressListToFile(file);
  }

  @Test(expected = IOException.class)
  public void saveAbWithFileNameEmptyStringShouldCatchException() throws IOException{
    String file = "";
    addressBook.addAnEntry("Test1", "104 Romaine",
        123456, "test@gmail.com", "testnote");
    addressBook.addAnEntry("Test4", "107 Romaine",
        1234569, "test3@gmail.com", "testnote3");
    addressBook.addAnEntry("Test6","110 Romaine",
        4403423,"tester45@gmail.com","notes");
    addressBook.saveAddressListToFile(file);
  }

  /*
   * An Empty address book should contain nothing,
   * but this test fails as an empty address book
   * has the default value ,,0,, 
   */
  @Test
  public void saveEmptyAddressBook() throws IOException{
    String file = "saveEmptyTest.txt";
    addressBook.saveAddressListToFile(file);

    File result = new File(file);
    File expected = fileEmptyAb;
    assertEquals(FileUtils.readLines(result),FileUtils.readLines(expected));
  }

  @Test
  public void saveAbContainingNullValues() throws IOException{
    String file = "savetest.txt";
    addressBook.addAnEntry("Test1", "104 Romaine",
        123456, "test@gmail.com", "testnote");
    addressBook.addAnEntry("Test4", "107 Romaine",
        1234569, "test3@gmail.com", "testnote3");
    addressBook.addAnEntry("Test5", null, 1234569,
        null, "testnote4");
    addressBook.saveAddressListToFile(file);

    String newEntry = "Test5,null,1234569,null,testnote4";
    entries.add(newEntry);
    File result = new File(file);

    generateFile();
    File expected = fileAb;
    assertEquals(FileUtils.readLines(expected),FileUtils.readLines(result));
  }

  @Test
  public void saveAbContainingEmptyStrings() throws IOException{
    String file = "savetest.txt";
    addressBook.addAnEntry("Test5", "", 1234569, "", "testnote4");
    addressBook.addAnEntry("","",0,"","");
    addressBook.saveAddressListToFile(file);

    entries.clear();
    String newEntry = "Test5,,1234569,,testnote4";
    String newEntry1 = ",,0,,";
    entries.add(newEntry);
    entries.add(newEntry1);
    File result = new File(file);

    generateFile();
    File expected = fileAb;
    assertEquals(FileUtils.readLines(expected),FileUtils.readLines(result));
  }

  @Test
  public void readAbFromFile() throws IOException{
    generateFile();
    List<AddressBookEntry> expected = entryList;
    List<AddressBookEntry> result = addressBook.readAddressesFromFile(filename);
    assertEquals(expected,result);
  }

  @Test(expected = IOException.class)
  public void readAbFromNonExistentFile() throws IOException{
    String file = "DoesNotExist";
    addressBook.readAddressesFromFile(file);
  }

  @Test
  public void readAbFromEmptyFile() throws IOException{
    entries.clear();
    generateFile();
    List<AddressBookEntry> expected = new ArrayList<AddressBookEntry>(); 
    List<AddressBookEntry> result = addressBook.readAddressesFromFile(filename);
    assertEquals(expected,result);
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

    generateFile();

    List<AddressBookEntry> result = addressBook.
        readAddressesFromFile(filename);
    assertEquals(result,expected);
  }

  /*
   * Failed case -
   * A saved addressbook with a null entry in an object
   * is read as a string "null" from file 
   * @throws IOException
   */
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

    generateFile();

    List<AddressBookEntry> result = addressBook.
        readAddressesFromFile(filename);
    assertEquals(result,expected);

  }

  @Test
  public void readAbFromFileHavingDuplicateEntries() throws IOException{
    entryList.add(entry1);
    entryList.add(entry2);
    entries.add(entries.get(0));
    entries.add(entries.get(1));
    generateFile();

    List<AddressBookEntry> expected = entryList;
    List<AddressBookEntry> result = addressBook.
        readAddressesFromFile(filename);
    assertEquals(expected,result);

  }

  /*
   * Test fails with an error as the author's implementation
   * does not abide the equals contractis wrong
   */
  @Test
  public void equalsReflexiveCheck(){
    addressBook.addAnEntry("Test1", "104 Romaine",
        123456, "test@gmail.com", "testnote");
    addressBook.addAnEntry("Test4", "107 Romaine",
        1234569, "test3@gmail.com", "testnote3");

    assertTrue(addressBook.equals(addressBook));
  }

  /*
   * Test fails with an error as the author's implementation
   * does not abide the equals contractis wrong
   */
  @Test
  public void equalsSymmetricCheck(){

    AddressBook ab2 = new AddressBook();

    ab2.addAnEntry("Test1", "104 Romaine",
        123456, "test@gmail.com", "testnote");
    ab2.addAnEntry("Test4", "107 Romaine",
        1234569, "test3@gmail.com", "testnote3");

    assertTrue(addressBook.equals(ab2) == ab2.equals(addressBook));
  }

  /*
   * Test fails with an error as the author's implementation
   * does not abide the equals contractis wrong
   */
  @Test
  public void equalsTransitiveCheck(){

    AddressBook ab2 = new AddressBook();

    ab2.addAnEntry("Test1", "104 Romaine",
        123456, "test@gmail.com", "testnote");
    ab2.addAnEntry("Test4", "107 Romaine",
        1234569, "test3@gmail.com", "testnote3");

    AddressBook ab3 = new AddressBook();

    ab3.addAnEntry("Test1", "104 Romaine",
        123456, "test@gmail.com", "testnote");
    ab3.addAnEntry("Test4", "107 Romaine",
        1234569, "test3@gmail.com", "testnote3");

    if(addressBook.equals(ab2) && ab2.equals(ab3)){
      assertTrue(addressBook.equals(ab3));
    }
  }

  /*
   * Test fails with an error as the author's implementation
   * does not abide the equals contractis wrong
   */
  @Test
  public void equalsConsistencyCheck(){
    addressBook.addAnEntry("Test1", "104 Romaine",
        123456, "test@gmail.com", "testnote");
    addressBook.addAnEntry("Test4", "107 Romaine",
        1234569, "test3@gmail.com", "testnote3");
    assertTrue(addressBook.equals(addressBook)
        == addressBook.equals(addressBook));
  }

  /*
   * Test fails with an error as the author's implementation
   * does not abide the equals contractis wrong
   */
  @Test
  public void equalsNotNullProperty(){
    addressBook.addAnEntry("Test1", "104 Romaine",
        123456, "test@gmail.com", "testnote");
    addressBook.addAnEntry("Test4", "107 Romaine",
        1234569, "test3@gmail.com", "testnote3");
    assertFalse(addressBook.equals(null));
  }

  @Test
  public void toStringcheck(){
    addressBook.addAnEntry("Test1", "104 Romaine",
        123456, "test@gmail.com", "testnote");
    final String expected = "{ [Test1,104 Romaine,"
        + "123456,test@gmail.com,testnote] }";
    String result = addressBook.toString();
    assertEquals(result,expected);
  }

  private void generateFile(){
    try{
      fileAb = new File(filename);
      FileUtils.writeLines(fileAb, entries);	
    }
    catch (IOException e){
      e.printStackTrace();
    }

  }
}
