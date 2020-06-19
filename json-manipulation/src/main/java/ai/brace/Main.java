package ai.brace;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String a1File = "src/main/resources/a1.json";
    private static final String a2File = "src/main/resources/a2.json";
    private static final String outputFile = "src/main/resources/output.json";

    public static void main(String[] args) {
        task1();
        System.out.println();
        task2();
        System.out.println();
        task3();
        System.out.println();
        task4();
    }

    public static void task1() {
        String json = null;
        try {
            json = Utils.stringOfFile(a1File);
        } catch (IOException e) {
            e.printStackTrace();
        }
        A aContents = Utils.getAFromJson(json);
        assert aContents != null;
        List<TextItem> textItems = aContents.getTextArray();
        textItems.sort(Utils::sortTextItemMethod);

        for (TextItem text: textItems) {
            System.out.println(text.getTextdata());
        }
    }

    private static void task2() {
        String jsonA1 = null, jsonA2 = null;
        try {
            jsonA1 = Utils.stringOfFile(a1File);
            jsonA2 = Utils.stringOfFile(a2File);
        } catch (IOException e) {
            e.printStackTrace();
        }
        A a1Contents = Utils.getAFromJson(jsonA1);
        A a2Contents = Utils.getAFromJson(jsonA2);
        assert a1Contents != null;
        List<TextItem> textItems = a1Contents.getTextArray();
        assert a2Contents != null;
        textItems.addAll(a2Contents.getTextArray());
        textItems.sort(Utils::sortTextItemMethod);

        for (TextItem text: textItems) {
            System.out.println(text.getTextdata());
        }
    }

    private static void task3() {
        String jsonA1 = null, jsonA2 = null;
        try {
            jsonA1 = Utils.stringOfFile(a1File);
            jsonA2 = Utils.stringOfFile(a2File);
        } catch (IOException e) {
            e.printStackTrace();
        }
        A a1Contents = Utils.getAFromJson(jsonA1);
        A a2Contents = Utils.getAFromJson(jsonA2);
        assert a1Contents != null;
        List<TextItem> textItems = a1Contents.getTextArray();
        assert a2Contents != null;
        textItems.addAll(a2Contents.getTextArray());

        List<String> words = new ArrayList<>();
        for (TextItem textdata: textItems) {
            String text = textdata.getTextdata();
            text = text.toLowerCase();
            text = text.replace("--", " ");
            words.addAll(Arrays.asList(text.split(" ")));
        }

        SortedMap<String, Integer> wordCounts = new TreeMap<>();
        for (String word : words) {
            // using regex, remove punctuation
            Pattern p = Pattern.compile("\\p{Punct}"); // posix thing, which i kind of like
//            Pattern p = Pattern.compile("\\W");      // alternatively you can do "anti-word"
            Matcher m = p.matcher(word);
            word = m.replaceAll("");

            int count = 1;
            if (wordCounts.containsKey(word)) {
                count = wordCounts.get(word) + 1;
            }
            wordCounts.put(word, count);
        }
        for (String word : wordCounts.keySet()) {
            System.out.println("(" + word + ") : " + wordCounts.get(word));
        }
    }

    private static void task4() {
        String jsonA1 = null, jsonA2 = null;
        try {
            jsonA1 = Utils.stringOfFile(a1File);
            jsonA2 = Utils.stringOfFile(a2File);
        } catch (IOException e) {
            e.printStackTrace();
        }
        A a1Contents = Utils.getAFromJson(jsonA1);
        A a2Contents = Utils.getAFromJson(jsonA2);
        assert a1Contents != null;
        assert a2Contents != null;
        List<A> aList = new ArrayList<>();
        aList.add(a1Contents);
        aList.add(a2Contents);
        aList.sort(Utils::sortAMethod);
        for (A a : aList) {
            System.out.println("lastModified (epoch): " + a.getLastModified());
            // Change the lastModified epoch datetime to an ISO format, e.g. 2010-01-01T12:00:00Z
            String isoDate = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    .format(new java.util.Date (Long.parseLong(a.getLastModified()) * 1000));
            a.setLastModified(isoDate);
            System.out.println("lastModified (ISO date): " + a.getLastModified());
        }

        A finalAContents = aList.get(0);
        for (int i = 1; i < aList.size(); i++) {
            finalAContents.merge(aList.get(i));
        }
        finalAContents.getTextArray().sort(Utils::sortTextItemMethod);

        // Replace the uuid with a random one.
        finalAContents.setUuid(UUID.randomUUID().toString());
        System.out.print("finalAContents: " + finalAContents.toString() + "\n");

        // Serialize the new JSON file to disk as output.json
        String finalJson = Utils.getJsonFromA(finalAContents);
        Utils.stringToFile(finalJson, outputFile);
        System.out.println(finalJson);
    }
}
