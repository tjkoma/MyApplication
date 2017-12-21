package com.jshc.waveprogressbar.activitys;

import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.jshc.waveprogressbar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的地图界面
 */
public class MyMapActivity extends AppCompatActivity {

    @BindView(R.id.base_title_textView)
    TextView baseTitleTextView;
    @BindView(R.id.mapView)
    MapView mapView;

    private MyLocationStyle myLocationStyle;
    private AMap aMap;
    private UiSettings uiSettings;
    private AMapLocationClient aMapLocationClient;
    private AMapLocationClientOption locationClientOption;
    private boolean followMove = true;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map);
        ButterKnife.bind(this);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        initView();
        showBlueLacation();
    }

    private void initView() {
        baseTitleTextView.setText("我的地图");
        uiSettings = aMap.getUiSettings();
        aMapLocationClient = new AMapLocationClient(getApplicationContext());
        locationClientOption = new AMapLocationClientOption();
        locationClientOption.setOnceLocation(true);//设置单次定位
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setTrafficEnabled(true);//显示交通状况
        aMap.setMapType(AMap.MAP_TYPE_NAVI);//设置成导航地图
        uiSettings.setMyLocationButtonEnabled(true);
        /**
         * 监听位置变化
         */
        aMapLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                aMapLocationClient.stopLocation();
                latitude = aMapLocation.getLatitude();
                longitude = aMapLocation.getLongitude();
            }
        });
    }

    //显示定位蓝点
    private void showBlueLacation() {
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        myLocationStyle.showMyLocation(true);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if (followMove) {
                    aMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
                }
            }
        });
        aMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                if (followMove) {
                    followMove = false;
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }
}
