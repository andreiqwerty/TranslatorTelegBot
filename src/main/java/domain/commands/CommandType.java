package domain.commands;

public enum CommandType {
    START("Начни использовать бота!"),
    HELP("Узнать полный список команд"),
    LANGINFO("Узнать установки языка"),
    SWAPLANG("Поменять язык перевода");

    public String getDescription() {
        return description;
    }

    private String description;

    CommandType(String description){
        this.description = description;
    }


}
