package layoutdemo.com.example.a1netsourcecodeinsight;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import layoutdemo.com.example.StreamUtil.StreamUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_url;
    private TextView tv_performCode;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.找到控件
        Button bt_searchURL = (Button) findViewById(R.id.bt_searchURL);
        et_url = (EditText) findViewById(R.id.et_url);
        tv_performCode = (TextView) findViewById(R.id.tv_performCode);
        //2.设置点击事件
        bt_searchURL.setOnClickListener(this);
        System.out.println("onCreate:"+Thread.currentThread().toString());

    }

    @Override
    public void onClick(View view) {
        //3.获取输入的地址
        final String urlString = et_url.getText().toString().trim();
        System.out.println("onClick:"+Thread.currentThread().toString());
        //4.查询获取的地址的内容
        /*
        * 需要使用到UrlConnetion类；
        * */
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    System.out.println("onClick:run()"+Thread.currentThread().toString());
                    try {
                        //4.1创建一个url对象
                        URL url = new URL(urlString);
                        //4.2获取一个urlConnection对象
                        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                        //4.3 为HttpURLConnection对象设置一些参数，请求方式，连接的超时时间
                        connection.setRequestMethod("GET");
                        System.out.println("11111111");
                        connection.setReadTimeout(1000);
                        //4.4 获取响应码
                        int code = connection.getResponseCode();
                        if (code == 200){
                            //5.获取有效的数据，并将数据解析成为String
                            InputStream stream = connection.getInputStream();
                            String result = StreamUtil.streamToString(stream);
                            tv_performCode.setText(result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
