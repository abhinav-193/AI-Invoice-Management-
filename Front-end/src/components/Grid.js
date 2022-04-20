import * as React from "react";
import { DataGrid } from "@mui/x-data-grid";
import axios from "axios";

export default function InfoGrid({
  rows,
  setRows,
  selectedRows,
  selectRow,
  custIDVisible,
  predictedRows,
  setCustIDVisible,
}) {
  const [page, setPage] = React.useState(0);
  const [rowCount, setRowCount] = React.useState(100);
  const [loading, setloading] = React.useState(false);
  const [pageSize, setPageSize] = React.useState(0);
  console.log("predictedRows");
  React.useEffect(() => {
    let mounted = true;

    if (mounted)
      if (rows.length == 0 || rows.length <= rowCount * page)
        try {
          setloading(true);
          let response = axios.get("http://localhost:8080/hrcback/Fetch", {
            params: {
              rowCount: rowCount,
              page: rows.length,
            },
          });

          response.then((e) => {
            setPageSize(e.data.records[0]["total_count"]);
            console.log(pageSize);
            let c = [];
            for (const data of e.data.records) {
              let doc = Object.keys(data).reduce((a, b) => {
                a[b] = data[b];
                return a;
              }, {});
              if (predictedRows[doc.sl_no] !== undefined) {
                doc["predicted_date"] = predictedRows[doc.sl_no];
              }
              c.push(doc);

              console.log(doc);
            }
            if (rows.length == 0) {
              setRows(c);
            } else {
              setRows(rows.concat(c));
            }
            setloading(false);

            console.log(c);
          });
        } catch (error) {
          setloading(false);

          console.log(error);
        }
    return () => mounted = false;
  }, [page, rowCount]);

  React.useEffect(
    (e) => {
      let mounted = true;
      console.log("HII");
      let l = rows;
      if (mounted)
        for (const [key, val] of Object.entries(predictedRows)) {
          let index = l.findIndex((e) => e.sl_no == key);
          if (index > -1)
            l[index].predicted_date = val;
        }
      console.log(l[0]);
      setRows(l);
      return () => mounted = false;
    },
    [predictedRows]
  );

  const columns = [
    { field: "sl_no", headerName: "Sl no", width: 140 },
    { field: "business_code", headerName: "Business Code", width: 140 },
    { field: "cust_number", headerName: "Customer Number", width: 140 },
    { field: "clear_date", headerName: "Clear Date", width: 140 },
    { field: "business_year", headerName: "Business Year", width: 140 },
    { field: "doc_id", headerName: "Document Id", width: 140 },
    { field: "posting_date", headerName: "Posting Date", width: 140 },
    { field: "document_create_date", headerName: "Document Create Date",width: 140},
    { field: "due_in_date", headerName: "Due In Date", width: 140 },
    { field: "invoice_currency", headerName: "Invoice Currency", width: 140 },
    { field: "document_type", headerName: "Document Type", width: 140 },
    { field: "posting_id", headerName: "Posting Id", width: 140 },
    { field: "total_open_amount", headerName: "Total Open Amount", width: 140 },
    { field: "baseline_create_date", headerName: "Baseline Create Date", width: 140 },
    { field: "cust_payment_terms", headerName: "Customer Payment Terms", width: 140 },
    { field: "invoice_id", headerName: "Invoice Id", width: 140 },
    { field: "predicted_date", headerName: "Predicted Date", width: 140 },
    // { field: "area_business", headerName: "Area Business", width: 140 },
    // { field: "is_deleted", headerName: "Is Deleted", width: 140 },
    /*{
      field: "document_create_date1",
      headerName: "Document Create Date 1",
      width: 140,
    },*/
    //{ field: "isOpen", headerName: "Is Open", width: 140 }, 
  ];

  return (
    <div style={{ height: "72vh", width: "100%", color: "#293C4A" }}>
      {false ? (
        <div></div>
      ) : (
        <DataGrid
          selectionModel={selectedRows.map((e) => e.sl_no)}
          onSelectionModelChange={(ids) => {
            selectRow(rows.filter((e) => ids.indexOf(e.sl_no) > -1));
          }}
          loading={rows.length == 0}
          sx={{
            "& .MuiToolbar-root": {
              color: "white",
            },
            border: "none",
            backgroundColor: "#293C4A",
            color: "white",
            "& .MuiDataGrid-columnSeparator--sideRight": {
              display: "none",
            },
            "& .MuiDataGrid-columnHeaderTitle": {
              color: "white",

              textOverflow: "clip",
              whiteSpace: "break-spaces",
              lineHeight: 1,
            },
          }}
          getRowId={(row) => row.sl_no}
          page={page}
          rowCount={loading ? 0 : pageSize}
          onPageChange={(e, data) => {
            setPage(e);
          }}
          onPageSizeChange={(e) => {
            setRowCount(e);
          }}
          rowsPerPageOptions={[5, 10, 15, 25, 50, 100]}
          rows={rows}
          columns={columns}
          checkboxSelection
        />
      )}
    </div>
  );
}
