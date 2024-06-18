<?php
function processTickets($startCode) {
    $regularEvents = array();
    $vipEvents = array();
    $vvipEvents = array();
    $processedCodes = array();

    for ($code = $startCode; $code <= 50; $code++) {
        if ($code % 15 == 0) {
            $vvipEvents[] = $code;
            $processedCodes[] = "$code: VVIP event. Immediate action required.";
            continue;
        } elseif ($code % 5 == 0) {
            $vipEvents[] = $code;
            $processedCodes[] = "$code: Divisible by 5, processing VIP event with code: $code";
        } elseif ($code % 3 == 0) {
            $regularEvents[] = $code;
            $processedCodes[] = "$code: Divisible by 3, processing regular event with code: $code";
        } else {
            $processedCodes[] = "$code: Skipping.";
            continue; 
        }

        if ($code == 50) {
            $processedCodes[] = "Termination code encountered. Stopping processing.";
            break; 
        }
    }
    $regularStr = implode(', ', $regularEvents);
    $vipStr = implode(', ', $vipEvents);
    $vvipStr = implode(', ', $vvipEvents);

    return array($processedCodes, $regularStr, $vipStr, $vvipStr);
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST['start_code']) && !empty($_POST['start_code'])) {
        $startCode = intval($_POST['start_code']);

        if ($startCode < 1 || $startCode > 50) {
            echo "Invalid input. Please enter a number between 1 and 50.";
        } else {
            list($processedCodes, $regularEvents, $vipEvents, $vvipEvents) = processTickets($startCode);
        }
    } else {
        echo "Invalid input. Please enter a number between 1 and 50.";
    }
} else {
    echo "No data received.";
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ticketing System</title>
    <style>
    </style>
</head>
<body>
    <h3>Processed Codes</h3>
    <div>
        <?php
        if (!empty($processedCodes)) {
            foreach ($processedCodes as $code) {
                echo $code . "<br>";
            }
        }
        ?>
    </div>

    <hr>

    <h3>Event Categories</h3>
    <div>
        <?php
        echo "Regular event: $regularEvents<br>";
        echo "VIP event: $vipEvents<br>";
        echo "VVIP event: $vvipEvents<br>";
        ?>
    </div>
</body>
</html>
