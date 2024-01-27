package com.example.firstapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.firstapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Spinner productDropdown;
    private EditText routineTimeEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up product dropdown
        productDropdown = findViewById(R.id.productDropdown);
        List<String> productOptions = new ArrayList<>();
        // Add your skincare products here
        productOptions.add("Product 1");
        productOptions.add("Product 2");
        // ...

        ArrayAdapter<String> productAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, productOptions);
        productDropdown.setAdapter(productAdapter);

        // Set up routine time entry
        routineTimeEntry = findViewById(R.id.routineTimeEntry);

        // Set up buttons
        Button addRoutineButton = findViewById(R.id.addRoutineButton);
        addRoutineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRoutine();
            }
        });

        Button openLinkButton = findViewById(R.id.openLinkButton);
        openLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProductLink();
            }
        });

        Button displayHealthyButton = findViewById(R.id.displayHealthyButton);
        displayHealthyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayHealthyRoutine();
            }
        });

        // Check and request necessary permissions
        checkAndRequestPermissions();
    }

    private void checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    private void showNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "skincareChannel")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Check if the app has the notification permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // If the permission is not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            // If the permission is already granted, show the notification
            NotificationManagerCompat.from(this).notify(new Random().nextInt(), builder.build());
        }
    }


    private void addRoutine() {
        try {
            int timeMinutes = Integer.parseInt(routineTimeEntry.getText().toString());
            String selectedProduct = productDropdown.getSelectedItem().toString();

            // Schedule routine here

            showNotification("Routine Added", "Skincare routine added for " + selectedProduct + "!");
        } catch (NumberFormatException e) {
            showNotification("Error", "Please enter a valid integer for time.");
        }
    }

    private void openProductLink() {
        String selectedProduct = productDropdown.getSelectedItem().toString();

        // Get the URL for the selected product and open it in a web browser
        // Example: String productUrl = getProductUrl(selectedProduct);
        // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(productUrl)));
    }

    private void displayHealthyRoutine() {
        // Display healthy skincare routine in a new window
        // Example: startActivity(new Intent(this, HealthyRoutineActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, you can proceed with your tasks
        } else {
            Toast.makeText(this, "Permission denied. Some features may not work properly.", Toast.LENGTH_SHORT).show();
        }
    }
}
