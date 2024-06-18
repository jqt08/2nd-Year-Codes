
<?php
session_start();
$index = $_GET['index'];
$device = $_SESSION['devices'][$index];
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Device</title>
    <style>
        form { margin: 20px; }
        label { display: block; margin-top: 10px; }
        input, textarea { width: 100%; padding: 10px; margin-top: 5px; }
    </style>
</head>
<body>
    <h1>Edit Device</h1>
    <form action="update_device.php" method="post">
        <input type="hidden" name="index" value="<?php echo $index; ?>">
        
        <label for="name">Name:</label>
        <input type="text" name="name" value="<?php echo htmlspecialchars($device['name']); ?>" required>
        
        <label for="description">Description:</label>
        <textarea name="description" required><?php echo htmlspecialchars($device['description']); ?></textarea>
        
        <label for="price">Price:</label>
        <input type="number" step="0.01" name="price" value="<?php echo htmlspecialchars($device['price']); ?>" required>
        
        <label for="quantity">Quantity:</label>
        <input type="number" name="quantity" value="<?php echo htmlspecialchars($device['quantity']); ?>" required>
        
        <input type="submit" value="Update Device">
    </form>
    <p><a href="view_devices.php">Back to Devices</a></p>
</body>
</html>