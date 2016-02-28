package edu.nyu.hr970.pqshw1;

import java.util.ArrayList;

/**
 * Name class contains the information a contact entry can have about the name of a person
 * Name can have following information- 
 * 	- Firstname
 *  - Lastname
 *  - Middlename
 *  - Nickname
 *  - Name Prefix
 *  - Name Suffix 
 * @author himaja
 *
 */
public class Name {
	private final String firstName;
	private String lastName;
	private String middleName;
	private String nickname;
	private String namePrefix;
	private String nameSuffix;

	/**
	 * Builder class (Inner) to create an object. This class is used to create a Name object which has to be constructed based on 
	 * number of optional parameters passed.
	 * 
	 * Examples to create an object - 
	 * 
	 *  name = new Name.nameBuilder().firstName("John")).lastName("Parker")).
					middleName("Doe")).nickname("Joe")).
					namePrefix("Mr")).nameSuffix("Jr")).build();
					
	 *  name = new Name.nameBuilder().firstName("John")).lastName("Parker")).build();
	 */
	public static class nameBuilder {
		private String firstName = "";
		private String lastName = "";
		private String middleName = "";
		private String nickname = "";
		private String namePrefix = "";
		private String nameSuffix = "";

		public nameBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public nameBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public nameBuilder middleName(String middleName) {
			this.middleName = middleName;
			return this;
		}

		public nameBuilder nickname(String nickname) {
			this.nickname = nickname;
			return this;
		}

		public nameBuilder namePrefix(String namePrefix) {
			this.namePrefix = namePrefix;
			return this;
		}

		public nameBuilder nameSuffix(String nameSuffix) {
			this.nameSuffix = nameSuffix;
			return this;
		}

		public Name build() {
			return new Name(this);
		}
	}

	private Name(nameBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.middleName = builder.middleName;
		this.nickname = builder.nickname;
		this.namePrefix = builder.namePrefix;
		this.nameSuffix = builder.nameSuffix;
	}

	public String getFirstName(Name name) {
		return name.firstName;
	}

	public String getLastName(Name name) {
		return name.lastName;
	}

	public String getMiddleName(Name name) {
		return name.middleName;
	}

	public String getNickname(Name name) {
		return name.nickname;
	}

	public String getNamePrefix(Name name) {
		return name.namePrefix;
	}

	public String getNameSuffix(Name name) {
		return name.nameSuffix;
	}

	/**
	 * Check if name object contains a specified string in any of its entries 
	 * @param searchString - String to be searched for
	 * @return True if the string is found, otherwise return false
	 */
	boolean search(String searchString) {
		boolean retVal = false;
		retVal = retVal || (firstName != null ? firstName.toLowerCase().contains(searchString) : retVal);
		retVal = retVal || (lastName != null ? lastName.toLowerCase().contains(searchString) : retVal);
		retVal = retVal || (middleName != null ? middleName.toLowerCase().contains(searchString) : retVal);
		retVal = retVal || (nickname != null ? nickname.toLowerCase().contains(searchString) : retVal);
		retVal = retVal || (namePrefix != null ? namePrefix.toLowerCase().contains(searchString) : retVal);
		retVal = retVal || (nameSuffix != null ? nameSuffix.toLowerCase().contains(searchString) : retVal);

		return retVal;
	}

	/**
	 * Gets the list of types of Name, a contact can have
	 * @return returns an ArrayList of Name types that a contact can have
	 */
	public ArrayList<String> getNameTypes() {
		ArrayList<String> retList = new ArrayList<String>();
		retList.add("firstName");
		retList.add("lastName");
		retList.add("middleName");
		retList.add("nickname");
		retList.add("namePrefix");
		retList.add("nameSuffix");
		return retList;
	}

	/**
	 * Gets the list of entries that a name object contains within a contact object
	 * @param Email - Takes the name object within a contact as a parameter
	 * @return - returns an ArrayList of values which are contained by this name object
	 */
	public ArrayList<String> getNameEntries() {
		ArrayList<String> retList = new ArrayList<String>();
		retList.add(this.firstName);
		retList.add(this.lastName);
		retList.add(this.middleName);
		retList.add(this.nickname);
		retList.add(this.namePrefix);
		retList.add(this.nameSuffix);

		return retList;
	}

	@Override public boolean equals(Object o) {
		if(o == null){
			return false;
		}
		if (o == this) {
			return true;
		}
		if (!(o instanceof Name)) {
			return false;
		}
		boolean retVal = true;
		Name name = (Name) o;
		retVal = retVal && (firstName == null ? name.firstName == null : firstName.equals(name.firstName));
		retVal = retVal && (lastName == null ? name.lastName == null : lastName.equals(name.lastName));
		retVal = retVal && (middleName == null ? name.middleName == null : middleName.equals(name.middleName));
		retVal = retVal && (nickname == null ? name.nickname == null : nickname.equals(name.nickname));
		retVal = retVal && (namePrefix == null ? name.namePrefix == null : namePrefix.equals(name.namePrefix));
		retVal = retVal && (nameSuffix == null ? name.nameSuffix == null : nameSuffix.equals(name.nameSuffix));
		return retVal;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = 31 * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = 31 * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = 31 * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = 31 * result + ((namePrefix == null) ? 0 : namePrefix.hashCode());
		result = 31 * result + ((nameSuffix == null) ? 0 : nameSuffix.hashCode());
		return result;
	}

	/**
	 * returns the contents of a name object
	 */
	@Override
	public String toString() {
		return "Firstname :  " + firstName + "  Lastname:  " + lastName + "  Middlename: " + middleName 
				+ nickname + "  namePrefix: " + namePrefix + "  nameSuffix: " + nameSuffix;
	}
}
