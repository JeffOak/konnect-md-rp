"use client";

import * as React from "react";
import {
  DataGrid,
  GridColDef,
  GridValueGetterParams,
  GridRowParams,
} from "@mui/x-data-grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Backdrop from "@mui/material/Backdrop";
import CircularProgress from "@mui/material/CircularProgress";

export default function DataTable() {
  const [loading, setLoading] = React.useState(false);
  const [erros, setErros] = React.useState([]);

  const fetchErros = async () => {
    try {
      setLoading(true);
      const data = await fetch(
        "http://localhost:27151/pedidos/md_rp/pedidos_erros",
        {
          method: "GET",
        }
      );
      setErros(await data.json());
    } catch (ex) {
    } finally {
      setLoading(false);
    }
  };

  const reprocessar = async (pedido) => {
    try {
      setLoading(true);
      console.log(pedido)
      await fetch(
        `http://localhost:27151/pedidos/md_rp/reprocessar/${pedido.id}`,
        {
          method: "GET",
        }
      );
    } catch (ex) {
    } finally {
      setLoading(false);
      fetchErros();
    }
  };

  React.useEffect(() => {
    fetchErros();
  }, []);

  const columns: GridColDef[] = [
    {
      field: "mdId",
      headerName: "Código do Pedido",
      width: 150,
      sortable: false,
      disableColumnMenu: true,
    },
    {
      field: "descricao_erro",
      headerName: "Descrição do erro",
      width: 600,
      sortable: false,
      disableColumnMenu: true,
    },
    {
      field: "idMd",
      headerName: "",
      width: 160,
      sortable: false,
      disableColumnMenu: true,
      align: "left",
      valueGetter: (params: GridValueGetterParams) =>
        `${params.row.id || ""} ${params.row.id || ""}`,
      renderCell: ({ row }: Partial<GridRowParams>) => (
        <Button color="primary" onClick={() => reprocessar(row)}>
          Reprocessar
        </Button>
      ),
    },
  ];

  const DataGridTitle = () => (
    <Box
      style={{
        width: "100%",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Typography variant="h5">Pedido não integrados</Typography>
    </Box>
  );

  return (
    <div>
      <Backdrop
        sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={loading}
      >
        <CircularProgress color="inherit" />
      </Backdrop>
      <div style={{ height: "100%", width: "100%" }}>
        <DataGrid
          rowHeight={60}
          rowSelection={false}
          rows={erros}
          columns={columns}
          pageSizeOptions={[erros.length, erros.length]}
          slots={{
            toolbar: DataGridTitle,
          }}
        />
      </div>
    </div>
  );
}
