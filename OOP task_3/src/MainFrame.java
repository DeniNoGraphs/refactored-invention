import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JButton button;
    private JButton TextSize;
    private JTextArea textArea;
    private int fontSize = 18;
    private JButton Message;
    private List<Person> johnsFriends;
    private RelationshipFinder finder;

    public MainFrame() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setFont(new Font("классический", Font.PLAIN, fontSize));

        button = new JButton("Найти друзей");
        TextSize = new JButton("Увеличить размер");
        Message = new JButton("Отправить сообщения");

        button.addActionListener(e -> {
            List<Person> people = new ArrayList<>();
            people.add(new Person("Денис", 30, "Воронеж", Arrays.asList("Футбол", "Настольные игры", "Чтение")));
            people.add(new Person("Дима", 32, "Липецк", Arrays.asList("Футбол", "Плавание", "Шахматы")));
            people.add(new Person("Кристина", 27, "Елец", Arrays.asList("Танцы", "Вышивание", "Чтение")));
            people.add(new Person("Ангелина", 31, "Воронеж", Arrays.asList("Гимнастика", "Чтение", "Рисование")));
            people.add(new Person("Антон", 29, "Воронеж", Arrays.asList("Тренажёрный зал", "Бег", "Настольные игры")));
            people.add(new Person("Наташа", 32, "Воронеж", Arrays.asList("Карате", "Чтение", "Спринт")));

            this.finder = new RelationshipFinder(people);
            this.finder.addExclusion("Ангелина");
            this.johnsFriends = this.finder.findCommonHobbyFriends(people.get(0));

            textArea.append("вот возможные друзья Дениса:\n");
            for (Person friend : johnsFriends) {
                String friendData = String.format(
                        "Name: %s\nAge: %d\nCity: %s\nHobbies: %s\n\n",
                        friend.getName(),
                        friend.getAge(),
                        friend.getLocation(),
                        String.join(", ", friend.getHobbies()));

                textArea.append(friendData);
            }
        });

        TextSize.addActionListener(e -> {
            fontSize += 2;
            Font currentFont = textArea.getFont();
            Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), fontSize);
            textArea.setFont(newFont);
        });

        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.add(button);
        southPanel.add(TextSize);
        southPanel.add(Message);
        add(southPanel, BorderLayout.SOUTH);

        Message.addActionListener(e -> {
            String message = "Привет, как твои дела?";
            for (Person friend : this.johnsFriends) {
                this.finder.sendMessageToPerson(friend.getName(), message);
            }
            JOptionPane.showMessageDialog(null, "Сообщения отправлены!");
        });

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
