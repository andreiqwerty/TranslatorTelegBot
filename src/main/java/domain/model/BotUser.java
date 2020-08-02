package domain.model;

public class BotUser {
    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String languageCode = "ru";
    private String translationLang = "en";

    public BotUser(int userId, String firstName, String lastName, String userName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public int getId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getTranslationLang() {
        return translationLang;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public void setTranslationLang(String translationLang) {
        this.translationLang = translationLang;
    }
}
