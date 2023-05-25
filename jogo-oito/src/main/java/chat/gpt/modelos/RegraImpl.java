package chat.gpt.modelos;

import java.util.Map;

public class RegraImpl implements Regra {

    @Override
    public boolean fazerMovimento(Tabuleiro tabuleiro, Posicao posicaoClicada) {

        if (podeMover(tabuleiro, posicaoClicada)) {
            Posicao posicaoVazia = tabuleiro.getTabuleiro().entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().getValor() == 9)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);


            if (movimentoValido(posicaoClicada, posicaoVazia)) {
                System.out.println("antes");
                System.out.println(tabuleiro);
                trocarPosicao(tabuleiro, posicaoClicada, posicaoVazia);
                System.out.println("depois");
                System.out.println(tabuleiro);
                return true;
            }

        }

        return false;
    }

    private void trocarPosicao(Tabuleiro tabuleiro, Posicao posicaoClicada, Posicao posicaoVazia) {
        Peca pecaVazia = new Peca(9);

        Peca pecaClicada = tabuleiro.getPeca(posicaoClicada);

        tabuleiro.getTabuleiro().replace(posicaoClicada, pecaClicada, pecaVazia);
        tabuleiro.getTabuleiro().replace(posicaoVazia, pecaVazia, pecaClicada);
    }

    private boolean podeMover(Tabuleiro tabuleiro, Posicao posicaoClicada) {
        return tabuleiro.getTabuleiro().get(posicaoClicada).getValor() != 9;
    }

    private boolean movimentoValido(Posicao posicaoClicada, Posicao posicaoVazia) {
        boolean mesmaLinha = posicaoVazia.getLinha() == posicaoClicada.getLinha();
        boolean mesmaColuna = posicaoVazia.getColuna() == posicaoClicada.getColuna();

        boolean movimentoCima = (posicaoVazia.getLinha() - 1) == posicaoClicada.getLinha();
        boolean movimentoBaixo = (posicaoVazia.getLinha() + 1) == posicaoClicada.getLinha();
        boolean movimentoEsquerda = (posicaoVazia.getColuna() - 1) == posicaoClicada.getColuna();
        boolean movimentoDireita = (posicaoVazia.getColuna() + 1) == posicaoClicada.getColuna();

        return movimentoBaixo && mesmaColuna || movimentoCima && mesmaColuna
                || movimentoEsquerda && mesmaLinha || movimentoDireita && mesmaLinha;
    }

    @Override
    public boolean verificarVitoria(Tabuleiro tabuleiro) {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int valorPecaAtual = tabuleiro.getPeca(new Posicao(i, j)).getValor();

                if (valorPecaAtual != count) return false;
                count++;
            }
        }
        return true;
    }

    @Override
    public void embaralharPecas(Tabuleiro tabuleiro) {

    }
}
