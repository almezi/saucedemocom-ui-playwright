Feature: User Authentication

  @smoke @sanity @regression
  Scenario: Successful login with standard credentials
    Given the user is on the SauceDemo login page
    When the user logs in with valid standard credentials
    Then the user should be directed to the inventory page
    And the products catalog header should be visible

  @regression
  Scenario: Login failure with locked out user
    Given the user is on the SauceDemo login page
    When the user logs in with username "locked_out_user" and password "secret_sauce"
    Then an error message stating "Sorry, this user has been locked out." should be displayed

  @regression
  Scenario: Login failure without a username
    Given the user is on the SauceDemo login page
    When the user logs in with username "" and password "secret_sauce"
    Then an error message stating "Epic sadface: Username is required" should be displayed

  @regression
  Scenario: Login failure without a password
    Given the user is on the SauceDemo login page
    When the user logs in with username "standard_user" and password ""
    Then an error message stating "Epic sadface: Password is required" should be displayed

  @regression
  Scenario: Login failure with an invalid password
    Given the user is on the SauceDemo login page
    When the user logs in with username "standard_user" and password "wrong_password"
    Then an error message stating "Epic sadface: Username and password do not match any user in this service" should be displayed

  @regression
  Scenario: Problem user can log in
    Given the user is on the SauceDemo login page
    When the user logs in with username "problem_user" and password "secret_sauce"
    Then the user should be directed to the inventory page
    And the products catalog header should be visible

  @regression
  Scenario: Performance glitch user can log in
    Given the user is on the SauceDemo login page
    When the user logs in with username "performance_glitch_user" and password "secret_sauce"
    Then the user should be directed to the inventory page
    And the products catalog header should be visible
