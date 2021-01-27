package com.qa.ims.persistence.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

	@Test
	public void testConstructor() {
		Customer customer = new Customer();
		assertEquals(Customer.class, customer.getClass());
	}

	@Test
	public void testConstructor1() {
		Customer customer = new Customer("henry", "oliver-edwards");
		assertEquals("henry", customer.getFirstName());
		assertEquals("oliver-edwards", customer.getSurname());

	}

	@Test
	public void testConstructor2() {
		Customer customer = new Customer(1L, "henry", "oliver-edwards");
		assertEquals(Long.valueOf("1"), customer.getId());
		assertEquals("henry", customer.getFirstName());
		assertEquals("oliver-edwards", customer.getSurname());
	}

	@Test
	public void testToString() {
		Customer customer = new Customer(1L, "henry", "oliver-edwards");
		String expected = "Customer{id=1, firstName='henry', surname='oliver-edwards'}";
		assertEquals(expected, customer.toString());
	}

	@Test
	public void testHashCode() {
		Customer customer = new Customer(1L, "henry", "oliver-edwards");
		Customer customer1 = new Customer(1L, "henry", "oliver-edwards");
		Customer customer2 = new Customer(2L, "aaron", "mayne");

		assertEquals(customer.hashCode(), customer1.hashCode());
		assertNotEquals(customer.hashCode(), customer2.hashCode());
	}
}
