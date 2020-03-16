package com.example.mqttconsumer.listener;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author chenduo
 * @Date 2020/3/16 13:10
 **/
@Component
public class EmqListener implements IMqttMessageListener {
	@Override
	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
		try {
			System.out.println("topic: " + s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
