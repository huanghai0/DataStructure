package downlodes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;


public class DownloadUI extends JFrame implements ActionListener {

    private JLabel labURLFile;    //源文件标签
    private JLabel labLocalDir;   //目标文件标签
    private JLabel labCount;

    private JTextField tfURLFile;
    private JTextField tfLocalFile;
    private JTextField tfCount;
    private JButton btnStart;
    public JProgressBar[] bars;

    //private static final long serialVersionUID = -503 5867 4813 0741 6008L;
    private static final long serialVersionUID = -2468542159874568741L;
    public DownloadUI() {
        init();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void init() {
        this.setTitle("多线程下载器");
        this.setBounds(100, 100, 650, 600);
        this.setLayout(null);

        //srcFile标签
        labURLFile = new JLabel("URL文件:");
        labURLFile.setBounds(10, 10, 80, 50);
        this.add(labURLFile);

        tfURLFile = new JTextField();
        tfURLFile.setBounds(70, 20, 500, 30);
        tfURLFile.setText("");
        this.add(tfURLFile);

        labLocalDir = new JLabel("保存路径");
        labLocalDir.setBounds(10, 60, 80, 50);
        this.add(labLocalDir);

        tfLocalFile = new JTextField();
        tfLocalFile.setBounds(70, 75, 500, 30);
        tfLocalFile.setText("D:\\");
        this.add(tfLocalFile);


        labCount = new JLabel("线程数");
        labCount.setBounds(10, 120, 80, 50);
        this.add(labCount);


        tfCount = new JTextField();
        tfCount.setBounds(70, 130, 500, 30);
        this.add(tfCount);


        btnStart = new JButton("开始");
        btnStart.setBounds(10, 170, 100, 30);
        this.add(btnStart);
        btnStart.addActionListener(this);


        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        //开始按钮
        if (source == btnStart) {
            String url = tfURLFile.getText();
            String local = tfLocalFile.getText();
            int count = Integer.parseInt(tfCount.getText());

            Downloader downloader = new Downloader(url, local, count, this);
//            2、取得infos
            List<DownloadInfo> infos = downloader.getInfos();
//            3、动态添加进度条
            this.addBars(infos);
//            4、开始下载
            downloader.startDownload();
        }
    }

    private void addBars(List<DownloadInfo> infos) {
        bars = new JProgressBar[infos.size()];
        int yOffset = 210;
        for (DownloadInfo info : infos) {
            bars[info.getIndex()] = new JProgressBar();
            bars[info.getIndex()].setMaximum(info.getEnd() - info.getStart() + 1);
            bars[info.getIndex()].setValue(0);
            bars[info.getIndex()].setBounds(10, yOffset + info.getIndex() * 50, 550, 30);
            this.add(bars[info.getIndex()]);
        }
        this.repaint();
    }

    public void updateBar(int index, int len) {
        bars[index].setValue(bars[index].getValue() + len);
    }
}
