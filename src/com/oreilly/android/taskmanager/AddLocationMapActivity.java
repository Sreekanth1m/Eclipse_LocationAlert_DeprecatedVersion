package com.oreilly.android.taskmanager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MyLocationOverlay;

public class AddLocationMapActivity extends Activity implements
		OnMapClickListener, View.OnClickListener {

	public static final String ADDRESS_RESULT = "address";

	private Button mapLocationButton;
	private Button useLocationButton;
	private EditText addressText;
	private GoogleMap mapView;
	private Address address;
	private MyLocationOverlay myLocationOverlay;
	private final LatLng LOCATION_BANG = new LatLng(12.9172909, 77.6229143);
	LatLng llcurLoc, tempLoc, userLoc;
	Location curLoc;
	private UiSettings uiSettings;
	private CameraUpdate selectedLoc;
	private double locLat, locLan;
	List<Address> addresses;
	Geocoder geocoder;
	String area, city, state, zip, country, addr;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.add_location);

		mapView = ((MapFragment) getFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		mapView.setMyLocationEnabled(true);

		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = service.getBestProvider(criteria, false);
		Location location = service.getLastKnownLocation(provider);
		LatLng userLocation = new LatLng(location.getLatitude(),
				location.getLongitude());

		curLoc = mapView.getMyLocation();

		if (curLoc != null) {
			llcurLoc = new LatLng(curLoc.getLatitude(), curLoc.getLongitude());
			tempLoc = llcurLoc;
		} else
			tempLoc = userLocation;

		mapView.addMarker(new MarkerOptions().position(tempLoc).title(
				"You are here"));
		mapView.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		CameraUpdate currentLoc = CameraUpdateFactory
				.newLatLngZoom(tempLoc, 12);

		mapView.animateCamera(currentLoc);
		uiSettings = mapView.getUiSettings();
		uiSettings.setZoomControlsEnabled(true);
		uiSettings.setMyLocationButtonEnabled(true);
		mapView.setOnMapClickListener(this);

		// Toast.makeText(this, "Unable to identify current location",
		// Toast.LENGTH_LONG).show();

		addressText = (EditText) findViewById(R.id.task_address);
		mapLocationButton = (Button) findViewById(R.id.map_location_button);
		useLocationButton = (Button) findViewById(R.id.use_this_location_button);
		mapLocationButton.setOnClickListener(this);
		useLocationButton.setOnClickListener(this);
		// setUpViews();
	}

	public void onMapClick(LatLng l) {
		// TODO Auto-generated method stub
		locLat = l.latitude;
		locLan = l.longitude;
		geocoder = new Geocoder(this, Locale.getDefault());

		try {
			addresses = geocoder.getFromLocation(locLat, locLan, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.w("My Current loction address", "Canont get Address!");
		}

		area = addresses.get(0).getSubLocality();
		city = addresses.get(0).getLocality();
		state = addresses.get(0).getAdminArea();
		zip = addresses.get(0).getPostalCode();
		country = addresses.get(0).getCountryName();

		addr = area + city + state + zip + country;
		addressText.setText(addr);
		Toast.makeText(this, addr, Toast.LENGTH_LONG).show();

	}

	// @Override
	protected void onResume() {
		super.onResume();
		mapView.setMyLocationEnabled(true);

	}

	@Override
	protected void onPause() {
		super.onPause();
		mapView.setMyLocationEnabled(false);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.use_this_location_button:
			
			break;
		case R.id.map_location_button:
			String taskAddress = addressText.getText().toString();
			getLatLongFromGivenAddress(taskAddress);
	       // mapView.moveCamera(CameraUpdateFactory.newLatLngZoom(userLoc, 12));
	        mapView.animateCamera(CameraUpdateFactory.newLatLngZoom(userLoc, 12));
	        onMapClick(userLoc);
	        break;
		
	}
	}
	public void getLatLongFromGivenAddress(String address) {
		double lat = 0.0, lng = 0.0;

		Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
		try {
			List<Address> addresses = geoCoder.getFromLocationName(address, 1);
			if (addresses.size() > 0) {
				GeoPoint p = new GeoPoint(
						(int) (addresses.get(0).getLatitude() * 1E6),
						(int) (addresses.get(0).getLongitude() * 1E6));

				lat = p.getLatitudeE6() / 1E6;
				lng = p.getLongitudeE6() / 1E6;
				userLoc = new LatLng(lat, lng);
				Log.d("Latitude", "" + lat);
				Log.d("Longitude", "" + lng);
			}
		} catch (Exception e) {
			e.printStackTrace();
			FetchLatLongFromService(address);
		}
	}

	private void FetchLatLongFromService(String address2) {

		String uri = "http://maps.google.com/maps/api/geocode/json?address="
				+ address2 + "&sensor=false";
		HttpGet httpGet = new HttpGet(uri);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		StringBuilder stringBuilder = new StringBuilder();
		try {
	        response = client.execute(httpGet);
	        HttpEntity entity = response.getEntity();
	        InputStream stream = entity.getContent();
	        int b;
	        while ((b = stream.read()) != -1) {
	            stringBuilder.append((char) b);
	        }
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

		JSONObject jsonObject = new JSONObject();
	    try {
	        jsonObject = new JSONObject(stringBuilder.toString());

	        double lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
	            .getJSONObject("geometry").getJSONObject("location")
	            .getDouble("lng");

	        double lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
	            .getJSONObject("geometry").getJSONObject("location")
	            .getDouble("lat");

	        Log.d("latitude"," " + lat);
	        Log.d("longitude"," " + lng);
	        userLoc = new LatLng(lat,lng);
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }

	}
	}

