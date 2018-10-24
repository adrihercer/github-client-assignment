# github-client-assignment

This project is a command line app that can gathers the public GitHub repositories from a user and displays the contributors for each one sorted by commits in descending order.

Prerequisites
----------------

The prerequisites needed to work with this app are:
* For compiling the code you need to have at least Maven 3.3+ and Java 8.
* For executing the app you need at least Java 8.

Building the app
----------------

This project was created using Maven for package management and building tasks. In order to build the project follow the next steps:
1. Clone this repository.
2. Get into the github-client-assigment directory.
3. Execute the command:
```bash
mvn clean install
``` 

The command is going to download all the dependencies needed by the project, compile the Java code, execute the unit testing classes and package the code and dependencies into a single jar to make it easy to use and share the app.

As part of the configuration, this project uses the JaCoCo plugin to generate a HTML report that allows to visualize the unit testing coverage. You can find it into the **jacoco-ut** directory inside the **target** directory that is generated after the code compilation.

Executing the app
-----------------

In order to execute the app, we need to provide the following parameters:
* OAuth token to access the GitHub API. This token has to be stored as an environment variable with the name **GITHUB_OAUTH_TOKEN environment variable**.
* Username, as a command line argument.

In the command line use the following steps:
1. Go to the **target** directory.
2. Execute the command:
```bash
java -jar adrihercer-githubclient.jar <username>
``` 
**Note:** You can copy the adrihercer-githubclient.jar file to any place you like. You just need to access such directory in the command line and type the command above.

Output
------

The output expected in the app is an error message if something wrong happened during the execution; for example:

```bash
No username parameter has been provided!
``` 

In the case everything goes well, the output is like this:

```bash
--------------------------------------
github-client-assignment
--------------------------------------
3       adrihercer
``` 

where the value between "--------------------------------------" is the repository's name and the list below is the constributors list. For each contributor the app displays the amount of commits and the name of the contributor, sorted by descending order.

