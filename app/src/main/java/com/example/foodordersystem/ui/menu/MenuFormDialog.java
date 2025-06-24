package com.example.foodordersystem.ui.menu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodordersystem.R;
import com.example.foodordersystem.data.database.DatabaseClient;
import com.example.foodordersystem.data.entity.MenuItem;

import java.util.concurrent.Executors;

public class MenuFormDialog extends Dialog {

    private final Context context;
    private final Runnable onItemAdded;
    private final MenuItem menuItemToEdit;
    private Uri selectedImageUri;
    public static final int PICK_IMAGE_REQUEST = 1;

    public MenuFormDialog(@NonNull Context context, Runnable onItemAdded, MenuItem menuItemToEdit) {
        super(context);
        this.context = context;
        this.onItemAdded = onItemAdded;
        this.menuItemToEdit = menuItemToEdit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_add_menu);

        EditText edtName = findViewById(R.id.edtName);
        EditText edtPrice = findViewById(R.id.edtPrice);
        ImageView imgPreview = findViewById(R.id.imgPreview);
        Button btnPickImage = findViewById(R.id.btnPickImage);
        Button btnSave = findViewById(R.id.btnSave);

        // Nếu đang sửa, hiển thị dữ liệu cũ
        if (menuItemToEdit != null) {
            edtName.setText(menuItemToEdit.itemName);
            edtPrice.setText(String.valueOf(menuItemToEdit.price));
            if (menuItemToEdit.imageUrl != null && !menuItemToEdit.imageUrl.isEmpty()) {
                selectedImageUri = Uri.parse(menuItemToEdit.imageUrl);
                imgPreview.setImageURI(selectedImageUri);
            }
        }

        btnPickImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            ((ManageMenuActivity) context).startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String priceStr = edtPrice.getText().toString().trim();

            if (name.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(context, "Vui lòng nhập đầy đủ tên và giá", Toast.LENGTH_SHORT).show();
                return;
            }

            double price = Double.parseDouble(priceStr);
            MenuItem item = (menuItemToEdit != null) ? menuItemToEdit : new MenuItem();
            item.itemName = name;
            item.price = price;
            item.imageUrl = selectedImageUri != null ? selectedImageUri.toString() : "";

            Executors.newSingleThreadExecutor().execute(() -> {
                if (menuItemToEdit != null) {
                    DatabaseClient.getInstance(context).getAppDatabase().menuDao().updateMenuItem(item);
                } else {
                    DatabaseClient.getInstance(context).getAppDatabase().menuDao().insertMenuItem(item);
                }

                ((ManageMenuActivity) context).runOnUiThread(() -> {
                    Toast.makeText(context, menuItemToEdit != null ? "Đã cập nhật món ăn" : "Đã thêm món ăn", Toast.LENGTH_SHORT).show();
                    dismiss();
                    onItemAdded.run();
                });
            });
        });
    }

    public void setImageUri(Uri uri) {
        selectedImageUri = uri;
        ImageView imgPreview = findViewById(R.id.imgPreview);
        imgPreview.setImageURI(uri);
    }
}
