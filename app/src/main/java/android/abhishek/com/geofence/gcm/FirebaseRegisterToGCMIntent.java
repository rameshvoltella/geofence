package android.abhishek.com.geofence.gcm;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Abhishek on 3/6/2017.
 */
public class FirebaseRegisterToGCMIntent extends IntentService {

	private static final String TAG = "FirebaseRegisterToGCMIntent";

	public FirebaseRegisterToGCMIntent() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(TAG, "onHandleIntent()");
		String refreshedToken = FirebaseInstanceId.getInstance().getToken();

		Log.i(TAG, "GCM Registration Token: " + refreshedToken);

		sendRegistrationToServer(refreshedToken);

		final Context context = getApplicationContext();
		final CharSequence text = "Refresh Token :"+ refreshedToken;
		final int duration = Toast.LENGTH_SHORT;

		Handler mHandler = new Handler(getMainLooper());
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(context, text, duration);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setDuration(Toast.LENGTH_LONG);
				toast.show();
			}
		});


	}

	/**
	 * Persist token to third-party servers.
	 *
	 * Modify this method to associate the user's FCM InstanceID token with any server-side account
	 * maintained by your application.
	 *
	 * @param token The new token.
	 */
	private void sendRegistrationToServer(String token) {
		// Add custom implementation, as needed.
	}

}
