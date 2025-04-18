package task.task3;

public class Perception {
    private final Fact fact;
    private final Species perceivingSpecies;
    private final int subjectiveScore;
    private final String subjectiveInterpretation;

    public Perception(Fact fact, Species perceivingSpecies, int subjectiveScore, String subjectiveInterpretation) {
        this.fact = fact;
        this.perceivingSpecies = perceivingSpecies;
        this.subjectiveScore = subjectiveScore;
        this.subjectiveInterpretation = subjectiveInterpretation;
    }

    public Fact getFact() {
        return fact;
    }

    public Species getPerceivingSpecies() {
        return perceivingSpecies;
    }

    public int getSubjectiveScore() {
        return subjectiveScore;
    }

    public String getSubjectiveInterpretation() {
        return subjectiveInterpretation;
    }

}
