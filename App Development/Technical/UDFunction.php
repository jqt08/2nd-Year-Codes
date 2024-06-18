<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Parameter Operations</title>
</head>
<body>
    <h1>Parameter Operations</h1>
    <form method="post" action="">
        <label for="param1">Enter parameter 1:</label><br>
        <input type="number" id="param1" name="param1" required><br>

        <label for="param2">Enter parameter 2:</label><br>
        <input type="number" id="param2" name="param2" required><br>

        <label for="param3">Enter parameter 3:</label><br>
        <input type="number" id="param3" name="param3" required><br>

        <button type="submit">Calculate</button>
    </form>

    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        if (isset($_POST["param1"]) && isset($_POST["param2"]) && isset($_POST["param3"])) {
            $param1 = $_POST["param1"];
            $param2 = $_POST["param2"];
            $param3 = $_POST["param3"];
            function calculateOperations($param1, $param2, $param3) {
                $sum = $param1 + $param2 + $param3;
                $diff = $param1 - $param2 - $param3;
                $product = $param1 * $param2 * $param3;
                if ($param2 != 0 && $param3 != 0) {
                    $quotient = $param1 / $param2 / $param3;
                } else {
                    $quotient = "Division by zero encountered.";
                }

                return array(
                    'sum' => $sum,
                    'difference' => $diff,
                    'product' => $product,
                    'quotient' => $quotient
                );
            }

            $results = calculateOperations($param1, $param2, $param3);

            echo "<p>Sum: " . $results['sum'] . "</p>";
            echo "<p>Difference: " . $results['difference'] . "</p>";
            echo "<p>Product: " . $results['product'] . "</p>";
            echo "<p>Quotient: " . $results['quotient'] . "</p>";
        }
    }
    ?>
</body>
</html>
