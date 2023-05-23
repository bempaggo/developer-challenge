
import chat.gpt.JogoDosOito;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author diego
 */
public class JogoDosOitoTest {
    
    @Test
    public void shouldReturnFalseWhenListUnordered() {
        JogoDosOito jogo = new JogoDosOito();
        boolean value = jogo.jogoConcluido();
        Assertions.assertEquals(false, value);
    }
    
    @Test
    public void shouldReturnFalseWhenListOrdered() {
        JogoDosOito jogo = new JogoDosOito();
        jogo.ordenar();
        boolean value = jogo.jogoConcluido();
        Assertions.assertEquals(true, value);
    }
}
