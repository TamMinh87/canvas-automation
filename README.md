# canvas-automation
## Scope
As per the requirement
  Expectations : Develop automation script for online calculator functionalities using the Xendit automation framework.
  1. Subtraction
  2. Division
  3. Addition

I suppose we only touch to the following buttons
  - Number buttons: `0` `1` `2` `3` `4` `5` `6` `7` `8` `9`
  - Operation buttons: `-` `+` `:`
  - Support buttons: `.` `+/-` and `=`
  - Other buttons are out of scope


## Approach
This is a canvas, so there is no locator for element inside canvas at all.

I'd like to split this to 2 parts
1. Equation input (e.g. 1 + 1 =), in general we have 2 ways
  - First, using Key Event, but with this approach, we cannot press '+/-' button (or at least I not found the way to press it yet)
  - Second, calculator coordinator and click on each button, this approach needs to deal with browser scaling (I follow this approach)
2. How to verify result
  - First, prepare baseline images, then compare actual screenshot with baseline (I follow this approach)
  - Second, I'm thinking about using OCR to read text from image. But due to limited time of the exercise, so I didn't choose this approach
  
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
  - I will change project to private after your team finishes evaluation process
2. Open as maven project
3. Change browser in config.properties file (default is chrome)
4. Run test: `mvn clean test`
5. Generate report: `allure serve allure-results`
  - On the report, there are 2 screenshots on the last 2 steps: one is actual image, another is difference image (highlight difference pixels)

## Test case
Well, all test cases are documented in code, please open CalculatorTest.java and take a look on DataProvider test data and baseline image under `resource` folder.

## Limitation
1. Not work on IE: this page has different DOM on IE, but since IE is not supported anymore, so I won't spend time to fix it
2. Not try on Safari browser yet: I don't have macOS, so don't have Safari to test
3. Not have UI test for each action/click (e.g check if Result area display value once we press any button), it makes sense to do this check, but lower priority, so we can do later if have time
4. Only provide baseline image for 1 screen resolution, but in the code I already handle browser scaling. If we really need to cover different browser resolution, then just need a minor fix to re-structure baseline-image's directory.
5. Few flaky tests due to pixel comparison: we compare pixel by pixel, so sometime Calculator on web renders not exactly the same
  - We can resolve this by updating `percentage_of_difference` (default is 5) that we can accept in `config.properties` file
