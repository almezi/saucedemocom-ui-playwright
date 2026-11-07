Feature: End-to-End Checkout

  @e2e @regression
  Scenario: Successfully ordering a Sauce Labs Onesie
    Given the user is logged into SauceDemo as a standard user
    When the user adds "Sauce Labs Onesie" to the shopping cart
    And navigates to the shopping cart
    Then the cart should contain 1 item of "Sauce Labs Onesie"
    When the user proceeds to checkout
    And fills in customer information with first name "John", last name "Doe", and postal code "12345"
    And continues to the checkout overview
    Then the order summary should show "Sauce Labs Onesie" with quantity 1
    When the user finishes the checkout
    Then the order confirmation message "Thank you for your order!" should be displayed
    When the user returns to the products page
    Then the user should be directed to the inventory page

  @regression
  Scenario: Checkout requires a first name
    Given the user is logged into SauceDemo as a standard user
    When the user adds "Sauce Labs Onesie" to the shopping cart
    And navigates to the shopping cart
    And the user proceeds to checkout
    And continues to the checkout overview
    Then a checkout error message stating "Error: First Name is required" should be displayed

  @regression
  Scenario: Checkout requires a postal code
    Given the user is logged into SauceDemo as a standard user
    When the user adds "Sauce Labs Onesie" to the shopping cart
    And navigates to the shopping cart
    And the user proceeds to checkout
    And fills in customer information with first name "John", last name "Doe", and postal code ""
    And continues to the checkout overview
    Then a checkout error message stating "Error: Postal Code is required" should be displayed

  @regression
  Scenario: Cancel checkout from customer information
    Given the user is logged into SauceDemo as a standard user
    When the user adds "Sauce Labs Onesie" to the shopping cart
    And navigates to the shopping cart
    And the user proceeds to checkout
    And the user cancels checkout
    Then the user should be on the cart page

  @regression
  Scenario: Cancel checkout from the overview
    Given the user is logged into SauceDemo as a standard user
    When the user adds "Sauce Labs Onesie" to the shopping cart
    And navigates to the shopping cart
    And the user proceeds to checkout
    And fills in customer information with first name "John", last name "Doe", and postal code "12345"
    And continues to the checkout overview
    And the user cancels the checkout overview
    Then the user should be directed to the inventory page

  @regression
  Scenario: Order subtotal plus tax equals the total
    Given the user is logged into SauceDemo as a standard user
    When the user adds "Sauce Labs Onesie" to the shopping cart
    And navigates to the shopping cart
    And the user proceeds to checkout
    And fills in customer information with first name "John", last name "Doe", and postal code "12345"
    And continues to the checkout overview
    Then the order subtotal plus tax should equal the total
