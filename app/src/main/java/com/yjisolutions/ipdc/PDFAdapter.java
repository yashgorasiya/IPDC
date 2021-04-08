package com.yjisolutions.ipdc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PDFAdapter extends RecyclerView.Adapter<PDFAdapter.viewHolder> {
    private final List<Model_PDF> listPDF;
    private final Context context;

    public PDFAdapter(List<Model_PDF> listPDF, Context context) {
        this.listPDF = listPDF;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pdf,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Model_PDF temp = listPDF.get(position);
        holder.PDFcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,pdfView.class);
                intent.putExtra("URL",temp.getPDFUrl());
                intent.putExtra("title",temp.getPDFName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.pdfNAme.setText(temp.getPDFName());

    }

    @Override
    public int getItemCount() {
        return listPDF.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private final TextView pdfNAme;
        private final CardView PDFcard;
        private final ImageView pdfImage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            pdfNAme=(TextView) itemView.findViewById(R.id.aboutPDFe);
            PDFcard=(CardView) itemView.findViewById(R.id.PDFcard);
            pdfImage=(ImageView) itemView.findViewById(R.id.listPDF);
        }
    }
}
