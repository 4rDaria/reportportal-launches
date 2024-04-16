@ui
Feature: User is able to select several launches and compare them

  Background:
    Given I am logged in and on the launches page

  Scenario Outline: Verify that user is able to select several launches and compare them
    Given I have a list of launch
    When I choose launches <launchNumbers>
    Then I can compare launches

    Examples:
      |launchNumbers|
      | "1,2,3"     |
      | "1,3"       |
      | "1,2,4"     |
      | "1,2,3,4"   |