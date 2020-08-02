package domain.model;

public class Message {
    private int code;
    private String lang;
    private String[] text;

    public int getCode() {
        return code;
    }

    public String getLang() {
        return lang;
    }

    public String[] getText() {
        return text;
    }

    public String getFormattedText() {
        StringBuilder sb = new StringBuilder(text.length);

        for (String str : text) {
            sb.append(str);
        }
        return sb.toString();
    }
}
