package task.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scenario {
    private final List<Fact> facts = new ArrayList<>();
    private Human human;
    private Dolphin dolphin;

    public void addFact(Fact fact) {
        this.facts.add(fact);
        System.out.println("Добавлен факт в сценарий: \"" + fact.getDescription() + "\"");
    }

    public void setupEntities(Human human, Dolphin dolphin) {
        this.human = human;
        this.dolphin = dolphin;
        System.out.println("Сущности Человек и Дельфин добавлены в сценарий.");
    }

    public void run() {
        if (human == null || dolphin == null) {
            System.err.println("Ошибка: Сущности не настроены для сценария!");
            return;
        }

        System.out.println("\n--- Запуск сценария: Восприятие фактов ---");


        human.perceiveFacts(facts);
        System.out.println();
        dolphin.perceiveFacts(facts);


        System.out.println("\n--- Синтез убеждений ---");
        human.synthesizeBelief();
        System.out.println();
        dolphin.synthesizeBelief();

        System.out.println("\n--- Результаты сценария ---");
        System.out.println("Состояние Человека:");
        System.out.println(" Считает себя разумнее: " + human.isBelievesHumansAreSmarter());
        System.out.println(" Общая причина: " + human.getReasonForBelief());
        System.out.println(" Субъективная самооценка: " + human.getSelfPerceivedIntelligenceScore());
        System.out.println(" Субъективная оценка Дельфинов: " + human.getPerceivedOtherSpeciesScore());

        System.out.println("\nСостояние Дельфина:");
        System.out.println(" Считает себя разумнее: " + dolphin.isBelievesDolphinsAreSmarter());
        System.out.println(" Общая причина: " + dolphin.getReasonForBelief());
        System.out.println(" Субъективная самооценка: " + dolphin.getSelfPerceivedIntelligenceScore());
        System.out.println(" Субъективная оценка Людей: " + dolphin.getPerceivedOtherSpeciesScore());

        System.out.println("\n--- Вывод ---");
        if (human.isBelievesHumansAreSmarter() && dolphin.isBelievesDolphinsAreSmarter()) {
            System.out.println("Обе стороны считают себя более разумными, основываясь на своих критериях и интерпретациях одних и тех же фактов.");
        } else if (human.isBelievesHumansAreSmarter()) {
            System.out.println("Человек считает себя более разумным.");
        } else if (dolphin.isBelievesDolphinsAreSmarter()) {
            System.out.println("Дельфин считает себя более разумным.");
        } else {
            System.out.println("Ни одна из сторон не считает себя более разумной (или логика определения не сработала).");
        }

        System.out.println("---------------------------");
    }

    public List<Fact> getFacts() {
        return facts;
    }

    public Human getHuman() {
        return human;
    }

    public Dolphin getDolphin() {
        return dolphin;
    }
}
