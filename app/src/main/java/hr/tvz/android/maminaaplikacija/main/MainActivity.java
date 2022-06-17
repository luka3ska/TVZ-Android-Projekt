package hr.tvz.android.maminaaplikacija.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;

import hr.tvz.android.maminaaplikacija.popisnamirnica.PopisNamirnica;
import hr.tvz.android.maminaaplikacija.R;
import hr.tvz.android.maminaaplikacija.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static String MOJ_KANAL = "mojKanal";
    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mp = MediaPlayer.create(this, R.raw.music);



        Button pokreniRacunanje = (Button)binding.dugmeIzracunaj;
        pokreniRacunanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                izracunaj();
            }
        });

        Button pokreniPopisNamirnica = (Button)binding.dugmeLista;
        pokreniPopisNamirnica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PopisNamirnica.class));
            }
        });






        NotificationChannel channel = new NotificationChannel(MOJ_KANAL, "Ime kanala", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Opis kanala");
        NotificationManager notificationManager = (NotificationManager)(getSystemService(Context.NOTIFICATION_SERVICE));
        notificationManager.createNotificationChannel(channel);


        // Dohvat registracijskog tokena uređaja za Firebase
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Main activity", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult();

                        Log.d("Main activity", token);

                    }
                });

    }

    private void izracunaj() {
        Double struja, voda, strujaVoda, cijenaPoOsobi, badnjevicStanari, ivanovicStanari, tosicStanari,
                radeticStanari, civiticoStanari, zidaricStanari, crnkovicStanari, glavasStanari,
                dzaferovicStanari, markovskiStanari, triskaStanari, stanari;

        Integer  badnjevicDani, ivanovicDani, tosicDani, radeticDani, civiticoDani, zidaricDani, crnkovicDani,
                glavasDani, dzaferovicDani, markovskiDani, triskaDani;



        struja = Double.valueOf(binding.struja.getText().toString());
        voda = Double.valueOf(binding.voda.getText().toString());

        badnjevicStanari = Double.valueOf(binding.badnjevicStanari.getText().toString());
        ivanovicStanari = Double.valueOf(binding.ivanovicStanari.getText().toString());
        tosicStanari = Double.valueOf(binding.tosicStanari.getText().toString());
        radeticStanari = Double.valueOf(binding.radeticStanari.getText().toString());
        civiticoStanari = Double.valueOf(binding.civiticoStanari.getText().toString());
        zidaricStanari = Double.valueOf(binding.zidaricStanari.getText().toString());
        crnkovicStanari = Double.valueOf(binding.crnkovicStanari.getText().toString());
        glavasStanari = Double.valueOf(binding.glavasStanari.getText().toString());
        dzaferovicStanari = Double.valueOf(binding.dzaferovicStanari.getText().toString());
        markovskiStanari = Double.valueOf(binding.markovskiStanari.getText().toString());
        triskaStanari = Double.valueOf(binding.triskaStanari.getText().toString());

        badnjevicDani = Integer.valueOf(binding.badnjevicDani.getText().toString());
        ivanovicDani = Integer.valueOf(binding.ivanovicDani.getText().toString());
        tosicDani = Integer.valueOf(binding.tosicDani.getText().toString());
        radeticDani = Integer.valueOf(binding.radeticDani.getText().toString());
        civiticoDani = Integer.valueOf(binding.civiticoDani.getText().toString());
        zidaricDani = Integer.valueOf(binding.zidaricDani.getText().toString());
        crnkovicDani = Integer.valueOf(binding.crnkovicDani.getText().toString());
        glavasDani = Integer.valueOf(binding.glavasDani.getText().toString());
        dzaferovicDani = Integer.valueOf(binding.dzaferovicDani.getText().toString());
        markovskiDani = Integer.valueOf(binding.markovskiDani.getText().toString());
        triskaDani = Integer.valueOf(binding.triskaDani.getText().toString());


        System.out.println("Badnjevic dani " + badnjevicDani);

        badnjevicStanari = badnjevicStanari + pretvoriDane(badnjevicDani);
        ivanovicStanari = ivanovicStanari + pretvoriDane(ivanovicDani);
        tosicStanari = tosicStanari + pretvoriDane(tosicDani);
        radeticStanari = radeticStanari + pretvoriDane(radeticDani);
        civiticoStanari = civiticoStanari + pretvoriDane(civiticoDani);
        zidaricStanari = zidaricStanari + pretvoriDane(zidaricDani);
        crnkovicStanari = crnkovicStanari + pretvoriDane(crnkovicDani);
        glavasStanari = glavasStanari + pretvoriDane(glavasDani);
        dzaferovicStanari = dzaferovicStanari + pretvoriDane(dzaferovicDani);
        markovskiStanari = markovskiStanari + pretvoriDane(markovskiDani);
        triskaStanari = triskaStanari + pretvoriDane(triskaDani);

        System.out.println("Badnjevic stanari nakon računanja " + badnjevicStanari);


        strujaVoda = struja + voda;

        stanari = badnjevicStanari + ivanovicStanari + tosicStanari + radeticStanari +
                civiticoStanari + zidaricStanari + crnkovicStanari + glavasStanari +
                dzaferovicStanari + markovskiStanari + triskaStanari;


        cijenaPoOsobi = strujaVoda / stanari;

        binding.badnjevicUkupno.setText(String.format("%.2f kn", cijenaPoOsobi * badnjevicStanari));
        binding.ivanovicUkupno.setText(String.format("%.2f kn", cijenaPoOsobi * ivanovicStanari));
        binding.tosicUkupno.setText(String.format("%.2f kn", cijenaPoOsobi * tosicStanari));
        binding.radeticUkupno.setText(String.format("%.2f kn", cijenaPoOsobi * radeticStanari));
        binding.civiticoUkupno.setText(String.format("%.2f kn", cijenaPoOsobi * civiticoStanari));
        binding.zidaricUkupno.setText(String.format("%.2f kn", cijenaPoOsobi * zidaricStanari));
        binding.crnkovicUkupno.setText(String.format("%.2f kn", cijenaPoOsobi * crnkovicStanari));
        binding.glavasUkupno.setText(String.format("%.2f kn", cijenaPoOsobi * glavasStanari));
        binding.dzaferovicUkupno.setText(String.format("%.2f kn", cijenaPoOsobi * dzaferovicStanari));
        binding.markovskiUkupno.setText(String.format("%.2f kn", cijenaPoOsobi * markovskiStanari));
        binding.triskaUkupno.setText(String.format("%.2f kn", cijenaPoOsobi * triskaStanari));









    }

    private Double pretvoriDane(Integer dani){
        Double stanari = 0.0;
        Double daniUMjesecu = 30.0;

        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);

        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            daniUMjesecu = 31.0;
        }
        if(month == 4 || month == 6 || month == 9 || month == 11){
            daniUMjesecu = 30.0;
        }
        if(month == 2 && year%4 == 0) daniUMjesecu = 29.0;
        if(month == 2 && year%4 != 0) daniUMjesecu = 28.0;


        System.out.println("vrijednost od varijable dani u metodi " + dani);
        System.out.println("vrijednost od varijable daniUMjesecu u metodi " + daniUMjesecu);

        stanari = dani/daniUMjesecu;

        System.out.println("Vrijednost stanari u metodi " + stanari);

        return stanari;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }




    public void nazoviSina(MenuItem item) {
        Uri callUri = Uri.parse("tel://098 942 6261");
        Intent callIntent = new Intent(Intent.ACTION_DIAL,callUri);
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        startActivity(callIntent);
    }


    public void upravljajGlazbom(MenuItem item){
        if(mp.isPlaying()) mp.pause();

        else mp.start();
    }
}