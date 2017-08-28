@Rummy @Mobile @Hybrid @JoinLeaveTable
Feature: KPR game play for Join and Leave table in loop

  @CashGames @Deals
  Scenario Outline: Validate join and leave table on table
    Given User is logged in app
    And User selects "CashGames" game module for "Deals" game type app icon
    And User selects <TableType> and <PlayerType> table
    When User selects take seat on table and validates seat is taken by player
    Then User selects leave table on table for "CashGames"

    Examples: 
      | TableType | PlayerType |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
      | Best of 2 | 2 Players  |
