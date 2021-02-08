package com.example.qrgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText inputTextEdit;
    private MaterialButton generateBtn;
    private ImageView outputQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        generateBtn.setOnClickListener(v -> {
            String text = Objects.requireNonNull(inputTextEdit.getText()).toString().trim();
            MultiFormatWriter writer = new MultiFormatWriter();
            try {
                BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, 350, 350);
                BarcodeEncoder encoder = new BarcodeEncoder();
                Bitmap bitmap = encoder.createBitmap(matrix);
                outputQRCode.setImageBitmap(bitmap);
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(inputTextEdit.getApplicationWindowToken(), 0);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        });
    }

    private void initialize() {
        inputTextEdit = findViewById(R.id.inputTextEdit);
        generateBtn = findViewById(R.id.generateBtn);
        outputQRCode = findViewById(R.id.outputQRCode);
    }
}