package test.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task.task3.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScenarioTest {

    @Test
    @DisplayName("Fact: создание и свойства")
    void factCreationAndProperties() {
        Fact fact = new Fact("Test fact description");
        assertEquals("Test fact description", fact.getDescription());
        assertFalse(fact.isWidelyKnown(), "Изначально факт не должен быть широко известен");
        assertFalse(fact.isImportant(), "Изначально факт не должен быть важным");

        fact.makeWidelyKnown();
        assertTrue(fact.isWidelyKnown(), "Факт должен стать широко известным после вызова makeWidelyKnown");

        fact.markImportant();
        assertTrue(fact.isImportant(), "Факт должен стать важным после вызова markImportant");
    }

    @Test
    @DisplayName("Perception: создание и свойства")
    void perceptionCreationAndProperties() {
        Fact fact = new Fact("Simple fact");
        Perception humanPerception = new Perception(fact, Species.HUMAN, 50, "Interesting observation");

        assertEquals(fact, humanPerception.getFact());
        assertEquals(Species.HUMAN, humanPerception.getPerceivingSpecies());
        assertEquals(50, humanPerception.getSubjectiveScore());
        assertEquals("Interesting observation", humanPerception.getSubjectiveInterpretation());
    }

    @Test
    @DisplayName("Human: восприятие фактов и формирование перцепций")
    void humanPerceivesFactsAndFormsPerceptions() {
        Human human = new Human();
        Fact fact1 = new Fact("Люди построили город");
        Fact fact2 = new Fact("Дельфины поют песни");
        List<Fact> facts = Arrays.asList(fact1, fact2);

        human.perceiveFacts(facts);

        assertEquals(2, human.getPerceptions().size(), "Должно быть создано по одной перцепции на каждый факт");

        Perception p1 = human.getPerceptions().stream()
                .filter(p -> p.getFact().equals(fact1))
                .findFirst().orElseThrow();
        assertEquals(Species.HUMAN, p1.getPerceivingSpecies());
        assertEquals(Human.getHumanAchievementScore(), p1.getSubjectiveScore(), "Человек должен высоко оценить факт о достижениях людей");
        assertFalse(p1.getSubjectiveInterpretation().isEmpty(), "Интерпретация Человека не должна быть пустой для факта о достижениях людей");

        Perception p2 = human.getPerceptions().stream()
                .filter(p -> p.getFact().equals(fact2))
                .findFirst().orElseThrow();
        assertEquals(Species.HUMAN, p2.getPerceivingSpecies());
        assertEquals(Human.getDolphinActivityScore(), p2.getSubjectiveScore(), "Человек должен низко оценить факт об активности дельфинов");
        assertFalse(p2.getSubjectiveInterpretation().isEmpty(), "Интерпретация Человека не должна быть пустой для факта об активности дельфинов");
    }

    @Test
    @DisplayName("Dolphin: восприятие фактов и формирование перцепций")
    void dolphinPerceivesFactsAndFormsPerceptions() {
        Dolphin dolphin = new Dolphin();
        Fact fact1 = new Fact("Дельфины играют");
        Fact fact2 = new Fact("Люди создали оружие");
        List<Fact> facts = Arrays.asList(fact1, fact2);

        dolphin.perceiveFacts(facts);

        assertEquals(2, dolphin.getPerceptions().size(), "Должно быть создано по одной перцепции на каждый факт");

        Perception p1 = dolphin.getPerceptions().stream()
                .filter(p -> p.getFact().equals(fact1))
                .findFirst().orElseThrow();
        assertEquals(Species.DOLPHIN, p1.getPerceivingSpecies());
        assertEquals(Dolphin.getDolphinActivityScore(), p1.getSubjectiveScore(), "Дельфин должен высоко оценить факт об активности дельфинов");
        assertFalse(p1.getSubjectiveInterpretation().isEmpty(), "Интерпретация Дельфина не должна быть пустой для факта об активности дельфинов");

        Perception p2 = dolphin.getPerceptions().stream()
                .filter(p -> p.getFact().equals(fact2))
                .findFirst().orElseThrow();
        assertEquals(Species.DOLPHIN, p2.getPerceivingSpecies());
        assertEquals(Dolphin.getHumanAchievementScore(), p2.getSubjectiveScore(), "Дельфин должен низко оценить факт о достижениях людей");
        assertFalse(p2.getSubjectiveInterpretation().isEmpty(), "Интерпретация Дельфина не должна быть пустой для факта о достижениях людей");
    }

    @Test
    @DisplayName("Human: синтез убеждения на основе перцепций")
    void humanSynthesizesBelief() {
        Human human = new Human();

        Fact humanFact1 = new Fact("Люди придумали колесо");
        Fact humanFact2 = new Fact("Люди строят Нью-Йорк");
        Fact dolphinFact1 = new Fact("Дельфины плескались");
        Fact dolphinFact2 = new Fact("Дельфины развлекались");

        human.perceptions.add(new Perception(humanFact1, Species.HUMAN, Human.getHumanAchievementScore(), "Изобретение"));
        human.perceptions.add(new Perception(humanFact2, Species.HUMAN, Human.getHumanAchievementScore(), "Строительство"));
        human.perceptions.add(new Perception(dolphinFact1, Species.HUMAN, Human.getDolphinActivityScore(), "Просто плескались"));
        human.perceptions.add(new Perception(dolphinFact2, Species.HUMAN, Human.getDolphinActivityScore(), "Просто развлекались"));

        human.synthesizeBelief();

        assertEquals(2 * Human.getHumanAchievementScore(), human.getSelfPerceivedIntelligenceScore(), "Самооценка Человека должна быть суммой оценок фактов о себе");
        assertEquals(2 * Human.getDolphinActivityScore(), human.getPerceivedOtherSpeciesScore(), "Оценка Дельфинов Человеком должна быть суммой оценок фактов о дельфинах");
        assertTrue(human.isBelievesHumansAreSmarter(), "Человек должен считать себя разумнее, так как его самооценка выше");

        assertTrue(human.getReasonForBelief().contains("Изобретение") && human.getReasonForBelief().contains("Строительство"),
                "Причина Человека должна включать интерпретации фактов о людях");
    }

    @Test
    @DisplayName("Dolphin: синтез убеждения на основе перцепций")
    void dolphinSynthesizesBelief() {
        Dolphin dolphin = new Dolphin();
        Fact dolphinFact1 = new Fact("Дельфины плескались");
        Fact dolphinFact2 = new Fact("Дельфины развлекались");
        Fact humanFact1 = new Fact("Люди придумали колесо");
        Fact humanFact2 = new Fact("Люди ведут войну");

        dolphin.perceptions.add(new Perception(dolphinFact1, Species.DOLPHIN, Dolphin.DOLPHIN_ACTIVITY_SCORE, "Гармония"));
        dolphin.perceptions.add(new Perception(dolphinFact2, Species.DOLPHIN, Dolphin.DOLPHIN_ACTIVITY_SCORE, "Радость"));
        dolphin.perceptions.add(new Perception(humanFact1, Species.DOLPHIN, Dolphin.HUMAN_ACHIEVEMENT_SCORE, "Суета"));
        dolphin.perceptions.add(new Perception(humanFact2, Species.DOLPHIN, Dolphin.HUMAN_ACHIEVEMENT_SCORE, "Разрушение"));


        dolphin.synthesizeBelief();

        assertEquals(2 * Dolphin.DOLPHIN_ACTIVITY_SCORE, dolphin.getSelfPerceivedIntelligenceScore(), "Самооценка Дельфина должна быть суммой оценок фактов о себе");
        assertEquals(2 * Dolphin.HUMAN_ACHIEVEMENT_SCORE, dolphin.getPerceivedOtherSpeciesScore(), "Оценка Людей Дельфином должна быть суммой оценок фактов о людях");
        assertTrue(dolphin.isBelievesDolphinsAreSmarter(), "Дельфин должен считать себя разумнее, так как его самооценка выше");

        assertTrue(dolphin.getReasonForBelief().contains("Гармония") && dolphin.getReasonForBelief().contains("Радость"),
                "Причина Дельфина должна включать интерпретации фактов о дельфинах");
    }

    @Test
    @DisplayName("Scenario: запуск полного цикла")
    void scenarioRunsFullCycle() {
        Scenario scenario = new Scenario();
        Human human = new Human();
        Dolphin dolphin = new Dolphin();
        scenario.setupEntities(human, dolphin);

        Fact humanFact = new Fact("Люди создают цивилизации");
        Fact dolphinFact = new Fact("Дельфины живут в океане");

        scenario.addFact(humanFact);
        scenario.addFact(dolphinFact);

        scenario.run();

        assertEquals(2, human.getPerceptions().size(), "Человек должен воспринять 2 факта");
        assertEquals(2, dolphin.getPerceptions().size(), "Дельфин должен воспринять 2 факта");

        assertTrue(human.isBelievesHumansAreSmarter(), "После сценария Человек должен считать себя разумнее");
        assertTrue(dolphin.isBelievesDolphinsAreSmarter(), "После сценария Дельфин должен считать себя разумнее");

        assertTrue(human.getSelfPerceivedIntelligenceScore() > human.getPerceivedOtherSpeciesScore(), "Самооценка Человека должна быть выше оценки Дельфинов");
        assertTrue(dolphin.getSelfPerceivedIntelligenceScore() > dolphin.getPerceivedOtherSpeciesScore(), "Самооценка Дельфина должна быть выше оценки Людей");

        assertFalse(human.getReasonForBelief().isEmpty(), "Причина Человека не должна быть пустой");
        assertFalse(dolphin.getReasonForBelief().isEmpty(), "Причина Дельфина не должна быть пустой");
    }
}
