<?php
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['submit'])) {
    $release_year = $_POST['release_year'];

    if (!is_numeric($release_year) || $release_year < 1888 || $release_year > date("Y")) {
        echo '<div class="alert alert-danger">Invalid release year.</div>';
    } else {
        editVideo($_GET['id'], $_POST['title'], $_POST['director'], $release_year, $_POST['cast'], $_POST['genre'], $_POST['description']);
        echo '<div class="alert alert-success">Video updated successfully.</div>';
    }
}

if (isset($_GET['id'])) {
    $video = getVideoById($_GET['id']);
    if ($video !== null) {
?>
<div class="card card-info">
    <div class="card-header">
        <h3 class="card-title">Edit Video</h3>
    </div>
    <form action="index.php?page=edit&id=<?php echo $video['id']; ?>" method="post">
        <div class="card-body">
            <div class="form-group">
                <label>Title</label>
                <input type="text" class="form-control" name="title" value="<?php echo htmlspecialchars($video['title']); ?>" required>
            </div>
            <div class="form-group">
                <label>Director</label>
                <input type="text" class="form-control" name="director" value="<?php echo htmlspecialchars($video['director']); ?>" required>
            </div>
            <div class="form-group">
                <label>Release Year</label>
                <input type="number" class="form-control" name="release_year" value="<?php echo htmlspecialchars($video['release_year']); ?>" required>
            </div>
            <div class="form-group">
                <label>Cast Members</label>
                <input type="text" class="form-control" name="cast" value="<?php echo htmlspecialchars($video['cast']); ?>" required>
            </div>
            <div class="form-group">
                <label>Genre</label>
                <select class="form-control" name="genre" required>
                    <option value="Action" <?php echo ($video['genre'] == 'Action') ? 'selected' : ''; ?>>Action</option>
                    <option value="Comedy" <?php echo ($video['genre'] == 'Comedy') ? 'selected' : ''; ?>>Comedy</option>
                    <option value="Drama" <?php echo ($video['genre'] == 'Drama') ? 'selected' : ''; ?>>Drama</option>
                    <option value="Horror" <?php echo ($video['genre'] == 'Horror') ? 'selected' : ''; ?>>Horror</option>
                    <option value="Romance" <?php echo ($video['genre'] == 'Romance') ? 'selected' : ''; ?>>Romance</option>
                </select>
            </div>
            <div class="form-group">
                <label>Description</label>
                <textarea class="form-control" name="description" rows="3" required><?php echo htmlspecialchars($video['description']); ?></textarea>
            </div>
        </div>
        <div class="card-footer">
            <button type="submit" name="submit" class="btn btn-info">Update Video</button>
            <button type="button" class="btn btn-default" onclick="window.location.href='index.php?page=view'">Cancel</button>
        </div>
    </form>
</div>
<?php
    } else {
        echo '<div class="alert alert-danger">Video not found.</div>';
    }
} else {
    echo '<div class="alert alert-danger">No video ID specified.</div>';
}
?>
