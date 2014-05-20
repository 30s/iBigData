package com.example.ibigdata.receivers;

import com.example.ibigdata.services.BigDataService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompleteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent dataService = new Intent(context, BigDataService.class);
		context.startService(dataService);
	}

}
