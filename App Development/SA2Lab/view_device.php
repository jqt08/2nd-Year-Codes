<?php
session_start();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Devices</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid black; padding: 10px; text-align: left; }
    </style>
</head>
<body>
    <h1>View Devices</h1>
    <?php if (isset($_SESSION['devices']) && count($_SESSION['devices']) > 0): ?>
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
                    <a href="view_device.php?index=<?php echo $index; ?>">View</a>
                    <a href="edit_device.php?index=<?php echo $index; ?>">Edit</a>
                    <a href="delete_device.php?index=<?php echo $index; ?>">Delete</a>
                    <form action="buy_device.php" method="get" style="display:inline;">
                        <input type="hidden" name="index" value="<?php echo $index; ?>">
                        <button type="submit">Purchase</button>
                    </form>
                </td>
            </tr>
            <?php endforeach; ?>
        </table>
    <?php else: ?>
        <p>No devices available.</p>
    <?php endif; ?>
    <p><a href="index.php">Back to Home</a></p>
</body>
</html>
