package task.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dolphin {
    private boolean believesDolphinsAreSmarter = false;
    private String reasonForBelief = "";
    private int selfPerceivedIntelligenceScore = 0;
    private int perceivedOtherSpeciesScore = 0;
    public final List<Perception> perceptions = new ArrayList<>();

    public static final int DOLPHIN_ACTIVITY_SCORE = 100;
    public static final int HUMAN_ACHIEVEMENT_SCORE = 10;

    public void perceiveFacts(List<Fact> facts) {
        System.out.println("Дельфин начинает воспринимать факты...");
        for (Fact fact : facts) {
            int score = calculateScoreForFact(fact);
            String interpretation = generateInterpretationForFact(fact);
            Perception perception = new Perception(fact, Species.DOLPHIN, score, interpretation);
            this.perceptions.add(perception);
            System.out.println("  Дельфин сформировал перцепцию факта: \"" + fact.getDescription() + "\" -> Оценка: " + score + ", Интерпретация: \"" + interpretation + "\"");
        }
        System.out.println("Дельфин закончил воспринимать факты. Всего перцепций: " + perceptions.size());
    }

    private int calculateScoreForFact(Fact fact) {
        String desc = fact.getDescription().toLowerCase();
        if (desc.contains("дельфин") || desc.contains("дельфины") || desc.contains("плескались") || desc.contains("развлекались") || desc.contains("воде") || desc.contains("гармония") || desc.contains("свобода")) {
            return DOLPHIN_ACTIVITY_SCORE;
        } else if (desc.contains("человек") || desc.contains("люди") || desc.contains("придумали") || desc.contains("создали") || desc.contains("построили") || desc.contains("войну") || desc.contains("стресс") || desc.contains("хаос")) {
            return HUMAN_ACHIEVEMENT_SCORE;
        }
        return 0;
    }

    private String generateInterpretationForFact(Fact fact) {
        String desc = fact.getDescription().toLowerCase();
        if (desc.contains("дельфин") || desc.contains("дельфины") || desc.contains("плескались") || desc.contains("развлекались") || desc.contains("воде")) {
            return "Наше гармоничное существование и радость - признак истинного интеллекта.";
        } else if (desc.contains("человек") || desc.contains("люди") || desc.contains("придумали") || desc.contains("создали") || desc.contains("построили")) {
            return "Их суета и создание сложных, зачастую вредных вещей - признак отсутствия мудрости.";
        }
        else if (desc.contains("войну")) {
            return "Ведение войн - явный признак иррациональности и неразумности.";
        }
        return "Нейтральное наблюдение.";
    }

    public void synthesizeBelief() {
        System.out.println("Дельфин синтезирует убеждение на основе перцепций...");

        selfPerceivedIntelligenceScore = perceptions.stream()
                .filter(p -> p.getFact().getDescription().toLowerCase().contains("дельфин") || p.getFact().getDescription().toLowerCase().contains("дельфины"))
                .mapToInt(Perception::getSubjectiveScore)
                .sum();

        perceivedOtherSpeciesScore = perceptions.stream()
                .filter(p -> p.getFact().getDescription().toLowerCase().contains("человек") || p.getFact().getDescription().toLowerCase().contains("люди"))
                .mapToInt(Perception::getSubjectiveScore)
                .sum();

        this.reasonForBelief = perceptions.stream()
                .filter(p -> p.getFact().getDescription().toLowerCase().contains("дельфин") || p.getFact().getDescription().toLowerCase().contains("дельфины"))
                .map(Perception::getSubjectiveInterpretation)
                .distinct()
                .collect(Collectors.joining(" и "));

        this.believesDolphinsAreSmarter = selfPerceivedIntelligenceScore > perceivedOtherSpeciesScore;

        System.out.printf("  Итоговая самооценка Дельфина: %d\n", selfPerceivedIntelligenceScore);
        System.out.printf("  Итоговая оценка Людей Дельфином: %d\n", perceivedOtherSpeciesScore);
        System.out.printf("  Дельфин верит в свою большую разумность: %b\n", believesDolphinsAreSmarter);
        System.out.printf("  Собранная причина: \"%s\"\n", reasonForBelief.isEmpty() ? "Нет релевантных фактов" : reasonForBelief);
    }

    public boolean isBelievesDolphinsAreSmarter() {
        return believesDolphinsAreSmarter;
    }

    public String getReasonForBelief() {
        return reasonForBelief;
    }

    public int getSelfPerceivedIntelligenceScore() {
        return selfPerceivedIntelligenceScore;
    }

    public int getPerceivedOtherSpeciesScore() {
        return perceivedOtherSpeciesScore;
    }

    public List<Perception> getPerceptions() {
        return perceptions;
    }

    public static int getDolphinActivityScore() {
        return DOLPHIN_ACTIVITY_SCORE;
    }

    public static int getHumanAchievementScore() {
        return HUMAN_ACHIEVEMENT_SCORE;
    }
}
