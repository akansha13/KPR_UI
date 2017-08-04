@Rummy @Mobile @Hybrid @2PlayerGamePlay
Feature: KPR game play between 2 players

  @InstantPlay @Deals
  Scenario Outline: Validate game play table on table
    Given User is logged in app
    And User selects "InstantPlay" game module app icon
    When User selects take seat on table
    And User selects sort cards on table
    And "Player1" groups his cards as <PureSequence1> and <PureSequence2> and <Set1> and <Set2>

    Examples: 
      | PureSequence1 | PureSequence2 | Set1  | Set2 |
      | J,Q,K         | A,2,3,4       | 5,5,5 |      |
