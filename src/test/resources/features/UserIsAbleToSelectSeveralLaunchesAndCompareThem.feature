@ui
Feature: User is able to select several launches and compare them

  Background:
    Given I am logged in and on the launches page

  Scenario Outline: Verify that user is able to select several launches and compare them
    Given I have a list of launch
    When I choose launches "launches"
    Then I can compare launches
    Examples:
    |launches    |
    | 1, 2       |