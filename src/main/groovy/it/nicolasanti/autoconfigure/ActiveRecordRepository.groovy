package it.nicolasanti.autoconfigure

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

/**
 * This trait delivery an ActiveRecord standard implementation
 * to all entity class marked like these:
 * <br><br>
 * <code> class YourClass implements ActiveRecordRepository</code>
 * <br><br>
 * 
 * Note: other methods could be added manually, if you like
 * these kind of things:
 * <br><br><code>
 * Studente.metaClass.'static'.YourMethodName = {
 *     ActiveRecordRepository.entityManager().doSomething()
 *  }</code>
 * <br><br>
 * 
 * All transaction methods are actually implemented in another 
 * class named enericActiveRecord
 * 
 * @author nicola
 **/


trait ActiveRecordRepository {
	
	//static GenericActiveRecord gac() inject by  ActiveRecordConfig

	static countEntities() {
		gac().entityManager.createQuery("SELECT COUNT(o) FROM ${this.name} o", Long.class).getSingleResult();
	}
	
	static findAllEntities() {
		gac().entityManager.createQuery("SELECT o FROM ${this.name} o", this).getResultList();
	}
	
	static  findEntity(id) {
		if (id == null) return null;
		return gac().entityManager.find(this, id);
	}
	
	static findEntries( int firstResult, int maxResults) {
			gac().entityManager.createQuery("SELECT o FROM ${this.name} o", this)
		 .setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}
		
	//i metodi che richiedono transazione sono delegati
	def persist() { gac().persist(this) }
	def remove()  { gac().remove(this) }
	void flush()  { gac().flush() }
	void clear()  { gac().clear() }
	def merge()   { gac().merge(this) }

}
