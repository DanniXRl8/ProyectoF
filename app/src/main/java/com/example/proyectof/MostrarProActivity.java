package com.example.proyectof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SearchView;
import com.example.proyectof.adapter.Proadapter;
import com.example.proyectof.model.Pro;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MostrarProActivity extends AppCompatActivity {

    RecyclerView mRecycle;
    Proadapter madapter;
    FirebaseFirestore mfirestore;
    SearchView search_View;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_pro);
        mfirestore = FirebaseFirestore.getInstance();
        search_View = findViewById(R.id.buscar);

        mRecycle = findViewById(R.id.recycler1);
        mRecycle.setLayoutManager(new LinearLayoutManager(this));
        setUpRecyclerView();
        search_View();
    }
        @SuppressLint("NotifyDataSetChanged")
        private void setUpRecyclerView() {
                mRecycle = findViewById(R.id.recycler1);
                mRecycle.setLayoutManager(new LinearLayoutManager(this));

                query = mfirestore.collection("Productos");

                FirestoreRecyclerOptions<Pro> firestoreRecyclerOptions =
                        new FirestoreRecyclerOptions.Builder<Pro>().setQuery(query, Pro.class).build();

                madapter = new Proadapter(firestoreRecyclerOptions, this);
                madapter.notifyDataSetChanged();
                mRecycle.setAdapter(madapter);
            }

    private void search_View() {
        search_View.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                textSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                textSearch(s);
                return false;
            }
        });
    }

    public void textSearch(String s) {

        FirestoreRecyclerOptions<Pro> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Pro>()
                        .setQuery(query.orderBy("Nombre")
                                .startAt(s).endAt(s + "~"), Pro.class).build();
        madapter = new Proadapter(firestoreRecyclerOptions, this);
        madapter.startListening();
        mRecycle.setAdapter(madapter);
    }
    @Override

    protected void onStart() {
        super.onStart();
        madapter.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        madapter.stopListening();
    }



}


