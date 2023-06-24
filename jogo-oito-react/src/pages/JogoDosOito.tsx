import React, { useState, useEffect } from 'react';
import '../App.css';
import Placar from '../components/Placar';

const JogoDosOito: React.FC = () => { 
  const [tabuleiro, setTabuleiro] = useState<number[][]>([
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 0]
  ]);

  const [venceu, setVenceu] = useState(false);
  const [numVitoria, setNumVitoria] = useState(-1)

  useEffect(() => {
    setVenceu(verificarVitoria());
   }, [tabuleiro]);

 
   useEffect(() => {
    if(venceu){
      const vitoria = numVitoria + 1
      setNumVitoria(vitoria)
    }
   }, [venceu])
 
   useEffect(() => {
     embaralharTabuleiro();
   }, []);

 const renderizarTabuleiro = () => {
  
    return (
      <div className="tabuleiro">
        {tabuleiro.map((linha, rowIndex) => (
          <div key={rowIndex} className="linha">
            {linha.map((valor, colIndex) => (
              <button
                key={colIndex}
                onClick={() => mover(rowIndex - 1, colIndex - 1)}
                className={valor === 0 ? 'vazio' : ''}
                disabled={venceu}
              >
                {valor !== 0 ? valor : ''}
              </button>
            ))}
          </div>
        ))}
      </div>
    );
  }
  


   const mover = (linha: number, coluna: number) => {
    if (!venceu) {
      const linhaVazia = tabuleiro.findIndex(row => row.includes(0));
      const colunaVazia = tabuleiro[linhaVazia].indexOf(0);
      const novaLinha = linhaVazia + linha;
      const novaColuna = colunaVazia + coluna;

      if (
        novaLinha >= 0 &&
        novaLinha < tabuleiro.length &&
        novaColuna >= 0 &&
        novaColuna < tabuleiro[novaLinha].length
      ) {
        const novoTabuleiro = [...tabuleiro];
        novoTabuleiro[linhaVazia][colunaVazia] = tabuleiro[novaLinha][novaColuna];
        novoTabuleiro[novaLinha][novaColuna] = 0;
        setTabuleiro(novoTabuleiro);
      }
    }
  };

  const verificarVitoria = () => {
    const tabuleiroVitoria = [
      [1, 2, 3],
      [4, 5, 6],
      [7, 8, 0]
    ];

    for (let i = 0; i < tabuleiro.length; i++) {
      for (let j = 0; j < tabuleiro[i].length; j++) {
        if (tabuleiro[i][j] !== tabuleiroVitoria[i][j]) {
          return false;
        }
      }
    }
    return true;
  };

  const embaralharTabuleiro = () => {
    const novoTabuleiro = [
      [1, 2, 3],
      [4, 5, 6],
      [7, 8, 0]
    ];
    for (let i = novoTabuleiro.length - 1; i > 0; i--) {
      for (let j = novoTabuleiro[i].length - 1; j > 0; j--) {
        const k = Math.floor(Math.random() * (i + 1));
        const l = Math.floor(Math.random() * (j + 1));
        const temp = novoTabuleiro[i][j];
        novoTabuleiro[i][j] = novoTabuleiro[k][l];
        novoTabuleiro[k][l] = temp;
      }
    }
    setTabuleiro(novoTabuleiro);
  };
 
  const reiniciarJogo = () => {  
    embaralharTabuleiro()    
    setVenceu(false);
  }; 
  
  return (
    <div className="App">
     
      <div>
      <h1>Jogo dos Oito</h1>
      {renderizarTabuleiro()}
       {venceu && (
        <div className="mensagem-vitoria">
          Parabéns, você venceu o jogo!
        </div>
      )} 
      <button onClick={() => reiniciarJogo()} disabled={venceu !== true}>
        Reiniciar
      </button>
      </div>

      <div>      
        <Placar numVitoria={numVitoria} isVenceu={venceu}/>
      </div>
    </div>
  );
};

export default JogoDosOito;