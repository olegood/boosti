import '@fontsource/roboto/300.css'
import '@fontsource/roboto/400.css'
import '@fontsource/roboto/500.css'
import '@fontsource/roboto/700.css'
import React from 'react'
import { Route, Routes } from 'react-router'
import AppFooter from './components/appFooter.js'
import ImportContainer from './components/import/importContainer.js'
import PageAbout from './components/pageAbout.js'
import QuestionsGrid from './components/questionsGrid.js'
import AppNavigation from './navigation/appNavigation.js'

export default function App() {
  return (
    <React.Fragment>
      <AppNavigation/>
      <Routes>
        <Route path="/" element={<QuestionsGrid/>}/>
        <Route path="/questions" element={<QuestionsGrid/>}/>
        <Route path="/import" element={<ImportContainer/>}/>
        <Route path="/about" element={<PageAbout/>}/>
      </Routes>
      <AppFooter/>
    </React.Fragment>
  )
}
