package tcp;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket client = new Socket("localhost", 8888);
        System.out.println("开启client端~~~");
        InputStream is = new BufferedInputStream(new FileInputStream("D:\\test\\1.mp3"));
        OutputStream os = new BufferedOutputStream(client.getOutputStream());

        int len = -1;
        byte[] flash = new byte[1024];
        while ((len = is.read(flash)) != -1) {
            os.write(flash, 0, len);
        }
        os.flush();
        os.close();
        is.close();
    }
}
