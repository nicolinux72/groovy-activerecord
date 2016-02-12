package it.nicolasanti.autoconfigure

import it.nicolasanti.autoconfigure.ActiveRecordRepository;
import it.nicolasanti.autoconfigure.Plural;

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity  @Table(name="studenti") //@Plural('Studentessi')
class Studente implements ActiveRecordRepository {
	
	@Id Long id
	String name
	String surname

}
