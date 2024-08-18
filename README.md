 Automation UI

## The Automation Testing 

Test Framework **JAVA** based and powered by **TestNG** and **Selenium**.

It's compatible to test:
- Web Desktop Chrome

## [Local Run] Installation

### Install - Depedencies

- Install HomeBrew
  ```
  /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
  ```
- Install [Java](https://www.oracle.com/id/java/technologies/javase/javase8-archive-downloads.html) (JDK)
  ```
  brew install openjdk@11
  ```
- Install [Maven](https://maven.apache.org/)
  ```
  brew install maven
  ```

## [Local Run] How To Run It?

These are some steps to run test on local environment:
- `cd` to the base directory of this project
- run `mvn clean install`
- run 'mvn test' the command for running test case
