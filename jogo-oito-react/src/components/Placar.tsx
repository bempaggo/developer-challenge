import './Placar.css';
import useCronometro from "../hook/useCronometro";
import { useEffect } from 'react';

interface IPlacar {
  numVitoria: number;
  isVenceu: boolean;
}


export default function Placar ({numVitoria, isVenceu}: IPlacar) {
  const {
    cronometro,    
    registros,
    pararCronometro,   
    guardarRegistro,
    reiniciarCronometro
  } = useCronometro();


 

  useEffect(() => {
    if(isVenceu === true){
      pararCronometro();
      guardarRegistro();   
    }else{
      reiniciarCronometro()
    }

   }, [isVenceu])


  return(
   <>
   <div>
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
     registros.slice(1).map((registro, index) => (
      <tbody key={index}>
        <td>Partida {index + 1}</td>
        <td>{registro}</td>
      </tbody>
    ))
    }
  
   </table>
   </div>
   </>
  )
}

