package br.senac.go.app1.model;

import java.util.Date;

public class Aluno {
//	private Key key; // Generated based on email
	private String name;
	private String email; 
	private Date createdAt;

	public Aluno() {
	}

//	public Key getKey() {
//		return key;
//	}
//
//	public void setKey(Key key) {
//		this.key = key;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [name=" + name + ", email=" + email + ", createdAt="
				+ createdAt + "]";
	}

}
