package com.example.rentalz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class MainActivity extends AppCompatActivity {
    EditText etName,etAddress,etType,etBedrooms,etPrice,etReporter;
    Button btSubmit;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign all the view variables
        etName= findViewById(R.id.et_name);
        etAddress= findViewById(R.id.et_address);
        etType= findViewById(R.id.et_type);
        etBedrooms= findViewById(R.id.et_bedrooms);
        etPrice= findViewById(R.id.et_price);
        etReporter= findViewById(R.id.et_reporter);
        btSubmit = findViewById(R.id.bt_submit);

        // Initialize form validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        // Validation
        //name
        awesomeValidation.addValidation(this,R.id.et_name, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        //address
        awesomeValidation.addValidation(this,R.id.et_address, RegexTemplate.NOT_EMPTY,R.string.invalid_address);
        //type
        awesomeValidation.addValidation(this,R.id.et_type, RegexTemplate.NOT_EMPTY,R.string.invalid_type);
        //bedrooms
        awesomeValidation.addValidation(this,R.id.et_bedrooms, RegexTemplate.NOT_EMPTY,R.string.invalid_bedrooms);
        //price
        awesomeValidation.addValidation(this,R.id.et_price, RegexTemplate.NOT_EMPTY,R.string.invalid_price);
        //reporter
        awesomeValidation.addValidation(this,R.id.et_reporter, RegexTemplate.NOT_EMPTY,R.string.invalid_reporter);

        //Handle submit button
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(awesomeValidation.validate()){
                    String name = etName.getText().toString();
                    String address = etAddress.getText().toString();
                    String type = etType.getText().toString();
                    String bedrooms = etBedrooms.getText().toString();
                    String price = etPrice.getText().toString();
                    String reporter = etName.getText().toString();
                    openConfirmDialog(name,address,type,bedrooms,price,reporter);
                }else{
                    Toast.makeText(getApplicationContext(),"Form validation failed, please check the information again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openConfirmDialog(String name, String address, String type, String bedrooms, String price,String reporter){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_confirm_dialog);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }


        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        Button btCancel = dialog.findViewById(R.id.bt_cancel);
        Button btConfirm = dialog.findViewById(R.id.bt_confirm);
        TextView modalName = dialog.findViewById(R.id.modal_name);
        TextView modalAddress = dialog.findViewById(R.id.modal_address);
        TextView modalType = dialog.findViewById(R.id.modal_type);
        TextView modalBedrooms = dialog.findViewById(R.id.modal_bedroom);
        TextView modalPrice = dialog.findViewById(R.id.modal_price);
        TextView modalReporter = dialog.findViewById(R.id.modal_reporter);

        modalName.setText("Name: "+ name);
        modalAddress.setText("Address: "+ address);
        modalType.setText("Type: "+ type);
        modalBedrooms.setText("Bedrooms: "+ bedrooms);
        modalPrice.setText("Price: "+ price);
        modalReporter.setText("Reporter: "+ reporter);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"New property created successfully",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}