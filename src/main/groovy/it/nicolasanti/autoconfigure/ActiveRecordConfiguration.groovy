package it.nicolasanti.autoconfigure

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

/**
 * Add ActiveRecord support to all JPA entities marked with  
 * ActiveRecordRepository trait. * 
 * 
 * @author nicola
 *
 */
@Configuration
@ConditionalOnClass( name= ["groovy.lang.GroovyObject","javax.persistence.EntityManager"])
class ActiveRecordConfiguration {
	
	public static Log logger = LogFactory.getLog(ActiveRecordConfiguration.class)
	
	@PersistenceContext
	EntityManager entityManager
	
	@Autowired
	GenericActiveRecord gac
	
	@Bean
	ActiveRecordRepository activeRecord() {
		
		logger.info ("ActiveRecord configuration start-up")
		
		//itero su tutte le entità gestite da JPA ed estendo
		//solo quelle che hanno il mixin di ActiveRecordRepository
		entityManager.getMetamodel().getEntities().each { type ->
			
			//type.javaType.metaClass.mixinClasses
			//   .find { ActiveRecordRepository.class == it.mixinClass.theClass }
			//   .each { extendModelClass(type) } //questo each è eseguito solo una volta per tipo
			
			if ( ActiveRecordRepository.class.name in type.javaType.interfaces*.name ) {
				injectGenericActiveRecord(type)
			}		
				
		}
		
		return null
	}
	
	void injectGenericActiveRecord(entiyType) {
		def model = entiyType.javaType
		logger.info("${model}: added active record support")
		
		//inietto -> static Entity.gac()
		model.metaClass.'static'.gac = { gac }

	}
	
	/*void extendModelClass(entiyType) {
		//println  "Java Type: ${entiyType.javaType}"
		//println  "Id   Type: ${entiyType.idType.javaType}"
		
		def model = entiyType.javaType
		def plurale = guestPlural(model.simpleName)
		
		model.annotations
		   .find { it  instanceof Plural }
		   .each { plurale=it.value() }
		
		   
		logger.info("ActiveRecord extension for model class ${model}, plural is ${plurale}")
		plurale = "" //disabilito il sistema dei plurali
		
		//inietto -> static ActiveRecordRepository.entityManager()
		ActiveRecordRepository.metaClass.'static'.entityManager = {
			entityManager
		}
		//inietto -> static Model.countAllEntity
		model.metaClass.'static'["countAll${plurale}"] = {
			entityManager.createQuery("SELECT COUNT(o) FROM ${model.name} o", Long.class).getSingleResult();
		}
		//inietto -> static Model.findAllEntity
		model.metaClass.'static'["findAll${plurale}"] = {
			entityManager.createQuery("SELECT o FROM ${model.name} o", model).getResultList();
		}
		//inietto -> static Model.findEntity
		model.metaClass.'static'["find${plurale}"] = { id ->
			if (id == null) return null;
			return entityManager.find(model, id);
		}
		//inietto -> static Model.findEntityEntries
		model.metaClass.'static'["findEntries${plurale}"] = { int firstResult, int maxResults ->
				entityManager.createQuery("SELECT o FROM ${model.name} o", model)
			 .setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
		}
	}
	
	String guestPlural(String name) {
	   name.replaceFirst(/.$/,"i")
	}*/
}
