package hr.tvz.android.maminaaplikacija.popisnamirnica;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hr.tvz.android.maminaaplikacija.R;
import hr.tvz.android.maminaaplikacija.bazapodataka.DatabaseHelper;
import hr.tvz.android.maminaaplikacija.bazapodataka.Proizvodi;

public class PopisNamirnica extends AppCompatActivity {

    EditText naslov;
    List<Proizvodi> lista;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proizvodi);
        naslov = (EditText)findViewById(R.id.title);
        listView = (ListView)findViewById(R.id.lista);
        ispisi();
    }

    private void ispisi() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        lista = databaseHelper.getAllToDos();

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        HashMap<String, String> item;
        for (Proizvodi proizvod : lista) {
            item = new HashMap<String, String>();
            item.put("title", proizvod.getTitle());
            list.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list,
                android.R.layout.simple_list_item_1, new String[] { "title"}, new int[] { android.R.id.text1
                 });
        listView.setAdapter(adapter);
    }

    public void unesi(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Proizvodi proizvodi = new Proizvodi(naslov.getText().toString());
        databaseHelper.createToDo(proizvodi);
        naslov.setText("");
        ispisi();
    }

    public void obrisi(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.deleteToDos();
        ispisi();
    }
}
