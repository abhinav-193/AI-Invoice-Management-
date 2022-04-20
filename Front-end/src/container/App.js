import React, { Fragment } from "react";
import AdvancedSearchGrid from "../components/Advsearch";
import ButtonRow from "./BtnRow";
import CustomerIDInfoGrid from "../components/CustInfo";
import InfoGrid from "../components/Grid";
import logo from "./logo.png"
import './App.css'

function App() {
  const [currentSeletcted, setSelection] = React.useState("");
  let [rows, setRows] = React.useState([]);
  const [selectedRows, selectRow] = React.useState([]);
  const [inputVal, setinputVal] = React.useState("");
  const [predictedRows, setPredictedRows] = React.useState({});

  const [advancedSearchOptions, setAdvancedSearchOptions] = React.useState({
    isactive: false,
  });
  const value = { value: "" };
  console.log(value);

  return (
    <Fragment>
    <div className="bod">
      <div>
      <div className="Header_home">
      <div className="company_name">
        <img src= {logo} />
      </div>
      <img
        className="img_hrc"
        height={"40"}
        width={"200"}
        src="https://cdn-resources.highradius.com/resources/wp-content/uploads/2020/04/highradius-logo-3.png"
      ></img>
    </div>
      </div>
      <div>
        <ButtonRow
          setSelection={setSelection}
          selectedRows={selectedRows}
          inputVal={inputVal}
          predictedRows={predictedRows}
          setPredictedRows={setPredictedRows}
          currentSeletcted={currentSeletcted}
          value={value}
          advancedSearchOptions={advancedSearchOptions}
          setAdvancedSearchOptions={setAdvancedSearchOptions}
          setinputVal={setinputVal}
        />
        {advancedSearchOptions.isActive ? (
          <AdvancedSearchGrid
            predictedRows={predictedRows}
            advancedSearchOptions={advancedSearchOptions}
            setRows={setRows}
            selectedRows={selectedRows}
            selectRow={selectRow}

          />
        ) : inputVal.length > 0 ? (
          <CustomerIDInfoGrid
            predictedRows={predictedRows}
            selectedRows={selectedRows}
            selectRow={selectRow}
            setinputVal={setinputVal}
            value={value}
            inputVal={inputVal}
          />
        ) 
      :
        (
            <InfoGrid
              predictedRows={predictedRows}
              rows={rows}
              setRows={setRows}
              selectedRows={selectedRows}
              selectRow={selectRow}
            />
        
        )
      }
        
        
      </div>
    </div>
    <div className="footer">
      <a href = "https://www.highradius.com/privacy-policy/">Privacy Policy</a>
      &nbsp;| Copyright 2022 Highradius. All Rights Reserved.
      </div>
    </Fragment>
  );
}
//

export default App;
