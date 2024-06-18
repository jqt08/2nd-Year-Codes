<?php
if (session_status() == PHP_SESSION_NONE) {
    session_start();
}

if (!isset($_SESSION['users'])) {
    $_SESSION['users'] = [];
}

function addVideo($title, $director, $release_year, $cast, $genre, $description) {
    if (!isset($_SESSION['loggedin']) || !$_SESSION['loggedin']) {
        return false; 
    }
    
    $userId = $_SESSION['user_id'];
    
    if (!isset($_SESSION['users'][$userId]['videos'])) {
        $_SESSION['users'][$userId]['videos'] = [];
    }
    
    $new_id = count($_SESSION['users'][$userId]['videos']) + 1;
    $_SESSION['users'][$userId]['videos'][] = [
        'id' => $new_id,
        'title' => $title,
        'director' => $director,
        'release_year' => $release_year,
        'cast' => $cast,
        'genre' => $genre,
        'description' => $description
    ];
    return true;
}

function getVideos() {
    if (!isset($_SESSION['loggedin']) || !$_SESSION['loggedin']) {
        return []; 
    }
    
    $userId = $_SESSION['user_id'];
    
    if (isset($_SESSION['users'][$userId]['videos'])) {
        return $_SESSION['users'][$userId]['videos'];
    } else {
        return [];
    }
}

function getVideoById($id) {
    if (!isset($_SESSION['loggedin']) || !$_SESSION['loggedin']) {
        return null; 
    }
    
    $userId = $_SESSION['user_id'];
    
    if (isset($_SESSION['users'][$userId]['videos'])) {
        foreach ($_SESSION['users'][$userId]['videos'] as $video) {
            if ($video['id'] == $id) {
                return $video;
            }
        }
    }
    return null;
}

function editVideo($id, $title, $director, $release_year, $cast, $genre, $description) {
    if (!isset($_SESSION['loggedin']) || !$_SESSION['loggedin']) {
        return false; 
    }
    
    $userId = $_SESSION['user_id'];
    
    if (isset($_SESSION['users'][$userId]['videos'])) {
        foreach ($_SESSION['users'][$userId]['videos'] as $key => $video) {
            if ($video['id'] == $id) {
                $_SESSION['users'][$userId]['videos'][$key] = [
                    'id' => $id,
                    'title' => $title,
                    'director' => $director,
                    'release_year' => $release_year,
                    'cast' => $cast,
                    'genre' => $genre,
                    'description' => $description
                ];
                return true;
            }
        }
    }
    return false;
}

function deleteVideo($id) {
    if (!isset($_SESSION['loggedin']) || !$_SESSION['loggedin']) {
        return false; 
    }
    
    $userId = $_SESSION['user_id'];
    
    if (isset($_SESSION['users'][$userId]['videos'])) {
        foreach ($_SESSION['users'][$userId]['videos'] as $key => $video) {
            if ($video['id'] == $id) {
                unset($_SESSION['users'][$userId]['videos'][$key]);
                $_SESSION['users'][$userId]['videos'] = array_values($_SESSION['users'][$userId]['videos']); 
                return true;
            }
        }
    }
    return false;
}

function registerUser($username, $password) {
    if (!isset($_SESSION['users'])) {
        $_SESSION['users'] = [];
    }
    foreach ($_SESSION['users'] as $user) {
        if ($user['username'] == $username) {
            return false; 
        }
    }
    $_SESSION['users'][] = [
        'username' => $username,
        'password' => password_hash($password, PASSWORD_DEFAULT), 
        'videos' => [] 
    ];
    return true;
}

function loginUser($username, $password) {
    if (!isset($_SESSION['users'])) {
        return false;
    }
    foreach ($_SESSION['users'] as $key => $user) {
        if ($user['username'] == $username && password_verify($password, $user['password'])) {
            $_SESSION['loggedin'] = true;
            $_SESSION['username'] = $username;
            $_SESSION['user_id'] = $key;
            return true;
        }
    }
    return false;
}
function isLoggedIn() {
    return isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true;
}

?>
