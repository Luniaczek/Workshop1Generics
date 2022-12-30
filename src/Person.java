public class Person {
    private String name;
    private String surname;
    private int counter;

    public Person(String name, String surname, int counter) {
        this.name = name;
        this.surname = surname;
        this.counter = counter;
    }

    @Override
    public String toString() {
        return name + "_" + surname + "_" + counter;
    }
}
