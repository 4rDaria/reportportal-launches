@ui
Feature: Each launch contains all test count data

  Background:
    Given I am logged in and on the launches page

  Scenario Outline: Verify each launch contains all test count data for a specific category
    Given I have a list of launch
    When I choose any launch
    Then Each launch should contain test count data for <category> with <categoryCss>

    Examples:
       | category       |   categoryCss        |
       |"TOTAL"         | "total-col"          |
       |"PASSED"        | "passed-col"         |
       |"FAILED"        | "failed-col"         |
       |"SKIPPED"       | "skipped-col"        |
       |"PRODUCT_BUG"   | "product-bug-col"    |
       |"AUTO_BUG       | "auto-bug-col"       |
       |"SYSTEM_ISSUE"  | "system-issue-col"   |
       |"TO_INVESTIGATE"| "to-investigate-col" |

