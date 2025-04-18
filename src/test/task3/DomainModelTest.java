package test.task3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task.task3.Belief;
import task.task3.Creature;
import task.task3.Reason;
import task.task3.ReasonType;

public class DomainModelTest {

    @Test
    @DisplayName("Тест создания существа (task.task3.Creature)")
    void testCreatureCreation() {
        Creature human = new Creature("Человек", "Представитель Homo sapiens");
        assertEquals("Человек", human.getName());
        assertEquals("Представитель Homo sapiens", human.getDescription());
        assertTrue(human.getReasons().isEmpty(), "Список причин должен быть пустым при создании");
    }

    @Test
    @DisplayName("Тест добавления и получения причин у существа")
    void testAddAndGetReasons() {
        Creature human = new Creature("Человек", "Представитель Homo sapiens");
        Reason reason1 = new Reason(ReasonType.INVENTION, "Изобретение колеса");
        Reason reason2 = new Reason(ReasonType.INVENTION, "Создание Нью-Йорка");

        human.addReason(reason1);
        human.addReason(reason2);

        assertEquals(2, human.getReasons().size(), "Должно быть две причины");
        assertTrue(human.getReasons().contains(reason1));
        assertTrue(human.getReasons().contains(reason2));
    }

    @Test
    @DisplayName("Тест создания причины (task.task3.Reason)")
    void testReasonCreation() {
        Reason reason = new Reason(ReasonType.INVENTION, "Изобретение колеса");
        assertEquals(ReasonType.INVENTION, reason.getType());
        assertEquals("Изобретение колеса", reason.getDescription());
    }

    @Test
    @DisplayName("Тест создания убеждения (task.task3.Belief) и логики сравнения")
    void testBeliefCreationAndLogic() {
        Creature human = new Creature("Человек", "Представитель Homo sapiens");
        Creature dolphin = new Creature("Дельфин", "Морской млекопитающий");

        human.addReason(new Reason(ReasonType.INVENTION, "Изобретение колеса"));
        human.addReason(new Reason(ReasonType.INVENTION, "Создание Нью-Йорка"));

        dolphin.addReason(new Reason(ReasonType.ENTERTAINMENT, "Плескание в воде"));

        Belief belief = new Belief(human, dolphin);
        assertTrue(belief.isSubjectMoreRational(), "Человек должен считаться более разумным, так как у него больше причин");

        Belief reverseBelief = new Belief(dolphin, human);
        assertFalse(reverseBelief.isSubjectMoreRational(), "Дельфин не должен считаться более разумным, чем Человек");
    }

    @Test
    @DisplayName("Тест убеждения при равном количестве причин")
    void testBeliefWithEqualReasons() {
        Creature human = new Creature("Человек", "Представитель Homo sapiens");
        Creature dolphin = new Creature("Дельфин", "Морской млекопитающий");

        human.addReason(new Reason(ReasonType.INVENTION, "Изобретение колеса"));
        dolphin.addReason(new Reason(ReasonType.ENTERTAINMENT, "Плескание в воде"));

        Belief belief = new Belief(human, dolphin);

        assertFalse(belief.isSubjectMoreRational(), "При равном количестве причин существо не считается более разумным");
    }
}
