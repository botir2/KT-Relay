package com.example.hoolanla.mqtt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton;
import android.widget.Switch;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    static String HOSTNAME = "tcp://211.38.86.93";
    String PubTopicStr = "$open-it/relay/order";
    String SubTopicStr = "$open-it/relay/status";
    MqttAndroidClient client;
    TextView subText;
    Switch sw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		sw = findViewById(R.id.switch1);

        subText = findViewById(R.id.subText);
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), HOSTNAME, clientId);
        MqttConnectOptions options = new MqttConnectOptions();

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this,"Conneted", Toast.LENGTH_LONG).show();
                    SetSubscription();
					pubStatus();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this,"Disconnect", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
            	String payload = new String(message.getPayload());

                subText.setText(payload);

                if(payload.equals("on")) {
					sw.setChecked(true);
				} else {
					sw.setChecked(false);
				}
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

		sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
				if (bChecked) {
					relayOn();
					subText.setText("on");
				} else {
					relayOff();
					subText.setText("off");
				}
			}
		});
	}


    public void pubStatus(){

        String topic = PubTopicStr;
        String message = "status";

        try {
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


	public void relayOn(){

		String topic = PubTopicStr;
		String message = "on";

		try {
			client.publish(topic, message.getBytes(),0,false);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}


	public void relayOff(){

		String topic = PubTopicStr;
		String message = "off";

		try {
			client.publish(topic, message.getBytes(),0,false);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}


	public void SetSubscription() {

        try {
            client.subscribe(SubTopicStr, 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void conn(View v){

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this,"Conneted", Toast.LENGTH_LONG).show();
                    SetSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this,"Disconnect", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void disConn(View v){
        try {
            IMqttToken token = client.disconnect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this,"Disconnect.", Toast.LENGTH_LONG).show();
                    SetSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this,"Cannot Disconnect", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
