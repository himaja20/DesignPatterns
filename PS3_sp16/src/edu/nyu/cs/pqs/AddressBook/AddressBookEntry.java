package edu.nyu.cs.pqs.AddressBook;

/**
 * AddressBookEntry class represents a single entry with attributes such as
 * name, address, phone number, email and note
 * 
 * @author Ritu
 *
 */
public class AddressBookEntry {
  private String name;
  private String postalAddress;
  private long phoneNumber;
  private String email;
  private String note;

  public AddressBookEntry() {
    name = null;
    postalAddress = null;
    phoneNumber = 0;
    email = null;
    note = null;
  }

  /**
   * sets name
   * 
   * @param entryName represents name of an entry
   */
  public void setName(String entryName) {
    name = entryName;
  }

  /**
   * sets address
   * 
   * @param entryAddress represents address of an entry
   */
  public void setPostalAddress(String entryAddress) {
    postalAddress = entryAddress;
  }

  /**
   * sets Phone Number
   * 
   * @param entryPhone represents phone number of an entry
   */
  public void setPhoneNumber(long entryPhone) {
    phoneNumber = entryPhone;
  }

  /**
   * sets email address
   * 
   * @param entryEmail represents email address of an entry
   */
  public void setEmail(String entryEmail) {
    email = entryEmail;
  }

  /**
   * sets note
   * 
   * @param entryNote represents note of an entry
   */
  public void setNote(String entryNote) {
    note = entryNote;
  }

  /**
   * retrieves name
   * 
   * @return name of an entry
   */
  public String getName() {
    return name;
  }

  /**
   * retrieves postal address
   * 
   * @return address of an entry
   */
  public String getPostalAddress() {
    return postalAddress;
  }

  /**
   * retrieves phone number
   * 
   * @return phone number of an entry
   */
  public long getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * retrieves email
   * 
   * @return email address of an entry
   */
  public String getEmail() {
    return email;
  }

  /**
   * retrieves addressing note of an entry
   * 
   * @return note of an entry
   */
  public String getNote() {
    return note;
  }

  /**
   * represents String format representation of an address book entry
   * 
   * @return formatted string
   */
  @Override
  public String toString() {
    String returnString = name + "," + postalAddress + "," + Long.toString(phoneNumber) + ","
        + email + "," + note;
    return returnString;
  }

  /**
   * compares this AddressBookEntry with another AddressBookEntry
   * 
   * @param entryToBeCompared an entry that needs to be compared with this object's address book
   *          entry
   * @return true if they are equal and false otherwise
   * @throws IllegalArgumentException with message "Unmatched Argument Type" if argument type is 
   *             not AddressBookEntry
   */
  @Override
  public boolean equals(Object entryToBeCompared) {
    try {
      AddressBookEntry entryToCompare = (AddressBookEntry) entryToBeCompared;
      if (this.name.equals(entryToCompare.name)
          && this.postalAddress.equals(entryToCompare.postalAddress)
          && this.phoneNumber == entryToCompare.phoneNumber
          && this.email.equals(entryToCompare.email) && this.note.equals(entryToCompare.note)) {
        return true;
      }
      return false;
    } catch (Exception unmatchedArgumentType) {
      throw new IllegalArgumentException("Unmatched Argument Type");
    }
  }
}
