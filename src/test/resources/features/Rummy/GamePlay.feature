@Rummy @Mobile @Hybrid @GamePlay
Feature: KPR game play

  @InstantPlay @Deals
  Scenario: Validate cards data from table
    Given User is logged in app
    And User selects "InstantPlay" game module app icon
    When User selects take seat on table
    And User selects cards data from table
    Then User selects leave table on table

  @InstantPlay @Deals @Sort
  Scenario: Validate sort cards on table
    Given User is logged in app
    And User selects "InstantPlay" game module app icon
    When User selects take seat on table
    And User selects sort cards on table
    Then User selects leave table on table

  @InstantPlay @Deals @LeaveTable
  Scenario: Validate leave table on table
    Given User is logged in app
    And User selects "InstantPlay" game module app icon
    When User selects take seat on table
    And User selects sort cards on table
    Then User selects leave table on table

  @InstantPlay @Deals @WrongShow
  Scenario: Validate leave table on table
    Given User is logged in app
    And User selects "InstantPlay" game module app icon
    When User selects take seat on table
    And User selects sort cards on table
    And User select card from closed deck
   # Then User validates wrong show
