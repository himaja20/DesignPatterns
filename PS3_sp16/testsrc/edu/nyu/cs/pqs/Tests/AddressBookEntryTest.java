package edu.nyu.cs.pqs.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.pqs.AddressBook.AddressBookEntry;

public class AddressBookEntryTest {

	AddressBookEntry entry;
	
	@Before
	public void setUp() throws Exception {
		 entry = new AddressBookEntry();
		 System.out.println(entry.toString());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createEmptyEntryTest() {
		assertTrue(entry.getName() == null);
		assertTrue(entry.getEmail() == null);
		assertTrue(entry.getNote() == null);
		assertTrue(entry.getPostalAddress() == null);
		assertTrue(entry.getPhoneNumber() == 0);
	}

	@Test
	public void nameShouldBeSet(){
		entry.setName("Test");
		assertEquals("Test",entry.getName());
	}
	
	@Test
	public void noteShouldBeSet(){
		entry.setNote("TestNote");
		assertEquals("TestNote",entry.getNote());
	}
	
	@Test
	public void adressShouldBeSet(){
		entry.setPostalAddress("Test, 107 Soth drive, new Jersey, 07306");
		assertEquals("Test, 107 Soth drive, new Jersey, 07306",entry.getPostalAddress());
	}
	
	@Test
	public void emailShouldBeSet(){
		entry.setEmail("Test@gmail.com");
		assertEquals("Test@gmail.com",entry.getEmail());
	}
	
	@Test
	public void phoneShouldBeSet(){
		entry.setPhoneNumber(000000000000000000000000000000000000000000000);
		assertEquals(000000000000000000000000000000000000000000000,entry.getPhoneNumber());
	}
	
	@Test
	public void toStringShouldBeReturned(){
		entry.setEmail("Test@gmail.com");
		entry.setPhoneNumber(000000);
		entry.setPostalAddress("test, new Jersey, 07306");
		entry.setName("Test");
		entry.setNote("notes");
		assertEquals("Test,test, new Jersey, 07306,000000,Test@gmail.com,notes",entry.toString());
	}
	
	@Test
	public void equalsContractVerifier(){
		
		//TODO her equals implementation is wrong. This implementation does not satisfy the reflexive, symmetric and transitive features of equals function
		//what to do in this scenario? Should I test it according to her logic..or fail the test? I am unable fail the test also as she is capturing all 
		//failures using an IllegalArgumentException !!s
		entry.setEmail("Test@gmail.com");
		entry.setPhoneNumber(000000);
		entry.setPostalAddress("test, new Jersey, 07306");
		entry.setName("Test");
		entry.setNote("notes");
		
		AddressBookEntry entry2 = new AddressBookEntry();
		
		entry2.setEmail("Test@gmail.com");
		entry2.setPhoneNumber(000000);
		entry2.setPostalAddress("test, new Jersey, 07306");
		entry2.setName("Test");
		entry2.setNote("notes");
		 
		assertTrue(entry.equals(entry2));
	}
	
}
