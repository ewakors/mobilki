package com.example.us.imagedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener, View.OnClickListener {
    private Session session;
    private TextView textView;
    private List<Product> mProductList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }

        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ab.setDisplayShowTitleEnabled(true);

        ab.addTab(ab.newTab().setText("Wyprzedaż").setTabListener(this).setIcon(R.drawable.procent));
        ab.addTab(ab.newTab().setText("Moda męska").setTabListener(this).setIcon(R.drawable.agent));
        ab.addTab(ab.newTab().setText("Moda damska").setTabListener(this).setIcon(R.drawable.agent_female));
       // ab.addTab(ab.newTab().setText("Nowości").setTabListener(this).setIcon(R.drawable.newbar));

    }

    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.myProfile:
               startActivity(new Intent(this,UserProfileActivity.class));
                return true;
            case R.id.logOut:
                logout();
                return true;
            case R.id.buttonCart:
                startActivity(new Intent(this,ShoppingCartActivity.class));
            default:
                break;
        }

        return true;
    }
    public void productWomen()
    {
        // Obtain a reference to the product catalog
        mProductList = ProductListWomen.getCatalogWomen(getResources());

        // Create the list
        ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        listViewCatalog.setAdapter(new ProductWomenAdapter(mProductList, getLayoutInflater(), false));

        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(),ProductWomenDetails.class);
                productDetailsIntent.putExtra(ProductListWomen.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });
    }
    public void productNew()
    {
        mProductList = ProductListNew.getCatalogNew(getResources());

        ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        listViewCatalog.setAdapter(new ProductNewAdapter(mProductList, getLayoutInflater(), false));

        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(),ProductNewDetails.class);
                productDetailsIntent.putExtra(ProductListNew.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

    }
    public void productMen()
    {
        mProductList = ProductListMen.getCatalogMen(getResources());

        ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        listViewCatalog.setAdapter(new ProductMenAdapter(mProductList, getLayoutInflater(), false));

        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(),ProductMenDetails.class);
                productDetailsIntent.putExtra(ProductListMen.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

    }
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        int nTabSelected = tab.getPosition();
        switch (nTabSelected) {
            case 0:
                setContentView(R.layout.activity_new);
                productNew();
                break;
            case 1:
                setContentView(R.layout.activity_men);
                productMen();
                break;
            case 2:
                setContentView(R.layout.activity_women);
                productWomen();
                break;
            case 3:
                setContentView(R.layout.activity_main);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnLogin:

               // startActivity(new Intent(CatalogActivity.this,MainActivity.class));
                break;
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // Called when a tab unselected.
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        // Called when a tab is selected again.
    }

}
