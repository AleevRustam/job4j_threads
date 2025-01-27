package ru.job4j.threads.threadlocal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String fileName;

    public Wget(String url, int speed) {
        this.url = url;
        this.fileName = extractFileNameFromUrl(url);
        this.speed = speed;
    }

    private static String extractFileNameFromUrl(String url) {
        int lastSlashIndex = url.lastIndexOf('/');
        if (lastSlashIndex == -1 || lastSlashIndex == url.length() - 1) {
            throw new IllegalArgumentException("Invalid URL: Cannot determine file name.");
        }
        return url.substring(lastSlashIndex + 1);
    }

    @Override
    public void run() {
        var startAt = System.currentTimeMillis();
        var file = new File(fileName);
        try (var input = new URL(url).openStream();
            var output = new FileOutputStream(file)) {
            System.out.println("Open connection: "
                    + (System.currentTimeMillis() - startAt)
                    + " ms");
            var dataBuffer = new byte[512];
            int bytesRead;
            var downloadedBytes = 0;
            while ((bytesRead = input.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                long beforeWrite = System.nanoTime();
                output.write(dataBuffer, 0, bytesRead);
                long afterWrite = System.nanoTime();

                downloadedBytes += bytesRead;
                if (downloadedBytes >= speed) {
                    long elapsedTime = System.currentTimeMillis() - startAt;
                    if (elapsedTime < 1000) {
                        Thread.sleep(1000 - elapsedTime);
                    }
                    startAt = System.currentTimeMillis();
                    downloadedBytes = 0;
                }
                System.out.println("Read " + bytesRead + " bytes : " + (afterWrite - beforeWrite) + " ns.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Downloaded file size: " + Files.size(file.toPath()) + " bytes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Use args format: <URL> <speed>");
        }

        String url = args[0];
        int speed = Integer.parseInt(args[1]);

        if (speed <= 0) {
            System.out.println("Speed must be positive.");
            return;
        }

        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
