import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Input,
} from "@mui/material";
import { wait } from "@testing-library/user-event/dist/utils";
import React from "react";
import "./BtnRow.css";
import DialogData from "../components/Dialog";
const ButtonRow = ({
  
  setPredictedRows,
  predictedRows,
  
  setSelection,
  selectedRows,
  setinputVal,
  currentSelected,
  value,
  inputVal,
  setAdvancedSearchOptions,
  advancedSearchOptions,
}) => {
  const [dialogOpen, openDialog] = React.useState(false);
  const [errorMessage, setErrorMessage] = React.useState("");

  const [currentObj, setCOB] = React.useState(<></>);

  const handleButtonClick = (e) => {
    for (let i = 1; i <= 3; i++) {
      let l = "b" + i.toString();
      if (e.target.id == l) {
        console.log("SELECTED " + i);
        document.getElementById(l).style.background = "#14AFF1";
      } else {
        console.log("NON SEL " + i);

        document.getElementById(l).style.background = "#293C4A";
      }
    }
    setSelection(e.target.id);
  };

  return (
    <div className="button-row">
      <div className="three-buttons">
        <button
          id="b1"
          onClick={async () => {
            if (selectedRows.length == 0) {
              alert("Please Select atleast one row to predict");
              return;
            }
            console.log(selectedRows);
            let respone = await fetch("http://127.0.0.1:5000/get_prediction", {
              method: "POST",
              body: JSON.stringify(selectedRows),
            });
            let data = await respone.json();
            let c = {};
            for (const [key, value] of Object.entries(data.sl_no)) {
              c[value] = new Date(data.predicted_date[key]).toLocaleString(
                "en-US",
                { year: "numeric", month: "short", day: "numeric" }
              );
            }

            let a = Object.assign(c, predictedRows);
            console.log(a);
            setPredictedRows(a);
            console.log(data);
          }}
          className="btn-rounded left-rounded"
        >
          PREDICT
        </button>
        <button
          id="b2"
          onClick={(e) => {
            console.log(currentSelected);
            if (currentSelected == "b2") {
              document.getElementById(e.target.id).style.background = "#293C4A";

              setSelection("");
            } else {
              handleButtonClick(e);
            }
          }}
          className="button-square btn-rounded"
        >
          ANALYTICS VIEW
        </button>
        <button
          id="b3"
          onClick={(e) => {
            if (advancedSearchOptions.isActive) {
              setAdvancedSearchOptions({ isActive: false });
              setSelection("");

              document.getElementById(e.target.id).style.background = "#293C4A";
            } else {
              setCOB(
                DialogData(
                  "adv_search",
                  openDialog,
                  (d) => {
                    let a = {};
                  
                    for (let i = 0; i < d.length; i++) {

                      if (d[i].value != undefined && d[i].value.length > 0)
                        a[d[i].name] = d[i].value;
                    }
                    console.log(a);

                    if (Object.entries(a).length == 0) {
                      wait(3000).then((e) => setErrorMessage(""));
                      setErrorMessage(
                        "Kindly enter the value for any one of the fields"
                      );
                      return;
                    }
                    openDialog(false);

                    handleButtonClick(e);

                    setAdvancedSearchOptions(
                      Object.assign({ isActive: true }, a)
                    );

                  },
                  (d) => {
                    for (let i = 0; i < d.length; i++) d[i].value = "";

                    openDialog(false);
                  }
                ),
                () => {
                  openDialog(false);
                }
              );
              openDialog(true);
            }

            // setSelection(e.target.id);
          }}
          className="btn-rounded right-rounded"
        >
          ADVANCE SEARCH
        </button>
        <button className="btn-rounded right-rounded refresh"
          onClick={() =>{ window.location.reload();
          console.log("clicked");}}>
          ↻
        </button>
      </div>
      <Input
        id="c-search"
        onKeyDown={(e) => {
          if (e.which == 13) {
            let val = value.value;
            console.log(value.value);
            console.log(inputVal);

            if (val != inputVal && val.length > 0) {
              setinputVal(val);
            }
          }
        }}
        onChange={(e) => {
          value.value = e.target.value;
          if (e.target.value.length === 0 && inputVal != "") {
            setinputVal("");
          }
        }}
        placeholder="Search Customer No."
        className="customer-search"
      ></Input>
      <div className="three-buttons">
        <button
          id="b4"
          onClick={() => {
            setCOB(
              DialogData(
                "add",
                openDialog,
                async (d) => {
                  let a = {};
                  console.log(d);
                  for (let i = 0; i < d.length; i++) {
                    if (d[i].value != undefined || d[i].value != "")
                      a[d[i].name] = d[i].value;
                  }
                  console.log(a);
                  for (const [key, val] of Object.entries(a)) {
                    if (key !== "clear_date" && val == "") {
                      wait(3000).then((e) => setErrorMessage(""));
                      setErrorMessage(
                        "The required field " + key + " is missing"
                      );
                      return;
                    }
                  }

                  try {
                    let response = await fetch(
                      "http://localhost:8080/hrcback/Add",
                      {
                        method: "POST",

                        body: new URLSearchParams(a),
                        headers: {
                          //      "Access-Control-Allow-Origin": "*",
                          // Accept: "application/json",
                          // "Content-Type": "application/x-www-form-urlencoded",
                        },
                      }
                    );
                    let e = await response.json();

                    if (response.ok) {
                      for (let i = 0; i < d.length; i++) d[i].value = "";

                      openDialog(false);
                    } else {
                      throw e;
                    }
                  } catch (err) {
                    wait(3000).then((e) => setErrorMessage(""));
                    setErrorMessage(err.message);
                  }
                },
                (d) => {
                  for (let i = 0; i < d.length; i++) d[i].value = "";

                  openDialog(false);
                }
              ),
              () => {
                openDialog(false);
              }
            );
            openDialog(true);
          }}
          className="btn-rounded left-rounded"
        >
          ADD
        </button>
        <button
          id="b5"
          onClick={() => {
            setCOB(
              DialogData(
                "edit",
                openDialog,
                (d) => {
                  // EDIT CLICK

                  // Checking if any rows are selected or not
                  if (selectedRows.length == 0) {
                    wait(3000).then((e) => setErrorMessage(""));
                    setErrorMessage("You have not selected any rows");

                    return;
                  }

                  // PARSING THE DATA
                  let a = {};
                  for (let i = 0; i < d.length; i++)
                    if (d[i].value != undefined && d[i].value != "")
                      a[d[i].name] = d[i].value;

                  console.log(Object.assign({ sl_no: selectedRows }, a));
                  let obj = Object.assign({ sl_no: selectedRows }, a);
                  if (
                    a["invoice_currency"] === undefined &&
                    a["cust_payment_terms"] === undefined
                  ) {
                    wait(3000).then((e) => setErrorMessage(""));
                    setErrorMessage("Fill atleast one of the fields");
                    return;
                  }

                  let body = "sl_no="+obj.sl_no[0].sl_no+"&cust_payment_terms="+a.cust_payment_terms+"&invoice_currency="+a.invoice_currency;
                  fetch("http://localhost:8080/hrcback/EditRecord?"+body, {
                    method: "POST",

                    headers: {
                      //      "Access-Control-Allow-Origin": "*",
                      // Accept: "application/json",
                      // "Content-Type": "application/x-www-form-urlencoded",
                    },
                  }).then((ed) =>
                    ed
                      .json()
                      .then((e) => {
                        console.log(e);
                        if (ed.ok) {
                          for (let i = 0; i < d.length; i++) d[i].value = "";

                          openDialog(false);
                          window.location.reload();
                        } else throw e;
                      })
                      .catch((err) => {
                        wait(3000).then((e) => setErrorMessage(""));
                        setErrorMessage(err.message);
                      })
                  );
                },
                (d) => {
                  openDialog(false);
                  // CaNCEL CLICK
                }
              )
            );
            openDialog(true);
          }}
          className="button-square btn-rounded"
        >
          EDIT
        </button>
        <button
          id="b6"
          onClick={() => {
            setCOB(
              DialogData(
                "delete",
                openDialog,
                () => {
                  openDialog(false);
                },
                () => {
                  if (selectedRows.length == 0) {
                    wait(3000).then((e) => setErrorMessage(""));
                    setErrorMessage("You have not selected any rows");

                    return;
                  }
                  let body = { sl_no: selectedRows.map(e => e.sl_no) };
                  fetch("http://localhost:8080/hrcback/Delete", {
                    method: "POST",

                    body: JSON.stringify(body),
                    headers: {},
                  }).then((ed) =>
                    ed
                      .json()
                      .then((e) => {
                        console.log(e);
                        if (ed.ok) {
                          openDialog(false);
                          window.location.reload();
                        } else throw e;
                      })
                      .catch((err) => {
                        wait(3000).then((e) => setErrorMessage(""));
                        setErrorMessage(err.message);
                      })
                  );
                }
              )
            );
            openDialog(true);
          }}
          className="btn-rounded right-rounded"
        >
          DELETE
        </button>
      </div>
      <div>
        <Dialog
          maxHeight={"90vh"}
          maxWidth={"100%"}
          sx={{
            "& .MuiPaper-root": {
              backgroundColor: " #2C4350",
              border: "none",

              borderRadius: 0,
            },
          }}
          open={dialogOpen}
          keepMounted
          onClose={() => {
            openDialog(false);
          }}
          aria-describedby="alert-dialog-slide-description"
        >
          <DialogTitle color="white">{currentObj.title}</DialogTitle>
          <DialogContent>
            {errorMessage != "" && (
              <div
                style={{
                  margin: "auto",
                  display: "flex",
                  borderRadius: "15px",
                  justifyContent: "center",
                  width: "90%",
                  padding: "5px 3px 5px 3px",
                  backgroundColor: "red",
                  color: "white",
                  boxShadow: "inset 0 0 20px red",
                }}
              >
                {"ERROR: " + errorMessage + "!"}
              </div>
            )}
            {currentObj.content}

            <DialogContentText id="alert-dialog-slide-description"></DialogContentText>
          </DialogContent>
          {currentObj.actions}

          <DialogActions></DialogActions>
        </Dialog>
      </div>
    </div>
  );
};
export default ButtonRow;
