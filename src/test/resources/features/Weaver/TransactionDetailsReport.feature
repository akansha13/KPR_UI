@Weaver @Mobile @Hybrid @TransactionDetails
Feature: This is Transaction Details Report feature

  Background: 
    Given User is logged in app

  @India @ValidateTransactionDetails @KPR
  Scenario Outline: To check Transaction Details Report API response with UI.
    Given User navigates to Transaction Details Report page
    When player sets Transaction Type <txnType> of transactionDetails between <fromDate> to <toDate>
    And User selects calendar date
    And player login successfully
    And player data of <txnType> transactionDetails between <fromDate> to <toDate>
    Then User validate DGE Transaction Details Report API response data with UI

    Examples: 
      | txnType | fromDate   | toDate     |
      | ALL     | 10/05/2012 | 10/05/2017 |
