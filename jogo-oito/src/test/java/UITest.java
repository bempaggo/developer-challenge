import chat.gpt.ui.JogoDosOitoUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UITest {

    private JogoDosOitoUI ui;

    @BeforeEach
    public void inicializar() {
        this.ui = new JogoDosOitoUI("Teste");
    }
    @Test
    void deveriaIniciarJogoSemProblemas() {
        Assertions.assertDoesNotThrow(() -> this.ui.iniciarJogo());
    }
}
