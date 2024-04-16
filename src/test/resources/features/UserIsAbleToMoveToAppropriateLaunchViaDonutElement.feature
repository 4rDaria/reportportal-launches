@ui
Feature: User is able to move to appropriate launch via donut element

  Background:
    Given I am logged in and on the launches page

  Scenario Outline: Verify user is able to move to appropriate launch via donut element
    Given I have a list of launch
    When I choose donut element
    Then I am able to move to appropriate launch via <donutElement> element of <category>

    Examples:
    |donutElement| category       |
    |"pb"        |"PRODUCT_BUG"   |
    |"ab"        |"AUTO_BUG"      |
    |"si"        |"SYSTEM_ISSUE"  |
    |"ti"        |"TO_INVESTIGATE"|
