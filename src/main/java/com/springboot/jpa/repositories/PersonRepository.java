package com.springboot.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.jpa.dto.PersonDto;
import com.springboot.jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
	
	
	@Query("select p from Person p where p.id in ?1")
	public List<Person> getPersonsByIds(List<Long> ids);
	
	
	
	@Query("select p.name, length(p.name) from Person p "
		  + "where length(p.name)="
		  + "(select min(length(p.name)) from Person p)")
	List<Object[]> getShortedName(); 
	
	
	@Query("select p  from Person p where p.id="
		  + "(select max(p.id) from Person p)")
	Optional<Person> getLastRegistration();
	
	
	@Query("select p.name, length(p.name) from Person p")
	List<Object[]> getPersonNameLength();
	
	
	
	@Query("select  count(p) from Person p")
	Long getTotalPerson();
	
	@Query("select min(p.id) from Person p")
	Long getMinId();
	
	@Query("select max(p.id) from Person p")
	Long getMaxId();
	
	
	@Query("select p from Person p order by p.name desc, p.lastname asc")
	List<Person>getAllOrdered();
	
	List<Person>findByIdBetweenOrderByIdDesc(Long id1, Long id2);
	List<Person>findByNameBetweenOrderByNameDescLastnameAsc(String c1, String c2);
	
	
	@Query("select p from Person p where p.name between ?1 and ?2 order by p.name desc " )
	List<Person> findAllBetweenName(String c1, String c2);
	
	@Query("select p from Person p where p.id between ?1 and ?2 order by p.name desc , p.lastname desc")
	List<Person> findAllBetweenId(Long id1, Long id2);
	
	@Query("select p.id, upper(p.name), lower(p.lastname),upper(p.pogrammingLanguaje) from Person p")
	List<Object[]> findAllPersonDalistCase();
	
	@Query("select lower(p.name ||' '|| p.lastname) from Person p")
	List<String>findAllFullNameLower();
	
	
	@Query("select  upper(p.name ||' '|| p.lastname) from Person p")
	List<String>findAllFullNameUppert();
	
	//@Query("select CONCAT(p.name,' ',p.lastname) fullname from Person p")
	@Query("select p.name ||' '|| p.lastname from Person p")
	List<String>findAllFullNameConcat();
	
	
	@Query("select p.name from Person p")
	List<String> findAllNames();
	
	@Query("select distinct (p.name) from Person p")
	List<String> findAllNamesDisntinct();
	
	@Query("select distinct(p.pogrammingLanguaje) from Person p")
	List<String> findAllNamesDisntinctLenguage();
	
	
	@Query("select count (distinct(p.pogrammingLanguaje)) from Person p")
	Long findAllNamesDisntinctLenguageCount();
	
	@Query("select new  com.springboot.jpa.dto.PersonDto(p.name,p.lastname) from Person p")
	List<PersonDto> findAllPersonDto();
	
	@Query("select new Person(p.name,p.lastname) from Person p")
	List<Person> findAllObjectPersonaLized();
	
	
	@Query("select p.name from Person p where p.id=?1 ")
	String getNameById(Long Id);
	
	@Query("select p.id from Person p where p.id=?1 ")
	Long getIdById(Long Id);
	
	
	@Query("select CONCAT (p.name,' ',p.lastname) from Person p where p.id=?1 ")
	String getFullNameById(Long Id);
	
	
	
	@Query("select p from Person p where p.id=?1")
	Optional<Person> findOne(Long id);
	
	
	@Query("select p from Person p where p.name=?1")
	Optional<Person> findOneName(String name);
	
	
	@Query("select p from Person p where p.name like %?1%")
	Optional<Person> findOneLikeName(String name);
	
	Optional<Person> findByNameContaining(String name);
	
	
	List<Person> findByPogrammingLanguaje(String pogrammingLanguaje);
	
	List<Person> findByPogrammingLanguajeAndName(String pogrammingLanguaje, String name);
	
	
	
	@Query("select p from Person p where p.pogrammingLanguaje=?1 and name=?2")
	List<Person> buscarByPogrammingLanguaje(String pogrammingLanguaje, String name);
	
	@Query("select p.name, p.pogrammingLanguaje from Person p ")
	List<Object[]> obtenerPersonaData();
	
	@Query("select p.name, p.pogrammingLanguaje from Person p where p.name=?1 ")
	List<Object[]> obtenerPersonaData( String name);
	
	@Query("select p, p.pogrammingLanguaje from Person p")
	List<Object[]> findAllMixPersona();
	
	
	@Query("select p.id, p.name,p.lastname, p.pogrammingLanguaje from Person p")
	List<Object[]> obtenerPersonaDataList();
	
	@Query("select p.id, p.name,p.lastname, p.pogrammingLanguaje from Person p where p.name=?1 ")
	Optional<Object> obtenerPersonaDataById( Long id);
	
	@Query("select p.name, p.pogrammingLanguaje from Person p where p.pogrammingLanguaje=?1 and p.name=?2 ")
	List<Object[]> obtenerPersonaData(String pogrammingLanguaje, String name);
	
	
	@Query("select p.name, p.pogrammingLanguaje from Person p where p.pogrammingLanguaje=?1  ")
	List<Object[]> obtenerPersonaDataByProgrammingLanguage(String pogrammingLanguaje);
	
	
	

}
