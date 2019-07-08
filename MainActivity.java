package com.example.user.kt_mqtt_ser;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {
    private Switch simpleSwitch1;
    private Switch simpleSwitchcon1;
    private Switch simpleSwitchcon2;
    private Switch simpleSwitchcon3;
    private Switch simpleSwitchcon4;
    private Switch simpleSwitchcon5;
    private Switch simpleSwitch2;
    private Switch simpleSwitch3;
    private Switch simpleSwitch4;
    private Switch simpleSwitch5;
    private TextView TextView;
    private TextView Subtext;
    ToggleButton toggle;
    static String MQTTHOST  = "tcp://211.38.86.93:1883";

    String topicSTR = "$open-it/relay/order";
    String topiCStatus = "$open-it/relay/status";

    String chanel_1 = "$open-it/switch/D4668F89-F22E-45CA-A2E8-1A6489CB9BB8/order";
    String chanel_Status_1 = "$open-it/switch/D4668F89-F22E-45CA-A2E8-1A6489CB9BB8/status";

    String chanel_2 = "$open-it/switch/D4668F89-F22E-45CA-A2E8-1A6489CB9BB8/order";
    String chanel_Status_2 = "$open-it/switch/D4668F89-F22E-45CA-A2E8-1A6489CB9BB8/status";

    String chanel_3 = "$open-it/switch/D4668F89-F22E-45CA-A2E8-1A6489CB9BB8";
    String chanel_Status_3 = "$open-it/switch/D4668F89-F22E-45CA-A2E8-1A6489CB9BB8/status";

    String chanel_4 = "$open-it/switch/D4668F89-F22E-45CA-A2E8-1A6489CB9BB8";
    String chanel_Status_4 = "$open-it/switch/D4668F89-F22E-45CA-A2E8-1A6489CB9BB8/status";

    String chanel_5 = "$open-it/switch/D4668F89-F22E-45CA-A2E8-1A6489CB9BB8";
    String chanel_Status_5 = "$open-it/switch/D4668F89-F22E-45CA-A2E8-1A6489CB9BB8/status";

    MqttAndroidClient client;
    static String Condition;
    Handler setDelay;
    Runnable startDelay;
    static boolean mbool;
    public boolean isMbool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Subtext = (TextView)findViewById(R.id.subtext);

        setDelay = new Handler();
        mqttconncetion();
        initView();
        client.setCallback(new MqttCallback() {

            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String payload = new String(message.getPayload());
                Subtext.setText(payload);

                if(payload.equals("ch1 on")) {
                    simpleSwitch1.setChecked(true);
                    simpleSwitchcon1.setChecked(true);

                }else if(payload.equals("ch1 off")){
                    simpleSwitch1.setChecked(false);
                    simpleSwitchcon1.setChecked(false);
                }
                else if(payload.equals("ch2 on")){
                    simpleSwitch2.setChecked(true);
                    simpleSwitchcon2.setChecked(true);
                }
                else if(payload.equals("ch2 off")){
                    simpleSwitch2.setChecked(false);
                    simpleSwitchcon2.setChecked(false);
                }
                else if(payload.equals("ch3 on")){
                    simpleSwitch3.setChecked(true);
                    simpleSwitchcon3.setChecked(true);
                }
                else if(payload.equals("ch3 off")){
                    simpleSwitch3.setChecked(false);
                    simpleSwitchcon3.setChecked(false);
                }
                else if(payload.equals("ch4 on")){
                    simpleSwitch4.setChecked(true);
                    simpleSwitchcon4.setChecked(true);
                }
                else if(payload.equals("ch4 off")){
                    simpleSwitch4.setChecked(false);
                    simpleSwitchcon4.setChecked(false);
                }
                else if(payload.equals("ch5 on")){
                    simpleSwitch5.setChecked(true);
                    simpleSwitchcon5.setChecked(true);
                }
                else if(payload.equals("ch5 off")){
                    simpleSwitch5.setChecked(false);
                    simpleSwitchcon5.setChecked(false);
                }
                else if(payload.equals("all on")){
                    simpleSwitch1.setChecked(true);
                    simpleSwitchcon1.setChecked(true);
                    simpleSwitch2.setChecked(true);
                    simpleSwitchcon2.setChecked(true);
                    simpleSwitch3.setChecked(true);
                    simpleSwitchcon3.setChecked(true);
                    simpleSwitch4.setChecked(true);
                    simpleSwitchcon4.setChecked(true);
                    simpleSwitch4.setChecked(true);
                    simpleSwitchcon4.setChecked(true);
                    simpleSwitch5.setChecked(true);
                    simpleSwitchcon5.setChecked(true);
                }
                else if(payload.equals("all off")){
                    simpleSwitch1.setChecked(false);
                    simpleSwitchcon1.setChecked(false);
                    simpleSwitch2.setChecked(false);
                    simpleSwitchcon2.setChecked(false);
                    simpleSwitch3.setChecked(false);
                    simpleSwitchcon3.setChecked(false);
                    simpleSwitch4.setChecked(false);
                    simpleSwitchcon4.setChecked(false);
                    simpleSwitch4.setChecked(false);
                    simpleSwitchcon4.setChecked(false);
                    simpleSwitch5.setChecked(false);
                    simpleSwitchcon5.setChecked(false);
                }
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }

        });

    }

    // connection to MQTT sercver
    public void mqttconncetion(){
        String clientId = MqttClient.generateClientId();
        client =  new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);
        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this,"Conneted", Toast.LENGTH_LONG).show();
                   sendMessagestatus();
                   setSubscrition();
                   sucmassage();
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

    //set buttons
    private void initView() {

        simpleSwitch1 = (Switch) findViewById(R.id.simpleSwitch1);
        simpleSwitch2 = (Switch) findViewById(R.id.simpleSwitch2);
        simpleSwitch3 = (Switch) findViewById(R.id.simpleSwitch3);
        simpleSwitch4 = (Switch) findViewById(R.id.simpleSwitch4);
        simpleSwitch5 = (Switch) findViewById(R.id.simpleSwitch5);

        simpleSwitchcon1 = (Switch) findViewById(R.id.switch1);
        simpleSwitchcon2 = (Switch) findViewById(R.id.switch2);
        simpleSwitchcon3 = (Switch) findViewById(R.id.switch3);
        simpleSwitchcon4 = (Switch) findViewById(R.id.switch4);
        simpleSwitchcon5 = (Switch) findViewById(R.id.switch5);

        TextView = (TextView) findViewById(R.id.Textview);

        toggle = (ToggleButton)findViewById(R.id.toggleButton);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(toggle.isChecked()){
                    Relay_on();
                }
               else{
                    RelayOff();
                }
            }
        });

//      condiotion.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//              Relay_or();
//          }
//      });

       // Toast.makeText(MainActivity.this, Condition, Toast.LENGTH_LONG).show();

        //set the switch to ON

        //attach a listener to check for changes in state
        simpleSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (simpleSwitch1.isChecked()) {
                    TextView.setText("ch1 on");
                   // relayOn();
                    sendMessage();
                } else {
                    TextView.setText("ch1 off");
                   // relayOff();
                    sendMessage();
                }
                startDelay = new Runnable() {
                    @Override
                    public void run() {
                        sendMessagestatus();
                    }
                };

                setDelay.postDelayed(startDelay, 1000);
            }

        });

        simpleSwitchcon1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (simpleSwitchcon1.isChecked()) {
                    TextView.setText("ch1 on");
                    sendMessage();
                } else {
                    TextView.setText("ch1 off");
                    sendMessage();
                }
            }
        });

        simpleSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (simpleSwitch2.isChecked()) {
                    TextView.setText("ch2 on");
                    sendMessage();
                } else {
                    TextView.setText("ch2 off");
                    sendMessage();
                }
                startDelay = new Runnable() {
                    @Override
                    public void run() {
                        sendMessagestatus();
                    }
                };

                setDelay.postDelayed(startDelay, 1000);
            }

        });

        simpleSwitchcon2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (simpleSwitchcon2.isChecked()) {
                    TextView.setText("ch2 on");
                    sendMessage();
                } else {
                    TextView.setText("ch2 off");
                    sendMessage();
                }
            }
        });

        simpleSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (simpleSwitch3.isChecked()) {
                    TextView.setText("ch3 on");
                    sendMessage();
                } else {
                    TextView.setText("ch3 off");
                    sendMessage();
                }
                startDelay = new Runnable() {
                    @Override
                    public void run() {
                        sendMessagestatus();
                    }
                };

                setDelay.postDelayed(startDelay, 1000);
            }

        });
        simpleSwitchcon3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (simpleSwitchcon3.isChecked()) {
                    TextView.setText("ch3 on");
                    sendMessage();
                } else {
                    TextView.setText("ch3 off");
                    sendMessage();
                }
            }
        });

        simpleSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (simpleSwitch4.isChecked()) {
                    TextView.setText("ch4 on");
                    sendMessage();
                } else {
                    TextView.setText("ch4 off");
                    sendMessage();
                }
                startDelay = new Runnable() {
                    @Override
                    public void run() {
                        sendMessagestatus();
                    }
                };

                setDelay.postDelayed(startDelay, 1000);
            }

        });
        simpleSwitchcon4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (simpleSwitchcon4.isChecked()) {
                    TextView.setText("ch4 on");
                    sendMessage();
                } else {
                    TextView.setText("ch4 off");
                    sendMessage();
                }
            }
        });

        simpleSwitch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (simpleSwitch5.isChecked()) {
                    TextView.setText("ch5 on");
                    sendMessage();
                } else {
                    TextView.setText("ch5 off");
                    sendMessage();
                }
                startDelay = new Runnable() {
                    @Override
                    public void run() {
                        sendMessagestatus();
                    }
                };

                setDelay.postDelayed(startDelay, 1000);
            }

        });
        simpleSwitchcon5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (simpleSwitchcon5.isChecked()) {
                    TextView.setText("ch5 on");
                    sendMessage();
                } else {
                    TextView.setText("ch5 off");
                    sendMessage();
                }
            }
        });

//
//       simpleSwitch1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(simpleSwitch1.isChecked()){
//                    TextView.setText("on");
//                    sendMessage();
//                }else{
//                    TextView.setText("off");
//                    sendMessage();
//                }
//
//                startDelay = new Runnable() {
//                    @Override
//                    public void run() {
//                        sendMessagestatus();
//                    }
//                };
//                    setDelay.postDelayed(startDelay, 1000);
//            }
//        });
    }

    public void sucmassage(){
        isMbool = mbool;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    // mesage is sending
    private void  sendMessagestatus(){
        String topic = chanel_1;
        String masseage = "status";
        try{
            client.publish(topic, masseage.getBytes() ,1,false);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

    // mesage is sending
    private void sendMessage(){
     String topic = chanel_1;
     String masseage = TextView.getText().toString();
        try{
            client.publish(topic, masseage.getBytes() ,1,false);
        }catch (MqttException e){
         e.printStackTrace();
        }
    }

    private void setSubscrition(){
        try{
            client.subscribe(chanel_Status_1, 1);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

    public void relayOn(){
        String topic = chanel_1;
        String message = "ch1 on";
        try {
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void RelayOff(){
        String topic = chanel_1;
        String message = "all off";
        try {
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void Relay_on(){
        String topic = chanel_1;
        String message = "all on";
        try {
            client.publish(topic, message.getBytes(),1,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
