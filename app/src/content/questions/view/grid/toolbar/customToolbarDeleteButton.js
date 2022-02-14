import DeleteIcon from '@mui/icons-material/Delete.js'
import React from 'react'
import CustomToolbarButton from './customToolbarButton.js'

export default function CustomToolbarDeleteButton(props) {
  return <CustomToolbarButton title={'Delete'} startIcon={<DeleteIcon/>} {...props}/>
}
