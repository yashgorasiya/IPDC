package com.yjisolutions.ipdc;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class home extends Fragment {


    public home() {
        // Required empty public constructor
    }
    private ArrayList<ListData> listData;
    public MyAdapter adapter;
    private RecyclerView rv;
    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        rv=root.findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        listData=new ArrayList<>();

        pd=new ProgressDialog(getContext());
        pd.setMessage("Please Wait Loading Data");
        pd.show();

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("data");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        ListData l=npsnapshot.getValue(ListData.class);
                        listData.add(l);
                        pd.dismiss();
                    }
                    adapter=new MyAdapter(listData,getContext());
                    rv.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Field to Load Data", Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }

}