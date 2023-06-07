package chat.gpt.src.servico;

import chat.gpt.src.modelo.Movimento;
import chat.gpt.src.modelo.Peca;
import chat.gpt.src.modelo.Tabuleiro;

import java.util.Optional;

public class RegraImpl implements Regra {

    @Override
    public Boolean verificarVitoria(Tabuleiro tabuleiro) {
        return tabuleiro.pecas().stream().allMatch(Peca::posicaoCorreta);
    }

    @Override
    public Boolean validarMovimento(Peca pecaMovida, Peca pecaVazia) {
        Optional<Movimento> movimentoFeito = Movimento.obterMovimento(pecaMovida.getPosicao() - pecaVazia.getPosicao());
        return movimentoFeito.filter(movimento -> Movimento.ehValido(movimento, pecaVazia, pecaMovida)).isPresent();
    }
}
