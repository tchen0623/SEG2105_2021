package com.example.sqlitelab;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity{
    TextView idView;
    EditText productBox;
    EditText priceBox;
    Button b_add;
    Button b_find;
    Button b_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        priceBox = (EditText) findViewById(R.id.productPrice);
        b_add = (Button)findViewById(R.id.add);
        b_find = (Button)findViewById(R.id.find);
        b_delete = (Button)findViewById(R.id.delete);
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newProduct(v);
            }
        });
        b_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lookupProduct(v);
            }
        });
        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeProduct(v);
            }
        });

    }

    public void newProduct(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);
        double price = Double.parseDouble(priceBox.getText().toString());
        Product product = new Product(productBox.getText().toString(),price);
        dbHandler.addProduct(product);
        productBox.setText("");
        priceBox.setText("");
    }
    public void lookupProduct (View view){
        MyDBHandler dbHandler = new MyDBHandler(this);
        Product product = dbHandler.findProduct(productBox.getText().toString());

        if (product != null){
            idView.setText(String.valueOf(product.getID()));
            priceBox.setText(String.valueOf(product.getPrice()));
        }else{
            idView.setText("No Match Found");
        }
    }
    public void removeProduct(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);

        boolean result = dbHandler.deleteProduct(productBox.getText().toString());
        if(result){
            idView.setText("Record Deleted");
            productBox.setText("");
            priceBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }
}