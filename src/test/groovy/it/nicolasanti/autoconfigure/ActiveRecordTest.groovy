package it.nicolasanti.autoconfigure

import java.util.List



import org.hamcrest.core.IsNot;
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

import static org.hamcrest.Matchers.greaterThan
import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.notNullValue
import static org.hamcrest.Matchers.equalTo
import static org.junit.Assert.assertThat



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
class ActiveRecordTest {
	
	@Test //@Ignore
	void findAll() {
		
		List<Studente> studenti = Studente.findAllEntities()
		assertThat(studenti.size(), is(equalTo(3)))
	}
	
	@Test //@Ignore
	void find() {
		Studente studenti = Studente.findEntity(new Long(1))
		assertThat(studenti,is(notNullValue()))
	}
	
	@Test //@Ignore
	void countAll() {
		int conta = Studente.countEntities()
		assertThat(conta, is(equalTo(3)))
	}
	
	@Test //@Ignore
	void findEntries() {
		List<Studente> studenti = Studente.findEntries(2,1)
		assertThat(studenti.size(), is(equalTo(1)))
	}
	
	@Transactional @Test //@Ignore
	void persist() {
		Studente studente = new Studente(id: 5 ,name: "Marchino", surname:"Rosino")
		studente.persist()
		//studente.flush()
		
		studente.remove()
	}

}
