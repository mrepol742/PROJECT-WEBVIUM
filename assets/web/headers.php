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
$headers = apache_request_headers();
foreach ($headers as $header => $value) {
    if ("$header" == "HOST" || "$header" == "X-Document-Root" || "$header" == "X-Server-Admin" || "$header" == "X-Server-Name" ) {
    } else {
       echo "$header: $value \n";
    }
}
?>