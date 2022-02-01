import './App.css';
import AppInfo from './components/appInfo.js';
import QuestionsContainer from './components/questions/questionsContainer';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ImportContainer from './components/importContainer';
import { Link } from '@mui/material';
import React from 'react';

// @fontsource
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';

function App() {
  return (<>
    <div className='topnav'>
      <Link href="/questions">Questions</Link>
      <Link href="/import">Import</Link>
    </div>

    <div className='content'>
      <BrowserRouter>
        <Routes>
          <Route path={'/'} element={<QuestionsContainer/>}/>
          <Route path={'/questions'} element={<QuestionsContainer/>}/>
          <Route path={'/import'} element={<ImportContainer/>}/>
        </Routes>
      </BrowserRouter>
    </div>
    <div className='footer'>
      <AppInfo/>
    </div>
  </>);
}

export default App;
