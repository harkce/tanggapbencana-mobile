package com.asgar.tanggapbencana.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.*;
import android.widget.Toast;

import com.asgar.tanggapbencana.R;
import com.asgar.tanggapbencana.api.ApiInterface;
import com.asgar.tanggapbencana.base.BaseActivity;
import com.asgar.tanggapbencana.databinding.ActivityTambahFeedBinding;
import com.asgar.tanggapbencana.model.DataRelawan;
import com.asgar.tanggapbencana.sharedPrefs.PrefRelawan;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class TambahFeed extends BaseActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final int PERMISSION_CODE_ACCESS_FINE_LOCATION = 455;
    private static final int REQUEST_CODE_PLACE_AUTOCOMPLETE = 123;
    private ActivityTambahFeedBinding binding;
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 1972;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    private Drawable icon;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tambah_feed);
        binding.setTambahFeed(this);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Tambah Lokasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (checkPlayServices()) {
            buildGoogleApiClient();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop location updates to save battery, but don't disconnect the GoogleApiClient object.
        try {
            if (mGoogleApiClient.isConnected()) {
                stopLocationUpdates();
            }
        }catch (NullPointerException ex){

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mGoogleApiClient!=null)
            mGoogleApiClient.disconnect();
    }


    public void restartActivity(){
        Intent intent = this.getIntent();
        this.finish();
        startActivity(intent);
    }

    @Override
    public void onLocationChanged(Location location) {
        //current location
        mLastLocation = location;
        binding.tfLatitude.setText(mLastLocation.getLatitude()+"");
        binding.tfLongitude.setText(mLastLocation.getLongitude()+"");
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    public void checkReqGPS() {
        if (!checkGPS()) {
            buildAlertMessageNoGps();
        } else {
            if (mGoogleApiClient == null) {
                mGoogleApiClient = new GoogleApiClient.Builder(this)
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this)
                        .addApi(LocationServices.API)
                        .build();
            }
            mGoogleApiClient.connect();
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }else {
                Toast.makeText(this.getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                this.finish();
            }
            return false;
        }
        return true;
    }

    public boolean checkGPS() {
        LocationManager locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void buildAlertMessageNoGps() {
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("GPS")
                .setMessage("Nyalakan GPS")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        //open gps setting
                        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                                REQUEST_CODE_PLACE_AUTOCOMPLETE);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        //if user pressed no do something
                    }
                });
        final android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_PLACE_AUTOCOMPLETE) {
            if(resultCode == this.RESULT_OK) {
                restartActivity();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void handlePermissionsAndGetLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            int hasWriteContactsPermission = this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_CODE_ACCESS_FINE_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE_ACCESS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    restartActivity();
                } else {
                    Toast.makeText(this,"Your location is not valid",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        try {
            startLocationUpdates();
            //this will get the last founded location in case onLocationChanged() is fail
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                //do something if mLastLocation exist
            }else {
                //do something if mLastLocation null
            }
        } catch (SecurityException e) {

        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //do some logging here
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //do some logging here
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.submit_feed, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.submit_feed){
            boolean isFill;
            if (!binding.tfNamaLokasi.getText().toString().equals("")){
                isFill = true;
            }else {
                isFill = false;
            }
            if (isFill){

                dialog = new ProgressDialog(this);
                dialog.setCancelable(false);
                dialog.setMessage("Mohon tunggu sebentar...");
                dialog.show();

                DataRelawan dataRelawan = PrefRelawan.getRelawan(this);

                Log.i("Feed", binding.tfNamaLokasi.getText().toString()+" "+
                        binding.tfLatitude.getText().toString()+" "+
                        binding.tfLongitude.getText().toString()+" "+
                        binding.tfDeskripsiKorban.getText().toString()+" "+
                        binding.tfDeskripsiKebutuhan.getText().toString()+" "+
                        binding.tfKeterangan.getText().toString()+" "+
                        dataRelawan.getRelawan().getNama()+" "+
                        dataRelawan.getRelawan().getKontak());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ApiInterface.url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiInterface post = retrofit.create(ApiInterface.class);
                Call<ResponseBody> call = post.uploadData(binding.tfNamaLokasi.getText().toString(),
                        binding.tfLatitude.getText().toString(),
                        binding.tfLongitude.getText().toString(),
                        binding.tfDeskripsiKorban.getText().toString(),
                        binding.tfDeskripsiKebutuhan.getText().toString(),
                        binding.tfKeterangan.getText().toString(),
                        dataRelawan.getRelawan().getNama(),
                        dataRelawan.getRelawan().getKontak());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        dialog.dismiss();
                        Toast.makeText(TambahFeed.this,"Berhasil Menambahkan",Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(TambahFeed.this,"Gagal Menambahkan, cek koneksi internet",Toast.LENGTH_LONG).show();
                    }
                });
            }else {
                icon = ContextCompat.getDrawable(this,R.drawable.ic_error_red_500_18dp);
                icon.setBounds(new Rect(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight()));
                binding.tfNamaLokasi.setCompoundDrawables(null,null,icon,null);
                binding.tfNamaLokasi.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        binding.tfNamaLokasi.setCompoundDrawables(null,null,null,null);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (binding.tfNamaLokasi.getText().toString().equals("")){
                            binding.tfNamaLokasi.setCompoundDrawables(null,null,icon,null);
                        }
                    }
                });
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void getLocation(View view){
        //for permission location
        handlePermissionsAndGetLocation();
        //device must have google play service
        checkReqGPS();
        try {
            if (mGoogleApiClient.isConnected()) {
                startLocationUpdates();
            }
        }catch (NullPointerException ex){
            Toast.makeText(this,"Gagal Mendapat Lokasi",Toast.LENGTH_SHORT).show();
        }
    }
}
