package downlodes;

import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 下载器
 */
public class Downloader {
    private String url;
    private String local;
    private List<DownloadInfo> infos;
    private int count;
    private int fileLength;
    private DownloadUI ui;

    public Downloader(String url, String local, int count, DownloadUI ui) {
        this.url = url;
        this.local = local;
        this.count = count;
        this.ui = ui;
        prepareDownload();
    }

    public List<DownloadInfo> getInfos() {
        return infos;
    }

    /**
     * 准备下载
     */
    private void prepareDownload() {
        try {
//          1、获取URL文件大小
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            fileLength = conn.getContentLength();
//          2、创建本地文件并且设置大小
            RandomAccessFile raf = new RandomAccessFile(local, "rw");
            raf.setLength(fileLength);
            raf.close();
//          3、计算下载信息集合
            initDownloadInfos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDownloadInfos() {
        infos = new ArrayList<DownloadInfo>();
        DownloadInfo info = null;
        int block = fileLength / count;
        for (int i = 0; i < count; i++) {
            info = new DownloadInfo();
            info.setURL(url);
            info.setLocal(local);
            info.setIndex(i);

            info.setStart(i * block);
            if (i != count - 1) {
                info.setEnd((i + 1) * block - 1);
            } else {
                info.setEnd(fileLength);
            }
            infos.add(info);
        }

    }

    public void startDownload() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (DownloadInfo info : infos) {
           //ew DownloadThread(info, ui).run();
             pool.execute(new DownloadThread(info, ui));
        }
    }
}
