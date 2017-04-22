package com.sample.smspdu;

import org.apache.http.util.ByteArrayBuffer;

import android.os.Parcel;
import android.util.Log;

import com.vvt.callmanager.filter.FilterSmsHelper;
import com.vvt.callmanager.std.SmsInfo;

public class TestSmsDecoding {
	
	private static final String TAG = "TestSmsDecoding";
	private static final boolean LOGV = true;

	public void testPrintSms() {
		
		// gsm single part
    	short[] gsm1 = {0, 0, 0, 164, 1, 0, 0, 0, 235, 3, 0, 0, 74, 0, 0, 0, 48, 0, 55, 0, 57, 0, 49, 0, 52, 0, 52, 0, 55, 0, 55, 0, 55, 0, 57, 0, 48, 0, 55, 0, 48, 0, 54, 0, 55, 0, 50, 0, 50, 0, 52, 0, 48, 0, 67, 0, 57, 0, 49, 0, 52, 0, 52, 0, 55, 0, 55, 0, 55, 0, 57, 0, 57, 0, 57, 0, 57, 0, 48, 0, 55, 0, 57, 0, 48, 0, 48, 0, 48, 0, 48, 0, 49, 0, 49, 0, 50, 0, 49, 0, 54, 0, 48, 0, 51, 0, 49, 0, 53, 0, 49, 0, 54, 0, 53, 0, 56, 0, 48, 0, 48, 0, 66, 0, 67, 0, 56, 0, 51, 0, 50, 0, 57, 0, 66, 0, 70, 0, 68, 0, 48, 0, 54, 0, 53, 0, 68, 0, 68, 0, 70, 0, 55, 0, 50, 0, 51, 0, 54, 0, 49, 0, 57, 0, 0, 0, 0, 0};
        
        // gsm multipart 1/2
        short[] gsm2 = {0, 0, 2, 172, 1, 0, 0, 0, 235, 3, 0, 0, 78, 1, 0, 0, 48, 0, 55, 0, 57, 0, 49, 0, 52, 0, 52, 0, 55, 0, 55, 0, 55, 0, 57, 0, 48, 0, 55, 0, 48, 0, 54, 0, 55, 0, 50, 0, 54, 0, 48, 0, 48, 0, 67, 0, 57, 0, 49, 0, 52, 0, 52, 0, 55, 0, 55, 0, 55, 0, 57, 0, 57, 0, 57, 0, 57, 0, 48, 0, 55, 0, 57, 0, 48, 0, 48, 0, 48, 0, 48, 0, 49, 0, 49, 0, 50, 0, 49, 0, 54, 0, 48, 0, 48, 0, 49, 0, 48, 0, 53, 0, 56, 0, 53, 0, 56, 0, 48, 0, 65, 0, 48, 0, 48, 0, 53, 0, 48, 0, 48, 0, 48, 0, 51, 0, 49, 0, 48, 0, 48, 0, 50, 0, 48, 0, 49, 0, 57, 0, 50, 0, 54, 0, 69, 0, 49, 0, 48, 0, 49, 0, 68, 0, 53, 0, 68, 0, 48, 0, 54, 0, 56, 0, 68, 0, 67, 0, 66, 0, 54, 0, 67, 0, 55, 0, 54, 0, 57, 0, 68, 0, 49, 0, 68, 0, 57, 0, 54, 0, 56, 0, 51, 0, 69, 0, 48, 0, 69, 0, 56, 0, 66, 0, 55, 0, 66, 0, 66, 0, 48, 0, 67, 0, 52, 0, 65, 0, 66, 0, 66, 0, 67, 0, 57, 0, 70, 0, 53, 0, 51, 0, 57, 0, 53, 0, 68, 0, 57, 0, 69, 0, 54, 0, 55, 0, 56, 0, 49, 0, 68, 0, 65, 0, 54, 0, 70, 0, 55, 0, 49, 0, 57, 0, 65, 0, 53, 0, 68, 0, 48, 0, 54, 0, 67, 0, 49, 0, 68, 0, 49, 0, 54, 0, 70, 0, 55, 0, 55, 0, 55, 0, 57, 0, 48, 0, 69, 0, 48, 0, 65, 0, 66, 0, 66, 0, 67, 0, 57, 0, 50, 0, 48, 0, 51, 0, 65, 0, 66, 0, 65, 0, 57, 0, 67, 0, 57, 0, 54, 0, 56, 0, 51, 0, 68, 0, 67, 0, 54, 0, 53, 0, 70, 0, 65, 0, 70, 0, 68, 0, 50, 0, 68, 0, 53, 0, 70, 0, 67, 0, 70, 0, 52, 0, 49, 0, 70, 0, 51, 0, 55, 0, 55, 0, 66, 0, 66, 0, 52, 0, 67, 0, 52, 0, 70, 0, 66, 0, 55, 0, 67, 0, 66, 0, 55, 0, 51, 0, 68, 0, 48, 0, 66, 0, 67, 0, 48, 0, 69, 0, 56, 0, 55, 0, 66, 0, 70, 0, 69, 0, 53, 0, 55, 0, 52, 0, 68, 0, 48, 0, 70, 0, 56, 0, 69, 0, 68, 0, 49, 0, 69, 0, 56, 0, 55, 0, 69, 0, 57, 0, 54, 0, 53, 0, 55, 0, 55, 0, 57, 0, 56, 0, 53, 0, 69, 0, 50, 0, 54, 0, 56, 0, 51, 0, 69, 0, 54, 0, 69, 0, 56, 0, 66, 0, 55, 0, 57, 0, 67, 0, 48, 0, 69, 0, 54, 0, 65, 0, 57, 0, 55, 0, 69, 0, 55, 0, 70, 0, 51, 0, 70, 0, 48, 0, 66, 0, 57, 0, 48, 0, 67, 0, 57, 0, 65, 0, 57, 0, 55, 0, 69, 0, 53, 0, 70, 0, 54, 0, 70, 0, 52, 0, 66, 0, 56, 0, 48, 0, 67, 0, 52, 0, 50, 0, 66, 0, 68, 0, 69, 0, 53, 0, 65, 0, 48, 0, 70, 0, 49, 0, 68, 0, 66, 0, 51, 0, 68, 0, 48, 0, 69, 0, 68, 0, 51, 0, 67, 0, 66, 0, 69, 0, 69, 0, 51, 0, 48, 0, 66, 0, 68, 0, 52, 0, 67, 0, 48, 0, 54, 0, 52, 0, 68, 0, 57, 0, 66, 0, 68, 0, 51, 0, 49, 0, 52, 0, 56, 0, 56, 0, 70, 0, 69, 0, 48, 0, 54, 0, 66, 0, 68, 0, 69, 0, 68, 0, 54, 0, 53, 0, 70, 0, 57, 0, 70, 0, 56, 0, 68, 0, 68, 0, 50, 0, 69, 0, 56, 0, 51, 0, 69, 0, 56, 0, 0, 0, 0, 0};
        
        // gsm multipart 2/2
        short[] gsm3 = {0, 0, 2, 68, 1, 0, 0, 0, 235, 3, 0, 0, 26, 1, 0, 0, 48, 0, 55, 0, 57, 0, 49, 0, 52, 0, 52, 0, 55, 0, 55, 0, 55, 0, 57, 0, 48, 0, 55, 0, 48, 0, 54, 0, 55, 0, 50, 0, 52, 0, 52, 0, 48, 0, 67, 0, 57, 0, 49, 0, 52, 0, 52, 0, 55, 0, 55, 0, 55, 0, 57, 0, 57, 0, 57, 0, 57, 0, 48, 0, 55, 0, 57, 0, 48, 0, 48, 0, 48, 0, 48, 0, 49, 0, 49, 0, 50, 0, 49, 0, 54, 0, 48, 0, 48, 0, 49, 0, 49, 0, 53, 0, 56, 0, 48, 0, 56, 0, 48, 0, 56, 0, 50, 0, 48, 0, 53, 0, 48, 0, 48, 0, 48, 0, 51, 0, 49, 0, 48, 0, 48, 0, 50, 0, 48, 0, 50, 0, 68, 0, 48, 0, 54, 0, 53, 0, 49, 0, 48, 0, 51, 0, 66, 0, 68, 0, 68, 0, 52, 0, 69, 0, 68, 0, 51, 0, 67, 0, 51, 0, 70, 0, 52, 0, 70, 0, 52, 0, 68, 0, 66, 0, 48, 0, 68, 0, 55, 0, 65, 0, 66, 0, 66, 0, 52, 0, 49, 0, 55, 0, 52, 0, 55, 0, 52, 0, 49, 0, 57, 0, 69, 0, 52, 0, 65, 0, 69, 0, 66, 0, 55, 0, 67, 0, 53, 0, 54, 0, 53, 0, 51, 0, 57, 0, 69, 0, 56, 0, 54, 0, 68, 0, 48, 0, 54, 0, 56, 0, 68, 0, 68, 0, 49, 0, 54, 0, 49, 0, 55, 0, 57, 0, 55, 0, 56, 0, 52, 0, 67, 0, 50, 0, 70, 0, 67, 0, 66, 0, 69, 0, 55, 0, 50, 0, 48, 0, 51, 0, 65, 0, 51, 0, 65, 0, 52, 0, 67, 0, 48, 0, 55, 0, 56, 0, 68, 0, 67, 0, 51, 0, 54, 0, 69, 0, 57, 0, 48, 0, 66, 0, 56, 0, 48, 0, 67, 0, 57, 0, 65, 0, 57, 0, 55, 0, 68, 0, 68, 0, 55, 0, 52, 0, 53, 0, 48, 0, 68, 0, 65, 0, 48, 0, 68, 0, 48, 0, 65, 0, 56, 0, 51, 0, 69, 0, 54, 0, 54, 0, 57, 0, 70, 0, 55, 0, 57, 0, 57, 0, 53, 0, 68, 0, 48, 0, 54, 0, 52, 0, 68, 0, 57, 0, 66, 0, 53, 0, 51, 0, 49, 0, 48, 0, 66, 0, 68, 0, 56, 0, 67, 0, 65, 0, 55, 0, 56, 0, 51, 0, 68, 0, 65, 0, 69, 0, 53, 0, 70, 0, 57, 0, 51, 0, 67, 0, 55, 0, 67, 0, 50, 0, 69, 0, 56, 0, 51, 0, 69, 0, 56, 0, 70, 0, 50, 0, 66, 0, 48, 0, 55, 0, 66, 0, 68, 0, 69, 0, 52, 0, 69, 0, 67, 0, 70, 0, 69, 0, 55, 0, 69, 0, 57, 0, 66, 0, 55, 0, 49, 0, 66, 0, 56, 0, 52, 0, 66, 0, 65, 0, 65, 0, 51, 0, 68, 0, 51, 0, 54, 0, 51, 0, 51, 0, 52, 0, 50, 0, 56, 0, 51, 0, 68, 0, 48, 0, 55, 0, 68, 0, 53, 0, 69, 0, 55, 0, 70, 0, 53, 0, 51, 0, 48, 0, 57, 0, 66, 0, 57, 0, 68, 0, 48, 0, 55, 0, 67, 0, 53, 0, 54, 0, 67, 0, 66, 0, 48, 0, 49, 0, 52, 0, 0, 0, 0, 0};
        
        // gsm multipart 1/2
        short[] gsm4 = {0, 0, 2, 172, 1, 0, 0, 0, 235, 3, 0, 0, 78, 1, 0, 0, 48, 0, 55, 0, 57, 0, 49, 0, 52, 0, 52, 0, 57, 0, 55, 0, 51, 0, 55, 0, 48, 0, 49, 0, 57, 0, 48, 0, 51, 0, 55, 0, 54, 0, 52, 0, 48, 0, 99, 0, 57, 0, 49, 0, 52, 0, 52, 0, 56, 0, 55, 0, 48, 0, 48, 0, 48, 0, 48, 0, 49, 0, 50, 0, 55, 0, 50, 0, 48, 0, 48, 0, 48, 0, 48, 0, 49, 0, 49, 0, 50, 0, 49, 0, 52, 0, 49, 0, 52, 0, 48, 0, 55, 0, 48, 0, 56, 0, 52, 0, 48, 0, 48, 0, 97, 0, 48, 0, 48, 0, 53, 0, 48, 0, 48, 0, 48, 0, 51, 0, 54, 0, 48, 0, 48, 0, 50, 0, 48, 0, 49, 0, 56, 0, 50, 0, 54, 0, 101, 0, 100, 0, 48, 0, 55, 0, 98, 0, 52, 0, 99, 0, 50, 0, 102, 0, 100, 0, 51, 0, 52, 0, 49, 0, 101, 0, 57, 0, 51, 0, 57, 0, 50, 0, 56, 0, 48, 0, 99, 0, 97, 0, 97, 0, 98, 0, 98, 0, 100, 0, 51, 0, 55, 0, 52, 0, 100, 0, 48, 0, 100, 0, 98, 0, 48, 0, 99, 0, 50, 0, 50, 0, 97, 0, 55, 0, 99, 0, 102, 0, 54, 0, 57, 0, 55, 0, 97, 0, 57, 0, 56, 0, 48, 0, 100, 0, 52, 0, 97, 0, 98, 0, 98, 0, 99, 0, 100, 0, 54, 0, 102, 0, 55, 0, 57, 0, 51, 0, 98, 0, 52, 0, 99, 0, 52, 0, 102, 0, 98, 0, 102, 0, 100, 0, 100, 0, 97, 0, 48, 0, 98, 0, 52, 0, 49, 0, 98, 0, 51, 0, 52, 0, 55, 0, 101, 0, 98, 0, 55, 0, 101, 0, 49, 0, 55, 0, 53, 0, 55, 0, 97, 0, 100, 0, 97, 0, 55, 0, 100, 0, 48, 0, 54, 0, 56, 0, 53, 0, 100, 0, 100, 0, 54, 0, 52, 0, 49, 0, 48, 0, 98, 0, 100, 0, 99, 0, 99, 0, 50, 0, 101, 0, 56, 0, 102, 0, 100, 0, 102, 0, 101, 0, 100, 0, 55, 0, 54, 0, 100, 0, 100, 0, 57, 0, 100, 0, 49, 0, 101, 0, 56, 0, 55, 0, 101, 0, 57, 0, 101, 0, 57, 0, 98, 0, 55, 0, 55, 0, 98, 0, 48, 0, 101, 0, 97, 0, 50, 0, 97, 0, 51, 0, 99, 0, 51, 0, 55, 0, 52, 0, 100, 0, 48, 0, 102, 0, 56, 0, 101, 0, 100, 0, 57, 0, 101, 0, 97, 0, 55, 0, 101, 0, 55, 0, 102, 0, 52, 0, 51, 0, 57, 0, 101, 0, 56, 0, 54, 0, 100, 0, 48, 0, 54, 0, 57, 0, 53, 0, 100, 0, 51, 0, 54, 0, 55, 0, 51, 0, 52, 0, 49, 0, 100, 0, 50, 0, 52, 0, 52, 0, 101, 0, 100, 0, 51, 0, 101, 0, 55, 0, 50, 0, 101, 0, 49, 0, 48, 0, 49, 0, 53, 0, 53, 0, 100, 0, 48, 0, 54, 0, 100, 0, 49, 0, 99, 0, 98, 0, 102, 0, 50, 0, 51, 0, 54, 0, 50, 0, 56, 0, 51, 0, 100, 0, 48, 0, 55, 0, 98, 0, 100, 0, 99, 0, 100, 0, 102, 0, 52, 0, 98, 0, 50, 0, 49, 0, 98, 0, 53, 0, 52, 0, 57, 0, 102, 0, 57, 0, 55, 0, 99, 0, 57, 0, 97, 0, 48, 0, 51, 0, 98, 0, 98, 0, 97, 0, 101, 0, 99, 0, 48, 0, 54, 0, 100, 0, 49, 0, 100, 0, 49, 0, 54, 0, 53, 0, 49, 0, 48, 0, 98, 0, 100, 0, 50, 0, 99, 0, 54, 0, 102, 0, 56, 0, 51, 0, 99, 0, 52, 0, 55, 0, 57, 0, 55, 0, 97, 0, 49, 0, 57, 0, 100, 0, 52, 0, 52, 0, 101, 0, 57, 0, 102, 0, 100, 0, 49, 0, 0, 0, 0, 0};
        
        // gsm multipart 2/2
        short[] gsm5 = {0, 0, 1, 120, 1, 0, 0, 0, 235, 3, 0, 0, 180, 0, 0, 0, 48, 0, 55, 0, 57, 0, 49, 0, 52, 0, 52, 0, 57, 0, 55, 0, 51, 0, 55, 0, 48, 0, 49, 0, 57, 0, 48, 0, 51, 0, 55, 0, 52, 0, 52, 0, 48, 0, 99, 0, 57, 0, 49, 0, 52, 0, 52, 0, 56, 0, 55, 0, 48, 0, 48, 0, 48, 0, 48, 0, 49, 0, 50, 0, 55, 0, 50, 0, 48, 0, 48, 0, 48, 0, 48, 0, 49, 0, 49, 0, 50, 0, 49, 0, 52, 0, 49, 0, 52, 0, 48, 0, 55, 0, 48, 0, 56, 0, 52, 0, 48, 0, 48, 0, 52, 0, 56, 0, 48, 0, 53, 0, 48, 0, 48, 0, 48, 0, 51, 0, 54, 0, 48, 0, 48, 0, 50, 0, 48, 0, 50, 0, 101, 0, 56, 0, 50, 0, 48, 0, 55, 0, 49, 0, 49, 0, 57, 0, 49, 0, 52, 0, 54, 0, 101, 0, 56, 0, 98, 0, 100, 0, 51, 0, 101, 0, 55, 0, 102, 0, 97, 0, 98, 0, 98, 0, 51, 0, 101, 0, 54, 0, 55, 0, 56, 0, 49, 0, 99, 0, 50, 0, 55, 0, 51, 0, 49, 0, 48, 0, 49, 0, 100, 0, 53, 0, 100, 0, 57, 0, 54, 0, 57, 0, 55, 0, 52, 0, 49, 0, 101, 0, 57, 0, 51, 0, 57, 0, 99, 0, 56, 0, 102, 0, 100, 0, 48, 0, 54, 0, 99, 0, 100, 0, 101, 0, 57, 0, 54, 0, 49, 0, 51, 0, 55, 0, 51, 0, 57, 0, 50, 0, 99, 0, 50, 0, 55, 0, 56, 0, 51, 0, 99, 0, 99, 0, 54, 0, 102, 0, 51, 0, 57, 0, 56, 0, 56, 0, 56, 0, 101, 0, 50, 0, 101, 0, 56, 0, 51, 0, 101, 0, 54, 0, 54, 0, 57, 0, 55, 0, 100, 0, 49, 0, 57, 0, 102, 0, 52, 0, 51, 0, 54, 0, 56, 0, 51, 0, 101, 0, 56, 0, 101, 0, 56, 0, 51, 0, 50, 0, 52, 0, 56, 0, 57, 0, 99, 0, 97, 0, 55, 0, 57, 0, 55, 0, 53, 0, 100, 0, 0, 0, 0, 0};
        
        printSms(gsm1);
        printSms(gsm2);
        printSms(gsm3);
        printSms(gsm4);
        printSms(gsm5);
        
        // single part
        short[] cdma1 = {0, 0, 0, 228, 1, 0, 0, 0, 252, 3, 0, 0, 2, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 8, 0, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 5, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 9, 0, 0, 0, 7, 0, 0, 0, 7, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 16, 0, 0, 0, 2, 0, 0, 0, 160, 0, 0, 0, 1, 0, 0, 0, 18, 0, 0, 0, 16, 0, 0, 0, 148, 0, 0, 0, 158, 0, 0, 0, 137, 0, 0, 0, 249, 0, 0, 0, 160, 0, 0, 0, 211, 0, 0, 0, 187, 0, 0, 0, 30, 0, 0, 0, 253, 0, 0, 0, 186, 0, 0, 0, 119, 0, 0, 0, 103, 0, 0, 0, 65, 0, 0, 0, 211, 0, 0, 0, 47, 0, 0, 0, 142, 0, 0, 0, 128, 0, 0, 0, 3, 0, 0, 0, 6, 0, 0, 0, 17, 0, 0, 0, 9, 0, 0, 0, 3, 0, 0, 0, 32, 0, 0, 0, 49, 0, 0, 0, 86, 0, 0, 0};
        // multipart 1/3
        short[] cdma2 = {0, 0, 2, 236, 1, 0, 0, 0, 252, 3, 0, 0, 2, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 8, 0, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 5, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 9, 0, 0, 0, 7, 0, 0, 0, 7, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 163, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 136, 0, 0, 0, 20, 0, 0, 0, 204, 0, 0, 0, 140, 0, 0, 0, 188, 0, 0, 0, 178, 0, 0, 0, 167, 0, 0, 0, 230, 0, 0, 0, 131, 0, 0, 0, 102, 0, 0, 0, 253, 0, 0, 0, 217, 0, 0, 0, 208, 0, 0, 0, 116, 0, 0, 0, 203, 0, 0, 0, 227, 0, 0, 0, 162, 0, 0, 0, 228, 0, 0, 0, 17, 0, 0, 0, 186, 0, 0, 0, 230, 0, 0, 0, 211, 0, 0, 0, 175, 0, 0, 0, 54, 0, 0, 0, 188, 0, 0, 0, 218, 0, 0, 0, 244, 0, 0, 0, 228, 0, 0, 0, 204, 0, 0, 0, 131, 0, 0, 0, 30, 0, 0, 0, 157, 0, 0, 0, 61, 0, 0, 0, 114, 0, 0, 0, 245, 0, 0, 0, 229, 0, 0, 0, 153, 0, 0, 0, 6, 0, 0, 0, 62, 0, 0, 0, 185, 0, 0, 0, 179, 0, 0, 0, 32, 0, 0, 0, 207, 0, 0, 0, 167, 0, 0, 0, 38, 0, 0, 0, 173, 0, 0, 0, 89, 0, 0, 0, 181, 0, 0, 0, 100, 0, 0, 0, 201, 0, 0, 0, 215, 0, 0, 0, 38, 0, 0, 0, 77, 0, 0, 0, 200, 0, 0, 0, 52, 0, 0, 0, 120, 0, 0, 0, 213, 0, 0, 0, 171, 0, 0, 0, 78, 0, 0, 0, 109, 0, 0, 0, 40, 0, 0, 0, 51, 0, 0, 0, 234, 0, 0, 0, 201, 0, 0, 0, 215, 0, 0, 0, 191, 0, 0, 0, 94, 0, 0, 0, 253, 0, 0, 0, 114, 0, 0, 0, 32, 0, 0, 0, 209, 0, 0, 0, 235, 0, 0, 0, 87, 0, 0, 0, 141, 0, 0, 0, 94, 0, 0, 0, 53, 0, 0, 0, 100, 0, 0, 0, 65, 0, 0, 0, 163, 0, 0, 0, 198, 0, 0, 0, 172, 0, 0, 0, 154, 0, 0, 0, 115, 0, 0, 0, 105, 0, 0, 0, 223, 0, 0, 0, 155, 0, 0, 0, 94, 0, 0, 0, 189, 0, 0, 0, 168, 0, 0, 0, 61, 0, 0, 0, 117, 0, 0, 0, 201, 0, 0, 0, 215, 0, 0, 0, 86, 0, 0, 0, 68, 0, 0, 0, 24, 0, 0, 0, 189, 0, 0, 0, 106, 0, 0, 0, 201, 0, 0, 0, 147, 0, 0, 0, 34, 0, 0, 0, 13, 0, 0, 0, 30, 0, 0, 0, 53, 0, 0, 0, 102, 0, 0, 0, 65, 0, 0, 0, 171, 0, 0, 0, 54, 0, 0, 0, 174, 0, 0, 0, 122, 0, 0, 0, 185, 0, 0, 0, 245, 0, 0, 0, 239, 0, 0, 0, 215, 0, 0, 0, 42, 0, 0, 0, 13, 0, 0, 0, 30, 0, 0, 0, 180, 0, 0, 0, 103, 0, 0, 0, 205, 0, 0, 0, 163, 0, 0, 0, 86, 0, 0, 0, 77, 0, 0, 0, 8, 0, 0, 0, 53, 0, 0, 0, 105, 0, 0, 0, 207, 0, 0, 0, 147, 0, 0, 0, 78, 0, 0, 0, 254, 0, 0, 0, 153, 0, 0, 0, 241, 0, 0, 0, 118, 0, 0, 0, 212, 0, 0, 0, 131, 0, 0, 0, 70, 0, 0, 0, 96, 0, 0, 0, 3, 0, 0, 0, 6, 0, 0, 0, 17, 0, 0, 0, 9, 0, 0, 0, 19, 0, 0, 0, 25, 0, 0, 0, 21, 0, 0, 0, 34, 0, 0, 0, 8, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 7, 0, 0, 0, 5, 0, 0, 0, 65, 0, 0, 0, 146, 0, 0, 0, 162, 0, 0, 0, 75, 0, 0, 0, 186, 0, 0, 0, 0, 0, 0, 0};
        // multipart 2/3
        short[] cdma3 = {0, 0, 2, 236, 1, 0, 0, 0, 252, 3, 0, 0, 2, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 8, 0, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 5, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 9, 0, 0, 0, 7, 0, 0, 0, 7, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 163, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 136, 0, 0, 0, 20, 0, 0, 0, 207, 0, 0, 0, 140, 0, 0, 0, 90, 0, 0, 0, 179, 0, 0, 0, 231, 0, 0, 0, 92, 0, 0, 0, 130, 0, 0, 0, 70, 0, 0, 0, 124, 0, 0, 0, 154, 0, 0, 0, 53, 0, 0, 0, 104, 0, 0, 0, 212, 0, 0, 0, 131, 0, 0, 0, 70, 0, 0, 0, 108, 0, 0, 0, 154, 0, 0, 0, 53, 0, 0, 0, 32, 0, 0, 0, 197, 0, 0, 0, 163, 0, 0, 0, 54, 0, 0, 0, 141, 0, 0, 0, 89, 0, 0, 0, 180, 0, 0, 0, 117, 0, 0, 0, 205, 0, 0, 0, 159, 0, 0, 0, 66, 0, 0, 0, 12, 0, 0, 0, 89, 0, 0, 0, 179, 0, 0, 0, 234, 0, 0, 0, 237, 0, 0, 0, 159, 0, 0, 0, 86, 0, 0, 0, 140, 0, 0, 0, 221, 0, 0, 0, 80, 0, 0, 0, 106, 0, 0, 0, 209, 0, 0, 0, 155, 0, 0, 0, 22, 0, 0, 0, 46, 0, 0, 0, 104, 0, 0, 0, 52, 0, 0, 0, 102, 0, 0, 0, 233, 0, 0, 0, 215, 0, 0, 0, 54, 0, 0, 0, 45, 0, 0, 0, 72, 0, 0, 0, 53, 0, 0, 0, 107, 0, 0, 0, 213, 0, 0, 0, 171, 0, 0, 0, 54, 0, 0, 0, 189, 0, 0, 0, 57, 0, 0, 0, 60, 0, 0, 0, 228, 0, 0, 0, 65, 0, 0, 0, 167, 0, 0, 0, 38, 0, 0, 0, 156, 0, 0, 0, 218, 0, 0, 0, 115, 0, 0, 0, 107, 0, 0, 0, 199, 0, 0, 0, 181, 0, 0, 0, 114, 0, 0, 0, 9, 0, 0, 0, 122, 0, 0, 0, 242, 0, 0, 0, 101, 0, 0, 0, 235, 0, 0, 0, 227, 0, 0, 0, 142, 0, 0, 0, 31, 0, 0, 0, 57, 0, 0, 0, 52, 0, 0, 0, 102, 0, 0, 0, 199, 0, 0, 0, 147, 0, 0, 0, 206, 0, 0, 0, 68, 0, 0, 0, 25, 0, 0, 0, 244, 0, 0, 0, 103, 0, 0, 0, 231, 0, 0, 0, 211, 0, 0, 0, 71, 0, 0, 0, 140, 0, 0, 0, 200, 0, 0, 0, 54, 0, 0, 0, 108, 0, 0, 0, 225, 0, 0, 0, 231, 0, 0, 0, 54, 0, 0, 0, 100, 0, 0, 0, 26, 0, 0, 0, 185, 0, 0, 0, 245, 0, 0, 0, 239, 0, 0, 0, 231, 0, 0, 0, 191, 0, 0, 0, 158, 0, 0, 0, 254, 0, 0, 0, 114, 0, 0, 0, 249, 0, 0, 0, 212, 0, 0, 0, 131, 0, 0, 0, 182, 0, 0, 0, 124, 0, 0, 0, 220, 0, 0, 0, 249, 0, 0, 0, 232, 0, 0, 0, 205, 0, 0, 0, 159, 0, 0, 0, 18, 0, 0, 0, 13, 0, 0, 0, 218, 0, 0, 0, 179, 0, 0, 0, 103, 0, 0, 0, 209, 0, 0, 0, 161, 0, 0, 0, 6, 0, 0, 0, 140, 0, 0, 0, 153, 0, 0, 0, 181, 0, 0, 0, 106, 0, 0, 0, 65, 0, 0, 0, 159, 0, 0, 0, 14, 0, 0, 0, 64, 0, 0, 0, 3, 0, 0, 0, 6, 0, 0, 0, 17, 0, 0, 0, 9, 0, 0, 0, 19, 0, 0, 0, 25, 0, 0, 0, 21, 0, 0, 0, 35, 0, 0, 0, 8, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 7, 0, 0, 0, 5, 0, 0, 0, 65, 0, 0, 0, 146, 0, 0, 0, 162, 0, 0, 0, 75, 0, 0, 0, 186, 0, 0, 0, 0, 0, 0, 0};
        // multipart 3/3
        short[] cdma4 = {0, 0, 0, 228, 1, 0, 0, 0, 252, 3, 0, 0, 2, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 8, 0, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 5, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 9, 0, 0, 0, 7, 0, 0, 0, 7, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 6, 0, 0, 0, 16, 0, 0, 0, 46, 0, 0, 0, 124, 0, 0, 0, 189, 0, 0, 0, 57, 0, 0, 0, 160, 0, 0, 0, 3, 0, 0, 0, 6, 0, 0, 0, 17, 0, 0, 0, 9, 0, 0, 0, 19, 0, 0, 0, 25, 0, 0, 0, 21, 0, 0, 0, 36, 0, 0, 0, 8, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 7, 0, 0, 0, 5, 0, 0, 0, 65, 0, 0, 0, 146, 0, 0, 0, 162, 0, 0, 0, 75, 0, 0, 0, 186, 0, 0, 0, 0, 0, 0, 0 };
        
        // mms 1/2
        short[] cdma5 = {0, 0, 1, 20, 1, 0, 0, 0, 252, 3, 0, 0, 4, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 28, 0, 0, 0, 178, 0, 0, 0, 64, 0, 0, 0, 1, 0, 0, 0, 44, 0, 0, 0, 1, 0, 0, 0, 80, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 9, 0, 0, 0, 129, 0, 0, 0, 137, 0, 0, 0, 146, 0, 0, 0, 249, 0, 0, 0, 177, 0, 0, 0, 193, 0, 0, 0, 177, 0, 0, 0, 145, 0, 0, 0, 155, 0, 0, 0, 9, 0, 0, 0, 145, 0, 0, 0, 145, 0, 0, 0, 161, 0, 0, 0, 145, 0, 0, 0, 187, 0, 0, 0, 49, 0, 0, 0, 185, 0, 0, 0, 185, 0, 0, 0, 161, 0, 0, 0, 202, 0, 0, 0, 249, 0, 0, 0, 129, 0, 0, 0, 51, 0, 0, 0, 171, 0, 0, 0, 155, 0, 0, 0, 43, 0, 0, 0, 146, 0, 0, 0, 75, 0, 0, 0, 33, 0, 0, 0, 233, 0, 0, 0, 193, 0, 0, 0, 201, 0, 0, 0, 177, 0, 0, 0, 185, 0, 0, 0, 153, 0, 0, 0, 161, 0, 0, 0, 177, 0, 0, 0, 144, 0, 0, 0, 0, 0, 0, 0};
        // mms 2/2
        short[] cdma6 = {0, 0, 2, 132, 1, 0, 0, 0, 252, 3, 0, 0, 4, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 28, 0, 0, 0, 178, 0, 0, 0, 64, 0, 0, 0, 1, 0, 0, 0, 136, 0, 0, 0, 4, 0, 0, 0, 48, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 156, 0, 0, 0, 64, 0, 0, 0, 92, 0, 0, 0, 32, 0, 0, 0, 8, 0, 0, 0, 48, 0, 0, 0, 69, 0, 0, 0, 244, 0, 0, 0, 104, 0, 0, 0, 12, 0, 0, 0, 221, 0, 0, 0, 124, 0, 0, 0, 37, 0, 0, 0, 164, 0, 0, 0, 36, 0, 0, 0, 100, 0, 0, 0, 20, 0, 0, 0, 193, 0, 0, 0, 129, 0, 0, 0, 137, 0, 0, 0, 146, 0, 0, 0, 249, 0, 0, 0, 177, 0, 0, 0, 193, 0, 0, 0, 177, 0, 0, 0, 145, 0, 0, 0, 155, 0, 0, 0, 9, 0, 0, 0, 145, 0, 0, 0, 145, 0, 0, 0, 161, 0, 0, 0, 145, 0, 0, 0, 187, 0, 0, 0, 49, 0, 0, 0, 185, 0, 0, 0, 185, 0, 0, 0, 161, 0, 0, 0, 202, 0, 0, 0, 249, 0, 0, 0, 128, 0, 0, 0, 4, 0, 0, 0, 108, 0, 0, 0, 132, 0, 0, 0, 72, 0, 0, 0, 180, 0, 0, 0, 1, 0, 0, 0, 193, 0, 0, 0, 153, 0, 0, 0, 145, 0, 0, 0, 169, 0, 0, 0, 161, 0, 0, 0, 161, 0, 0, 0, 201, 0, 0, 0, 185, 0, 0, 0, 185, 0, 0, 0, 161, 0, 0, 0, 122, 0, 0, 0, 162, 0, 0, 0, 202, 0, 0, 0, 130, 0, 0, 0, 41, 0, 0, 0, 234, 0, 0, 0, 130, 0, 0, 0, 98, 0, 0, 0, 106, 0, 0, 0, 112, 0, 0, 0, 4, 0, 0, 0, 178, 0, 0, 0, 115, 0, 0, 0, 43, 0, 0, 0, 185, 0, 0, 0, 2, 0, 0, 0, 107, 0, 0, 0, 43, 0, 0, 0, 155, 0, 0, 0, 155, 0, 0, 0, 11, 0, 0, 0, 59, 0, 0, 0, 40, 0, 0, 0, 4, 0, 0, 0, 84, 0, 0, 0, 4, 0, 0, 0, 112, 0, 0, 0, 24, 0, 0, 0, 30, 0, 0, 0, 131, 0, 0, 0, 244, 0, 0, 0, 64, 0, 0, 0, 52, 0, 0, 0, 0, 0, 0, 0, 34, 0, 0, 0, 117, 0, 0, 0, 248, 0, 0, 0, 214, 0, 0, 0, 252, 0, 0, 0, 27, 0, 0, 0, 67, 0, 0, 0, 163, 0, 0, 0, 163, 0, 0, 0, 129, 0, 0, 0, 209, 0, 0, 0, 121, 0, 0, 0, 123, 0, 0, 0, 107, 0, 0, 0, 107, 0, 0, 0, 153, 0, 0, 0, 115, 0, 0, 0, 155, 0, 0, 0, 131, 0, 0, 0, 147, 0, 0, 0, 75, 0, 0, 0, 115, 0, 0, 0, 163, 0, 0, 0, 131, 0, 0, 0, 27, 0, 0, 0, 153, 0, 0, 0, 115, 0, 0, 0, 27, 0, 0, 0, 123, 0, 0, 0, 105, 0, 0, 0, 209, 0, 0, 0, 193, 0, 0, 0, 129, 0, 0, 0, 121, 0, 0, 0, 251, 0, 0, 0, 107, 0, 0, 0, 155, 0, 0, 0, 59, 0, 0, 0, 75, 0, 0, 0, 33, 0, 0, 0, 232, 0, 0, 0};
        
        // multipart 1/2
        short[] cdma7 = {0, 0, 3, 100, 1, 0, 0, 0, 252, 3, 0, 0, 5, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 55, 0, 0, 0, 56, 0, 0, 0, 49, 0, 0, 0, 50, 0, 0, 0, 56, 0, 0, 0, 49, 0, 0, 0, 52, 0, 0, 0, 52, 0, 0, 0, 49, 0, 0, 0, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 193, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 16, 0, 0, 0, 1, 0, 0, 0, 40, 0, 0, 0, 3, 0, 0, 0, 6, 0, 0, 0, 17, 0, 0, 0, 18, 0, 0, 0, 20, 0, 0, 0, 3, 0, 0, 0, 82, 0, 0, 0, 57, 0, 0, 0, 14, 0, 0, 0, 14, 0, 0, 0, 128, 0, 0, 0, 10, 0, 0, 0, 55, 0, 0, 0, 56, 0, 0, 0, 49, 0, 0, 0, 50, 0, 0, 0, 56, 0, 0, 0, 49, 0, 0, 0, 52, 0, 0, 0, 52, 0, 0, 0, 49, 0, 0, 0, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 162, 0, 0, 0, 77, 0, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 31, 0, 0, 0, 32, 0, 0, 0, 16, 0, 0, 0, 12, 0, 0, 0, 19, 0, 0, 0, 118, 0, 0, 0, 131, 0, 0, 0, 218, 0, 0, 0, 97, 0, 0, 0, 126, 0, 0, 0, 154, 0, 0, 0, 15, 0, 0, 0, 73, 0, 0, 0, 201, 0, 0, 0, 64, 0, 0, 0, 101, 0, 0, 0, 85, 0, 0, 0, 222, 0, 0, 0, 155, 0, 0, 0, 166, 0, 0, 0, 134, 0, 0, 0, 216, 0, 0, 0, 97, 0, 0, 0, 21, 0, 0, 0, 62, 0, 0, 0, 123, 0, 0, 0, 75, 0, 0, 0, 212, 0, 0, 0, 192, 0, 0, 0, 106, 0, 0, 0, 85, 0, 0, 0, 222, 0, 0, 0, 107, 0, 0, 0, 123, 0, 0, 0, 201, 0, 0, 0, 218, 0, 0, 0, 98, 0, 0, 0, 125, 0, 0, 0, 254, 0, 0, 0, 237, 0, 0, 0, 5, 0, 0, 0, 160, 0, 0, 0, 217, 0, 0, 0, 163, 0, 0, 0, 245, 0, 0, 0, 191, 0, 0, 0, 11, 0, 0, 0, 171, 0, 0, 0, 214, 0, 0, 0, 211, 0, 0, 0, 232, 0, 0, 0, 52, 0, 0, 0, 46, 0, 0, 0, 235, 0, 0, 0, 32, 0, 0, 0, 133, 0, 0, 0, 238, 0, 0, 0, 97, 0, 0, 0, 116, 0, 0, 0, 126, 0, 0, 0, 255, 0, 0, 0, 107, 0, 0, 0, 182, 0, 0, 0, 236, 0, 0, 0, 232, 0, 0, 0, 244, 0, 0, 0, 63, 0, 0, 0, 79, 0, 0, 0, 77, 0, 0, 0, 187, 0, 0, 0, 216, 0, 0, 0, 117, 0, 0, 0, 21, 0, 0, 0, 30, 0, 0, 0, 27, 0, 0, 0, 166, 0, 0, 0, 135, 0, 0, 0, 199, 0, 0, 0, 108, 0, 0, 0, 245, 0, 0, 0, 63, 0, 0, 0, 63, 0, 0, 0, 161, 0, 0, 0, 207, 0, 0, 0, 67, 0, 0, 0, 104, 0, 0, 0, 52, 0, 0, 0, 174, 0, 0, 0, 155, 0, 0, 0, 57, 0, 0, 0, 160, 0, 0, 0, 233, 0, 0, 0, 34, 0, 0, 0, 118, 0, 0, 0, 159, 0, 0, 0, 57, 0, 0, 0, 112, 0, 0, 0, 128, 0, 0, 0, 170, 0, 0, 0, 232, 0, 0, 0, 54, 0, 0, 0, 142, 0, 0, 0, 95, 0, 0, 0, 145, 0, 0, 0, 177, 0, 0, 0, 65, 0, 0, 0, 232, 0, 0, 0, 61, 0, 0, 0, 238, 0, 0, 0, 111, 0, 0, 0, 165, 0, 0, 0, 144, 0, 0, 0, 218, 0, 0, 0, 164, 0, 0, 0, 252, 0, 0, 0, 190, 0, 0, 0, 77, 0, 0, 0, 1, 0, 0, 0, 221, 0, 0, 0, 215, 0, 0, 0, 96, 0, 0, 0, 54, 0, 0, 0, 142, 0, 0, 0, 139, 0, 0, 0, 40, 0, 0, 0, 133, 0, 0, 0, 233, 0, 0, 0, 99, 0, 0, 0, 124, 0, 0, 0, 30, 0, 0, 0, 35, 0, 0, 0, 203, 0, 0, 0, 208, 0, 0, 0, 206, 0, 0, 0, 162, 0, 0, 0, 116, 0, 0, 0, 254, 0, 0, 0, 136, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        short[] cdma8 = {0, 0, 2, 4, 1, 0, 0, 0, 252, 3, 0, 0, 5, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 55, 0, 0, 0, 56, 0, 0, 0, 49, 0, 0, 0, 50, 0, 0, 0, 56, 0, 0, 0, 49, 0, 0, 0, 52, 0, 0, 0, 52, 0, 0, 0, 49, 0, 0, 0, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 105, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 16, 0, 0, 0, 1, 0, 0, 0, 56, 0, 0, 0, 3, 0, 0, 0, 6, 0, 0, 0, 17, 0, 0, 0, 18, 0, 0, 0, 20, 0, 0, 0, 3, 0, 0, 0, 82, 0, 0, 0, 64, 0, 0, 0, 14, 0, 0, 0, 14, 0, 0, 0, 128, 0, 0, 0, 10, 0, 0, 0, 55, 0, 0, 0, 56, 0, 0, 0, 49, 0, 0, 0, 50, 0, 0, 0, 56, 0, 0, 0, 49, 0, 0, 0, 52, 0, 0, 0, 52, 0, 0, 0, 49, 0, 0, 0, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 74, 0, 0, 0, 74, 0, 0, 0, 64, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 31, 0, 0, 0, 32, 0, 0, 0, 16, 0, 0, 0, 23, 0, 0, 0, 65, 0, 0, 0, 3, 0, 0, 0, 136, 0, 0, 0, 200, 0, 0, 0, 163, 0, 0, 0, 116, 0, 0, 0, 94, 0, 0, 0, 159, 0, 0, 0, 63, 0, 0, 0, 213, 0, 0, 0, 217, 0, 0, 0, 243, 0, 0, 0, 60, 0, 0, 0, 14, 0, 0, 0, 19, 0, 0, 0, 152, 0, 0, 0, 128, 0, 0, 0, 234, 0, 0, 0, 236, 0, 0, 0, 180, 0, 0, 0, 186, 0, 0, 0, 15, 0, 0, 0, 73, 0, 0, 0, 206, 0, 0, 0, 71, 0, 0, 0, 232, 0, 0, 0, 54, 0, 0, 0, 111, 0, 0, 0, 75, 0, 0, 0, 9, 0, 0, 0, 185, 0, 0, 0, 201, 0, 0, 0, 97, 0, 0, 0, 60, 0, 0, 0, 30, 0, 0, 0, 99, 0, 0, 0, 121, 0, 0, 0, 204, 0, 0, 0, 68, 0, 0, 0, 113, 0, 0, 0, 116, 0, 0, 0, 31, 0, 0, 0, 51, 0, 0, 0, 75, 0, 0, 0, 232, 0, 0, 0, 207, 0, 0, 0, 161, 0, 0, 0, 180, 0, 0, 0, 31, 0, 0, 0, 71, 0, 0, 0, 65, 0, 0, 0, 146, 0, 0, 0, 68, 0, 0, 0, 229, 0, 0, 0, 60, 0, 0, 0, 186, 0, 0, 0, 232, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        
        printSms(cdma1);
        printSms(cdma2);
        printSms(cdma3);
        printSms(cdma4);
        printSms(cdma5);
        printSms(cdma6);
        printSms(cdma7);
        printSms(cdma8);
    }
    
    private void printSms(short[] data) {
    	if (LOGV) Log.v(TAG, "printSms # ENTER");
    	
    	byte[] byteData = getBytes(data);
    	Parcel p = Parcel.obtain();
    	p.unmarshall(byteData, 0, data.length);
    	
    	SmsInfo smsInfo = FilterSmsHelper.getSmsInfo(p);
    	if (LOGV) Log.v(TAG, String.format("smsInfo: %s", smsInfo));
        
        if (LOGV) Log.v(TAG, "printSms # EXIT");
    }
    
	private byte[] getBytes(short[] rawData) {
    	if (rawData == null) return null;
    	
		ByteArrayBuffer buffer = new ByteArrayBuffer(rawData.length);
    	for (short x : rawData) {
    		buffer.append((byte) x);
    	}
    	
    	return buffer.toByteArray();
    }
}
