package com.aisharooble.mapdemo;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    AlertDialog.Builder builder; //'.' denotes (inner) class within a class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        builder = new AlertDialog.Builder(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addInitialMarkers();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {
                //build the builder
                final EditText editText = new EditText(MapsActivity.this);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(editText)
                .setMessage("Type title")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = editText.getText().toString();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(text));
                        //dialog.dismiss();
                        //TODO: handle an empty string.
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
            }
        });
    }

    private void addInitialMarkers() {
        // Add a marker in Sydney and move the camera
        LatLng london = new LatLng(51.476853, 0.0005002);// 1st: latitude; 2nd: longitude; Origin: 51.476853, 0.0005002
        mMap.addMarker(new MarkerOptions().position(london).title("Marker in London"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(london, 12));

        LatLng pizzeria = new LatLng(55.6761, 12.5683);
        mMap.addMarker(new MarkerOptions().position(pizzeria).title("Best Pizzeria"));

//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng LatLng) {
//                AlertDialog ad = new AlertDialog.Builder(MapsActivity.this).setTitle("Title").setMessage("Title?" ).show();
//                mMap.addMarker(new MarkerOptions().position(LatLng).title("?"));
//            }
//        });

        LatLng liveVenue = new LatLng(2.0469, 45.3182);
        mMap.addMarker(new MarkerOptions().position(liveVenue).title("Best Live Venue"));
    }
}
