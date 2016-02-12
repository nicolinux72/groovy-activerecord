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

	/**
	 * Perform a SELECT COUNT for this entity on database 
	 */
	static countEntities() {
		gac().entityManager.createQuery("SELECT COUNT(o) FROM ${this.name} o", Long.class).getSingleResult();
	}
	
	/**
	 * Return all entities store on database: pay attention on high volume
	 * tables.
	 */
	static findAllEntities() {
		gac().entityManager.createQuery("SELECT o FROM ${this.name} o", this).getResultList();
	}
	
	/**
	 * Retreive from database the entity with the passed primary key
	 */
	static  findEntity(id) {
		if (id == null) return null;
		return gac().entityManager.find(this, id);
	}
	
	/**
	 * Found all entities then paginate the results 
	 */
	static findEntries( int firstResult, int maxResults) {
			gac().entityManager.createQuery("SELECT o FROM ${this.name} o", this)
		 .setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}
		
	/**
	 * Persiste this instance
	 * @return
	 */
	def persist() { gac().persist(this) }
	
	/**
	 * Remove from db this instance
	 * @return
	 */
	def remove()  { gac().remove(this) }
	
	/**
	 * Force EntityManager to flush pending changes
	 */
	void flush()  { gac().flush() }
	
	/**
	 * Clear EntityManager
	 */
	void clear()  { gac().clear() }
	
	/**
	 * Merge this instance with the one inside EntityManager 
	 * @return
	 */
	def merge()   { gac().merge(this) }

}
