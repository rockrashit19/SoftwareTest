package task.task3;

public class Dolphin {
    private boolean believesDolphinsAreSmarter = false;
    private String reasonForBelief = "";

    public void reflectOnIntelligence(String reason) {
        this.believesDolphinsAreSmarter = true;
        this.reasonForBelief = reason;
        System.out.println("Дельфин думает: \"Мы разумнее людей, потому что " + reason + "\"");
    }

    public boolean isBelievesDolphinsAreSmarter() {
        return believesDolphinsAreSmarter;
    }

    public String getReasonForBelief() {
        return reasonForBelief;
    }
}
