package com.springboot.jpa.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.jpa.dto.PersonDto;
import com.springboot.jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaAplication implements CommandLineRunner {
	
	@Autowired
	PersonRepository repository;
	

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaAplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		

		//findOne();
		
		//repository.findOneName("Pepe").ifPresent(System.out :: println);
		//repository.findByNameContaining("ri").ifPresent(System.out :: println);
		
		//create();
		update();
		//delete2();
		//personalizedQueries();
		//personalizedQueries2();
		//personalizedQueriesDistinct();
		//personalizedQueriesConcatUpperAndLowerCase();
		//personalizedQueriesBetween();
		//queriesFunctionAggregation();
		//subQuery();
		//whereIn();
	}
	
	public void findOne() {
		//Person person= repository.findById(2L).get();
		//Person person= repository.findById(2L).orElseThrow();
		
		Person person=null;
		Optional<Person> optinalPerson= repository.findById(2L);
		//String namePerson = optinalPerson.get().getName();
		//if(!optinalPerson.isEmpty())
		if(optinalPerson.isPresent()) {
			
			person = optinalPerson.get();
		}
		
		
		repository.findById(1L).ifPresent(System.out :: println);
		
		
		System.out.println(person);
		
	}
	
	@Transactional(readOnly = true)
	public void whereIn() {
		
		System.out.println("===============consulta where in ======================");
		List<Person> persons = repository.getPersonsByIds(Arrays.asList(1L,2L,5L,7L));
		
		persons.forEach(System.out:: println);
	}
	
	@Transactional(readOnly = true)
	public void subQuery() {
		
		
		System.out.println("===============subconsulta ======================");
		List<Object[]> regs = repository.getShortedName();
		regs.forEach(reg ->{
			String name = (String) reg[0];
			Integer length=  (Integer) reg[1];
			
			System.out.println("name: "+name+ ", length="+length);
		});
		
		Optional<Person> optionalPerson = repository.getLastRegistration();
		
		if(optionalPerson.isPresent()) {
			
			Person person = optionalPerson.get();
			System.out.println(person);
		}
		
	}
	
	@Transactional(readOnly = true)
	private void queriesFunctionAggregation() {
		
		System.out.println("===============consulta con el total de registros de la tabla persona ======================");
		Long count = repository.getTotalPerson();
		System.out.println(count);

		System.out.println("===============consulta con el valor minimo de ID ======================");
		Long min = repository.getMinId();
		System.out.println(min);
		
		System.out.println("===============consulta con el valor maximo de ID ======================");
		Long max = repository.getMaxId();
		System.out.println(max);
		
		System.out.println("===============consulta con el valor maximo de ID ======================");
		
		List<Object[]> regs =  repository.getPersonNameLength();
		regs.forEach(reg ->{
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			
			System.out.println("name: "+name+ ", length="+length);
		});
		
		
	}
	
	@Transactional(readOnly = true)
	public void personalizedQueriesBetween() {
		
		System.out.println("===============consulta  por rangos======================");
		
		List<Person> persons =  repository.findAllBetweenId(2L,5L);
		persons.forEach(System.out :: println);
		
		persons= repository.findByNameBetweenOrderByNameDescLastnameAsc("J", "Q");
		persons.forEach(System.out :: println);
		
		
		persons = repository.getAllOrdered();
		persons.forEach(System.out :: println);
	}
	
	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperAndLowerCase() {
		
		System.out.println("===============consulta nombre y apellidos de personas======================");
		
		List<String> nameConcat = repository.findAllFullNameConcat();
		nameConcat.forEach(System.out :: println);
		
		
		System.out.println("===============consulta nombre y apellidos mayuscula======================");
		
		List<String> nameUpper = repository.findAllFullNameUppert();
		nameUpper.forEach(System.out :: println);
		
		System.out.println("===============consulta nombre y apellidos minuscula======================");
		
		List<String>nameLower = repository.findAllFullNameLower();
		nameLower.forEach(System.out:: println);
		
		System.out.println("===============consulta nombre y apellidos upper lower y case======================");
		
		List<Object[]> nameCase = repository.findAllPersonDalistCase();
		nameCase.forEach(reg -> System.out.println("id="+reg[0] + ", nombre ="+ reg[1] + ", apellido ="+ reg[2]+ ", lenguaje ="+reg[3]) );
		
		
	}
	
	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		
		
		System.out.println("===============consulta por nombre ======================");
		
		List<String> names= repository.findAllNames();
		names.forEach(System.out :: println);
		
		
		System.out.println("===============consulta por nombre con distinct ======================");
		
		List<String>namesDistinct = repository.findAllNamesDisntinct();
			
		namesDistinct.forEach(System.out :: println);	
		
		System.out.println("===============consulta por nombre con distinct lenguage ======================");
			
		List<String> lenguage = repository.findAllNamesDisntinctLenguage();
		
		lenguage.forEach(System.out :: println);
		
		System.out.println("===============consulta cantidad distinct lenguage ======================");
		
		Long cantidad= repository.findAllNamesDisntinctLenguageCount();
		System.out.println("total de lenguajes de programacion: "+cantidad);
		
	}
	
	@Transactional(readOnly = true)
	public void personalizedQueries2() {
		
		System.out.println("===============consulta por objeto de persona y lenguaje de programacion======================");
		
		List<Object[]> personRegs = repository.findAllMixPersona();
		
		personRegs.forEach(reg -> {
			System.out.println("programmingLanguage ="+reg[1] +", person="+reg[0] );
		});
				
		System.out.println("consulta que puebla y devuelve objecto entity de una instancia personalizada");
		List<Person> persons = repository.findAllObjectPersonaLized();
		persons.forEach(System.out :: println);
		
		
		
		System.out.println("consulta que puebla y devuelve objecto dto de una clase personalizada");
		
		List<PersonDto> personDto= repository.findAllPersonDto();
		
		personDto.forEach(System.out :: println);
	}
	
	@Transactional(readOnly = true)
	public void personalizedQueries() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("===============consulta solo el nombre por el id======================");
		System.out.println("ingrese el id para el nombre");
		Long id = scanner.nextLong();
		scanner.close();
		
		System.out.println("====mostrar todo el nombre =====");
		String name =  repository.getNameById(id);
		System.out.println(name);
		
		
		System.out.println("====mostrar todo el id =====");
		Long idBd =  repository.getIdById(id);
		System.out.println(name);
		
		
		System.out.println("====mostrar todo el nombre completo con concat =====");
		String fullname =  repository.getFullNameById(id);
		System.out.println(fullname);
		
		System.out.println("==consulta por campos personalizados por el id=====");
	    Optional<Object> optionalReg =  repository.obtenerPersonaDataById(id);
	    if(optionalReg.isPresent()) {
	    	Object[] personReg= (Object[]) optionalReg.orElseThrow();
	    	
	    	System.out.println("id="+personReg[0] + ", nombre ="+ personReg[1] + ", apellido ="+ personReg[2]+ ", lenguaje ="+personReg[3]);
	    }
		
		
		
		System.out.println("====consulta por campos personalizados lista =====");
		
		List<Object[]> regs = repository.obtenerPersonaDataList();
		regs.forEach(reg -> System.out.println("id="+reg[0] + ", nombre ="+ reg[1] + ", apellido ="+ reg[2]+ ", lenguaje ="+reg[3]));
		
		
	}
	
	@Transactional
	public void update() {
		Scanner scanner= new Scanner(System.in);
		
		System.out.println("Ingrrse el id a editar");
		
		Long id= scanner.nextLong();
		Optional<Person> optionaRepository = repository.findById(id);
		
		/*optionaRepository.ifPresent(p -> {
			System.out.println("Ingrese el lenguaje de programacion");
			String programmingLanguege = scanner.next();
			
			p.setPogrammingLanguaje(programmingLanguege);
			Person personBD = repository.save(p);
			System.out.println(personBD);
			
		});*/
		
		if(optionaRepository.isPresent()) {
			Person personDB= optionaRepository.orElseThrow();
			 System.out.println(personDB);
			
			System.out.println("Ingrese el lenguaje de programacion");
			String programmingLanguege = scanner.next();
			
			personDB.setPogrammingLanguaje(programmingLanguege);
			Person personUpdate = repository.save(personDB);
			System.out.println(personUpdate);
			
		}else {
			System.out.println("Id no esta en la BD");
		}
		
		scanner.close();
	}
	
	@Transactional
	public void delete2() {
		
		repository.findAll().forEach(System.out :: println);
		
		Scanner scanner= new Scanner(System.in);
		
		System.out.println("Ingrese el id a eliminar");
		Long id = scanner.nextLong();
		
		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(p -> repository.delete(p), () -> System.out.println("Lo sentimos la persona no existe"));
		
		repository.findAll().forEach(System.out :: println);
		
		scanner.close();
		
			
		
	}
	
	@Transactional
	public void delete() {
		
		repository.findAll().forEach(System.out :: println);
		
		Scanner scanner= new Scanner(System.in);
		
		System.out.println("Ingrese el id a eliminar");
		Long id = scanner.nextLong();
		repository.deleteById(id);
		repository.findAll().forEach(System.out :: println);
		
		scanner.close();
		
			
		
	}
	
	@Transactional
	public void create() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("ingrese el nombre: ");
		String name = scanner.next();
		System.out.println("ingrese el apellido: ");
		String lastname = scanner.next();
		System.out.println("Ingrese el lenguaje de programacion: ");
		String programmingLanguage = scanner.next();
		scanner.close();
		
		
		
		Person person= new Person(null,name,lastname,programmingLanguage);
		
		Person newPerson = repository.save(person);
		System.out.println(newPerson);
		
		repository.findById(newPerson.getId()).ifPresent(System.out :: println);
	}
	
	@Transactional(readOnly = true)
	public void list() {

		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.findByPogrammingLanguaje("Python");
		
		//List<Person> persons = (List<Person>) repository.buscarByPogrammingLanguaje("Python", "pepe");
		List<Person> persons = (List<Person>) repository.findByPogrammingLanguajeAndName("Python", "pepe");
		
		
		persons.stream()
		       .forEach(p -> System.out.println(p));
		
		

		List<Object[]> personValue= repository.obtenerPersonaData();
		personValue.stream()
				   .forEach(p -> {
					  System.out.println(p[0]+ "es experto en "+ p[1] );
					  //System.out.println(p.g );
				   });
		
		
	}
	
	

}
