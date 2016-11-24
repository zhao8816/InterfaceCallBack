package com.example.exercise_interfacecallback;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class MyHttpUtils {	// ����һ�������������ݵĹ�����
	
	private LoadData loadData;//��¼�ⲿ�������Ľӿڶ���


	// ������������ķ�����ʹ��xUtils����
	public void getData(String url) {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				//���������緵�صĽ�����ݸ��ӿڶ����failed()�������ⲿ����д���������ʱ��Ҳ�ܵõ��������ˣ���ͬ��
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

	// ����һ���ӿڣ�������̳�����ӿڣ��Ϳ���ʵ������ӿڵĳ��󷽷�
	public interface LoadData {
		// �����������󷽷������󷽷�û�з����壩
		void failed(String error);

		void success(String success);
	}

	// �ṩһ���ɹ��ⲿ���ʵķ���
	public void setLoadData(LoadData loadData) {
		this.loadData = loadData;
	}
}
