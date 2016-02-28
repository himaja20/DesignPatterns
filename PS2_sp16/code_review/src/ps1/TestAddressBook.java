package ps1;

/**
 * Test Class for AddressBook application demo
 * @author abhishek bhunia
 *
 */
public class TestAddressBook {

  public static void main(String[] args) {
    //Create sample address book
    AddressBook book = AddressBook.createAddressBook("book1");
    
    //Add contacts
    Person p1 = new Person.Builder("Sam").address("251 Mercer St").phone("201-238-1234").
            email("sam@nyu.edu").note("A").build();
    book.insertContact(p1);
    
    Person p2 = new Person.Builder("John").address("251 Mercer St").phone("201-238-2345").
            email("john@nyu.edu").note("A-").build();
    book.insertContact(p2);
    
    Person p3 = new Person.Builder("Aubrey").address("721 Broadway").phone("201-238-3456").
            email("aubrey@nyu.edu").note("B+").build();
    book.insertContact(p3);
    
    Person p4 = new Person.Builder("John").address("721 Broadway").phone("201-238-5678").
            email("john2@nyu.edu").note("A-").build();
    book.insertContact(p4);
    
    Person p5 = new Person.Builder("Mike").address("251 Mercer St").phone("201-238-4567").
            email("mike@nyu.edu").note("A").build();
    book.insertContact(p5);
    
    //Print all contacts 
    System.out.println("\nTrying to print all contacts stored in memory");
    book.printContacts();
    
    //Search all contacts with address "251 Mercer St"
    System.out.println("\nContacts with address \"251 Mercer St\"");
    book.printContacts(book.searchContact("251 Mercer St"));
    System.out.println();
    
    //Search all contacts with address "721 Broadway"
    System.out.println("\nContacts with address \"721 Broadway\"");
    book.printContacts(book.searchContact("721 Broadway"));
    System.out.println();
    
    //Search all contacts with grade "A"
    System.out.println("\nContacts with note A");
    book.printContacts(book.searchContact("A"));
    System.out.println();
    
    //Deleting contact John(2)
    System.out.println("\nDeleting John(2) john2@nyu.edu");
    book.deleteContact(p4);
    
    //Print all contacts after deleting John(2)
    System.out.println("\nAddress Book after deleting John(2)\n");
    book.printContacts();
    
    //Save all contacts
    System.out.println("\nStoring Address Book to Disk");
    book.saveAddressBook("contacts-db.txt");
    
    //Print all contacts before flushing
    System.out.println("\nTrying to print from local(before local delete)\n");
    book.printContacts();
    
    //Delete local book
    System.out.println("\nDeleting in memory Address Book");
    book.deleteLocal();
    
    //Print all contacts after flushing: "should not print anything"
    System.out.println("\nTrying to print from memory(after memory delete): should not print anything\n");
    book.printContacts();
    
    //Load all contacts
    System.out.println("\nReloading data from disk");
    book.loadAddressBook("contacts-db.txt");
    
    //Print all contacts after loading data from file
    System.out.println("\nTrying to print from loaded data\n");
    book.printContacts();
    
  }
}
