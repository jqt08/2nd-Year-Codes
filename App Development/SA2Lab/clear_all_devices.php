
<?php
session_start();
unset($_SESSION['devices']);
$_SESSION['message'] = "All devices cleared.";
header('Location: view_devices.php');
exit;
?>