<?php
// Input data
$age = 7;            // Age of the attendee
$is_student = true;   // Whether the attendee is a student

echo"The age of the attendee is:  $age<br>";
// Determine the base price based on age
if ($age >= 18) {
    $base_price = 100; // Adult pricing
} elseif ($age >= 13 && $age <=17) {
    $base_price = 50; // Teenager pricing
} else {
    $base_price = 0; // Children for free
}

// Apply student discount if applicable
if ($is_student) {
    $discount = $base_price * 0.20;  // Calculate 20% discount
    $final_price = $base_price - $discount;
} else {
    $final_price = $base_price;
}

// Output the final price
echo "The registration fee for the attendee is: $" . number_format($final_price, 2);
?>