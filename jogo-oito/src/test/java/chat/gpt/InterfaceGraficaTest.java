package chat.gpt;

import chat.gpt.src.InterfaceGrafica;
import chat.gpt.src.Regra;
import chat.gpt.src.RegraImpl;
import chat.gpt.src.Tabuleiro;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class InterfaceGraficaTest {

    @Test
    public void interfaceGraficaValidarBotoesTest() throws InterruptedException {
        Tabuleiro tabuleiro = new Tabuleiro(3);
        Regra regra = new RegraImpl();
        InterfaceGrafica interfaceGrafica = new InterfaceGrafica(tabuleiro, regra);
        int tentativas = 0;

        while (!interfaceGrafica.isActive() && tentativas < 3) {
            Thread.sleep(500);
            tentativas++;
        }

        int count = 0;
        Component[] components = interfaceGrafica.getContentPane().getComponents();
        boolean botaoEmbaralharEncontrado = false;
        boolean botaoReiniciarEncontrado = false;
        boolean botoesDePecasEncontrados = false;
        for (Component component : components) {
            if (component instanceof JButton botao) {
                if (botao.getText().equals("Embaralhar")) {
                    botaoEmbaralharEncontrado = true;
                } else if (botao.getText().equals("Reiniciar")) {
                    botaoReiniciarEncontrado = true;
                }
            }
            if (component instanceof JButton && component.getFont().equals(new Font("Arial", Font.BOLD, 36))) {
                count++;
            }
        }

        if (count == tabuleiro.getDimensao() * tabuleiro.getDimensao()) botoesDePecasEncontrados = true;

        Assert.assertTrue(botaoEmbaralharEncontrado);
        Assert.assertTrue(botaoReiniciarEncontrado);
        Assert.assertTrue(botoesDePecasEncontrados);
    }

    @Test
    public void interfaceGraficaEmbaralharClick() throws InterruptedException {
        Tabuleiro tabuleiro = new Tabuleiro(3);
        Regra regra = new RegraImpl();
        InterfaceGrafica interfaceGrafica = new InterfaceGrafica(tabuleiro, regra);
        int tentativas = 0;

        while (!interfaceGrafica.isActive() && tentativas < 3) {
            Thread.sleep(500);
            tentativas++;
        }

        Component[] components = interfaceGrafica.getContentPane().getComponents();

        for (Component component : components) {
            if (component instanceof JButton botao) {
                if (botao.getText().equals("Embaralhar")) {
                    botao.doClick();
                    interfaceGraficaValidarBotoesTest();
                }
            }
        }
    }

    @Test
    public void interfaceGraficaReiniciarClick() throws InterruptedException {
        Tabuleiro tabuleiro = new Tabuleiro(3);
        Regra regra = new RegraImpl();
        InterfaceGrafica interfaceGrafica = new InterfaceGrafica(tabuleiro, regra);
        int tentativas = 0;

        while (!interfaceGrafica.isActive() && tentativas < 3) {
            Thread.sleep(500);
            tentativas++;
        }

        Component[] components = interfaceGrafica.getContentPane().getComponents();

        for (Component component : components) {
            if (component instanceof JButton botao) {
                if (botao.getText().equals("Reiniciar")) {
                    botao.doClick();
                    interfaceGraficaValidarBotoesTest();
                }
            }
        }
    }
}

