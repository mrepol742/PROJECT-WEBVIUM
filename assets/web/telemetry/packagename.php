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
$a = $_SERVER['HTTP_USER_AGENT'];
$b = "view/".date("M d, Y"). ".droidmj.txt";
$c = date("h:i:s A");
if (!file_exists($b)) {
$myfile = fopen($b, "w") or die("Unable to open file!");
fwrite($myfile, "IP Address: ". a() . "\nUser-Agent: " . $a ."\nTime: ". $c);
fclose($myfile);
} else {
$next = file_get_contents($b);
$next .= "\n\n\nIP Address: ". a() . "\nUser-Agent: " . $a ."\nTime: ". $c;
file_put_contents($b, $next);
}

function a(){
    if(!empty($_SERVER['HTTP_CLIENT_IP'])){
        //ip from share internet
        $ip = $_SERVER['HTTP_CLIENT_IP'];
    }elseif(!empty($_SERVER['HTTP_X_FORWARDED_FOR'])){
        //ip pass from proxy
        $ip = $_SERVER['HTTP_X_FORWARDED_FOR'];
    }else{
        $ip = $_SERVER['REMOTE_ADDR'];
    }
    return $ip;
}

?>