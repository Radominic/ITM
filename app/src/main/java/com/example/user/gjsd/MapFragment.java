package com.example.user.gjsd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.gjsd.modules.GPSManager;
import com.example.user.gjsd.modules.GuManager;
import com.example.user.gjsd.modules.MarketExplorer;
import com.example.user.gjsd.modules.MyClient;
import com.example.user.gjsd.modules.Point;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private GPSManager gpsManager;
    private MarketExplorer marketExplorer;
    private GuManager guManager;
    private MyClient myClient;

    private MapView mapView;

    @SuppressLint("ValidFragment")
    public MapFragment() {
        // Required empty public constructor
        // 로딩화면에서 셋팅하기

        marketExplorer = new MarketExplorer();
        guManager = new GuManager();
        myClient = new MyClient();
    }

    public void setGpsManager(GPSManager gpsManager) {
        this.gpsManager = gpsManager;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = new MapView(this.getContext());
        mapView.setDaumMapApiKey("a6a70a1cac21fb3bdf4b989ef4226727");

        ViewGroup map_view = (ViewGroup) view.findViewById(R.id.map_view);
        map_view.addView(mapView);

//        default center point
        setMapMyLocation();
        setMarketIncludeN(gpsManager.getMyPoint(), 3);
        mapView.fitMapViewAreaToShowAllPOIItems();
        mapView.zoomOut(true);

        return view;
    }

    public void setMapMyLocation() {
        mapView.setMapCenterPoint(gpsManager.getMyMapPoint(), true);
    }

    public void setMapGuLocation(String guName) {
        mapView.setMapCenterPoint(guManager.getGuMapPoint(guName), true);
    }

    public void setMapMarketLocation(String marketName) {
        mapView.setMapCenterPoint(marketExplorer.getMarketMapPoint(marketName), true);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setMarketIncludeN(Point p, int numOfMarkets) {
        //p기준 정렬
        marketExplorer.updateMarketsSortedByDistance(p);
        //n개 가져오기
        String[] nearMarkets = marketExplorer.getNearNMarkets(numOfMarkets);
        for (String marketName : nearMarkets) {
            createMarker(marketName, "10000");
        }
    }

    private void createMarker(String marketName, String price) {
        MapPOIItem mCustomMarker;
        mCustomMarker = new MapPOIItem();
        String name = marketName;
        mCustomMarker.setItemName(marketName);
        mCustomMarker.setTag(1);
        mCustomMarker.setMapPoint(marketExplorer.getMarketMapPoint(marketName));
        mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mCustomMarker.setCustomImageResourceId(R.drawable.custom_marker_red);
        mCustomMarker.setCustomImageAutoscale(false);
        mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);

        mapView.addPOIItem(mCustomMarker);

//        mapView.selectPOIItem(mCustomMarker, true);
//        mCustomMarker.set
//        mapView.setMapCenterPoint(marketExplorer.getMarketPoint(s), false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
