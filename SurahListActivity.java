package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SurahListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SurahAdapter adapter;
    private List<Surah> surahList;
    private int currentPage = 1; // الصفحة الحالية
    private int totalPages = 114; // إجمالي عدد السور، قم بتعديله بناءً على عدد السور الفعلي

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_list);

        // إعداد الـ Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        surahList = getJuz30Surahs();
        List<Surah> filteredSurahList = new ArrayList<>(surahList);

        adapter = new SurahAdapter(this, filteredSurahList, surah -> {
            Intent intent = new Intent(SurahListActivity.this, SurahTextActivity.class);
            intent.putExtra("surahName", surah.getName());
            intent.putExtra("surahText", surah.getText());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);

        EditText searchInput = findViewById(R.id.search_input);
        Button searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(v -> {
            String query = searchInput.getText().toString().trim().toLowerCase(Locale.getDefault());
            query = convertToArabic(query);
            filterSurahs(query);
        });

        // إعداد أزرار التنقل بين الصفحات
        Button buttonPrevPage = findViewById(R.id.button_prev_page);
        Button buttonNextPage = findViewById(R.id.button_next_page);

        buttonPrevPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage > 1) {
                    currentPage--;
                    navigateToPage(currentPage);
                }
            }
        });

        buttonNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage < totalPages) {
                    currentPage++;
                    navigateToPage(currentPage);
                }
            }
        });

        // الانتقال إلى الصفحة الأولى عند بدء التطبيق
        navigateToPage(currentPage);
    }

    private String convertToArabic(String input) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "ا");
        map.put("b", "ب");
        map.put("t", "ت");
        map.put("th", "ث");
        map.put("j", "ج");
        map.put("h", "ح");
        map.put("kh", "خ");
        map.put("d", "د");
        map.put("dh", "ذ");
        map.put("r", "ر");
        map.put("z", "ز");
        map.put("s", "س");
        map.put("sh", "ش");
        map.put("ṣ", "ص");
        map.put("d", "ض");
        map.put("ṭ", "ط");
        map.put("ẓ", "ظ");
        map.put("aa", "ع");
        map.put("gh", "غ");
        map.put("f", "ف");
        map.put("q", "ق");
        map.put("k", "ك");
        map.put("l", "ل");
        map.put("m", "م");
        map.put("n", "ن");
        map.put("h", "ه");
        map.put("w", "و");
        map.put("y", "ي");

        StringBuilder arabicString = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String key = String.valueOf(input.charAt(i));
            String value = map.get(key);
            arabicString.append(value != null ? value : key);
        }
        return arabicString.toString();
    }

    private void filterSurahs(String query) {
        List<Surah> filteredSurahList = new ArrayList<>();
        if (query.isEmpty()) {
            filteredSurahList.addAll(surahList);
        } else {
            for (Surah surah : surahList) {
                if (surah.getText().toLowerCase(Locale.getDefault()).contains(query) ||
                        surah.getName().toLowerCase(Locale.getDefault()).contains(query) ||
                        surah.getArabicName().contains(query)) {
                    filteredSurahList.add(surah);
                }
            }
        }
        adapter.updateList(filteredSurahList); // تأكد من وجود هذه الطريقة في SurahAdapter
    }

    private void navigateToPage(int pageNumber) {
        // قم بتنفيذ الإجراء المطلوب للانتقال إلى الصفحة المحددة
        // يمكن أن يكون هذا تحميل بيانات جديدة أو عرض محتوى مختلف
        Toast.makeText(this, "الانتقال إلى السورة " + pageNumber, Toast.LENGTH_SHORT).show();
    }

    private List<Surah> getJuz30Surahs() {
        List<Surah> surahs = new ArrayList<>();
        surahs.add(new Surah(78, "An-Naba", "النبأ", 40, "عَمَّ يَتَسَاءَلُونَ\nعَنِ النَّبَإِ الْعَظِيمِ\nالَّذِي هُمْ فِيهِ مُخْتَلِفُونَ\nكَلَّا سَيَعْلَمُونَ\nثُمَّ كَلَّا سَيَعْلَمُونَ..."));
        surahs.add(new Surah(79, "An-Nazi'at", "النازعات", 46, "وَالنَّازِعَاتِ غَرْقًا\nوَالنَّاشِطَاتِ نَشْطًا\nوَالسَّابِحَاتِ سَبْحًا..."));
        surahs.add(new Surah(80, "Abasa", "عبس", 42, "عَبَسَ وَتَوَلَّىٰ\nأَنْ جَاءَهُ الْأَعْمَىٰ\nوَمَا يُدْرِيكَ لَعَلَّهُ يَزَّكَّىٰ..."));
        surahs.add(new Surah(81, "At-Takwir", "التكوير", 29, "إِذَا الشَّمْسُ كُوِّرَتْ\nوَإِذَا النُّجُومُ انْكَدَرَتْ\nوَإِذَا الْجِبَالُ سُيِّرَتْ..."));
        surahs.add(new Surah(82, "Al-Infitar", "الإنفطار", 19, "إِذَا السَّمَاءُ انفَطَرَتْ\nوَإِذَا الْكَوَاكِبُ انتَثَرَتْ\nوَإِذَا الْبِحَارُ فُجِّرَتْ..."));
        surahs.add(new Surah(83, "Al-Mutaffifin", "المطففين", 36, "وَيْلٌ لِلْمُطَفِّفِينَ\nالَّذِينَ إِذَا اكْتَالُوا عَلَى النَّاسِ يَسْتَوْفُونَ\nوَإِذَا كَالُوهُمْ أَوْ وَزَنُوهُمْ يُخْسِرُونَ..."));
        surahs.add(new Surah(84, "Al-Inshiqaq", "الإنشقاق", 25, "إِذَا السَّمَاءُ انشَقَّتْ\nوَإِذَا الْأَرْضُ مُدَّتْ\nوَإِذَا الْبِحَارُ فُجِّرَتْ..."));
        surahs.add(new Surah(85, "Al-Buruj", "البروج", 22, "وَالسَّمَاءِ ذَاتِ الْبُرُوجِ\nوَالْيَوْمِ الْمَوْعُودِ\nوَشَاهِدٍ وَمَشْهُودٍ..."));
        surahs.add(new Surah(86, "At-Tariq", "الطارق", 17, "وَالسَّمَاءِ وَالطَّارِقِ\nوَمَا أَدْرَاكَ مَا الطَّارِقُ\nالنَّجْمُ الثَّاقِبُ..."));
        surahs.add(new Surah(87, "Al-A'la", "الأعلى", 19, "سَبِّحِ اسْمَ رَبِّكَ الْأَعْلَىٰ\nالَّذِي خَلَقَ فَسَوَّىٰ\nوَالَّذِي قَدَّرَ فَهَدَىٰ..."));
        surahs.add(new Surah(88, "Al-Ghashiyah", "الغاشية", 26, "هَلْ أَتَاكَ حَدِيثُ الْغَاشِيَةِ\nوُجُوهٌ يَوْمَئِذٍ خَاشِعَةٌ\nعَامِلَةٌ نَّاصِبَةٌ..."));
        surahs.add(new Surah(89, "Al-Fajr", "الفجر", 30, "نص السورة..."));
        surahs.add(new Surah(90, "Al-Balad", "البلد", 20, "نص السورة..."));
        surahs.add(new Surah(91, "Ash-Shams", "الشمس", 15, "نص السورة..."));
        surahs.add(new Surah(92, "Al-Lail", "الليل", 21, "نص السورة..."));
        surahs.add(new Surah(93, "Ad-Duha", "الضحى", 11, "نص السورة..."));
        surahs.add(new Surah(94, "Ash-Sharh", "الشرح", 8, "نص السورة..."));
        surahs.add(new Surah(95, "At-Tin", "التين", 8, "نص السورة..."));
        surahs.add(new Surah(96, "Al-Alaq", "العلق", 19, "نص السورة..."));
        surahs.add(new Surah(97, "Al-Qadr", "القدر", 5, "نص السورة..."));
        surahs.add(new Surah(98, "Al-Bayyinah", "البينة", 8, "نص السورة..."));
        surahs.add(new Surah(99, "Az-Zalzalah", "الزلزلة", 8, "نص السورة..."));
        surahs.add(new Surah(100, "Al-Adiyat", "العاديات", 11, "نص السورة..."));
        surahs.add(new Surah(101, "Al-Qari'ah", "القارعة", 11, "نص السورة..."));
        surahs.add(new Surah(102, "At-Takathur", "التكاثر", 8, "نص السورة..."));
        surahs.add(new Surah(103, "Al-Asr", "العصر", 3, "نص السورة..."));
        surahs.add(new Surah(104, "Al-Humazah", "الهمزة", 9, "نص السورة..."));
        surahs.add(new Surah(105, "Al-Fil", "الفيل", 5, "نص السورة..."));
        surahs.add(new Surah(106, "Quraish", "قريش", 4, "نص السورة..."));
        surahs.add(new Surah(107, "Al-Ma'un", "الماعون", 7, "نص السورة..."));
        surahs.add(new Surah(108, "Al-Kawthar", "الكوثر", 3, "نص السورة..."));
        surahs.add(new Surah(109, "Al-Kafirun", "الكافرون", 6, "نص السورة..."));
        surahs.add(new Surah(110, "An-Nasr", "النصر", 3, "نص السورة..."));
        surahs.add(new Surah(111, "Al-Masad", "المسد", 5, "نص السورة..."));
        surahs.add(new Surah(112, "Al-Ikhlas", "الإخلاص", 4, "نص السورة..."));
        surahs.add(new Surah(113, "Al-Falaq", "الفلق", 5, "نص السورة..."));
        surahs.add(new Surah(114, "An-Nas", "الناس", 6, "نص السورة..."));
        return surahs;
    }
}