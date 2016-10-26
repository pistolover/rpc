package server;

import java.io.File;

public class FileItem {

    private File file;

    private byte[] data;

    private boolean inMemery = false;

    private String fileName;

    public FileItem(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public boolean isInMemery() {
        return inMemery;
    }

    public void setInMemery(boolean inMemery) {
        this.inMemery = inMemery;
    }

    @Override
    public String toString() {
        return "FileItem{" + "file=" + file + ", fileName='" + fileName + '\'' + '}';
    }
}
