package com.example.ishitasinha.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText searchBar;
    RecyclerView listView;
    List<ListItems> searchList;

    String baseUrl = "http://www.omdbapi.com/?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchBar = (EditText) findViewById(R.id.search_bar);
        listView = (RecyclerView) findViewById(R.id.search_items);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void search(View view) {
        if (searchBar.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter a title to search for.", Toast.LENGTH_SHORT).show();
        } else {
            final String searchText = searchBar.getText().toString();
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
            listView.setLayoutManager(mLayoutManager);
            OmdbClient.getOmdbApiService().searchList(searchText).enqueue(new Callback<List<ListItems>>() {
                @Override
                public void onResponse(Call<List<ListItems>> call, Response<List<ListItems>> response) {
                    SearchAdapter adapter = new SearchAdapter(response.body());
                    listView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<ListItems>> call, Throwable t) {

                }
            });
//            new AsyncTask<Void, Void, String>() {
//
//                @Override
//                protected String doInBackground(Void... voids) {
//                    String resultJsonStr = null;
//                    try {
//                        Uri uri = Uri.parse(baseUrl)
//                                .buildUpon()
//                                .appendQueryParameter("s", searchText)
//                                .build();
//                        URL url = new URL(uri.toString());
//                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                        urlConnection.setRequestMethod("GET");
//                        urlConnection.connect();
//
//                        InputStream inputStream = urlConnection.getInputStream();
//                        StringBuffer buffer = new StringBuffer();
//                        if (inputStream == null) {
//                            return null;
//                        }
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                        String line;
//                        while ((line = reader.readLine()) != null) {
//                            buffer.append(line + "\n");
//                        }
//                        if (buffer.length() == 0) {
//                            // Stream was empty.  No point in parsing.
//                            return null;
//                        }
//                        resultJsonStr = buffer.toString();
//                        Log.v("AsyncTask", resultJsonStr);
//                        if (urlConnection != null) {
//                            urlConnection.disconnect();
//                        }
//                        if (reader != null) {
//                            try {
//                                reader.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    return resultJsonStr;
//                }
//
//                @Override
//                protected void onPostExecute(String s) {
//                    super.onPostExecute(s);
//                    displayData(s);
//
//                }
//            }.execute();

        }
    }

//    public void displayData(String jsonStr) {
//        Gson gson = new Gson();
//        Log.v("jsonStr", jsonStr);
//        try {
//            JSONObject resultJson = new JSONObject(jsonStr);
//            JSONArray jsonArray = resultJson.getJSONArray("Search");
//            searchList = new ArrayList<>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                ListItems item = gson.fromJson(jsonArray.getJSONObject(i).toString(), ListItems.class);
////                JSONObject jsonObject = jsonArray.getJSONObject(i);
////                String title = jsonObject.getString("Title");
////                String posterUrl = jsonObject.getString("Poster");
////                String released = jsonObject.getString("Year");
////                String rating = jsonObject.getString("imdbRating");
//                searchList.add(item);
//            }
//
//            SearchAdapter adapter = new SearchAdapter(searchList);
//            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
//            listView.setLayoutManager(mLayoutManager);
//            listView.setAdapter(adapter);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
