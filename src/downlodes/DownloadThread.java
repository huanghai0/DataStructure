package downlodes;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadThread implements Runnable {

    private DownloadInfo info;
    private DownloadUI ui;

    public DownloadThread(DownloadInfo info, DownloadUI ui) {
        this.info = info;
        this.ui = ui;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(info.getURL());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Range", "bytes=" + info.getStart() + "-" + info.getEnd());
            InputStream is = conn.getInputStream();

            RandomAccessFile raf = new RandomAccessFile(info.getLocal(), "rw");
            raf.seek(info.getStart());
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                raf.write(buffer, 0, len);
                ui.updateBar(info.getIndex(), len);
            }
            raf.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
