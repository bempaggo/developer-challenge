import './Placar.css';
import useCronometro from "../hook/useCronometro";

interface IPlacar {
  numVitoria: number;
}


export default function Placar ({numVitoria}: IPlacar) {
  const {
    cronometro,    
    registros,   
  } = useCronometro();
  return(
   <>
   <div className='Placar'>
   <table className='Placar_Table'>
    <thead>
      <th>N° Vitorias</th>
      <th>Tempo da Partida</th>
    </thead>
    <tbody >
      <td>{numVitoria}</td>
      <td>{cronometro}</td>
    </tbody>
   </table>

   <table className='Placar_Table'>
    <thead>
      <th>Partida N°</th>
      <th>Tempo</th>
    </thead>
    {
      registros.map((registro, index) => (
        <tbody key={index}>
        <td>Partida {index+1}</td>
        <td>{registro}</td>
      </tbody>
      ))
    }
  
   </table>
   </div>
   </>
  )
}

