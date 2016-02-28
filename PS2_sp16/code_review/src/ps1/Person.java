package ps1;
import java.util.*;

/**
 * Person class with immutable objects, set methods are deliberately not defined on Person
 * instead they are defined on Builder (for creating more Persons with slightly varying data etc.)
 * 
 * @author abhishek bhunia
 * 
 */

public class Person {
  private final String name;
  private final String address;
  private final String phone;
  private final String email;
  private final String note;
  
  /**
   * 
   * public Builder class for Person
   *
   */
  public static class Builder {
    // required parameter
    private String name;
    // optional parameters
    private String address;
    private String phone;
    private String email;
    private String note;	
    
    /**
     * 
     * @param name : this is a required parameter, used as a name of the person
     */
    public Builder (String name) {
      this.name = name;
    }

    /**
     * 
     * @param address : optional address parameter, if not invoked, the property will stay null
     * @return : returns the builder object
     */
    public Builder address(String address) {
      this.address = address;
      return this;
    }
    
    /**
     * 
     * @param : optional phone parameter, if not invoked, the property will stay null
     * @return : returns the builder object
     */
    public Builder phone(String phone) {
      this.phone = phone;
      return this;
    }
    
    /**
     * 
     * @param email : optional email parameter, if not invoked, the property will stay null
     * @return : returns the builder object
     * @return
     */
    public Builder email(String email) {
      this.email = email;
      return this;
    }
    
    /**
     * 
     * @param note: optional note parameter, if not invoked, the property will stay null
     * @return : returns the builder object
     * @return
     */
    public Builder note(String note) {
      this.note = note;
      return this;
    }
    
    /**
     * 
     * @return : build method builds a Person object from Build object and returns it
     */
    public Person build() {
      return new Person(this);
    }
    
    /**
     * Getter for builder class : Can be useful to create successive person objects using the same builder
     * @return
     */
    public String getBuilderName() {
      return name;
    }
    
    /**
     * Getter for builder class : Can be useful to create successive person objects using the same builder
     * @return
     */
    public String getBuilderAddress() {
      return address;
    }
    
    /**
     * Getter for builder class : Can be useful to create successive person objects using the same builder
     * @return
     */
    public String getBuilderPhone() {
      return phone;
    }
    
    /**
     * Getter for builder class : Can be useful to create successive person objects using the same builder
     * @return
     */
    public String getBuilderEmail() {
      return email;
    }
    
    /**
     * Getter for builder class : Can be useful to create successive person objects using the same builder
     * @return
     */
    public String getBuilderNote() {
      return note;
    }
    
    /**
     * Setter for builder class : Can be useful to create successive person objects using the same builder
     * @param name
     */
    public void setBuilderName(String name) {
      this.name = name;
    }
    
    /**
     * Setter for builder class : Can be useful to create successive person objects using the same builder
     * @param address
     */
    public void setBuilderAddress(String address) {
      this.address = address;
    }
    
    /**
     * Setter for builder class : Can be useful to create successive person objects using the same builder
     * @param phone
     */
    public void setBuilderPhone(String phone) {
      this.phone = phone;
    }
    
    /**
     * Setter for builder class : Can be useful to create successive person objects using the same builder
     * @param email
     */
    public void setBuilderEmail(String email) {
      this.email = email;
    }
    
    /**
     * Setter for builder class : Can be useful to create successive person objects using the same builder
     * @param note
     */
    public void setBuilderNote(String note) {
      this.note = note;
    }
    
  }
  //private Person constructor
  private Person(Builder builder) {
    name       = builder.name;
    address    = builder.address;
    phone      = builder.phone;
    email      = builder.email;
    note       = builder.note;
  }
  
  /**
   * Returns the name of the Person
   * @return
   */
  public String getPersonName() {
    return name;
  }
  
  /**
   * Returns the address of the Person
   * @return
   */
  public String getPersonAddress() {
    return address;
  }
  
  /**
   * Returns the phone number of the Person
   * @return
   */
  public String getPersonPhone() {
    return phone;
  }
  
  /**
   * Returns the email of the Person
   * @return
   */
  public String getPersonEmail() {
    return email;
  }
  
  /**
   * Returns the note related to the Person
   * @return
   */
  public String getPersonNote() {
    return note;
  }  
  
  @Override
  public boolean equals(Object ob) {
    if (ob != null && (ob instanceof Person)) {
      Person person = (Person) ob;
      if (this.name.equals(person.getPersonName()) 
          && (this.address == null ? person.address == null : this.address.equals(person.getPersonAddress()))
          && (this.phone == null ? person.phone == null : this.phone.equals(person.getPersonPhone())) 
          && (this.email == null ? person.email == null : this.email.equals(person.getPersonEmail()))
          && (this.note == null ? person.note == null : this.note.equals(person.getPersonNote()))) {
      	  
      	return true;
      }
      
      return false;
    }
    
    return false;    
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(name.hashCode(), address.hashCode(), phone.hashCode(), email.hashCode(), note.hashCode());
  }
  
  @Override
  public String toString() {
    String personInfo = "Person Details[";
    personInfo = personInfo + " Name: " + name;
    if(address != null)
    	personInfo = personInfo + ", Address: " + address;
    if(phone != null)
    	personInfo = personInfo + ", Phone: " + phone;
    if(email != null)
    	personInfo = personInfo + ", Email: " + email;
    if(note != null)
    	personInfo = personInfo + ", Note: " + note;
    personInfo = personInfo + "]";
    return personInfo;
  }
}
