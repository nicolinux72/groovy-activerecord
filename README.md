# Groovy ActiveRecord

This library adds ActiveRecord support to your groovy jpa entity inside a Spring Boot application. That's all.

## Setup your project 

Add grovy active-record library to your Spring Boot project dependencies. For example add these lines to your pom.xml file: 

```xml
<dependency>
    <groupId>it.nicolasanti</groupId>
    <artifactId>groovy-activerecord</artifactId>
    <version>1.0.0</version>
</dependency>
```

Or this line to your build.gradle file:

```groovy
compile 'it.nicolasanti:groovy-activerecord:1.0.0'
```

Please consider that this library work with early versio of Spring boot also, so you may consider to replace dependency to Spring Boot 1.3.0.RELEASE with the version your are actually use.

The last step is remember to scan for component the groovy-activerecord'jar also, something like this:

```groovy
@ComponentScan("it.nicolasanti.autoconfigure")
```




## Setup your entity class

Simpliy add the trait ActiveRecordRepository to your entity jpa entity class in this way:

```groovy
@Entity  
class YourEntity implements ActiveRecordRepository {
  ...
}
```

Then Spring Boot, starting up your application, will add to your entity class standard Active Record methods like:

```groovy
/** Perform a SELECT COUNT for this entity on database */
static countEntities() 
	
/** Return all entities store on database: pay attention on high volume tables. */
static findAllEntities() 
	
/** Retreive from database the entity with the passed primary key */
static  findEntity(id) 
	
/** Found all entities then paginate the results */
static findEntries( int firstResult, int maxResults) 
		
/** Persiste this instance */
def persist() 
	
/** Remove from db this instance */
def remove()  

/** Force EntityManager to flush pending changes */
void flush()  

/** Clear EntityManager */
void clear()  

/** Merge this instance with the one inside EntityManager  */
def merge()   
```

You could found all these methods inside the class 
[ActiveRecordRepository]
(https://github.com/nicolinux72/groovy-activerecord/blob/master/src/main/groovy/it/nicolasanti/autoconfigure/ActiveRecordRepository.groovy)

# Working example

If you are interested in see a workging example you could take a look to test classes: you'll find an entity class named [Studente](https://github.com/nicolinux72/groovy-activerecord/blob/master/src/test/groovy/it/nicolasanti/autoconfigure/Studente.groovy) (with means student, in italian) with and id, a name and a surname as only properties. The injected ActiveRecord methods are tested in the class [ActiveRecordTest](https://github.com/nicolinux72/groovy-activerecord/blob/master/src/test/groovy/it/nicolasanti/autoconfigure/ActiveRecordTest.groovy).
