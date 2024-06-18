<?php
session_start();

// Clear the purchase history
if (isset($_SESSION['purchases'])) {
    unset($_SESSION['purchases']);
}

// Redirect back to the purchase history page
$_SESSION['message'] = "Purchase history cleared successfully.";
header('Location: purchase_history.php');
exit;
?>
