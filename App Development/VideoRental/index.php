<?php
session_start();

if (!isset($_SESSION['loggedin']) || $_SESSION['loggedin'] !== true) {
    header('Location: login.php');
    exit;
}

if (!isset($_SESSION['alert'])) {
    $_SESSION['alert'] = null;
}

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

require_once 'functions.php';

$page = isset($_GET['page']) ? $_GET['page'] : 'view';
$pages = ['view', 'add', 'edit', 'delete'];
if (!in_array($page, $pages)) {
    $page = 'view';
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Video Rental</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/admin-lte@3.2.0/dist/css/adminlte.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="logout.php" role="button">
                    <i class="fas fa-sign-out-alt"></i> Logout
                </a>
            </li>
        </ul>
    </nav>

    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <a href="index.php" class="brand-link">
            <span class="brand-text font-weight-light">Video Rental</span>
        </a>
        <div class="sidebar">
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                    <li class="nav-item">
                        <a href="index.php?page=view" class="nav-link">
                            <i class="nav-icon fas fa-list"></i>
                            <p>View Videos</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="index.php?page=add" class="nav-link">
                            <i class="nav-icon fas fa-plus"></i>
                            <p>Add Video</p>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </aside>

    <div class="content-wrapper">
        <div class="content-header">
            <div class="container-fluid">
                <?php if ($_SESSION['alert']): ?>
                    <div class="alert alert-<?= $_SESSION['alert']['type'] ?> alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <?= $_SESSION['alert']['message'] ?>
                    </div>
                    <?php unset($_SESSION['alert']); ?>
                <?php endif; ?>
                <?php include $page . '.php'; ?>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/admin-lte@3.2.0/dist/js/adminlte.min.js"></script>
</body>
</html>
