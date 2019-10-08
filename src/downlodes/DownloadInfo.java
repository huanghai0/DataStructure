package downlodes;

public class DownloadInfo {
    private String URL;
    private String local;
    private int start;
    private int end;
    private int index;

    public String getURL() {
        return URL;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
