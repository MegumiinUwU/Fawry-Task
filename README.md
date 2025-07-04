# Output sample

```
Creating sample products...

Available Products:
  Cheese: Cheddar Cheese - $8.99 (Qty: 50, Weight: 0.50kg, Exp: 2025-07-14)
  Cheese: Expired Cheese - $6.99 (Qty: 20, Weight: 0.30kg, Exp: 2025-07-02) (EXPIRED)
  Biscuits: Chocolate Biscuits - $4.50 (Qty: 100, Weight: 0.20kg, Exp: 2025-08-03)
  TV: Samsung 55" 4K TV - $899.99 (Qty: 5, Weight: 15.50kg)
  Mobile: iPhone 14 - $999.99 (Qty: 10, Weight: 0.17kg)
  Mobile Scratch Card: Vodafone Recharge - $10.00 (Qty: 1000, Provider: Vodafone, Value: $10.00)
  Mobile Scratch Card: Orange Recharge - $15.00 (Qty: 500, Provider: Orange, Value: $15.00)

Creating sample customers...

Customers:
  Customer: Alice Johnson (alice@email.com) - Balance: $1500.00
  Customer: Bob Smith (bob@email.com) - Balance: $500.00
  Customer: Charlie Brown (charlie@email.com) - Balance: $50.00

=== SCENARIO 1: Successful Checkout ===
Cart before checkout:
Cart Contents:
  Cheddar Cheese x 2 = $17.98
  Chocolate Biscuits x 3 = $13.50
  Vodafone Recharge x 1 = $10.00
Subtotal: $41.48

=== SHIPPING SERVICE ===
Processing shipment for 5 items:
  - Cheddar Cheese (Weight: 0.50kg)
  - Cheddar Cheese (Weight: 0.50kg)
  - Chocolate Biscuits (Weight: 0.20kg)
  - Chocolate Biscuits (Weight: 0.20kg)
  - Chocolate Biscuits (Weight: 0.20kg)
Total shipping weight: 1.60kg
Shipping fee: $9.00
=== END SHIPPING ===

=== CHECKOUT DETAILS ===
Customer: Alice Johnson

Items purchased:
  Cheddar Cheese x 2 = $17.98
  Chocolate Biscuits x 3 = $13.50
  Vodafone Recharge x 1 = $10.00

Order subtotal: $41.48
Shipping fees: $9.00
Paid amount: $50.48
Customer current balance after payment: $1449.52
=== END CHECKOUT ===

? Checkout completed successfully
Customer after checkout: Customer: Alice Johnson (alice@email.com) - Balance: $1449.52

=== SCENARIO 2: Expired Product Error ===
Attempting to checkout with expired product:
Cart Contents:
  Expired Cheese x 1 = $6.99
Subtotal: $6.99

? Product 'Expired Cheese' is expired

=== SCENARIO 3: Insufficient Balance Error ===
Customer: Customer: Charlie Brown (charlie@email.com) - Balance: $50.00
Attempting to checkout:
Cart Contents:
  Samsung 55" 4K TV x 1 = $899.99
Subtotal: $899.99

? Insufficient balance. Required: $899.99, Available: $50.00

=== SCENARIO 4: Out of Stock Error ===
? Requested quantity (10) exceeds available stock (5) for Samsung 55" 4K TV
? Requested quantity (1) exceeds available stock (0) for iPhone 14

=== SCENARIO 5: Empty Cart Error ===
Attempting to checkout with empty cart:
? Cart is empty

=== SCENARIO 6: Mixed Cart (Shippable + Non-Shippable) ===
Cart with mixed items:
Cart Contents:
  iPhone 14 x 1 = $999.99
  Vodafone Recharge x 2 = $20.00
  Orange Recharge x 1 = $15.00
Subtotal: $1034.99

=== SHIPPING SERVICE ===
Processing shipment for 1 items:
  - iPhone 14 (Weight: 0.17kg)
Total shipping weight: 0.17kg
Shipping fee: $5.44
=== END SHIPPING ===

=== CHECKOUT DETAILS ===
Customer: Alice Johnson

Items purchased:
  iPhone 14 x 1 = $999.99
  Vodafone Recharge x 2 = $20.00
  Orange Recharge x 1 = $15.00

Order subtotal: $1034.99
Shipping fees: $0.00
Paid amount: $1034.99
Customer current balance after payment: $414.53
=== END CHECKOUT ===

? Checkout completed successfully
```