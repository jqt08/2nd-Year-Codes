<?php
session_start();

$balance = isset($_SESSION['balance']) ? $_SESSION['balance'] : 0;
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Balance</title>
</head>
<body>
    <h1>Your Current Balance</h1>
    <p>Your current balance is: $<?php echo $balance; ?></p>
    <a href="index.php">Go Back</a>
</body>
</html>
