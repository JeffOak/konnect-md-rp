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
import Input from "@mui/material/Input";
import Backdrop from "@mui/material/Backdrop";
import CircularProgress from "@mui/material/CircularProgress";

const rows = [
  { idRp: 1, descricao: "AÇAÍ 500ML", idMd: 200 },
  { idRp: 2, descricao: "AÇAÍ 300ML", idMd: null },
  { idRp: 3, descricao: "AÇAÍ 1L", idMd: 234324 },
  { idRp: 4, descricao: "AÇAÍ 2L", idMd: 345345123 },
];

export default function DataTable() {
  const [loading, setLoading] = React.useState(false);
  const [produtos, setProdutos] = React.useState([]);

  const fetchProdutos = async () => {
    try {
      setLoading(true);
      const data = await fetch("http://localhost:27151/vinculacao/produtos", {
        method: "GET",
      });
      setProdutos(await data.json());
    } catch (ex) {
    } finally {
      setLoading(false);
    }
  };

  const updateVinculacao = async (produtoVinculacao) => {
    try {
      setLoading(true);
      await fetch("http://localhost:27151/vinculacao/produtos", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: produtoVinculacao.id,
          idMd: produtoVinculacao.idMd,
        }),
      });
    } catch (ex) {
    } finally {
      setLoading(false);
    }
  };

  React.useEffect(() => {
    fetchProdutos();
  }, []);

  const columns: GridColDef[] = [
    {
      field: "id",
      headerName: "ID",
      width: 70,
      sortable: false,
      disableColumnMenu: true,
    },
    {
      field: "descricao",
      headerName: "Descrição",
      width: 500,
      sortable: false,
      disableColumnMenu: true,
    },
    {
      field: "idMd",
      headerName: "Código Mais Delivery",
      width: 160,
      sortable: false,
      disableColumnMenu: true,
      valueGetter: (params: GridValueGetterParams) =>
        `${params.row.id || ""} ${params.row.id || ""}`,
      renderCell: ({ row }: Partial<GridRowParams>) => (
        <Input
          defaultValue={row.idMd}
          onBlur={(e) => {
            row.idMd = e.target.value;
            updateVinculacao(row);
          }}
        />
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
      <Typography variant="h5">Produtos</Typography>
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
          loading={loading}
          rowSelection={false}
          rows={produtos}
          columns={columns}
          slots={{
            toolbar: DataGridTitle,
          }}
          pageSizeOptions={[produtos.length, produtos.length]}
        />
      </div>
    </div>
  );
}
