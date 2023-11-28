import { useState, useEffect } from 'react';

const useCronometro = () => {
  const [tempo, setTempo] = useState(0);
  const [cronometro, setCronometro] = useState('00:00:00:000');
  const [parado, setParado] = useState(false);
  const [registros, setRegistros] = useState<string[]>([]);

  useEffect(() => {
    const intervalId = setInterval(() => {
      if (!parado) {
        setTempo((tempo) => tempo + 1);
      }
    }, 1);

    return () => clearInterval(intervalId);
  }, [parado]);

  useEffect(() => {
    const horas = Math.floor(tempo / 3600000);
    const minutos = Math.floor((tempo % 3600000) / 60000);
    const segundos = Math.floor((tempo % 60000) / 1000);
    const milissegundos = tempo % 1000;

    const formatarTempo = (valor: number) => {
      return valor.toString().padStart(2, '0');
    };

    const cronometroFormatado = `${formatarTempo(horas)}:${formatarTempo(
      minutos
    )}:${formatarTempo(segundos)}:${formatarTempo(milissegundos)}`;

    setCronometro(cronometroFormatado);
  }, [tempo]);

  const pararCronometro = () => {
    setParado(true);
  };

  const guardarRegistro = () => {
    setRegistros([...registros, cronometro]);
  };

  const reiniciarCronometro = () => {
    setTempo(0);
    setCronometro('00:00:00:000');
    setParado(false);
  };

  return {
    cronometro,
    parado,
    registros,
    pararCronometro,
    guardarRegistro,
    reiniciarCronometro,
  };
};

export default useCronometro;
