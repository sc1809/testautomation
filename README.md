#### Test Cases to automate
Test scenarios to automate:
1- Two topping pizza (small/med/large) with qty as 1
2. One topping pizza with Cash as payment with qty as x number
3. No topping pizza with Credit card as payment
4. Pizza order submission with qty as 0
5. One test case to reset the values

#### Defects
1. No field validation. I'm able to submit order without entering the qty, pizza type, payment type 
2. Topping selection should be based on Pizza type (for no topping pizza, dropdowns should be disabled and likewise)
3. Qty, phonenum should take only number
4. If the dialog is open, user shouldn't be able to select anything on the page until the user closes the dialog
5. Placement of the dialog is at the bottom of the screen
6. Clicking on reset doesnt reset the values of topping
7. Pizza type and qty should be mandatory fields and place order should be disabled 
  

