package com.example.us.imagedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private List<Product> mCartList, mCartListW,mCartListM;
    private ProductNewAdapter mProductAdapter;
    private ProductWomenAdapter mProductAdapterW;
    private ProductMenAdapter mProductAdapterM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        mCartList = ProductListNew.getCartList();
        mCartListW = ProductListWomen.getCartList();
        mCartListM = ProductListMen.getCartList();

        // Make sure to clear the selections
        for (int i = 0; i < mCartList.size(); i++) {
            mCartList.get(i).selected = false;
        }

        for (int i = 0; i < mCartListW.size(); i++) {
            mCartListW.get(i).selected = false;
        }

        for (int i = 0; i < mCartListM.size(); i++) {
            mCartListM.get(i).selected = false;
        }

        // Create the list
        final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        mProductAdapter = new ProductNewAdapter(mCartList, getLayoutInflater(), true);
        listViewCatalog.setAdapter(mProductAdapter);

        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(), ProductNewDetails.class);
                productDetailsIntent.putExtra(ProductListNew.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

        final ListView listViewCatalogWomen = (ListView) findViewById(R.id.ListViewCatalogWomen);
        mProductAdapterW = new ProductWomenAdapter(mCartListW, getLayoutInflater(), true);
        listViewCatalogWomen.setAdapter(mProductAdapterW);
        listViewCatalogWomen.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(), ProductWomenDetails.class);
                productDetailsIntent.putExtra(ProductListWomen.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

        final ListView listViewCatalogMen = (ListView) findViewById(R.id.ListViewCatalogMen);
        mProductAdapterM = new ProductMenAdapter(mCartListM, getLayoutInflater(), true);
        listViewCatalogMen.setAdapter(mProductAdapterM);
        listViewCatalogMen.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(), ProductMenDetails.class);
                productDetailsIntent.putExtra(ProductListMen.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the data
        if (mProductAdapter != null ||mProductAdapterW != null || mProductAdapterM != null) {
            mProductAdapter.notifyDataSetChanged();
            mProductAdapterW.notifyDataSetChanged();
            mProductAdapterM.notifyDataSetChanged();
        }

        double subTotal = 0;
        for (Product p : mCartList) {
            int quantity = ProductListNew.getProductQuantity(p);
            subTotal += p.price * quantity;
        }
        for (Product p : mCartListW) {
            int quantity = ProductListWomen.getProductQuantity(p);
            subTotal += p.price * quantity;
        }
        for (Product p : mCartListM) {
            int quantity = ProductListMen.getProductQuantity(p);
            subTotal += p.price * quantity;
        }

        TextView productPriceTextView = (TextView) findViewById(R.id.TextViewSubtotal);
        productPriceTextView.setText("Subtotal: $" + subTotal);

    }
}

