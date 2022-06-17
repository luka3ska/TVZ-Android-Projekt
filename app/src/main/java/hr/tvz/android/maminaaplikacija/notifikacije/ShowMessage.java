package hr.tvz.android.maminaaplikacija.notifikacije;

import android.app.Notification;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.RemoteMessage;

import hr.tvz.android.maminaaplikacija.R;
import hr.tvz.android.maminaaplikacija.main.MainActivity;

public class ShowMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);

        RemoteMessage remoteMessage = getIntent().getExtras().getParcelable("poruka");
        ((TextView)findViewById(R.id.tvOd)).setText(remoteMessage.getFrom());
        ((TextView)findViewById(R.id.tvPoruka)).setText(remoteMessage.getNotification().getBody());

        Notification.Builder builder = new Notification.Builder(
                getApplicationContext(), MainActivity.MOJ_KANAL)
                .setSmallIcon(android.R.drawable.ic_menu_save)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_send))
                .setContentTitle("My notification")
                .setContentText("Hello world!")
                .setSubText("20")
                .setTicker("New notification has arrived");

        builder.build();
    }


}