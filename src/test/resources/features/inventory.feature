Feature: Product Inventory

  @regression
  Scenario: Sort products by name descending
    Given the user is logged into SauceDemo as a standard user
    When the user sorts products by "Name (Z to A)"
    Then the first product should be "Test.allTheThings() T-Shirt (Red)"

  @regression
  Scenario: Sort products by price ascending
    Given the user is logged into SauceDemo as a standard user
    When the user sorts products by "Price (low to high)"
    Then the first product should be "Sauce Labs Onesie"

  @regression
  Scenario: Cart badge counts added products
    Given the user is logged into SauceDemo as a standard user
    When the user adds "Sauce Labs Backpack" to the shopping cart
    And the user adds "Sauce Labs Bike Light" to the shopping cart
    Then the cart badge should show 2 items
