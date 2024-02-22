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
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
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
  const [pagamentos, setPagamentos] = React.useState([]);
  const [pagamentosMd, setPagamentosMd] = React.useState([]);

  const fetchPagamentos = async () => {
    try {
      setLoading(true);
      const data = await fetch("http://localhost:27151/vinculacao/pagamentos", {
        method: "GET",
      });
      setPagamentos(await data.json());
    } catch (ex) {
    } finally {
      setLoading(false);
    }
  };

  const fetchPagamentosMd = async () => {
    try {
      setLoading(true);
      const data = await fetch(
        "http://localhost:27151/vinculacao/pagamentos/md",
        {
          method: "GET",
        }
      );
      setPagamentosMd(await data.json());
    } catch (ex) {
    } finally {
      setLoading(false);
    }
  };

  const updateVinculacao = async (pagamentoVinculacao) => {
    try {
      setLoading(true);
      await fetch("http://localhost:27151/vinculacao/pagamentos", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: pagamentoVinculacao.id,
          idMd: pagamentoVinculacao.idMd,
        }),
      });
    } catch (ex) {
    } finally {
      setLoading(false);
    }
  };

  React.useEffect(() => {
    fetchPagamentos();
    fetchPagamentosMd();
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
      headerName: "Mais Delivery",
      width: 160,
      sortable: false,
      disableColumnMenu: true,
      align: "left",
      valueGetter: (params: GridValueGetterParams) =>
        `${params.row.id || ""} ${params.row.id || ""}`,
      renderCell: ({ row }: Partial<GridRowParams>) => (
        <FormControl fullWidth>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={row.idMd}
            onChange={(e) => {
              row.idMd = e.target.value;
              updateVinculacao(row);
            }}
          >
            <MenuItem key={null} value={null}>
              Nenhum
            </MenuItem>
            {pagamentosMd.map((pagamento) => (
              <MenuItem key={pagamento.id} value={pagamento.id}>
                {pagamento.descricao}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
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
      <Typography variant="h5">Formas de Pagamentos</Typography>
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
          rows={pagamentos}
          columns={columns}
          pageSizeOptions={[pagamentos.length, pagamentos.length]}
          slots={{
            toolbar: DataGridTitle,
          }}
        />
      </div>
    </div>
  );
}
