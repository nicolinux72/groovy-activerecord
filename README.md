# groovy active-record

This library add ActiveRecord support to jpa entity written in groovy inside Spring Boot application.

Simpliy add the trait ActiveRecordRepository to your entity class:

@Entity  
class YourEntity implements ActiveRecordRepository {
  ...
}

Then Spring Boot,when your application is booted, will add to you class standard Active Record methods like:

static countEntities() 
static findAllEntities() 
static  findEntity(id) 
static findEntries( int firstResult, int maxResults) 
persist()
remove() 
flush() 
clear()  
merge()

You could found all thes methods inside the class ActiveRecordRepository

