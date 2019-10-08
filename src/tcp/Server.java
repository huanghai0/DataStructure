package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("开启server端~~~~");
        Socket client = server.accept();
        InputStream is = new BufferedInputStream(client.getInputStream());
        OutputStream os = new BufferedOutputStream(new FileOutputStream("D:\\test\\tcp.mp3"));
        byte[] flash = new byte[1024];
        int len = -1;
        while ((len = is.read(flash)) != -1) {
            os.write(flash, 0, len);
        }
        os.flush();
        os.close();
        is.close();
    }
}
