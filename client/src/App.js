import '@fontsource/roboto/300.css'
import '@fontsource/roboto/400.css'
import '@fontsource/roboto/500.css'
import '@fontsource/roboto/700.css'
import React from 'react'
import AppFooter from './components/appFooter.js'
import AppTabs from './components/appTabs.js'
import ResponsiveAppBar from './components/responsiveAppBar'

export default function App() {
  return (<>
    <ResponsiveAppBar/>
    <AppTabs/>
    <AppFooter/>
  </>)
}
