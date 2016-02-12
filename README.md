# groovy active-record

This library add ActiveRecord support to jpa entity written in groovy inside Spring Boot application.

## setup your project 

Add grovy active-record library to your Spring Boot project dependencies.

## instrument an entity class

Simpliy add the trait ActiveRecordRepository to your entity class:

```groovy
@Entity  
class YourEntity implements ActiveRecordRepository {
  ...
}
```

Then Spring Boot,when your application is booted, will add to you class standard Active Record methods like:

```groovy
static countEntities() 
static findAllEntities() 
static  findEntity(id) 
static findEntries( int firstResult, int maxResults) 
persist()
remove() 
flush() 
clear()  
merge()
```

You could found all thes methods inside the class 
[ActiveRecordRepository]
(https://github.com/nicolinux72/groovy-activerecord/blob/master/src/main/groovy/it/nicolasanti/autoconfigure/ActiveRecordRepository.groovy)

# working example

See test classes for a working example: you'll find an entity class named [Studente](https://github.com/nicolinux72/groovy-activerecord/blob/master/src/test/groovy/it/nicolasanti/autoconfigure/Studente.groovy) (italian word for student) with and id, a name and a surname. The ActiveRecord methods are tested in the class [ActiveRecordTest](https://github.com/nicolinux72/groovy-activerecord/blob/master/src/test/groovy/it/nicolasanti/autoconfigure/ActiveRecordTest.groovy).
