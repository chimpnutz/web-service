/*
 * SimpleModal Basic Modal Dialog
 * http://www.ericmmartin.com/projects/simplemodal/
 * http://code.google.com/p/simplemodal/
 *
 * Copyright (c) 2010 Eric Martin - http://ericmmartin.com
 *
 * Licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 *
 * Revision: $Id: basic.js 254 2010-07-23 05:14:44Z emartin24 $
 */

jQuery(function ($) {
	// Load dialog on page load
	//$('#basic-modal-content').modal();

	// Load dialog on click
	
	
	$('.basic').click(function (e) {
		
		var branchname;
		var balance;
		var branchid;
		var amount;
		var mobile;
		var result = "";

		if ($(this).attr('id') == 'basic-2')
		{
			$(this).parent().children('#divamount').each(function ()
		{
			
			$(this).children('#amount').each (function() 
			{
	
				mobile = $(this).parent().children('#mobile').text();
				branchname = $(this).parent().children('#branchname').text();
				branchid = $(this).parent().children('#branchid').text();
				amount = $(this).val();
			
				if(!amount == ""){
					
					result = result + '<div  class="branch_class">' + branchname + '</div><div  class="branchid_class" style="margin-left: 53px;">' + branchid + '</div><div  class="mobile_no" style="margin-left: 34px;">' + mobile + '</div><div class="amount_class" style="margin-left: 59px;">' + amount + '</div><br/> <div style="clear:both;"></div>	';		
				}
				
			}
			);
			
		});
			
		modalcontent = '#' + $(this).attr('id') + '-content';

		$(modalcontent).children('form').children('#data').html(result);
		
		$(modalcontent).modal();
		
		
			
		}
		else {
			
			
		$(this).parent().children('#divamount').each(function ()
		{
			
			$(this).children('#amount').each (function() 
			{

//				branchname = $(this).parent().children('#branchname').text();
//				balance = $(this).parent().children('#balance').text();
//				branchid = $(this).parent().children('#branchid').text();
				branchname = $(this).siblings('#branchname').text();
				balance = $(this).siblings('#balance').text();
				branchid = $(this).siblings('#branchid').text();
				amount = $(this).val();
				
				if(!amount == ""){
					
					result = result + '<div  class="branch_class">' + branchname + '</div><div  class="branchid_class" style="margin-left: 2px;">' + branchid + '</div><div  class="balance_class" style="margin-left:83px;">' + balance + '</div><div  class="amount_class" style="margin-left:65px;">' + amount + '</div> <br/> <div style="clear:both;"></div>';
					
						
				}
				
			}
			);
			
		});
		
		modalcontent = '#' + $(this).attr('id') + '-content';
		$(modalcontent).children('form').children('#data').html(result);
		
		$(modalcontent).modal();
		$('#error').empty();
		
	}

		return false;
	});
	
	
    $("#simplemodal-overlay").live("click", function(){
    	$.modal.close();
    });

    

});