package ai.brace;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a terrible class name.
 */
public class A {
    private String version;
    private String uuid;
    private String lastModified;
    private String title;
    private String author;
    private String translator;
    private String releaseDate;
    private String language;
    private List<TextItem> textArray = new ArrayList<>();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<TextItem> getTextArray() {
        return textArray;
    }

    public void setTextArray(List<TextItem> textArray) {
        this.textArray = textArray;
    }

    public void merge(A a) {
        if (a.version != null) {
            setVersion(a.getVersion());
        }
        if (a.uuid != null) {
            setUuid(a.getUuid());
        }
        if (a.lastModified != null) {
            setLastModified(a.getLastModified());
        }
        if (a.title != null) {
            setTitle(a.getTitle());
        }
        if (a.author != null) {
            setAuthor(a.getAuthor());
        }
        if (a.translator != null) {
            setTranslator(a.getTranslator());
        }
        if (a.releaseDate != null) {
            setReleaseDate(a.getReleaseDate());
        }
        if (a.language != null) {
            setLanguage(a.getLanguage());
        }
        this.textArray.addAll(a.getTextArray());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("version", version)
                .add("uuid", uuid)
                .add("lastModified", lastModified)
                .add("title", title)
                .add("author", author)
                .add("translator", translator)
                .add("releaseDate", releaseDate)
                .add("language", language)
                .add("textArray", textArray)
                .toString();
    }
}

class TextItem {
    private int id;
    private String textdata;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextdata() {
        return textdata;
    }

    public void setTextdata(String textdata) {
        this.textdata = textdata;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("textdata", textdata)
                .toString();
    }
}
