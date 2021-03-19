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

if (substr($a, 0, 7 ) === "http://" || substr($a, 0, 8 ) === "https://") {
$a = get_meta_tags($a);
} else {
$a = get_meta_tags("http://".$a);

}
echo $a['author']. "\n"
. $a['keywords']. "\n"
. $a['description'] . "\n"
. $a['subject'] . "\n"
. $a['copyright'] . "\n"
. $a['language'] . "\n"
. $a['robots'] . "\n"
. $a['revised'] . "\n"
. $a['abstract'] . "\n"
. $a['topic'] . "\n"
. $a['summary'] ."\n"
. $a['Classification'] . "\n"
. $a['designer'] . "\n"
. $a['reply-to'] . "\n"
. $a['owner'] . "\n"
. $a['url'] . "\n"
. $a['identifier-URL'] . "\n"
. $a['directory'] . "\n"
. $a['category'] . "\n"
. $a['coverage'] . "\n"
. $a['distribution'] . "\n"
. $a['rating'] . "\n"
. $a['revisit-after'] . "\n"
. $a['Expires'] . "\n"
. $a['Pragma'] . "\n"
. $a['Cache-Control'] . "\n"
. $a['Content-Type'] . "\n"
. $a['viewport'];
?>