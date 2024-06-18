
<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $index = $_POST['index'];
    $_SESSION['devices'][$index] = [
        'name' => $_POST['name'],
        'description' => $_POST['description'],
        'price' => $_POST['price'],
        'quantity' => $_POST['quantity']
    ];

    $_SESSION['message'] = "Device updated successfully.";
    header('Location: view_devices.php');
    exit;
}
?>