import '@fontsource/roboto/300.css'
import '@fontsource/roboto/400.css'
import '@fontsource/roboto/500.css'
import '@fontsource/roboto/700.css'
import React from 'react'
import { Route, Routes } from 'react-router'
import PageAbout from './content/about/pageAbout.js'
import AppFooter from './content/appFooter.js'
import Welcome from './content/home/welcome.js'
import ImportContainer from './content/import/importContainer.js'
import AddQuestion from './content/questions/addQuestion.js'
import QuestionsGrid from './content/questions/questionsGrid.js'
import AppNavigation from './navigation/appNavigation.js'

const routes = [
  {
    path: '/',
    element: <Welcome/>
  },
  {
    path: '/questions',
    element: <QuestionsGrid/>
  },
  {
    path: '/questions/add',
    element: <AddQuestion/>
  },
  {
    path: '/import',
    element: <ImportContainer/>
  },
  {
    path: '/about',
    element: <PageAbout/>
  }
]

export default function App() {
  return (
    <React.Fragment>
      <AppNavigation/>
      <Routes>
        {routes?.map(({ path, element }) => <Route path={path} element={element}/>)}
      </Routes>
      <AppFooter/>
    </React.Fragment>
  )
}
