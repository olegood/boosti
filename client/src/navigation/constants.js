import HomeIcon from '@mui/icons-material/Home.js'
import ImportExportIcon from '@mui/icons-material/ImportExport.js'
import InfoIcon from '@mui/icons-material/Info.js'
import QuestionAnswerIcon from '@mui/icons-material/QuestionAnswer.js'
import React from 'react'

export const MENU_ITEMS = [
  {
    icon: <HomeIcon/>,
    text: 'Home',
    path: '/'
  },
  {
    icon: <QuestionAnswerIcon/>,
    text: 'Questions',
    path: '/questions'
  },
  {
    icon: <ImportExportIcon/>,
    text: 'Import',
    path: '/import'
  },
  {
    icon: <InfoIcon/>,
    text: 'About',
    path: '/about'
  }
]
