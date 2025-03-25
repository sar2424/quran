package com.example.quran;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TasbeehActivity extends AppCompatActivity {

    private int currentPage = 1; // الصفحة الحالية
    private int totalPages = 5; // إجمالي عدد الصفحات، قم بتعديله بناءً على عدد الصفحات الفعلي
    private TextView tasbeehTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbeeh);

        // إعداد الـ Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tasbeehTextView = findViewById(R.id.tasbeeh_text_view);

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

    private void navigateToPage(int pageNumber) {
        // قم بتنفيذ الإجراء المطلوب للانتقال إلى الصفحة المحددة
        // يمكن أن يكون هذا تحميل بيانات جديدة أو عرض محتوى مختلف
        tasbeehTextView.setText("الانتقال إلى الصفحة " + pageNumber);
    }
}