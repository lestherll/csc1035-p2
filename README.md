# CSC1035 Project 2 (Team Project)
Booking-Timetabling System that allows the user book for rooms based on their needs.

## Dependencies
Dependencies are defined in the [pom file](pom.xml). These dependencies are needed to
run the source code and the unit tests.

## Usage
The project is simple to start. [Main.java](src/main/java/Main.java) must be ran using 
the appropriate JDK version.

## Architecture
The architecture of the project tries to follow the Model-View-Controller architecture 
pattern. This is is very useful because it allows us and contributors to be able to 
isolate the classes from each other. This makes it easier to adhere to the Single 
Responsibility Principle and separation of concerns. 

### [Models](src/main/java/csc1035/project2/model)
These classes are the core of the solution to the problem that we are trying to solve
with the given client specification. They are a model of the involved parties in the 
client specification. They contain fields that are relevant to the real world representation
of objects.

### [Controller](src/main/java/csc1035/project2/controller)
Controllers are the ones that are responsible for communicating with the model and the
database tables. In a sense, it serves as an interface(not to be confused with java interface)
between the UI, model and Hibernate itself.

An example implementation of a controller can be found [here](src/main/java/csc1035/project2/controller/Controller.java))
In this example, a certain design pattern called Dependency injection and Inversion of control
is used to allow the Controller to have access to the database, making it the CRUD class
of this project.

### [IO](src/main/java/csc1035/project2/io)
UserInterface will serve as the command line application that the user will actually interact
with. In turn, the UI interacts with the CRUD object and allow us to create, remove, update
and delete records or objects at will.


### [Util](src/main/java/csc1035/project2/util)
These are the classes that contain the helper methods that can be used throughout the
project. The most import Helper Class is the HibernateUtil. This allows the program to
create SessionFactory objects and in turn create sessions and begin transactions. These
are crucial because it is what enables us to communicate with the database, schema, and
tables.