public class Contact {
	private String name;
	private String phoneNumber;
	
	public Contact(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().equals(Contact.class))
			return false;
		Contact other = (Contact)obj;
		return other.name.equals(this.name) && other.phoneNumber.equals(this.phoneNumber);
	}

	@Override
	public String toString() {
		return this.name + " - " + this.phoneNumber;
	}
}
