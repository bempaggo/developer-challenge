package game;

import builder.JogoDosOitoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.JogoDosOito;

import static org.assertj.core.api.Assertions.assertThat;

class JogoDosOitoBuilderTest {

    private JogoDosOito jogoDosOito;

    @BeforeEach
    void setUp() {
        jogoDosOito = new JogoDosOito();
    }

    @Test
    void testCreateButtons() {
        assertThat(jogoDosOito.getButtons()).isEmpty();

        jogoDosOito = new JogoDosOitoBuilder()
                .createButtons()
                .build();

        assertThat(jogoDosOito.getButtons()).hasSize(9);
    }

    @Test
    void testConfigureMenu() {
        assertThat(jogoDosOito.getReset()).isNull();
        assertThat(jogoDosOito.getFeedback()).isNull();

        jogoDosOito = new JogoDosOitoBuilder()
                .configureMenu()
                .build();

        assertThat(jogoDosOito.getReset()).isNotNull();
        assertThat(jogoDosOito.getFeedback()).isNotNull();
    }

    @Test
    void testConfigureInterface() {
        assertThat(jogoDosOito.isVisible()).isFalse();
        assertThat(jogoDosOito.getSize())
                .extracting("width", "height")
                .containsExactly(0.0, 0.0);

        jogoDosOito = new JogoDosOitoBuilder()
                .configureInterface()
                .build();

        assertThat(jogoDosOito.isVisible()).isTrue();
        assertThat(jogoDosOito.getSize())
                .extracting("width", "height")
                .containsExactly(300.0, 300.0);
    }

}
