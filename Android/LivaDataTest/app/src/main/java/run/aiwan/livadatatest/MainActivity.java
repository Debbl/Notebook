package run.aiwan.livadatatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ViewModelWithLiveData viewModelWithLiveData;
    TextView textView;
    ImageView imageButtonLike,imageButtonDisLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModelWithLiveData = new ViewModelProvider(this).get(ViewModelWithLiveData.class);
        textView = findViewById(R.id.textView);
        imageButtonLike = findViewById(R.id.imageButton);
        imageButtonDisLike = findViewById(R.id.imageButton2);

        viewModelWithLiveData.getLikeDNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(String.valueOf(integer));
            }
        });
        imageButtonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelWithLiveData.addNumber(1);
            }
        });
        imageButtonDisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelWithLiveData.addNumber(-1);
            }
        });

    }
}