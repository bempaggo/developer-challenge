import React, { useState, useEffect } from 'react';
import '../App.css';
import Placar from '../components/Placar';
import useJogo from '../hook/useJogo';
import RenderizarTabuleiro from '../components/RenderizarTabuleiro';
import useCronometro from '../hook/useCronometro';

const JogoDosOito: React.FC = () => {
  const {  
    venceu,  
    numVitoria,
    reiniciarJogo,
  } = useJogo();  
  
  return (
    <div className="App">
     
      <div>
      <h1>Jogo dos Oito</h1>
      <RenderizarTabuleiro/>
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
        <Placar numVitoria={numVitoria} />
      </div>
    </div>
  );
};

export default JogoDosOito;