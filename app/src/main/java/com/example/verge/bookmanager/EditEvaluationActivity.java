package com.example.verge.bookmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verge.DAO.BookDAO;

public class EditEvaluationActivity extends AppCompatActivity {
    TextView evaluationText;
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_evaluation);
        evaluationText= findViewById(R.id.evaluationText);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                String id = getIntent().getStringExtra("id");
                BookDAO dao = new BookDAO(EditEvaluationActivity.this);
                dao.editBook(id,content);
                Toast.makeText(EditEvaluationActivity.this,"评价成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
