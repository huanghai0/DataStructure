package socket;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThread {
    private  static ExecutorService tp = Executors.newCachedThreadPool();
    static class HeadleMsg implements Runnable{
        Socket clientSocket;

        public HeadleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
        public void run(){
            BufferedReader is =null;
            PrintWriter os = null;
            try {
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os = new PrintWriter(clientSocket.getOutputStream(),true);
                String inputline = null;
                long b = System.currentTimeMillis();
              //  while ( inputline = is.readLine() != null){

            //    }
            }catch(IOException e){

            }
        }
    }
}
