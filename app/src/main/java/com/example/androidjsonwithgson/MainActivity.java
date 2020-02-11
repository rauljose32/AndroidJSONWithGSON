package com.example.androidjsonwithgson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.androidjsonwithgson.model.Poema;
import com.example.androidjsonwithgson.util.Auxiliar;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String URL = "https://jsonplaceholder.typicode.com/posts/";
    private ListView lv;
    private List<HashMap<String, String>> listPoema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listPoema = new ArrayList();
        lv = findViewById(R.id.listView);

    }//onCreate

    private class ObterJsonComGson extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Download JSON", Toast.LENGTH_LONG).show();
        }//method

        @Override
        protected Void doInBackground(Void... voids) {
            Auxiliar auxiliar = new Auxiliar();
            String jsonCon = auxiliar.conectar(URL);

            try {
                Gson gson = new Gson();
                JSONArray jsonArray = new JSONArray(jsonCon);
                for (int i = 0; i < jsonArray.length(); i++) {
                    Poema p = new Poema();
                    p = gson.fromJson(jsonArray.getJSONObject(i), Poema.class);

                    HashMap<String, String> list = new HashMap<>();
                    list.put("userId", String.valueOf(p.getUserId()));
                    list.put("id", String.valueOf(p.getId()));
                    list.put("title", p.getTitle());
                    list.put("body", p.getBody());

                    listPoema.add(list);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }//method

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this,
                    listPoema,
                    R.layout.item_list,
                    new String[]{"id", "title", "body"},
                    new int[]{R.id.textViewId, R.id.textViewTitle, R.id.textViewBody});
            lv.setAdapter(adapter);
        }//method

    }//innerClass

}//class
