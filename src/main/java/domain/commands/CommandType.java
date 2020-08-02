package domain.commands;

public enum CommandType {
    START("Start now!"),
    HELP("List of commands"),
    LANGINFO("Get lang settings"),
    SWAPLANG("Change the lang");

    public String getDescription() {
        return description;
    }

    private String description;

    CommandType(String description){
        this.description = description;
    }


}
