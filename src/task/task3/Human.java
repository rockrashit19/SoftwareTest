package task.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Human {
    private boolean believesHumansAreSmarter = false;
    private String reasonForBelief = "";
    private int selfPerceivedIntelligenceScore = 0;
    private int perceivedOtherSpeciesScore = 0;
    public final List<Perception> perceptions = new ArrayList<>();

    public static final int HUMAN_ACHIEVEMENT_SCORE = 100;
    public static final int DOLPHIN_ACTIVITY_SCORE = 10;

    public void perceiveFacts(List<Fact> facts) {
        System.out.println("Человек начинает воспринимать факты...");
        for (Fact fact : facts) {
            int score = calculateScoreForFact(fact);
            String interpretation = generateInterpretationForFact(fact);
            Perception perception = new Perception(fact, Species.HUMAN, score, interpretation);
            this.perceptions.add(perception);
            System.out.println("  Человек сформировал перцепцию факта: \"" + fact.getDescription() + "\" -> Оценка: " + score + ", Интерпретация: \"" + interpretation + "\"");
        }
        System.out.println("Человек закончил воспринимать факты. Всего перцепций: " + perceptions.size());
    }

    private int calculateScoreForFact(Fact fact) {
        String desc = fact.getDescription().toLowerCase();
        if (desc.contains("человек") || desc.contains("люди") || desc.contains("придумали") || desc.contains("создали") || desc.contains("построили") || desc.contains("войну")) {
            return HUMAN_ACHIEVEMENT_SCORE;
        } else if (desc.contains("дельфин") || desc.contains("дельфины") || desc.contains("плескались") || desc.contains("развлекались") || desc.contains("воде")) {
            return DOLPHIN_ACTIVITY_SCORE;
        }
        return 0;
    }

    private String generateInterpretationForFact(Fact fact) {
        String desc = fact.getDescription().toLowerCase();
        if (desc.contains("человек") || desc.contains("люди") || desc.contains("придумали") || desc.contains("создали") || desc.contains("построили")) {
            return "Наши достижения показывают нашу изобретательность и способность преобразовывать мир.";
        } else if (desc.contains("войну")) {
            return "Способность к сложным социальным структурам и стратегиям, пусть и разрушительным.";
        }
        else if (desc.contains("дельфин") || desc.contains("дельфины") || desc.contains("плескались") || desc.contains("развлекались") || desc.contains("воде")) {
            return "Их действия кажутся простыми и без цели, не требуют сложного интеллекта.";
        }
        return "Нейтральное наблюдение.";
    }

    public void synthesizeBelief() {
        System.out.println("Человек синтезирует убеждение на основе перцепций...");

        selfPerceivedIntelligenceScore = perceptions.stream()
                .filter(p -> p.getFact().getDescription().toLowerCase().contains("человек") || p.getFact().getDescription().toLowerCase().contains("люди"))
                .mapToInt(Perception::getSubjectiveScore)
                .sum();

        perceivedOtherSpeciesScore = perceptions.stream()
                .filter(p -> p.getFact().getDescription().toLowerCase().contains("дельфин") || p.getFact().getDescription().toLowerCase().contains("дельфины"))
                .mapToInt(Perception::getSubjectiveScore)
                .sum();


        this.reasonForBelief = perceptions.stream()
                .filter(p -> p.getFact().getDescription().toLowerCase().contains("человек") || p.getFact().getDescription().toLowerCase().contains("люди"))
                .map(Perception::getSubjectiveInterpretation)
                .distinct() // Избегаем повторений, если несколько фактов приводят к одной интерпретации
                .collect(Collectors.joining(" и "));

        this.believesHumansAreSmarter = selfPerceivedIntelligenceScore > perceivedOtherSpeciesScore;

        System.out.printf("  Итоговая самооценка Человека: %d\n", selfPerceivedIntelligenceScore);
        System.out.printf("  Итоговая оценка Дельфинов Человеком: %d\n", perceivedOtherSpeciesScore);
        System.out.printf("  Человек верит в свою большую разумность: %b\n", believesHumansAreSmarter);
        System.out.printf("  Собранная причина: \"%s\"\n", reasonForBelief.isEmpty() ? "Нет релевантных фактов" : reasonForBelief);
    }

    public boolean isBelievesHumansAreSmarter() {
        return believesHumansAreSmarter;
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

    public static int getHumanAchievementScore() {
        return HUMAN_ACHIEVEMENT_SCORE;
    }

    public static int getDolphinActivityScore() {
        return DOLPHIN_ACTIVITY_SCORE;
    }


}
