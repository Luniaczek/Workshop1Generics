import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private boolean isRunning = true;
    Map<String, Integer> map = new HashMap<>();
    List<Person> queue = new LinkedList<>();


    private int getNextID(String personalInformation){
        int nextID = 1;
        if (map.containsKey(personalInformation)){
            nextID = map.get(personalInformation) + 1;

        }
        map.put(personalInformation, nextID);
        return nextID;

    }
    public static void main(String[] args) {
        Main program = new Main();
        program.run();


    }

    private void run() {
        //Scanner scanner = new Scanner(System.in);
        try {
            File file = new File("C:\\Java\\ProjectGenericsFinal\\data\\comands");
            Scanner scanner = new Scanner(file);

            while (isRunning) {
                System.out.print("> ");
                String command = scanner.nextLine();
                System.out.println(command);
                if (command.equals("END")) {
                    end();
                } else if (command.startsWith("ADD PERSON")) {
                    String personalInfo = command.substring(11, command.length() - 1);
                    addPerson(personalInfo);

                } else if (command.startsWith("PROCESS")) {
                    process();

                } else if (command.startsWith("LEAVE PERSON")) {

                    String leavingPerson = command.substring(13, command.length() - 1);
                    try {
                        leavePerson(leavingPerson);
                        System.out.println(leavingPerson + " has left");
                    } catch (Exception exc) {
                        System.out.println(exc);

                    }

                } else {
                    System.out.println("I don't know this command");
                }

            }
        } catch (FileNotFoundException exc) {
            return;
        }

    }

    private void end() {
        isRunning = false;
    }

    private void addPerson(String personalInformation){
        String[] separatedNameSurmane = personalInformation.split("_");
        Person person = new Person(separatedNameSurmane[0], separatedNameSurmane[1], getNextID(personalInformation) );
        System.out.println("Add person: " + person);
        queue.add(person);
        System.out.println("Queue: " + queue);
    }

    private void process(){
        Person removePerson = queue.remove(0);
        System.out.println("Processing queue: " + removePerson + " arrived at the store");
        System.out.println("Current queue: " + queue);
    }

    private void leavePerson(String personalInformation) throws Exception{
        boolean hasLeft = queue.removeIf(person -> person.toString().equals(personalInformation));
        if (!hasLeft){
            throw new Exception("Unable to find person");
        }
        System.out.println("Current queue: " + queue);

    }


}