package edu.nyu.cs.pqs.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.pqs.AddressBook.AddressBook;

public class AddressBookTest {
	
	AddressBook ab;

	/**
	 * Set up is to create some stubs of contacts on 
	 * which I would like to test the Address book functionality.
	 * 
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		ab = new AddressBook();	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addAnEntryTest() {
		
		ab.addAnEntry("Test1", "104 ROmaine Avenue, Jersey City, NJ", 123456, "test@gmail.com", "testnote");
	}
	
	@Test
	public void addAnEntryTestWithNullValues() {
		AddressBook ab = new AddressBook();
		ab.addAnEntry(null, null, 123456, null, null);
	}
	
	

}
