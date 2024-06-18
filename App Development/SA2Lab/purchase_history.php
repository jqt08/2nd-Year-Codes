<?php
session_start();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Purchase History</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid black; padding: 10px; text-align: left; }
    </style>
</head>
<body>
    <h1>Purchase History</h1>
    <?php if (isset($_SESSION['purchases']) && count($_SESSION['purchases']) > 0): ?>
        <table>
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Total Price</th>
                <th>Purchase Date</th>
            </tr>
            <?php 
            $totalCost = 0;
            $totalItems = 0;
            foreach ($_SESSION['purchases'] as $purchase): 
                $totalCost += $purchase['total_price'];
                $totalItems += $purchase['quantity'];
            ?>
            <tr>
                <td><?php echo htmlspecialchars($purchase['name']); ?></td>
                <td><?php echo htmlspecialchars($purchase['quantity']); ?></td>
                <td><?php echo htmlspecialchars($purchase['total_price']); ?></td>
                <td><?php echo htmlspecialchars($purchase['purchase_date']); ?></td>
            </tr>
            <?php endforeach; ?>
        </table>
        <h2>Total Costs and Items Sold</h2>
        <p>Total Cost: <?php echo htmlspecialchars($totalCost); ?></p>
        <p>Total Items Sold: <?php echo htmlspecialchars($totalItems); ?></p>
        <p><a href="clear_purchase_history.php">Clear Purchase History</a></p>
    <?php else: ?>
        <p>No purchases made.</p>
    <?php endif; ?>
    <p><a href="index.php">Back to Home</a></p>
</body>
</html>
