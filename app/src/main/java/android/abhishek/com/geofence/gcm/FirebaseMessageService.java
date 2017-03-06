package android.abhishek.com.geofence.gcm;

import android.abhishek.com.geofence.GeofencingActivity;
import android.abhishek.com.geofence.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Abhishek on 3/6/2017.
 */
public class FirebaseMessageService extends FirebaseMessagingService {

	private static final String TAG = "FCM Service";

	public static final int GEOFENCE_NOTIFICATION_ID = 0;

	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {

		// This callback is called when push notification is received
		Log.d(TAG, "From: " + remoteMessage.getFrom());
		Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
		String messageBody = remoteMessage.getNotification().getBody();
		sendNotification(messageBody);
	}

	private void sendNotification( String msg ) {
		Log.i(TAG, "sendNotification: " + msg );

		Intent notificationIntent = GeofencingActivity.makeNotificationIntent(
				getApplicationContext(), msg
		);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(GeofencingActivity.class);
		stackBuilder.addNextIntent(notificationIntent);
		PendingIntent notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


		// Creating and sending Notification
		NotificationManager notificatioMng =
				(NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );
		notificatioMng.notify(
				GEOFENCE_NOTIFICATION_ID,
				createNotification(msg, notificationPendingIntent));

	}

	// Create notification
	private Notification createNotification(String msg, PendingIntent notificationPendingIntent) {
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
		notificationBuilder
				.setSmallIcon(R.mipmap.ic_launcher)
				.setColor(Color.RED)
				.setContentTitle("Push notification")
				.setContentText(msg)
				.setContentIntent(notificationPendingIntent)
				.setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
				.setAutoCancel(true);
		return notificationBuilder.build();
	}


}
