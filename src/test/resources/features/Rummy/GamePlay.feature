@Rummy @Mobile @Hybrid @GamePlay
Feature: KPR game play

  @InstantPlay @Deals
  Scenario: Validate game play
    Given User is logged in app
    And User selects "InstantPlay" game module app icon
    And User selects take seat on table
    When User selects cards data from table

  # And User selects cards image data from table
  @InstantPlay @Deals @Sort
  Scenario: Validate game play
    Given User is logged in app
    And User selects "InstantPlay" game module app icon
    And User selects take seat on table
    When User selects sort cards on table
