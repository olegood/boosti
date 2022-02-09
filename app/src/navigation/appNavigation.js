import { ExpandLess, ExpandMore } from '@mui/icons-material'
import AddIcon from '@mui/icons-material/Add'
import HomeIcon from '@mui/icons-material/Home.js'
import ImportExportIcon from '@mui/icons-material/ImportExport.js'
import InfoIcon from '@mui/icons-material/Info.js'
import MenuIcon from '@mui/icons-material/Menu'
import QuestionAnswerIcon from '@mui/icons-material/QuestionAnswer.js'
import TableViewIcon from '@mui/icons-material/TableView'
import {
  AppBar,
  Box,
  Collapse,
  Divider,
  Drawer,
  IconButton,
  List,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Toolbar
} from '@mui/material'
import React, { useState } from 'react'
import { useNavigate } from 'react-router'

export default function AppNavigation() {

  const [openDrawer, setOpenDrawer] = useState(false)
  const [openQuestions, setOpenQuestions] = useState(false)

  const navigate = useNavigate()

  const handleMenuIconClick = () => {
    setOpenDrawer(!openDrawer)
  }

  const handleQuestionsItemClick = () => {
    setOpenQuestions(!openQuestions)
  }

  const handleNavigate = path => {
    navigate(path, { replace: true })
    setOpenDrawer(false)
  }

  return (
    <React.Fragment>
      <AppBar position="static">
        <Toolbar>
          <IconButton color="inherit" onClick={handleMenuIconClick}>
            <MenuIcon/>
          </IconButton>
        </Toolbar>
      </AppBar>
      <Drawer open={openDrawer}>
        <Box sx={{ width: 250 }}>
          <List>
            <ListItemButton key="Home" onClick={() => handleNavigate('/')}>
              <ListItemIcon><HomeIcon/></ListItemIcon>
              <ListItemText primary="Home"/>
            </ListItemButton>

            <ListItemButton key="Questions" onClick={handleQuestionsItemClick}>
              <ListItemIcon><QuestionAnswerIcon/></ListItemIcon>
              <ListItemText primary="Questions"/>
              {openQuestions ? <ExpandLess/> : <ExpandMore/>}
            </ListItemButton>
            <Collapse in={openQuestions} timeout="auto" unmountOnExit>
              <List component="div" disablePadding>
                <ListItemButton sx={{ pl: 4 }} key={'Add New'} onClick={() => handleNavigate('/questions/add')}>
                  <ListItemIcon><AddIcon/></ListItemIcon>
                  <ListItemText primary="Add New"/>
                </ListItemButton>
                <ListItemButton sx={{ pl: 4 }} key={'See All'} onClick={() => handleNavigate('/questions')}>
                  <ListItemIcon><TableViewIcon/></ListItemIcon>
                  <ListItemText primary="See All"/>
                </ListItemButton>
              </List>
            </Collapse>

            <ListItemButton button key="Import" onClick={() => handleNavigate('/import')}>
              <ListItemIcon><ImportExportIcon/></ListItemIcon>
              <ListItemText primary="Import"/>
            </ListItemButton>
            <Divider/>
            <ListItemButton button key="About" onClick={() => handleNavigate('/about')}>
              <ListItemIcon><InfoIcon/></ListItemIcon>
              <ListItemText primary="About"/>
            </ListItemButton>
          </List>
        </Box>
      </Drawer>
    </React.Fragment>
  )
}
