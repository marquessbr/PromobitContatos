<?php

	$response = array();

    error_reporting(E_ALL);
	$ID 		= htmlspecialchars($_REQUEST['ID']);
	$NAME 		= htmlspecialchars($_REQUEST['name']);
	$COMPANY 	= htmlspecialchars($_REQUEST['company']);
	$EMAIL 		= htmlspecialchars($_REQUEST['email']);
	$PHONE   	= htmlspecialchars($_REQUEST['phone']);
	$WEBSITE	= htmlspecialchars($_REQUEST['website']);
	$CUSTON_NOTE= htmlspecialchars($_REQUEST['custon_note']);

	// http://www.raonimotores.com.br/mobiles/ossonhosdeaaz/dreamstointerpret.php?ID=1&name=Armando&email=mbr@mbr.com&dream=sonhei%20com%20um%20macaco&datedream=00/00/0000&timedream=00:00:00&keypub=00#0000000#000000
	
	if ( !isset($_REQUEST['ID']) ) $ID = -1;

	$KEY_ID = "ID";                    
	$KEY_NAME = "name";
	$KEY_COMPANY = "company";
	$KEY_EMAIL = "email";	
	$KEY_PHONE = "phone";          
	$KEY_WEBSITE = "website";
	$KEY_CUSTON_NOTE = "custon_note";

	$pdostring = 'sqlite::memory:';
	$pdoConn = new PDO($pdostring);
	$pdoConn->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);

/*
CREATE TABLE "dreams_added" ( `ID` INTEGER, `name` VARCHAR ( 60 ) DEFAULT 'UMPUBLISHED', `email` TEXT DEFAULT 'umpublished@dreamanotation.com', `dream` VARCHAR ( 2500 ), `datedream` VARCHAR ( 10 ) DEFAULT (substr(datetime('now','localtime'),0,11)), `timedream` VARCHAR ( 8 ) DEFAULT (substr(datetime('now','localtime'),11,9)), `related` TEXT DEFAULT 'UMPUBLISHED', `interpreted` CHAR ( 3 ), `interpretation` TEXT DEFAULT 'UMPUBLISHED', `dateinter` VARCHAR ( 10 ) DEFAULT 'NULL', `keypub` VARCHAR ( 23 ) DEFAULT 'UMPUBLISHED' )
*/

	$createCmd = "CREATE TABLE IF NOT EXISTS contacts (\"".
		$KEY_ID."\" INTEGER,\"".
		$KEY_NAME."\" VARCHAR(200) DEFAULT 'NOTINFORMED',\"".
		$KEY_COMPANY."\" TEXT DEFAULT 'NOTINFORMED',\"".
		$KEY_EMAIL."\" TEXT DEFAULT 'NOTINFORMED@dreamanotation.com',\"".
		$KEY_PHONE."\" VARCHAR(20),\"".
		$KEY_WEBSITE."\" VARCHAR(350) DEFAULT 'http://not_informedwebsite.com',\"".
		$KEY_CUSTON_NOTE."\" TEXT);";
		
	$pdoConn->exec($createCmd);
	
	$sql = sprintf("INSERT INTO contacts (`".
		$KEY_ID."`,`".
		$KEY_NAME."`,`".
		$KEY_COMPANY."`,`".
		$KEY_EMAIL."`,`".
		$KEY_PHONE."`,`".
		$KEY_WEBSITE."`,`".
		$KEY_CUSTON_NOTE."`) VALUES ('%s','%s','%s','%s','%s','%s','%s')",
		$ID,
		$NAME,
		$COMPANY,
		$EMAIL,
		$PHONE,
		$WEBSITE,
		$CUSTON_NOTE);

	$pdoConn->beginTransaction();

	if ($ID != -1) {
		$result = $pdoConn->exec($sql);
		$pdoConn->commit();
		$pdoConn=null;
	}	
	
    if ($result) {
        $response["success"] = 1;
        $response["message"] = "%s";
    } else {
        $response["success"] = 0;
        $response["message"] = "%s";
    }
	echo json_encode($response);
?>