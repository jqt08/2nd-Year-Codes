<?php
require_once 'functions.php';
$videos = getVideos();
?>

<!DOCTYPE html>
<html>
<head>
    <title>View Videos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/admin-lte@3.2.0/dist/css/adminlte.min.css">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <div class="content-wrapper">
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">View Videos</h1>
                    </div>
                </div>
            </div>
        </div>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title">Video List</h3>
                            </div>
                            <div class="card-body">
                                <?php if (empty($videos)): ?>
                                    <p>No videos available.</p>
                                <?php else: ?>
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Title</th>
                                                <th>Director</th>
                                                <th>Release Year</th>
                                                <th>Cast</th>
                                                <th>Genre</th>
                                                <th>Description</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <?php foreach ($videos as $video): ?>
                                                <tr>
                                                    <td><?= htmlspecialchars($video['id']) ?></td>
                                                    <td><?= htmlspecialchars($video['title']) ?></td>
                                                    <td><?= htmlspecialchars($video['director']) ?></td>
                                                    <td><?= htmlspecialchars($video['release_year']) ?></td>
                                                    <td><?= htmlspecialchars($video['cast']) ?></td>
                                                    <td><?= htmlspecialchars($video['genre']) ?></td>
                                                    <td><?= htmlspecialchars($video['description']) ?></td>
                                                    <td>
                                                        <a href="index.php?page=edit&id=<?= $video['id'] ?>" class="btn btn-primary btn-sm">Edit</a>
                                                        <a href="index.php?page=delete&id=<?= $video['id'] ?>" class="btn btn-danger btn-sm">Delete</a>
                                                    </td>
                                                </tr>
                                            <?php endforeach; ?>
                                        </tbody>
                                    </table>
                                <?php endif; ?>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/admin-lte@3.2.0/dist/js/adminlte.min.js"></script>
</body>
</html>
