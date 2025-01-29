package ru.job4j.io;

import java.io.*;

public class ParseFile {
    private final FileReaderUtil fileReaderUtil;
    private final FileWriterUtil fileWriterUtil;

    public ParseFile(FileReaderUtil fileReaderUtil, FileWriterUtil fileWriterUtil) {
        this.fileReaderUtil = fileReaderUtil;
        this.fileWriterUtil = fileWriterUtil;
    }

    public String getContent() throws IOException {
        return fileReaderUtil.getContent();
    }

    public String getContentWithoutUnicode() throws IOException {
        return fileReaderUtil.getContentWithoutUnicode();
    }

    public void saveContent(String content) throws IOException {
        fileWriterUtil.saveContent(content);
    }
}
