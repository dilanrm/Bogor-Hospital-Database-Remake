package com.app.dilan.rumahsakitbogor;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<RS> list = new ArrayList<>();
    final String STATE_TITLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String perms = Manifest.permission.CALL_PHONE;

        if (ContextCompat.checkSelfPermission(MainActivity.this, perms) != PackageManager.PERMISSION_GRANTED) {
               checkPerm();
        }

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list.addAll(RSData.getListData());
        showRecyclerList();

        if (savedInstanceState == null) {
            setActionBarTitle("Mode List");
            list.addAll(RSData.getListData());
            showRecyclerList();
            mode = R.id.action_list;
        } else {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
            ArrayList<RS> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode = savedInstanceState.getInt(STATE_MODE);
            setActionBarTitle(stateTitle);
            list.addAll(stateList);
            setMode(stateMode);
        }
    }

    public void checkPerm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Aplikasi ini membutuhkan izin membuka telepon");
        builder.setPositiveButton("Buka pengaturan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Nanti", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void showSelectedRS(RS rs){
        Toast.makeText(this, "Kamu memilih "+rs.getNama(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(MainActivity.this, DetailActivity.class);

        i.putExtra("nama", rs.getNama());
        i.putExtra("alamat", rs.getAlamat());
        i.putExtra("lokasi", rs.getLokasi());
        i.putExtra("telepon",rs.getTelepon());
        i.putExtra("foto", rs.getFoto());
        startActivity(i);

    }

    private void showRecyclerList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListRSAdapter listRSAdapter = new ListRSAdapter(this);
        listRSAdapter.setListRS(list);
        rvCategory.setAdapter(listRSAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedRS(list.get(position));

            }
        });
    }

    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridRSAdapter gridRSAdapter = new GridRSAdapter(this);
        gridRSAdapter.setListRS(list);
        rvCategory.setAdapter(gridRSAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedRS(list.get(position));

            }
        });
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewRSAdapter cardViewRSAdapter = new CardViewRSAdapter(this);
        cardViewRSAdapter.setListRS(list);
        rvCategory.setAdapter(cardViewRSAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());

        return super.onOptionsItemSelected(item);
    }

    private void setMode(int stateMode) {
        String title = null;
        switch (stateMode){
            case R.id.action_list:
                setActionBarTitle("Mode List");
                showRecyclerList();
                break;

            case R.id.action_grid:
                setActionBarTitle("Mode Grid");
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                setActionBarTitle("Mode CardView");
                showRecyclerCardView();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
