<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Array Operations</title>
</head>
<body>
    <h1>Array Operations</h1>
    <form method="post" action="">
        <label for="numbers">Enter 10 numbers separated by commas:</label><br>
        <input type="text" id="numbers" name="numbers" required><br>
        <button type="submit">Calculate</button>
    </form>

    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        if (isset($_POST["numbers"])) {
            $inputNumbers = $_POST["numbers"];
            $inputArray = explode(",", $inputNumbers);
            if (count($inputArray) != 10) {
                echo "<p>Error: Please enter exactly 10 numbers.</p>";
            } else {
                $numbers = array_map('intval', $inputArray);
                $sum = array_sum($numbers);
                $diff = $numbers[0]; 
                for ($i = 1; $i < count($numbers); $i++) {
                    $diff -= $numbers[$i];
                }
                $product = 1; 
                foreach ($numbers as $num) {
                    $product *= $num;
                }
                $quotient = $numbers[0]; 
                for ($i = 1; $i < count($numbers); $i++) {
                    if ($numbers[$i] != 0) {
                        $quotient /= $numbers[$i];
                    } else {
                        echo "<p>Error: Division by zero encountered.</p>";
                        break;
                    }
                }
                echo "<p>Numbers: " . implode(", ", $numbers) . "</p>";
                echo "<p>Sum: $sum</p>";
                echo "<p>Difference: $diff</p>";
                echo "<p>Product: $product</p>";
                echo "<p>Quotient: $quotient</p>";
            }
        }
    }
    ?>
</body>
</html>
