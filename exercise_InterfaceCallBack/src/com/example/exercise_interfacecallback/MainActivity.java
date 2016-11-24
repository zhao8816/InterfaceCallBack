package com.example.exercise_interfacecallback;

import com.example.exercise_interfacecallback.MyHttpUtils.LoadData;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

//创建一个类，继承自定义的LoadData接口，并重写该接口的两个抽象方法。
public class MainActivity extends Activity implements LoadData{

	private String url ="http://ic.snssdk.com/2/article/v25/stream/?count=20&min_behot_time=1478131494&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=39.906906&bd_longitude=116.397251&bd_loc_time=1478131545&loc_mode=5&loc_time=1478036023&latitude=39.905498333333334&longitude=116.39099833333333&city=%E5%8C%97%E4%BA%AC&lac=4429&cid=19971&iid=6058067105&device_id=33397630772&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=Lenovo%20K30-TM&os_api=19&os_version=4.4.2&uuid=864394010176057&openudid=B00594EF7F290000";
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		
	}
	
	//按钮监听事件
	public void getData (View v){
		
		//实例化自定义的工具类
		MyHttpUtils myHttpUtils = new MyHttpUtils();
		//调用请求网络数据的方法
		myHttpUtils.getData(url);
		//设置监听，把本类的上下文传过去，这样它就可以把请求结果传过来
		myHttpUtils.setLoadData(MainActivity.this);
	}

	//重写接口的抽象方法，拿到网络请求结果
	@Override
	public void failed(String error) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void success(final String success) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				tv.setText(success);
			}
		});
	}
}
