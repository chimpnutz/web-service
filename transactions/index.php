<?php
include "connect.php";
require_once 'Spreadsheet/Excel/Writer.php';

// We give the path to our file here
$workbook = new Spreadsheet_Excel_Writer('D:\\web_report_data.xls');

$worksheet =& $workbook->addWorksheet('sales.cost');
$col = 15;
$worksheet->setColumn(0,$col*2,20);

$worksheet->write(0, 0, 'Transaction Date');
$worksheet->write(0, 1, 'Heading');
$worksheet->write(0, 2, 'Tag');
$worksheet->write(0, 3, 'Amount');
			
$i =1;

	$result = mysql_query("select * from web_base_sales_cost  where transactiondate >= CONCAT(date_format(date_sub(NOW(),interval 1 day),'%Y-%m'),'-01') and transactiondate < NOW()");
	
	while($row = mysql_fetch_array($result)) 
	{				
		 $worksheet->write($i, 0, $row['transactiondate']);		
		 $worksheet->write($i, 1, $row['heading']);
		 $worksheet->write($i, 2, $row['tag']);
		 $worksheet->write($i, 3, $row['amount']);
		 $i++;
	} 

$worksheet1 =& $workbook->addWorksheet('Actual Deposit');
$worksheet1->setColumn(0,$col*2,20);

$worksheet1->write(0, 0, 'Partner Name');
$worksheet1->write(0, 1, 'Transaction Date');
$worksheet1->write(0, 2, 'Amount');	

$x = 1;
	
	$rs = mysql_query("select * from web_base_report_deposits where transactiondate >= CONCAT(date_format(date_sub(NOW(),interval 1 day),'%Y-%m'),'-01') and transactiondate < NOW()");

	while($row = mysql_fetch_array($rs)) 
	{				
		 $worksheet1->write($x, 0, $row['partnerid']);		
		 $worksheet1->write($x, 1, $row['transactiondate']);
		 $worksheet1->write($x, 2, $row['amount']);
		 
		 $x++;
	} 
	
// We still need to explicitly close the workbook
$workbook->close();

echo "excel has been generated";
?>