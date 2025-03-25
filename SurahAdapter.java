package com.example.quran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder> {

    private Context context;
    private List<Surah> surahList;
    private OnSurahClickListener listener;

    public interface OnSurahClickListener {
        void onSurahClick(Surah surah);
    }

    public SurahAdapter(Context context, List<Surah> surahList, OnSurahClickListener listener) {
        this.context = context;
        this.surahList = surahList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_surah, parent, false);
        return new SurahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahViewHolder holder, int position) {
        Surah surah = surahList.get(position);
        holder.surahName.setText(surah.getName());
        holder.surahArabicName.setText(surah.getArabicName());
        holder.itemView.setOnClickListener(v -> listener.onSurahClick(surah));
    }

    @Override
    public int getItemCount() {
        return surahList.size();
    }

    public void updateList(List<Surah> newList) {
        surahList.clear(); // مسح القائمة الحالية
        surahList.addAll(newList); // إضافة العناصر الجديدة
        notifyDataSetChanged(); // إعلام الـ RecyclerView بالتغييرات
    }

    public static class SurahViewHolder extends RecyclerView.ViewHolder {
        TextView surahName;
        TextView surahArabicName;

        public SurahViewHolder(@NonNull View itemView) {
            super(itemView);
            surahName = itemView.findViewById(R.id.surah_name);
            surahArabicName = itemView.findViewById(R.id.surah_arabic_name);
        }
    }
}