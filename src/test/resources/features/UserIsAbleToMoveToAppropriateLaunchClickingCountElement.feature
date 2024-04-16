@ui
Feature: User is able to move to appropriate launch via count element

  Background:
    Given I am logged in and on the launches page

  Scenario Outline: Verify user is able to move to appropriate launch via count element
    Given I have a list of launch
    When I choose count element
    Then I am able to move to appropriate launch via <countElement> element of <category> with <param>

    Examples:
      |countElement        | category  |  param                                          |
      |"total-col"         |"TOTAL"    | "PASSED%252CFAILED%252CSKIPPED%252CINTERRUPTED" |
      |"passed-col"        |"PASSED"   | "PASSED"                                        |
      |"failed-col"        |"FAILED"   | "FAILED%252CINTERRUPTED"                        |
      |"skipped-col"       |"SKIPPED"  | "SKIPPED"                                       |