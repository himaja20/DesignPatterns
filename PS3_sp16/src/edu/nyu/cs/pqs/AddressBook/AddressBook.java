package edu.nyu.cs.pqs.AddressBook;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

/**
 * AddressBook class is a library which implements functions like creating an
 * address book, adding an entry searching an entry or removing an entry
 * 
 * @author Ritu
 *
 */
public class AddressBook {
  private List<AddressBookEntry> entryList;

  public AddressBook() {
    entryList = new ArrayList<AddressBookEntry>();
  }

  /**
   * adds entries to an ArrayList, in this case entryList
   * 
   * @param entryName name of an address book entry
   * @param entryAddress represents postal address
   * @param entryPhone represents phone number
   * @param entryEmail represents email
   * @param entryNote represents note
   */
  public void addAnEntry(String entryName, String entryAddress, long entryPhone, String entryEmail,
      String entryNote) {
    AddressBookEntry entry = new AddressBookEntry();
    entry.setName(entryName);
    entry.setPostalAddress(entryAddress);
    entry.setPhoneNumber(entryPhone);
    entry.setEmail(entryEmail);
    entry.setNote(entryNote);
    entryList.add(entry);
  }

  /**
   * saves list of added entries to the file
   * 
   * @param fileName name of file in which address book entries are to be saved
   * @throws IOException when file does not exists
   */
  public void saveAddressListToFile(String fileName) throws IOException {
    File outputFile = new File(fileName);
    FileOutputStream outputStream = new FileOutputStream(outputFile);
    BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    String entryInFile = null;
    for (int i = 0; i < entryList.size(); i++) {
      entryInFile = entryList.get(i).toString();
      bufferWriter.write(entryInFile);
      bufferWriter.newLine();
    }
    bufferWriter.close();
  }

  /**
   * reads from address book file
   * 
   * @param fileName name of the file which is to be read
   * @return List of address book entries that are present in the file
   * @throws IOException when file does not exists
   */
  public List<AddressBookEntry> readAddressesFromFile(String fileName) throws IOException {
    FileInputStream input = new FileInputStream(fileName);
    BufferedReader bufferReader = new BufferedReader(new InputStreamReader(input));
    String line = bufferReader.readLine();
    String[] splitEntry;
    AddressBookEntry readEntry = null;
    ArrayList<AddressBookEntry> readList = new ArrayList<AddressBookEntry>();
    while (line != null) {
      splitEntry = line.split(",");
      readEntry = new AddressBookEntry();
      readEntry.setName(splitEntry[0]);
      readEntry.setPostalAddress(splitEntry[1]);
      readEntry.setPhoneNumber(Long.parseLong(splitEntry[2]));
      readEntry.setEmail(splitEntry[3]);
      readEntry.setNote(splitEntry[4]);
      readList.add(readEntry);
      line = bufferReader.readLine();
    }
    bufferReader.close();
    return readList;
  }

  /**
   * removes an entry from the address book by searching that entry using entry
   * type
   * 
   * @param fileName String name of the address book file which is to be searched in
   *          order to apply remove operation
   * @param lookUpEntry AddressBookEntry object that is to be removed from AddressBook
   * @return true if entry found and removed, and false otherwise
   */
  public boolean removeAddressBookEntry(String fileName, AddressBookEntry lookUpEntry) {
    try {
      if (!entryList.isEmpty()) {
        entryList.clear();
      }
      entryList = readAddressesFromFile(fileName);
      int index = getIndexIfContains(lookUpEntry);
      if (index < entryList.size()) {
        entryList.remove(index);
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
    }
    return false;
  }

  /**
   * searches address book for all entry attributes
   * 
   * @param fileName String name of the address book file which is to be searched
   * @param searchText String text that is to be searched in address book
   * @param entryAttribute EntryAttribute enum is used to search by the type of entry such as
   *          name, address etc
   * @return List of address book entry objects that matches searchText
   * @throws Illegal Argument Exception with message "Unknown entryAttribute" if
   *           entryAttribute argument is invalid
   */
  public List<AddressBookEntry> searchAddressBook(String fileName, String searchText,
      EntryAttribute entryAttribute) {
    ArrayList<AddressBookEntry> searchedList = new ArrayList<AddressBookEntry>();
    try {
      List<AddressBookEntry> completeList = readAddressesFromFile(fileName);
      switch (entryAttribute) {
      case NAME:
        for (int i = 0; i < completeList.size(); i++) {
          if (completeList.get(i).getName().equals(searchText)) {
            searchedList.add(completeList.get(i));
          }
        }
        break;
      case ADDRESS:
        for (int i = 0; i < completeList.size(); i++) {
          if (completeList.get(i).getPostalAddress().equals(searchText)) {
            searchedList.add(completeList.get(i));
          }
        }
        break;
      case PHONE:
        for (int i = 0; i < completeList.size(); i++) {
          if (Long.toString(completeList.get(i).getPhoneNumber()).equals(searchText)) {
            searchedList.add(completeList.get(i));
          }
        }
        break;
      case EMAIL:
        for (int i = 0; i < completeList.size(); i++) {
          if (completeList.get(i).getEmail().equals(searchText)) {
            searchedList.add(completeList.get(i));
          }
        }
        break;
      case NOTE:
        for (int i = 0; i < completeList.size(); i++) {
          if (completeList.get(i).getNote().equals(searchText)) {
            searchedList.add(completeList.get(i));
          }
        }
        break;
      default:
        throw new IllegalArgumentException("Unknown Search entryAttribute");
      }
    } catch (Exception e) {
    }
    return searchedList;
  }

  private int getIndexIfContains(AddressBookEntry checkEntry) {
    int i = 0;
    for (i = 0; i < entryList.size(); i++) {
      if (entryList.get(i).equals(checkEntry)) {
        return i;
      }
    }
    return i;
  }

  /**
   * generates string format for all the entries in an address book list
   * 
   * @return String formatted representation of Address Book List
   */
  @Override
  public String toString() {
    String returnString = null;
    for (AddressBookEntry eachEntry : entryList) {
      returnString = returnString + "[" + eachEntry.toString() + "]" + " ";
    }
    return "{" + returnString + "}";
  }

  /**
   * compares this object's entry list with another list of AddressBookEntry 
   * 
   * @param entryListToBeCompared entry list that needs to be compared with this object's address
   *          book
   * @return true if they are equal and false otherwise
   * @throws IllegalArgumentException with message "Unmatched Argument Type" if argument type is 
   *             not list of AddressBookEntry
   */
  @Override
  public boolean equals(Object entryListToBeCompared) {
    try {
      @SuppressWarnings("unchecked")
      List<AddressBookEntry> listToCompare = (ArrayList<AddressBookEntry>) entryListToBeCompared;
      int i = 0;
      for (AddressBookEntry eachEntry : entryList) {
        if (!eachEntry.equals(listToCompare.get(i))) {
          return false;
        }
      }
      if (i == entryList.size()) {
        return true;
      }
      return false;
    } catch (Exception unmatchedArgumentType) {
      throw new IllegalArgumentException("Unmatched Argument Type");
    }
  }
}
