package com.springboot.jpa.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String lastname;
	@Column(name="programming_language")
	private String pogrammingLanguaje;
	
	@Embedded
	private Audit audit = new Audit();

	
	public Person() {
		
	}
	
	
	
	public Person(String name, String lastname) {
		super();
		this.name = name;
		this.lastname = lastname;
	}



	public Person(Long id, String name, String lastname, String pogrammingLanguaje) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.pogrammingLanguaje = pogrammingLanguaje;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPogrammingLanguaje() {
		return pogrammingLanguaje;
	}
	public void setPogrammingLanguaje(String pogrammingLanguaje) {
		this.pogrammingLanguaje = pogrammingLanguaje;
	}

		
	
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", lastname=" + lastname + ", pogrammingLanguaje="
				+ pogrammingLanguaje +",createAt="+audit.getCreatAt()+ ", updateAt="+audit.getUpdatedAt()+ "]";
	}
	

	
	
	
	
	

}
