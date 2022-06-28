package com.example.proyectof.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectof.CreteProActivity;
import com.example.proyectof.R;
import com.example.proyectof.model.Pro;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Proadapter extends FirestoreRecyclerAdapter<Pro,Proadapter.ViewHolder> {

    private FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();
    Activity activity;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public Proadapter(@NonNull FirestoreRecyclerOptions<Pro> options, Activity activity) {
        super(options);
        this.activity=activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i , @NonNull Pro Pro) {
        DocumentSnapshot documentSnapshot=getSnapshots().getSnapshot(viewHolder.getAbsoluteAdapterPosition());
        final String id=documentSnapshot.getId();

        viewHolder.Nombre.setText(Pro.getNombre());
        viewHolder.Piezas.setText(Pro.getPiezas());
        viewHolder.color.setText(Pro.getColor());


        viewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(activity, CreteProActivity.class);
                i.putExtra("id_Pro",id);
                activity.startActivity(i);

            }
        });

        viewHolder.btn_dalate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            delatePro(id);
            }
        });
    }

    private void delatePro(String id) {
        mFirestore.collection("Productos").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Eliminado Correctamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Error al Eliminar ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pro_single,parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre,Piezas,color;
        ImageView btn_dalate;
        ImageView btn_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
Nombre=itemView.findViewById(R.id.nombre2);
Piezas=itemView.findViewById(R.id.piezas2);
color=itemView.findViewById(R.id.color2);
btn_dalate=itemView.findViewById(R.id.btn_eliminar1);
btn_edit=itemView.findViewById(R.id.btn_editar);

        }
    }
}
