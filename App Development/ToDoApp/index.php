<!--http://localhost/JAKEPHP/todo-app/index.php-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo List</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Todo List</h1>
        <form method="POST" class="form-inline justify-content-center">
            <input type="text" name="task" class="form-control mb-2 mr-sm-2" placeholder="New task" required>
            <button type="submit" name="add" class="btn btn-primary mb-2">Add Task</button>
        </form>
        <table class="table table-striped mt-4">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Task</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <?php
                session_start();
                if (!isset($_SESSION['tasks'])) {
                    $_SESSION['tasks'] = [];
                }
                if (isset($_POST['add'])) {
                    $new_task = htmlspecialchars($_POST['task']);
                    $_SESSION['tasks'][] = $new_task;
                }
                if (isset($_POST['delete'])) {
                    $task_index = $_POST['delete'];
                    unset($_SESSION['tasks'][$task_index]);
                    $_SESSION['tasks'] = array_values($_SESSION['tasks']);
                }
                foreach ($_SESSION['tasks'] as $index => $task) {
                    echo '<tr>';
                    echo '<th scope="row">' . ($index + 1) . '</th>';
                    echo '<td>' . $task . '</td>';
                    echo '<td>';
                    echo '<form method="POST" class="mb-0">';
                    echo '<button type="submit" name="delete" value="' . $index . '" class="btn btn-danger btn-sm">Delete</button>';
                    echo '</form>';
                    echo '</td>';
                    echo '</tr>';
                }
                ?>
            </tbody>
        </table>
    </div>
</body>
</html>
