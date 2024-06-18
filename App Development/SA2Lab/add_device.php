<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $device = [
        'name' => $_POST['name'],
        'description' => $_POST['description'],
        'price' => $_POST['price'],
        'quantity' => $_POST['quantity']
    ];
    
    if (!isset($_SESSION['devices'])) {
        $_SESSION['devices'] = [];
    }

    $_SESSION['devices'][] = $device;
    $_SESSION['message'] = "Device added successfully.";
    header('Location: add_device.php');
    exit;
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Device</title>
    <style>
        form { margin: 20px; }
        label { display: block; margin-top: 10px; }
        input, textarea { width: 100%; padding: 10px; margin-top: 5px; }
    </style>
</head>
<body>
    <h1>Add Device</h1>
    <?php
    if (isset($_SESSION['message'])) {
        echo '<p>' . $_SESSION['message'] . '</p>';
        unset($_SESSION['message']);
    }
    ?>
    <form action="add_device.php" method="post">
        <label for="name">Name:</label>
        <input type="text" name="name" required>
        
        <label for="description">Description:</label>
        <textarea name="description" required></textarea>
        
        <label for="price">Price:</label>
        <input type="number" step="0.01" name="price" required>
        
        <label for="quantity">Quantity:</label>
        <input type="number" name="quantity" required>
        
        <input type="submit" value="Add Device">
    </form>
    <p><a href="index.php">Back to Home</a></p>
</body>
</html>