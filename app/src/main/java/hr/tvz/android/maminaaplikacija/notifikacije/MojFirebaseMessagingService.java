package hr.tvz.android.maminaaplikacija.notifikacije;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import hr.tvz.android.maminaaplikacija.main.MainActivity;

public class MojFirebaseMessagingService extends FirebaseMessagingService{
    private static final String TAG = " FCM Service";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //Ovdje je potrebno hendlati poruke
        //Poslati broadcast, podignuti aktivnost, startati servis ili sl
        Intent intent = new Intent(getApplicationContext(), ShowMessage.class);
        intent.putExtra("poruka", remoteMessage);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        posaljiNotifikaciju();

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "To: " + remoteMessage.getTo());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());


    }

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "Refresh token: " + token);
        sendRegistrationToServer(token);
    }

    public void sendRegistrationToServer(String token) {
        // Ovdje je potrebno poslati token na vlastiti server kako bi se točno znalo kamo poruka mora doći
    }

    public void posaljiNotifikaciju() {
        Notification.Builder builder = new Notification.Builder(
                getApplicationContext(), MainActivity.MOJ_KANAL)
                .setSmallIcon(android.R.drawable.ic_menu_save)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_send))
                .setContentTitle("My notification")
                .setContentText("Hello world!")
                .setSubText("20")
                .setTicker("New notification has arrived");

        Intent resultIntent = new Intent(this, ShowMessage.class);
        resultIntent.putExtra("string", "Mogu se prenositi i podaci");
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
