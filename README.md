# InteractiveQA
InteractiveQA is a Java project designed to provide an interactive question and answer system where users can ask questions and save pairs of questions and answers to JSON files.

## How to run this application:

### Install Maven
This project uses the Maven framework.
Before running this project, make sure that Maven is installed on your computer. you can check that Maven is installed correctly by running the following command from the command line:
 - ```mvn -v```

### Run application
In the root directory of this project, use following commands:
  - ```mvn compile``` compiles the project
  - ```mvn test``` runs the tests
  - ```mvn clean install``` downloads project related dependencies.

## About this application
In this program, users have the option to ask questions or add their own questions and answers. Users interoperate with the app through Terminal, entering the number 1 to ask a question. Enter the number 2 to add a question and answer. Enter the number 0 to exit the program.

### Add question format
```<question>? <answer1>" "<answer2>" "<answerX>"```
Where <question> is the string of the question and <answer1>, <answer2>, <answerX> are the answers to the question, each answer enclosed in double quotes and separated by spaces.

