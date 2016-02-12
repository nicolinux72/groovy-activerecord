package it.nicolasanti.autoconfigure

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Transient

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional

/**
 * Transactional ActiveRecord's methods need to be implemented
 * in a Spring instatiated class and marker @Transactional.
 * This class to the job.
 *
 * @author nicola
 *
 */
@Service
class GenericActiveRecord {
	
	@PersistenceContext
	EntityManager entityManager

	@Transactional
	def persist(obj) {
		entityManager.persist(obj)
	}
	
	@Transactional
	def remove(obj) {
		if (entityManager.contains(obj)) {
			entityManager.remove(obj);
		} else {
			def attached = find(obj.id);
			entityManager.remove(attached);
		}
	}
	
	@Transactional
	void flush() { entityManager.flush(); }
	
	@Transactional
	void clear() { entityManager.clear(); 	}
	
	@Transactional
	def merge(obj) {
		def merged = entityManager.merge(obj);
		entityManager.flush();
		return merged;
	}

}
