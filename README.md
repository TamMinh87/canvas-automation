# canvas-automation
## Scope
As per the requirement
  Expectations : Develop automation script for online calculator functionalities using the Xendit automation framework.
  1. Subtraction
  2. Division
  3. Addition

I suppose we only touch to the following buttons: 0 -> 9, '.', '+/-' and 4 operations ':' '-' '+' '=' buttons

## Approach
This is a canvas, so there is no locator for element inside canvas at all.

I'd like to split this to 2 parts
1. Equation input (e.g. 1 + 1 =), in general we have 2 ways
  - First, using Key Event, but with this approach, we cannot press '+/-' button (or at least I not found the way to press it yet)
  - Second, calculator coordinator and click on each button, this approach needs to deal with browser scaling (I follow this approach)
2. How to verify result
  - First, prepare baseline images, then compare actual screenshot with baseline (I follow this approach)
  - Second, I'm thinking about using OCR to read text from image (but not have to do MVP for this)
  
## Library/pattern
 - page factory
 - read data from config.properties file
 - image-comparison library
 - allure report
 - testNG data provider

## Project structure
This is a simple maven selenium java project, follow the old famous page object pattern, and combine allure report. So I won't put more explanation here, we can discuss later if needed.

## How to run it
1. Clone the project from branch 'allure-report'
  - I keep in this branch to make it's less visible to other candidate, since my project is public
  - I will change project to private after 3 days
2. Open as maven project
3. Change browser in config.properties file (default is chrome)
4. Run test: `mvn clean test`
5. Generate report: `allure serve allure-results`
  - I attach 2 screenshots to last 2 steps: one is actual image, another is difference image (highlight difference pixels)

## Test case
Well, all test cases are documented in code, please open CalculatorTest.java and take a look on DataProvider test data and baseline image under `resource` folder.
