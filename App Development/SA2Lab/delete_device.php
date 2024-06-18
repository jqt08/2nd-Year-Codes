
<?php
session_start();

$index = $_GET['index'];
unset($_SESSION['devices'][$index]);
$_SESSION['devices'] = array_values($_SESSION['devices']); // Re-index array

$_SESSION['message'] = "Device deleted successfully.";
header('Location: view_devices.php');
exit;
?>
