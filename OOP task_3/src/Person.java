import java.util.List;

class Person {
    private String name;
    private List<String> hobbies;

    private int age;

    private String location;
    private String contact;


    public Person(String name, int age, String location, List<String> hobbies) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть нулевым или пустым");
        }

        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Местоположение не может быть нулевым");
        }

        this.name = name;
        this.hobbies = hobbies;
        this.age = age;
        this.location = location;
    }

    public void sendMessage(String message) {
        System.out.println("Отправить сообщение " + contact + ": " + message);
    }

    public String getName() {
        return name;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public int getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }

}
