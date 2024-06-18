<?php
require_once 'functions.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $title = $_POST['title'];
    $director = $_POST['director'];
    $release_year = $_POST['release_year'];
    $cast = $_POST['cast'];
    $genre = $_POST['genre'];
    $description = $_POST['description'];

    if (!is_numeric($release_year) || $release_year < 1888 || $release_year > date("Y")) {
        echo '<div class="alert alert-danger alert-dismissible fade show" role="alert">
                Invalid release year.
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
              </div>';
    } else {
        addVideo($title, $director, $release_year, $cast, $genre, $description);
        echo '<div class="alert alert-success alert-dismissible fade show" role="alert">
                Video added successfully.
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
              </div>';
    }
}
?>

<div class="card card-primary">
    <div class="card-header">
        <h3 class="card-title">Add New Video</h3>
    </div>
    <form action="index.php?page=add" method="post">
        <div class="card-body">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" name="title" placeholder="Enter title" required>
            </div>
            <div class="form-group">
                <label for="director">Director</label>
                <input type="text" class="form-control" name="director" placeholder="Enter director" required>
            </div>
            <div class="form-group">
                <label for="release_year">Release Year</label>
                <input type="number" class="form-control" name="release_year" placeholder="Enter release year" required>
            </div>
            <div class="form-group">
                <label for="cast">Cast Members</label>
                <input type="text" class="form-control" name="cast" placeholder="Enter cast members, separated by commas" required>
            </div>
            <div class="form-group">
                <label for="genre">Genre</label>
                <select class="form-control" name="genre" required>
                    <option value="Action">Action</option>
                    <option value="Comedy">Comedy</option>
                    <option value="Drama">Drama</option>
                    <option value="Horror">Horror</option>
                    <option value="Romance">Romance</option>
                </select>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea class="form-control" name="description" rows="3" placeholder="Enter description" required></textarea>
            </div>
        </div>
        <div class="card-footer">
            <button type="submit" class="btn btn-primary">Add Video</button>
        </div>
    </form>
</div>
