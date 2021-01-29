Coverage: 75%
# Project Title

One Paragraph of project description goes here

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

These technologies are required to run and build to program

#### Required 
- A working copy of Java 11 with a JVM install, this allows you to 
  run the unpacked files
    - Can be downloaded from https://adoptopenjdk.net/

#### Optional
- To build to project into a jar or fat-jar you will need a copy of maven installed
    - Can be downloaded from https://maven.apache.org/download.cgi
    
- An IDE to run the code in 
    - Either [Eclipse](https://www.eclipse.org/downloads/) or [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows) will be work 

### Installing

In your IDE of choice open the project as a Maven project

```
File -> Open -> select pom.xml
```

Once the project has loaded you can begin by right clicking the file called runner, 
and clicking the run button (usually a green symbol), this will initiate the program
and a menuing system will appear in the terminal. from here you can select which option
you would like to choose. Below is an example of how you would add an item to a customers order.
```
CUSTOMER
UPDATE
ADD
1
1
1
```
The Above code when executed in order will add to a customer with ID = 1, with an order ID = 1,
and add the Item with ID = 1 to this order. 

## Running the tests

In this project I used 2 types of testing, unit testing, which tests individuals objects within the system,
this was done using JUnit. I also used behavioural testing with Mockito to test the expected behaviour of some classes (especially useful 
for when user input was needed).

### Unit Tests 

Unit tests aim to test the individual components of the code, such as classes. These tests then run these classes
and test the output of a class method against an expected output. To test a constructor for customer class I could do. 
```
@Test
public void testConstructor() {
    Customer customer = new Customer();
    assertEquals(Customer.class, customer.getClass());
}     
```

### Behavioural Tests

Behavioural tests try to test how we think that a class should react when 
given certain data. For this we often mock certain things such as user input 
to see how our program would react.
```
    @Test
    public void testCreate() {
        when(javaUtilities.getString()).thenReturn("henry", "oliver-edwards");
        when(customerDao.create(new Customer("henry", "oliver-edwards"))).thenReturn(new Customer("henry", "oliver-edwards"));
        when(customerDao.readLatest()).thenReturn(new Customer("henry", "oliver-edwards"));
        Customer expected = new Customer("henry", "oliver-edwards");
        Customer actual = customerController.create();
        assertEquals(expected, actual);
    }
```

### Coding style tests

A coding style tests to see whether your code holds up to industry standards, these can be anything
from naming conventions to not repeating blocks of code, these tests can even detect common security flaws
in code. For this I used SonarQube to read through the packaged .jar file.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Henry Oliver-Edwards** - *Continued work* - [QAHenryOliver-Edwards](https://github.com/QAHenryOliverEdwards)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Thank you to my group team Hawks for providing me with inspiration for new features
to add to the project and helping me with Mockito testing
* Thank you to my trainer/instructor/teacher Nickolas Johnson for teaching me all
the tools implemented in this project
