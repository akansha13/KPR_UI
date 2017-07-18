@Weaver @Mobile @Hybrid @ForgotPassword
Feature: Forgot Password Feature

  @India @ValidateForgotPassword @KPR
  Scenario: Testing Forgot Password with valid emailID
    Given User navigates to Forgot Password
    When User enters valid email ID in App
    Then User validates Login Screen is visible in App

  @India @ValidateInvalidForgotPassword @KPR
  Scenario Outline: Testing Forgot Password with blank/invalid emailID
    Given User navigates to Forgot Password
    When User enters blank/invalid email ID in App <email>
    Then User validates error messages <errormsg> visible in App

    Examples: 
      | email                   | errormsg                           |
      |                         | * Please enter valid Email Address |
      | nice121212              | * Please enter valid Email Address |
      | nice121212@skilrock.com | * Email address is not found       |
