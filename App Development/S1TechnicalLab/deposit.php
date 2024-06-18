<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $amount = isset($_POST['amount']) ? intval($_POST['amount']) : 0;

    if ($amount > 0) {

        if (!isset($_SESSION['balance'])) {
            $_SESSION['balance'] = 0;
        }

        $_SESSION['balance'] += $amount;
        header("Location: balance.php");
        exit();
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Deposit Money</title>
</head>
<body>
    <h1>Deposit Money</h1>
    <form method="post">
        <label for="amount">Enter Amount to Deposit:</label>
        <input type="number" id="amount" name="amount" required>
        <button type="submit">Deposit</button>
    </form>
    <a href="index.php">Go Back</a>
</body>
</html>
