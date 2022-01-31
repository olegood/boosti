import './App.css';
import AppInfo from "./components/appInfo.js";
import QuestionsContainer from "./components/questions/questionsContainer";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import ImportContainer from "./components/importContainer";
import React from 'react';

function App() {
  return (<>
    <div className='topnav'>
      <a href="/questions">Questions</a>
      <a href="/import">Import</a>
    </div>

    <div className='content'>
      <BrowserRouter>
        <Routes>
          <Route path={"/"} element={<QuestionsContainer/>}/>
          <Route path={"/questions"} element={<QuestionsContainer/>}/>
          <Route path={"/import"} element={<ImportContainer/>}/>
        </Routes>
      </BrowserRouter>
    </div>
    <div className='footer'>
      <AppInfo/>
    </div>
  </>);
}

export default App;
