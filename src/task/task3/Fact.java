package task.task3;

public class Fact {
    private final String description;
    private boolean isWidelyKnown;
    private boolean isImportant;

    public Fact(String description) {
        this.description = description;
        this.isWidelyKnown = false;
        this.isImportant = false;
    }

    public void makeWidelyKnown() {
        this.isWidelyKnown = true;
        System.out.println("Факт стал широко известен: \"" + this.description + "\"");
    }

    public void markImportant() {
        this.isImportant = true;
        System.out.println("Факт отмечен как важный: \"" + this.description + "\"");
    }

    public String getDescription() {
        return description;
    }

    public boolean isWidelyKnown() {
        return isWidelyKnown;
    }

    public boolean isImportant() {
        return isImportant;
    }
}
