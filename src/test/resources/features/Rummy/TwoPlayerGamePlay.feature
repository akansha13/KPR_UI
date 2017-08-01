@Rummy @Mobile @Hybrid @2PlayerGamePlay
Feature: KPR game play between 2 players

  @InstantPlay @Deals 
  Scenario: Validate game play table on table
    Given User is logged in app
    And User selects "InstantPlay" game module app icon
    When User selects take seat on table
    And User selects cards data from table
    Then User validates "First Hand History" data
