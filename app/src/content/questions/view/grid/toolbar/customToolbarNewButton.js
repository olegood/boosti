import AddIcon from '@mui/icons-material/Add'
import React from 'react'
import CustomToolbarButton from './customToolbarButton.js'

export default function CustomToolbarNewButton(props) {
  return <CustomToolbarButton title={'New'} startIcon={<AddIcon/>} {...props}/>
}
