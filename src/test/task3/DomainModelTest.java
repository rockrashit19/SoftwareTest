package test.task3;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task.task3.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DomainModelTest {
    @Test
    @DisplayName("Начальное состояние Человека - не считает себя разумнее, причина пуста")
    void humanInitialState() {
        Human human = new Human();
        assertFalse(human.isBelievesHumansAreSmarter(), "Изначально Человек не должен считать себя разумнее");
        assertEquals("", human.getReasonForBelief(), "Изначально причина убеждения Человека должна быть пустой");
    }

    @Test
    @DisplayName("Человек успешно формирует убеждение о своей разумности с указанием причины")
    void humanFormsBeliefWithReason() {
        Human human = new Human();
        String reason = "мы изобрели интернет";
        human.reflectOnIntelligence(reason);

        assertTrue(human.isBelievesHumansAreSmarter(), "После размышления Человек должен считать себя разумнее");
        assertEquals(reason, human.getReasonForBelief(), "Причина убеждения Человека должна совпадать с заданной");
    }

    @Test
    @DisplayName("Начальное состояние Дельфина - не считает себя разумнее, причина пуста")
    void dolphinInitialState() {
        Dolphin dolphin = new Dolphin();
        assertFalse(dolphin.isBelievesDolphinsAreSmarter(), "Изначально Дельфин не должен считать себя разумнее");
        assertEquals("", dolphin.getReasonForBelief(), "Изначально причина убеждения Дельфина должна быть пустой");
    }

    @Test
    @DisplayName("Дельфин успешно формирует убеждение о своей разумности с указанием причины")
    void dolphinFormsBeliefWithReason() {
        Dolphin dolphin = new Dolphin();
        String reason = "мы поем красивые песни";
        dolphin.reflectOnIntelligence(reason);

        assertTrue(dolphin.isBelievesDolphinsAreSmarter(), "После размышления Дельфин должен считать себя разумнее");
        assertEquals(reason, dolphin.getReasonForBelief(), "Причина убеждения Дельфина должна совпадать с заданной");
    }
}
