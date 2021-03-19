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
error_reporting(0);
$a = $_GET['url'];
$b = "";
if (substr($a, 0, 7 ) === "http://" || substr($a, 0, 8 ) === "https://") {
    $b = file_get_contents($a);
} else {
    $b = file_get_contents("http://" . $a);
}

$dom = new DOMDocument;
$dom->loadHTML($b);
echo "Link:\n";
foreach ($dom->getElementsByTagName('link') as $node) {
    echo $node->nodeValue.': '.$node->getAttribute("href")."\n";
}
echo "\n\n\nA:\n";
foreach ($dom->getElementsByTagName('a') as $node) {
    echo $node->nodeValue.': '.$node->getAttribute("href")."\n";
}
echo "\n\n\nImg:\n";
foreach ($dom->getElementsByTagName('img') as $node) {
    echo $node->nodeValue.': '.$node->getAttribute("src")."\n";
}
echo "\n\n\nSource:\n";
foreach ($dom->getElementsByTagName('source') as $node) {
    echo $node->nodeValue.': '.$node->getAttribute("src")."\n";
}
echo "\n\n\nScript:\n";
foreach ($dom->getElementsByTagName('script') as $node) {
    echo $node->nodeValue.': '.$node->getAttribute("src")."\n";
}
?>