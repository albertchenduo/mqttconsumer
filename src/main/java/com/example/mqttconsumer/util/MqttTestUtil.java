package com.example.mqttconsumer.util;

import com.alibaba.fastjson.JSON;
import com.example.mqttconsumer.listener.EmqListener;
import com.example.mqttconsumer.listener.SharedSubCallbackRouter;
import org.eclipse.paho.client.mqttv3.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author chenduo
 * @Date 2020/3/16 13:06
 **/
public class MqttTestUtil {

	public static void main(String[] args) {
		String broker = "tcp://localhost:1883";
		String clientId = "c2";

		try {
			MqttClient sampleClient = new MqttClient(broker, clientId);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			sampleClient.connect(connOpts);

			Map<String, IMqttMessageListener> listeners = new HashMap<>();
			listeners.put("$share/group2/mqtt", new IMqttMessageListener() {
				@Override
				public void messageArrived(String topic, MqttMessage message) throws Exception {
					//message received
					System.out.println("topic:"+topic);
					System.out.println(JSON.toJSONString(message));
				}
			});
			sampleClient.setCallback(new SharedSubCallbackRouter(listeners));
			sampleClient.subscribe("mqtt",new EmqListener());



		} catch (MqttException me) {
			System.out.println("reason" + me.getReasonCode());
			System.out.println("msg" + me.getMessage());
			System.out.println("loc" + me.getLocalizedMessage());
			System.out.println("cause" + me.getCause());
			System.out.println("excep" + me);
			me.printStackTrace();
		}
	}
}
