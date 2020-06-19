package ai.brace;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.Type;

public class Utils {
    /**
     * Here's that ol' Gson magic.
     */
    protected static A getAFromJson(String json) {
        Type type = new TypeToken<A>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    protected static String getJsonFromA(A src) {
        Type type = new TypeToken<A>() {}.getType();
        return new Gson().toJson(src, type);
    }

    protected static int sortAMethod(A o1, A o2) {
        if (o1.getLastModified().compareTo(o2.getLastModified()) < 0) {
            return -1;
        } else if (o1.getLastModified().compareTo(o2.getLastModified()) > 0) {
            return 1;
        }
        return 0;
    }

    protected static int sortTextItemMethod(TextItem o1, TextItem o2) {
        if (o1.getId() < o2.getId()) {
            return -1;
        } else if (o1.getId() > o2.getId()) {
            return 1;
        }
        return 0;
    }

    protected static String stringOfFile(String filename) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        File url = new File(filename);
        IOUtils.copy(url.toURI().toURL().openStream(), output);
        return output.toString();
    }

    protected static void stringToFile(String content, String filename) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
