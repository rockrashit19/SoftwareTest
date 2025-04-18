package task.task3;

public class Human {
    private boolean believesHumansAreSmarter = false;
    private String reasonForBelief = "";

    public void reflectOnIntelligence(String reason) {
        this.believesHumansAreSmarter = true;
        this.reasonForBelief = reason;
        System.out.println("Человек размышляет: \"Мы разумнее дельфинов, потому что " + reason + "\"");
    }

    public boolean isBelievesHumansAreSmarter() {
        return believesHumansAreSmarter;
    }
    public String getReasonForBelief() {
        return reasonForBelief;
    }
}