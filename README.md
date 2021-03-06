# IMS (Inventory Management System)

This is a simple project for managing an inventory system. It allows the user to communicate
with a database through a CLI (command line interface). This project implements basic CRUD functionality
as well as some other small features such as an order calculator. The project is made entirely in java, 
there are artifacts stored under the GitHub releases section which can be run locally.
## Getting Started

To get a local copy of this project you can either use Git Bash to clone this repository, or download 
this as a .rar file ```(code -> Download zip)```. Once you have the files the installing section will tell
you how to run the code.

### Prerequisites

These technologies are required to run and build to program

#### Required 
- A Windows/macOS/Linux/Unix operating system
  - The code has been tested in a windows 10 environment however any machine 
    that supports the JVM *should* be able to run this code
- A working copy of Java 11 with a JVM install, this allows you to 
  run the unpacked files
    - Can be downloaded from https://adoptopenjdk.net/

#### Optional
- To build to project into a jar or fat-jar you will need a copy of maven installed
    - Can be downloaded from https://maven.apache.org/download.cgi
    
- An IDE to run the un-packaged java code in 
    - Either [Eclipse](https://www.eclipse.org/downloads/) or [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows) will be work 

- To see the HTML coverage reports generated by IntelliJ you will need a browser
    - The latest version of [chrome](https://www.google.com/intl/en_uk/chrome/), [firefox](https://www.mozilla.org/en-GB/firefox/new/) or [edge](https://www.microsoft.com/en-us/edge) should be appropriate.
### Installing

#### Running the Java code
In your IDE of choice open the project as a Maven project

```
File -> Open -> select pom.xml
```

Different IDE's have different ways of opening a maven project so please 
look up how to import a maven project for your specific IDE. The above example should
work for IntelliJ where it will auto-detect the project from the pom.xml file

#### Running the .jar artifact
Make sure you download the latest release, in the release folder open either a cmd or PowerShell instance,
and type ```Java -jar ims-*-jar-with-dependencies.jar``` where * is the version you have downloaded. This should start
the program in cmd/PowerShell

Once the project has loaded you can begin by right clicking the file called runner, 
and clicking the run button (usually a green symbol), this will initiate the program, and a menuing system will appear in the terminal. from here you can select which option
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
#### Current coverage : 75%
To open the current coverage reports navigate to the coverage folder and open the index.html file.

In this project I used 2 types of testing, unit testing, which tests individuals objects within the system,
this was done using JUnit. I also used behavioural testing with Mockito to test the expected behaviour of some classes (especially useful 
for when user input was needed). The testing report is located 

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

## Project Management

Project management was done using Jira, the Jira for this project
is located [here](https://qahenryoliveredwards.atlassian.net/secure/RapidBoard.jspa?projectKey=IMS&rapidView=1&view=planning.nodetail&atlOrigin=eyJpIjoiMThjZWU4NmEzMDQ3NDFmOWE0NjA2MWMwNmIzYTdjZWMiLCJwIjoiaiJ9)

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
