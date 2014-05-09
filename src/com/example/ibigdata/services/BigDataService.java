package com.example.ibigdata.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseObject;

public class BigDataService extends Service {

	public static final String TAG = "ibigdata";

	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			ParseObject usage = new ParseObject("ScreenUsage");
			usage.put("ts", System.currentTimeMillis());
			if (action.equals(Intent.ACTION_SCREEN_OFF)) {
				Log.d(TAG, "screen off " + System.currentTimeMillis());
				usage.put("val", 0);
			} else if (action.equals(Intent.ACTION_SCREEN_ON)) {
				Log.d(TAG, "screen on " + System.currentTimeMillis());
				usage.put("val", 1);
			}
			usage.saveEventually();
		}
	};

	@Override
	public void onCreate() {
		Parse.initialize(this, "pQZLIr5NXvErXQCAvdW30K9WPHZ92afu7FHdDgZM",
				"cEb0UIuQc421gFYQexijCjU0xsn3VpJAXbywVxv8");
	};

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(receiver, filter);
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
