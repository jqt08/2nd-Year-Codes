<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $amount = isset($_POST['amount']) ? intval($_POST['amount']) : 0;

    if ($amount > 0) {
        if (!isset($_SESSION['balance'])) {
            $_SESSION['balance'] = 0;
        }
        if ($_SESSION['balance'] >= $amount) {
            
            $_SESSION['balance'] -= $amount;
            header("Location: balance.php");
            exit();
        } else {
            $error = "Error: Insufficient balance.";
        }
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Withdraw Money</title>
</head>
<body>
    <h1>Withdraw Money</h1>
    <?php if (isset($error)) { ?>
        <p><?php echo $error; ?></p>
    <?php } ?>
    <form method="post">
        <label for="amount">Enter Amount to Withdraw:</label>
        <input type="number" id="amount" name="amount" required>
        <button type="submit">Withdraw</button>
    </form>
    <a href="index.php">Go Back</a>
</body>
</html>
