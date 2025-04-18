package task.task3;

public class Main {
    public static void main(String[] args){
        Human human = new Human();
        Dolphin dolphin = new Dolphin();

        String reasonForHuman = "мы придумали так много: колесо, Нью-Йорк, войну и т.д.";
        String reasonForDolphins = "мы всегда только плескались в воде и развлекались";

        human.reflectOnIntelligence(reasonForHuman);
        dolphin.reflectOnIntelligence(reasonForDolphins);

        System.out.println("\n--- Проверка состояний ---");
        System.out.println("Человек считает себя разумнее? " + human.isBelievesHumansAreSmarter());
        System.out.println("Причина убеждения человека: " + human.getReasonForBelief());
        System.out.println("Дельфин считает себя разумнее? " + dolphin.isBelievesDolphinsAreSmarter());
        System.out.println("Причина убеждения дельфина: " + dolphin.getReasonForBelief());
        System.out.println("--------------------------");
    }
}
