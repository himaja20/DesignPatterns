package ps1;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * AddressBook library for creating and managing(create, delete contacts) address books
 * @author abhishek bhunia
 *
 */
public class AddressBook {
  /**
   * Contacts are locally stored in a hashset, which can later be flushed to a file 
   */
  private Set<Person> contactSet = new HashSet<Person>();  
  private final String bookName;
  
  /**
   * private constructor used to disable instantiation and force static factory method design pattern
   * @param name : Name of the address book, application can manage multiple address books
   */
  private AddressBook(String name) {    
    bookName = name;
  }
  
  /**
   * static factory method
   * @param name : creates a named address book locally managed by the application
   * @return 
   */
  public static AddressBook createAddressBook(String name) {
    return new AddressBook(name);
  }
  
  /**
   * 
   * @param person : receives Person object built by the Builder and stores it locally
   * @return
   */
  public boolean insertContact(Person person) {
    return contactSet.add(person);
  }
  
  /**
   * 
   * @param person : this person is removed from local(in memory) storage
   * @return
   */
  public boolean deleteContact(Person person) {
    return contactSet.remove(person);
  }
  
  /**
   * 
   * @param property : it's a string that can be matched against 'any' field of the object 
   * @return : a list of all matched contacts against 'property'
   */
  public List<Person> searchContact(String property) {
    List<Person> searchResult = new ArrayList<Person>();
   
    for (Person person : contactSet) {
      if (person.getPersonName().equals(property) 
          || (person.getPersonAddress() != null && person.getPersonAddress().equals(property))
          || (person.getPersonPhone() != null && person.getPersonPhone().equals(property))
          || (person.getPersonEmail() != null && person.getPersonEmail().equals(property))
          || (person.getPersonNote() != null && person.getPersonNote().equals(property))) {
    	 
        searchResult.add(person);
      }
    }
    return searchResult;
  }
  
  /**
   * returns all in-memory contacts of the referred address book
   * @return
   */
  public List<Person> getAllContacts() {
    List<Person> allContacts = new ArrayList<Person>(contactSet);
    return allContacts;
  }
  
  /**
   * parametrized method to print selected contacts returned by search criteria
   * @param contacts
   */
  public void printContacts(List<Person> contacts) {
    for (Person person : contacts) {
      System.out.println(person);
    }
  }
  
  /**
   * method to print all in-memory contacts in referred address book
   */
  public void printContacts() {
    for (Person person : contactSet) {
      System.out.println(person);
    }
  }
  
  /**
   * Deleted in memory address book
   */
  public void deleteLocal() {
	  contactSet.clear();
  }
  
  /**
   * Uses the following library to load/store object data in JSON format
   * https://code.google.com/archive/p/json-simple/
   * @return
   */
  private JSONArray getJSONArray() {
    if (contactSet.size() == 0)
      return null;
    
    JSONArray allContacts = new JSONArray();
    for (Person person : contactSet) {
      //Create JSON object for each contact
      JSONObject ob = new JSONObject();
      ob.put("Name", person.getPersonName());
      String s = person.getPersonAddress();
      if (s != null)
        ob.put("Address", s);
      s = person.getPersonPhone();
      if (s != null)
          ob.put("Phone", s);
      s = person.getPersonEmail();
      if (s != null)
          ob.put("Email", s);
      s = person.getPersonNote();
      if (s != null)
          ob.put("Note", s);
      //Add object to JSON array      
      allContacts.add(ob);
    }
    return allContacts;
  }
  
  /**
   * Uses the following library to store object data in JSON format
   * https://code.google.com/archive/p/json-simple/
   * @param name : name of the file to store the data
   */
  public void saveAddressBook(String name) {
    
    FileWriter file = null;
    try {
      file = new FileWriter(name);
      file.write(getJSONArray().toJSONString());
      
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
          file.flush();
		  file.close();
		} catch (IOException e) {			
            e.printStackTrace();
		}    	
    }
  }

  /**
   * Uses the following library to load object data in JSON format
   * https://code.google.com/archive/p/json-simple/
   * @param filename
   * @return
   */
  public boolean loadAddressBook(String filename) {
	  
    JSONParser parser = new JSONParser();
    try {
      JSONArray contacts = (JSONArray) parser.parse(new FileReader(filename));
      for (Object person : contacts) {
        JSONObject ob = (JSONObject) person;
        String name = (String) ob.get("Name");
        String address = (String) ob.get("Address");
        String phone = (String) ob.get("Phone");
        String email = (String) ob.get("Email");
        String note = (String) ob.get("Note");
        
        Person newPerson = new Person.Builder(name).address(address).phone(phone).
        		               email(email).note(note).build();        
        this.insertContact(newPerson);        
      }      
      
      return true;      
      
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
    
    return false;
  }

}
