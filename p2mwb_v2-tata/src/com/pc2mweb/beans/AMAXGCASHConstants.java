package com.pc2mweb.beans;


public class AMAXGCASHConstants {
	public static final String TOPUP_SUCCESSFUL_CODE     = "00";
	public static final String TOPUP_SUCCESSFUL_MSG      = "TOPUP SUCCESSFUL";
	
	public static final String INSUFFICIENT_BALANCE_CODE = "50";
	public static final String INSUFFICIENT_BALANCE_MSG  = "INSUFFICIENT WALLET";
	
	public static final String TOPUP_FAILED_CODE         = "60";
	public static final String TOPUP_FAILED_MSG          = "TOPUP FAILED";
	
	public static final String OPERATION_FAILED_CODE     = "61";
	public static final String OPERATION_FAILED_MSG      = "OPERATION FAILED";
	
	public static final String IN_PROCESS_CODE  = "62";
	public static final String IN_PROCESS_MSG  = "IN PROCESS AMAX";
	
	public static final String IN_AMAX_CODE  = "63";
	public static final String IN_AMAX_MSG  = "STARTING AMAX";
	
	public static final String IN_TOPUPFAILED_CODE  = "64";
	public static final String IN_TOPUPFAILED_MSG  = "TOPUP ERROR";
	
	public static final String INVALID_MOBILE_CODE       = "70";
	public static final String INVALID_MOBILE_MSG        = "NOT A VALID NUMBER";
	
	public static final String DUPLICATE_TXID_CODE       = "71";
	public static final String DUPLICATE_TXID_MSG        = "DUPLICATE TXID";

	public static final String NOT_A_PREPAID_NUMBER_CODE = "72";
	public static final String NOT_A_PREPAID_NUMBER_MSG  = "NOT A PREPAID NUMBER";

	public static final String INVALID_DENOM_CODE = "73"; 
	public static final String INVALID_DENOM_MSG  = "INVALID DENOM"; 
	
	public static final String EXCEEDED_AMOUNT_CODE = "79";
	public static final String EXCEEDED_AMOUNT_MSG = "EXCEEDED TOPUP LIMIT";
	
	public static final String INSUFFICIENT_PSIBALANCE_CODE = "80";
	public static final String INSUFFICIENT_PSIBALANCE_MSG  = "INSUFFICIENT PSI AMAX WALLET";
	
	public static final String TIME_INTERVAL_ERR_CODE  = "81";
	public static final String TIME_INTERVAL_ERR_MSG  = "FAILED DUE TO TIME INTERVAL";
	
	public static final int ALLOWS_BIT = 0;
	public static final int ALLOW_GCASH = 0;
	public static final int ALLOW_GCASH_FEE_COLLECT = 1;
	public static final int ALLOW_GCASH_FEE_INCLUDED = 2;
	public static final int AMAX_SERVICE_BIT = 1;
	
	
	public static final int GCASH_SYSTEM_DOWN = -1 ;
	public static final String GCASH_SYSTEM_DOWN_MSG="GCASH SYSTEM DOWN";
	
	public static final int GCASH_INVALID_AMT=4;
	public static final String GCASH_INVALID_AMT_MSG="GCASH INVALID AMT";
	  
	public static final int GCASH_INVALID_MSISDN =50; 
	public static final String GCASH_INVALID_MSISDN_MSG="GCASH INVALID MSISDN";
	
	public static final int GCASH_INSUFFICIENT_WALLET=5; 
	public static final String GCASH_INSUFFICIENT_WALLET_MSG="GCASH INSUFFICIENT WALLET";
	
	public static final int GCASH_SYSTEM_DOWN_2=2; 
	public static final String GCASH_SYSTEM_DOWN_2_MSG="GCASH SYSTEM DOWN 2";
	 
	public static final int GCASH_LIMIT_EXCEEDED=7; 
	public static final String GCASH_LIMIT_EXCEEDED_MSG="GCASH LIMIT EXCEEDED";
	
	public static final int GCASH_DECLINED_MSISDN=52; 
	public static final String GCASH_DECLINED_MSISDN_MSG="GCASH DECLINED MSISDN";
	
	public static final int GCASH_SYSTEM_DOWN_GEN=99; 
	public static final String GCASH_SYSTEM_DOWN_GEN_MSG="GCASH SYSTEM DOWN GEN";
	
	public static final String SERVICE_NOT_ALLOW = "74";
	public static final String SERVICE_NOT_ALLOW_MSG  = "SERVICE NOT ALLOWED";
	
	public static final String GCASH_SERVICE_NOT_ALLOW = "74";
	public static final String GCASH_SERVICE_NOT_ALLOW_MSG  = "GCASH NOT ALLOWED"; //changed
	
	public static final String WALLET_DEBIT_ERROR_CODE = "79";
	public static final String WALLET_DEBIT_CODE_MSG = "DEBIT OPERATION FAILED";
	
	public static final int TOPUPRESPONSE_SESSION_ERROR_CODE = 5;
	public static final int TOPUPRESPONSE_TOPUP_ERROR_CODE = 5;
	
}

