<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HJT Electronic Devices Store</title>
    <style>
        body { font-family: Arial, sans-serif; }
        ul { list-style-type: none; }
        li { margin: 10px 0; }
        a { text-decoration: none; color: blue; }
    </style>
</head>
<body>
    <h1>Welcome to HJT Electronic Devices Store</h1>
    <ul>
        <li><a href="add_device.php">Add Device</a></li>
        <li><a href="view_devices.php">View Devices</a></li>
        <li><a href="buy_device.php">Purchase Device</a></li>
        <li><a href="purchase_history.php">View Purchase History</a></li>
    </ul>

    <?php
    session_start();
    if (isset($_SESSION['devices']) && count($_SESSION['devices']) > 0): ?>
        <h2>Available Devices</h2>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
            <?php foreach ($_SESSION['devices'] as $index => $device): ?>
            <tr>
                <td><?php echo htmlspecialchars($device['name']); ?></td>
                <td><?php echo htmlspecialchars($device['description']); ?></td>
                <td><?php echo htmlspecialchars($device['price']); ?></td>
                <td><?php echo htmlspecialchars($device['quantity']); ?></td>
                <td>
                    <a href="view_device_details.php?index=<?php echo $index; ?>">View Details</a>
                </td>
            </tr>
            <?php endforeach; ?>
        </table>
    <?php else: ?>
        <p>No devices available.</p>
    <?php endif; ?>
</body>
</html>
