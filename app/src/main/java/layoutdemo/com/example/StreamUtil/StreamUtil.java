package layoutdemo.com.example.StreamUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017-10-23.
 */

public class StreamUtil {
    public static String streamToString(InputStream in) {
        String result = "";

        try {
            //创建一个字节数组的写入流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int lenght = 0;
            while ((lenght = in.read(buffer)) != -1){
               outputStream.write(buffer,0,lenght);
                outputStream.flush();
            }
            result = outputStream.toString();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
