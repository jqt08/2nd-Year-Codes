<?php
session_start();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Device Details</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid black; padding: 10px; text-align: left; }
    </style>
</head>
<body>
    <h1>View Device Details</h1>
    <?php if (isset($_SESSION['devices']) && count($_SESSION['devices']) > 0): ?>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            <?php foreach ($_SESSION['devices'] as $index => $device): ?>
            <tr>
                <td><?php echo htmlspecialchars($device['name']); ?></td>
                <td><?php echo htmlspecialchars($device['description']); ?></td>
                <td><?php echo htmlspecialchars($device['price']); ?></td>
                <td><?php echo htmlspecialchars($device['quantity']); ?></td>
            </tr>
            <?php endforeach; ?>
        </table>
    <?php else: ?>
        <p>No devices available.</p>
    <?php endif; ?>
    <p><a href="index.php">Back to Home</a></p>
</body>
</html>
