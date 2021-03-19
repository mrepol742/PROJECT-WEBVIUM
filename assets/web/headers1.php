/*
 *
 *
 *
 * DROID MJ Property || Confidential
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

<?php
$a = $_GET['url'];
if (substr($a, 0, 7 ) === "http://" || substr($a, 0, 8 ) === "https://") {
    echo implode("\n", get_headers($a));
} else {
    echo implode("\n", get_headers("http://".$a));
}
?>