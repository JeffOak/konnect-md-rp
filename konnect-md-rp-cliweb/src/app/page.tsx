"use client";

import { useState, useEffect } from "react";

import Typography from "@mui/material/Typography";
import Backdrop from "@mui/material/Backdrop";
import CircularProgress from "@mui/material/CircularProgress";

export default function Home() {
  const [estabelecimento, setEstabelecimento] = useState(null);
  const [erro, setErro] = useState(false);
  const [loading, setLoading] = useState(true);

  const fetchEstabelecimento = async () => {
    try {
      setErro(false);
      setLoading(true);
      const data = await fetch("http://localhost:27151/", {
        method: "GET",
      });
      setEstabelecimento(await data.json());
    } catch (ex) {
      setErro(true);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    // Use setTimeout to update the message after 2000 milliseconds (2 seconds)
    const timeoutId = setTimeout(() => {
      fetchEstabelecimento();
    }, 2000);

    // Cleanup function to clear the timeout if the component unmounts
    return () => clearTimeout(timeoutId);
  }, []);

  if (erro) {
    return (
      <div
        style={{
          padding: 10,
        }}
      >
        <Typography variant="h5">
          Erro ao se conectar com o Mais Delivery
        </Typography>
        <Typography variant="h5">Entre em contato com o suporte</Typography>
      </div>
    );
  }

  if (loading) {
    return (
      <Backdrop
        sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={loading}
      >
        <CircularProgress color="inherit" />
      </Backdrop>
    );
  }

  return (
    <div
      style={{
        padding: 10,
      }}
    >
      <Typography variant="h4">
        Estabelecimento: {estabelecimento && estabelecimento.dsNome}
      </Typography>
      <Typography variant="h5">
        Status :{" "}
        {estabelecimento && estabelecimento.flgAberto === 1
          ? "Aberto"
          : "Fechado"}
      </Typography>
    </div>
  );
}
