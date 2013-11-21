<?php
include "connect.php";
require_once 'Spreadsheet/Excel/Writer.php';

// We give the path to our file here
$workbook = new Spreadsheet_Excel_Writer('D:\\web_report_data.xls');

$sales_cost_sheet =& $workbook->addWorksheet('Sales.Cost');
$col = 15;
$sales_cost_sheet->setColumn(0,$col*2,20);

$sales_cost_sheet->write(0, 0, 'Transaction Date');
$sales_cost_sheet->write(0, 1, 'Heading');
$sales_cost_sheet->write(0, 2, 'Tag');
$sales_cost_sheet->write(0, 3, 'Amount');
			
$i =1;

	$result = mysql_query("select * from web_base_sales_cost  where transactiondate >= CONCAT(date_format(date_sub(NOW(),interval 1 day),'%Y-%m'),'-01') and transactiondate < NOW()");
	
	while($row = mysql_fetch_array($result)) 
	{				
		 $sales_cost_sheet->write($i, 0, $row['transactiondate']);		
		 $sales_cost_sheet->write($i, 1, $row['heading']);
		 $sales_cost_sheet->write($i, 2, $row['tag']);
		 $sales_cost_sheet->write($i, 3, $row['amount']);
		 $i++;
	} 

		$actual_deposit_sheet =& $workbook->addWorksheet('Actual Deposit');
		$actual_deposit_sheet->setColumn(0,$col*2,20);

		$actual_deposit_sheet->write(0, 0, 'Partner Name');
		$actual_deposit_sheet->write(0, 1, 'Transaction Date');
		$actual_deposit_sheet->write(0, 2, 'Amount');	

		$x = 1;
	
	$rs = mysql_query("select * from web_base_report_deposits where transactiondate >= CONCAT(date_format(date_sub(NOW(),interval 1 day),'%Y-%m'),'-01') and transactiondate < NOW()");

	while($row = mysql_fetch_array($rs)) 
	{				
		 $actual_deposit_sheet->write($x, 0, $row['partnerid']);		
		 $actual_deposit_sheet->write($x, 1, $row['transactiondate']);
		 $actual_deposit_sheet->write($x, 2, $row['amount']);
		 
		 $x++;
	}
	
	$resu = mysql_query("SELECT DISTINCT partnerid, CASE WHEN channel_id = 18 THEN 'TRANSFER' ELSE 'WEB' END AS channel FROM web_base_report_sales WHERE transactiondate >= CONCAT(DATE_FORMAT(DATE_SUB(NOW(),INTERVAL 1 DAY),'%Y-%m'),'-01') AND transactiondate < NOW()");
	while($channel = mysql_fetch_array($resu))
	{
	if($channel['channel'] == 'WEB')
	{
		$websheet =& $workbook->addWorksheet($channel['partnerid']." WEB");
		$websheet->setColumn(0,$col*2,20);

		$websheet->write(0, 0, 'Transaction Date');
		$websheet->write(0, 1, 'Tag');
		$websheet->write(0, 2, 'Amount');
		$y=1;
		
		$rs = mysql_query("select transactiondate, tag, amount from web_base_report_sales where transactiondate >= CONCAT(date_format(date_sub(NOW(),interval 1 day),'%Y-%m'),'-01') and transactiondate < NOW() and partnerid = '".$channel['partnerid']."' and channel_id <> 18");		
		while($row = mysql_fetch_array($rs)) 
		{				
			$websheet->write($y, 0, $row['transactiondate']);
			$websheet->write($y, 1, $row['tag']);
			$websheet->write($y, 2, $row['amount']);
			
			$y++;
		}   
	}
	else
	{
		$websheet =& $workbook->addWorksheet($channel['partnerid']." TRANSFER");
		$websheet->setColumn(0,$col*2,20);

		$websheet->write(0, 0, 'Transaction Date');
		$websheet->write(0, 1, 'Tag');
		$websheet->write(0, 2, 'Amount');
		$y=1;
		
		$rs = mysql_query("select transactiondate, tag, amount from web_base_report_sales where transactiondate >= CONCAT(date_format(date_sub(NOW(),interval 1 day),'%Y-%m'),'-01') and transactiondate < NOW() and partnerid = '".$channel['partnerid']."' and channel_id =18");		
		while($row = mysql_fetch_array($rs)) 
		{				
		$websheet->write($y, 0, $row['transactiondate']);
		$websheet->write($y, 1, $row['tag']);
		$websheet->write($y, 2, $row['amount']);
		
		$y++;
		} 
	}

}
	
// We still need to explicitly close the workbook
$workbook->close();

echo "excel has been generated";
?>