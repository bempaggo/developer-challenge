import './RenderizarTabuleiro.css'
import useJogo from "../hook/useJogo";





export default function RenderizarTabuleiro () {
  const {
    mover,
    tabuleiro,
    venceu,
  } = useJogo();
  
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

