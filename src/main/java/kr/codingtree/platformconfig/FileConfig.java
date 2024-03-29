package kr.codingtree.platformconfig;

import kr.codingtree.platformconfig.section.FileSection;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.*;
import java.util.HashMap;

public abstract class FileConfig extends MemoryConfig implements FileSection {

    @Override
    @SneakyThrows(IOException.class)
    public void load(File file) {
        if (!file.exists()) {
            save(file);
        }

        @Cleanup FileInputStream fis = new FileInputStream(file);
        @Cleanup InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        @Cleanup BufferedReader br = new BufferedReader(isr);

        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) sb.append(line + "\n");

        values.clear();

        HashMap<String, Object> map = stringToMap(sb.toString());

        if (map != null && map.size() > 0) valuesToDot(null, values, map);

        if (defaults.size() > 0) save(file);
    }

    @Override
    @SneakyThrows(IOException.class)
    public void save(File file) {
        if (!file.exists()) {
            if (file.getPath().contains("\\")) {
                System.out.println(file.getPath());
                new File(file.getPath().substring(0, file.getPath().lastIndexOf("\\"))).mkdirs();
            } else if (file.getPath().contains("/")) {
                System.out.println(file.getPath());
                new File(file.getPath().substring(0, file.getPath().lastIndexOf("/"))).mkdirs();
            }

            file.createNewFile();
        }

        @Cleanup FileOutputStream fos = new FileOutputStream(file);
        @Cleanup OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        @Cleanup BufferedWriter bw = new BufferedWriter(osw);

        bw.write(saveToString());
    }

    @Override
    public abstract String saveToString();

    @Override
    public abstract HashMap<String, Object> stringToMap(String mapString);
}
