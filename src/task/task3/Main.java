package task.task3;

public class Main {
    public static void main(String[] args) {
        Scenario scenario = new Scenario();

        Human human = new Human();
        Dolphin dolphin = new Dolphin();
        scenario.setupEntities(human, dolphin);

        Fact humanInventionsFact = new Fact("Люди придумали так много: колесо, Нью-Йорк, войну и т.д.");
        humanInventionsFact.makeWidelyKnown();
        humanInventionsFact.markImportant();

        Fact dolphinActivitiesFact = new Fact("Дельфины всегда только плескались в воде и развлекались");
        dolphinActivitiesFact.makeWidelyKnown();
        dolphinActivitiesFact.markImportant();

        Fact anotherHumanFact = new Fact("Люди строят сложные компьютеры");
        Fact anotherDolphinFact = new Fact("Дельфины используют сложную систему эхолокации");

        scenario.addFact(humanInventionsFact);
        scenario.addFact(dolphinActivitiesFact);
        scenario.addFact(anotherHumanFact);
        scenario.addFact(anotherDolphinFact);
        
        scenario.run();
    }
}
