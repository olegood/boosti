import './App.css';
import QuestionsContainer from './components/questions/questionsContainer';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ImportContainer from './components/importContainer';
import React from 'react';

// @fontsource start
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
// @fontsource end

import ResponsiveAppBar from './components/responsiveAppBar';
import PageAbout from './components/pageAbout';

function App() {
  return (<>
      <BrowserRouter>
        <ResponsiveAppBar/>
        <Routes>
          <Route path={'/'} element={<QuestionsContainer/>}/>
          <Route path={'/questions'} element={<QuestionsContainer/>}/>
          <Route path={'/import'} element={<ImportContainer/>}/>
          <Route path={'/about'} element={<PageAbout/>}/>
        </Routes>
      </BrowserRouter>
  </>);
}

export default App;
