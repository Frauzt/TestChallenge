Feature: Grid Filter Row
  In order to retrieve data from Grid Filter row
  As a user
  I want to extract the data via searching for an ID and display the result

  Background: WebDriver setup

  Scenario: User inputs an ID to retrieve a row of data from the Grid Filter Table

    Given that the user is on the landing page
    When the user input "867" as the ID
    Then the row of data that corresponds with that ID will be displayed