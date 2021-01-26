package com.qa.ims.persistence.domain;

public class Customer {

	private Long id;
	private String firstName;
	private String surname;

	public Customer(String firstName, String surname) {
		this.setFirstName(firstName);
		this.setSurname(surname);
	}

	public Customer(Long id, String firstName, String surname) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setSurname(surname);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Customer{");
		sb.append("id=").append(id);
		sb.append(", firstName='").append(firstName).append('\'');
		sb.append(", surname='").append(surname).append('\'');
		sb.append('}');
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Customer customer = (Customer) o;

		if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
		if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
		return surname != null ? surname.equals(customer.surname) : customer.surname == null;
	}
}



