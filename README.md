# Automation UI testing framework for currency conversion calculator

## Dependencies
- Java
- Maven
- Selenium WebDriver
- TestNG
- WebDriverManager
- Allure


## How to run
1. We should run next command in terminal:
```mvn clean test -DsiteUrl="https://www.paysera.lt"```
2. Next command for report view:
```allure serve allure-results```
*But you need installed allure for viewing.

## Application properties
- **browser.name** - browser name which will be used for test run (value "chrome" by default).
- **browser.is.headless.run** - flag which can be changed for headless run or not (value "true" by default).

These properties located in `{project_folder}/src/main/resources/application.properties`