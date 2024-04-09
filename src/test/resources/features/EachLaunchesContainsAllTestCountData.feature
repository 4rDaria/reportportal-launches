@ui
Feature: Verify each launch contains all test count data

  Background:
    Given I am logged in and on the launches page

  Scenario: Verify each launch contains all test count data for a specific category
    Given I have a list of launch
    When I choose any launch
    Then Each launch should contain test count data for category
