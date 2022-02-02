import '@fontsource/roboto/300.css'
import '@fontsource/roboto/400.css'
import '@fontsource/roboto/500.css'
import '@fontsource/roboto/700.css'
import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import ImportContainer from './components/import/importContainer.js'
import PageAbout from './components/pageAbout'
import QuestionsContainer from './components/questions/questionsContainer'
import ResponsiveAppBar from './components/responsiveAppBar'

export default function App() {
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
  </>)
}
