@Rummy @Mobile @Hybrid @2PlayerGamePlay
Feature: KPR game play between 2 players

  @InstantPlay @Deals
  Scenario Outline: Validate game play table on table
    Given User is logged in app
    And User selects "InstantPlay" game module app icon
    When User selects take seat on table
    And User selects sort cards on table
    And "Player1" groups his cards as <PureSequence1>
    And "Player1" groups his cards as <PureSequence2>
    And "Player1" groups his cards as <Set1>
    And "Player1" picks card from closed deck and discard <Discard1>
    And "Player2" picks card from closed deck and discard <Discard2>
    And "Player1" picks card from closed deck
    And "Player1" groups his cards as <Sequence1>
    And "Player1" places show <Show1>

    Examples: 
      | PureSequence1 | PureSequence2   | Set1        | Sequence1    | Discard1 | Discard2 | Show1 |
      | 1 J,1 Q,1 K   | 3 A,3 2,3 3,3 4 | 3 5,0 5,2 5 | 0 9,0 10,1 7 | 1 5      | 3 5      | 0 3   |
