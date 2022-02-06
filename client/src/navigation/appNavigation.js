import MenuIcon from '@mui/icons-material/Menu'
import { AppBar, Box, Drawer, IconButton, List, ListItem, ListItemIcon, ListItemText, Toolbar } from '@mui/material'
import React, { useState } from 'react'
import { useNavigate } from 'react-router'
import { MENU_ITEMS } from './constants.js'

export default function AppNavigation() {

  const [openDrawer, setOpenDrawer] = useState(false)

  const navigate = useNavigate()

  const handleMenuIconClick = () => {
    setOpenDrawer(!openDrawer)
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
            {MENU_ITEMS?.map(item => {
              const { icon, text, path } = item
              return (
                <ListItem button key={text} onClick={() => {
                  navigate(path, { replace: true })
                  setOpenDrawer(false)
                }}>
                  <ListItemIcon>{icon}</ListItemIcon>
                  <ListItemText primary={text}/>
                </ListItem>
              )
            })}
          </List>
        </Box>
      </Drawer>
    </React.Fragment>
  )
}
