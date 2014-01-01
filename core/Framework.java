/*	Vapor API Terms and Conditions
 *
 *  - You MAY NOT
 *  	- attempt to claim ownership of, or pass off the Vapor source code and materials as your own work unless:
 *
 *  		- used as constituent component in an Android application that you intend to release and/or profit from
 *
 *		- use or redistribute the Vapor source code and materials without explicit attribution to the owning parties
 *
 *  	- advertise Vapor in a misleading, inappropriate or offensive fashion
 *
 * - Indemnity
 * 		You agree to indemnify and hold harmless the authors of the Software and any contributors for any direct, indirect, 
 * 		incidental, or consequential third-party claims, actions or suits, as well as any related expenses, liabilities, damages, 
 * 		settlements or fees arising from your use or misuse of the Software, or a violation of any terms of this license.
 *  
 *  - DISCLAIMER OF WARRANTY
 *  	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, 
 *		WARRANTIES OF QUALITY, PERFORMANCE, NON-INFRINGEMENT, MERCHANTABILITY, OR FITNESS FOR A PARTICULAR PURPOSE.
 *  
 *  - LIMITATIONS OF LIABILITY
 *  	YOU ASSUME ALL RISK ASSOCIATED WITH THE INSTALLATION AND USE OF THE SOFTWARE. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * 		HOLDERS OF THE SOFTWARE BE LIABLE FOR CLAIMS, DAMAGES OR OTHER LIABILITY ARISING FROM, OUT OF, OR IN CONNECTION WITH THE 
 *		SOFTWARE. LICENSE HOLDERS ARE SOLELY RESPONSIBLE FOR DETERMINING THE APPROPRIATENESS OF USE AND ASSUME ALL RISKS ASSOCIATED 
 *		WITH ITS USE, INCLUDING BUT NOT LIMITED TO THE RISKS OF PROGRAM ERRORS, DAMAGE TO EQUIPMENT, LOSS OF DATA OR SOFTWARE PROGRAMS, 
 *		OR UNAVAILABILITY OR INTERRUPTION OF OPERATIONS.
 *  
 *  © Darius Hodaei. License Version 1.1. Last Updated 30/06/2013.
*/

package vapor.core;

import java.math.BigInteger;
import java.security.MessageDigest;

import vapor.app.VaporService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

final class Framework {
	private static boolean licenseChecked = false, isLicensed = false;

	private static final int VAPOR_KEY_LENGTH = 200, APP_KEY_LENGTH = 392,
			INVALID_LICENSE = 0, APP_LICENSE = 1, DEV_LICENSE = 2;
	
	final static String VAPOR_SYS = "Vapor API (v1.0)"
			// NOTE: Each line is 40 chars wide
			, SYS_UNLICENSED =  "******************************************\n* Vapor API Framework v1.0 : UNLICENSED  *\n*                                        *\n* Vapor key in MANIFEST is invalid.      *\n* Please buy a license from              *\n* http://vapor-api.com/licenses, and/or  *\n* check you have entered it correctly.   *\n*                                        *\n* \u00A9 2013. Darius Hodaei.	         	 *\n******************************************"
			, SYS_DEBUG =       "******************************************\n* Vapor API Framework v1.0 : DEBUG MODE  *\n*                                        *\n* NOTE: you MUST buy a Vapor API License *\n* from http://vapor-api.com/licenses     *\n* before you distribute your app         *\n*                                        *\n* \u00A9 2013. Darius Hodaei.	         	 *\n******************************************"
			, SYS_APP_LICENSE = "******************************************\n* Vapor API Framework v1.0 : LICENSED    *\n*                                        *\n* Thank you for purchasing Vapor API!    *\n*					 *\n* \u00A9 2013. Darius Hodaei.		 *\n******************************************"
			,CONTEXT_NOT_SET =  "******************************************\n* Vapor API Framework v1.0 : NO CONTEXT  *\n*                                        *\n* Vapor requires a Context to be set     *\n* in order to run properly. If you are   *\n* NOT using VaporActivity for your       *\n* activities, please call $.act(this)    *\n* in each .onCreate(Bundle) override.    *\n*                                        *\n* \u00A9 2013. Darius Hodaei.	         	 *\n******************************************"
			;
	// cannot instantiate
	private Framework(){};


	static boolean licensed(){

		// only test once
		if(!licenseChecked){
			isLicensed = validateVapor();
			licenseChecked = true;
		}
		return isLicensed;
	}

	private final static void licenseMessage(final String message){
		new Thread() {
		    public void run() {
		    	
		    	while(true){
		    		$.toast(message).duration(Toast.LENGTH_LONG).gravity(Gravity.BOTTOM).show();
			    	try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    }
		}.start();
	}
	
	
	private final static boolean validateVapor(){
	
		Context cxt = $.act();
		
		if(cxt == null){
			Log.w(VAPOR_SYS,CONTEXT_NOT_SET);
			return false;
		}
		
		boolean isDebug = debug();
		// unlicensed Vapor is allowed in debug mode
		if(isDebug){
			
			licenseMessage("Vapor API : Debug mode. Please buy a license before releasing this app! :)");
			return true;
		}
		else{
			// get credentials
			String manifestVaporKey = license();
			// perform validation on credentials

			if(!validate(manifestVaporKey)){
				// unlicensed
				Log.e(VAPOR_SYS,SYS_UNLICENSED);
		
				licenseMessage("Vapor API : You MUST buy a Vapor API license for this app.");
				return false;
			}
			else{
				// licensed
				Log.i(VAPOR_SYS,SYS_APP_LICENSE);
				return true;
			}
		}
	
	}

	// read in Vapor key from manifest
	private static String license(){ return $.meta().getString(vaporMetaName()); }

	private static String vaporMetaName(){ return "VaporAPI(v1.0)";}
	
	private static boolean validate(String manifestVaporKey){
	
		if(manifestVaporKey == null) return false;
		
		return vaporKeyGenerator(appSignature()).equalsIgnoreCase(manifestVaporKey);
	}

	 static String  appSignature(){
		Activity act = $.act();
		try {
			return act.getPackageManager()
					.getPackageInfo(act.getPackageName(), PackageManager.GET_SIGNATURES)
					.signatures[0].toCharsString();
			
		} catch (NameNotFoundException e) {
			return null;
		}
	}
	
	private static boolean debug(){
		return ( 0 != ( $.act().getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE ) );
	}
	
	

	private static String vaporKeyGenerator(String appSignature)
	{
	    try{
	    	// Paypal will truncate the app signature to 200 chars, so we
	    	// emulate this truncation here
	    	String truncatedAppSignature = appSignature.substring(0,200);
	    	
			byte[] bytesOfMessage = truncatedAppSignature.getBytes("ASCII");
			
			byte[] hash = MessageDigest.getInstance("MD5").digest(bytesOfMessage);

			String hashStr = new BigInteger(1, hash).toString(16);

			//32 bytes
			hash = hashStr.getBytes("US-ASCII");

			byte[] permVapBytes = new byte[64];
			int vIndex = 0;
			for (int index = 0; index < 32; index += 8)
			{
				byte[] expansion = expansionPbox(new byte[]{
								hash[index]
								,hash[index + 1]
								,hash[index + 2]
								,hash[index + 3]
								,hash[index + 4]
								,hash[index + 5]
								,hash[index + 6]
								,hash[index + 7]
							});

				for (int index2 = 0; index2 < expansion.length; index2++)
				{
					permVapBytes[vIndex++] = expansion[index2];
				}
			}
		   return  new String(permVapBytes, "US-ASCII");
	   }
	   catch(Exception e){
			return null;
	   }
	}

	private static byte[] expansionPbox(byte[] inputBytes)
	{

	    if (inputBytes.length != 8) return null;

	    byte[] expansion = new byte[16];

	    expansion[0] = inputBytes[7];
	    expansion[1] = inputBytes[4];
	    expansion[2] = inputBytes[3];
	    expansion[3] = inputBytes[0];
	    expansion[4] = inputBytes[1];
	    expansion[5] = inputBytes[6];
	    expansion[6] = inputBytes[7];
	    expansion[7] = inputBytes[2];
	    expansion[8] = inputBytes[4];
	    expansion[9] = inputBytes[1];
	    expansion[10] = inputBytes[3];
	    expansion[11] = inputBytes[6];
	    expansion[12] = inputBytes[7];
	    expansion[13] = inputBytes[5];
	    expansion[14] = inputBytes[1];
	    expansion[15] = inputBytes[0];
	    return expansion;
	}
	
}
