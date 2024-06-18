<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $index = $_POST['index'];
    $quantity = $_POST['quantity'];

    if ($quantity <= $_SESSION['devices'][$index]['quantity']) {
        $total_price = $quantity * $_SESSION['devices'][$index]['price'];
        $_SESSION['devices'][$index]['quantity'] -= $quantity;

        if (!isset($_SESSION['purchases'])) {
            $_SESSION['purchases'] = [];
        }

        $_SESSION['purchases'][] = [
            'name' => $_SESSION['devices'][$index]['name'],
            'quantity' => $quantity,
            'total_price' => $total_price,
            'purchase_date' => date('Y-m-d H:i:s')
        ];

        $_SESSION['message'] = "Device purchased successfully.";
        header('Location: purchase_history.php');
        exit;
    } else {
        $_SESSION['message'] = "Not enough quantity in stock.";
        header('Location: view_devices.php');
        exit;
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Buy Device</title>
    <style>
        form { margin: 20px; }
        label { display: block; margin-top: 10px; }
        input, select { width: 100%; padding: 10px; margin-top: 5px; }
    </style>
</head>
<body>
    <h1>Buy Device</h1>
    <form action="buy_device.php" method="post">
        <label for="index">Select Device:</label>
        <select name="index" required>
            <?php if (isset($_SESSION['devices']) && count($_SESSION['devices']) > 0): ?>
                <?php foreach ($_SESSION['devices'] as $index => $device): ?>
                    <option value="<?php echo $index; ?>">
                        <?php echo htmlspecialchars($device['name']); ?> - $<?php echo htmlspecialchars($device['price']); ?>
                    </option>
                <?php endforeach; ?>
            <?php else: ?>
                <option value="">No devices available</option>
            <?php endif; ?>
        </select>
        
        <label for="quantity">Quantity:</label>
        <input type="number" name="quantity" required>
        
        <input type="submit" value="Buy Device">
    </form>
    <p><a href="view_devices.php">Back to Devices</a></p>
</body>
</html>
