package com.example.exercise_interfacecallback;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class MyHttpUtils {	// 定义一个请求网络数据的工具类
	
	private LoadData loadData;//记录外部传过来的接口对象


	// 创建请求网络的方法（使用xUtils请求）
	public void getData(String url) {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				//把请求网络返回的结果传递给接口对象的failed()方法，外部类重写这个方法的时候，也能得到这个结果了（下同）
				loadData.failed(arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result = arg0.result;
				loadData.success(result);
			}
		});
	}

	// 定义一个接口，其他类继承这个接口，就可以实现这个接口的抽象方法
	public interface LoadData {
		// 创建两个抽象方法（抽象方法没有方法体）
		void failed(String error);

		void success(String success);
	}

	// 提供一个可供外部访问的方法
	public void setLoadData(LoadData loadData) {
		this.loadData = loadData;
	}
}
