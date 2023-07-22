import builder.JogoDosOitoBuilder;

public class Application {

    public static void main(String[] args) {
        new JogoDosOitoBuilder()
                .createButtons()
                .configureMenu()
                .configureInterface()
                .build();
    }
}
