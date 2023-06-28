package chat.gpt.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.Font;

public class BotaoTest {

    
    @Test
    void testCriarBotaoComTexto() {
        String texto = "Teste";
        Botao botao = Botao.criarBotao(texto);
        Assertions.assertEquals(texto, botao.getText());
    }

    @Test
    void testCriarBotaoVazio() {
        Botao botao = Botao.criarBotaoVazio();
        Assertions.assertEquals("", botao.getText());
        Assertions.assertEquals(new Font("Arial", Font.BOLD, 36), botao.getFont());
    }

   /* @Test
    void testGetLinha() {
        int linha = 1;
        int coluna = 2;
        Botao botao = new Botao(linha, coluna);
        Assertions.assertEquals(linha, botao.getLinha());
    }

    @Test
    void testGetColuna() {
        int linha = 1;
        int coluna = 2;
        Botao botao = new Botao(linha, coluna);
        Assertions.assertEquals(coluna, botao.getColuna());
    } */
}
