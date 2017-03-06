package android.abhishek.com.geofence.gcm;


import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Abhishek on 3/6/2017.
 */
public class FirebaseIDService extends FirebaseInstanceIdService {
	private static final String TAG = "FirebaseIDService";

	@Override
	public void onTokenRefresh() {
		Log.i(TAG, "onTokenRefresh()");
		Intent registerToGCMIntent = new Intent(this, FirebaseRegisterToGCMIntent.class);
		startService(registerToGCMIntent);
	}

}

