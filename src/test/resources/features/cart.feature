Feature: Shopping Cart

  @regression
  Scenario: Remove an item from the cart
    Given the user is logged into SauceDemo as a standard user
    When the user adds "Sauce Labs Onesie" to the shopping cart
    And navigates to the shopping cart
    And the user removes "Sauce Labs Onesie" from the shopping cart
    Then the cart should be empty
    And the cart badge should not be visible

  @regression
  Scenario: Continue shopping from the cart
    Given the user is logged into SauceDemo as a standard user
    When the user adds "Sauce Labs Onesie" to the shopping cart
    And navigates to the shopping cart
    And the user continues shopping
    Then the user should be directed to the inventory page
