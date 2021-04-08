package com.yjisolutions.ipdc;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class notes extends Fragment {

    public notes() {
        // Required empty public constructor
    }

    private PDFAdapter adapter;
    private List<Model_PDF> listPDF;
    private ProgressDialog pd;
    private RecyclerView rvPDF;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_notes, container, false);
        rvPDF = root.findViewById(R.id.recPDF);

        listPDF = new ArrayList<>();

        rvPDF.setLayoutManager(new LinearLayoutManager(getContext()));

        pd=new ProgressDialog(getContext());
        pd.setMessage("Please Wait Loading Data");
        pd.show();

        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("pdf");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        Model_PDF l = npsnapshot.getValue(Model_PDF.class);
                        listPDF.add(l);
                        pd.dismiss();

                    }


                    adapter = new PDFAdapter(listPDF,getContext());
                    rvPDF.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Field to Load Data", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}