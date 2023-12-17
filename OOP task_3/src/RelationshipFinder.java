import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RelationshipFinder {
    private List<Person> users;
    private Set<String> excludedNames = new HashSet<>();

    public RelationshipFinder(List<Person> users) {
        this.users = users;
    }
    public void addExclusion(String name) {
        excludedNames.add(name);
    }

    public List<Person> findCommonHobbyFriends(Person person) {
        List<Person> friends = new ArrayList<>();
        for (Person user : users) {
            if (excludedNames.contains(user.getName())) {
                continue;
            }
            boolean sharedHobby = user.getHobbies().stream().anyMatch(hobby -> person.getHobbies().contains(hobby));

            if (!user.getName().equals(person.getName()) &&
                    user.getLocation().equals(person.getLocation()) &&
                    Math.abs(user.getAge() - person.getAge()) <= 2 &&
                    sharedHobby) {
                friends.add(user);
            }
        }
        return friends;
    }
    public void sendMessageToPerson(String name, String message) {
        for (Person person : users) {
            if (person.getName().equals(name)) {
                person.sendMessage(message);
                break;
            }
        }
    }
}