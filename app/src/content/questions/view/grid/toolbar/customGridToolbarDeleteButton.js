import DeleteIcon from '@mui/icons-material/Delete.js'
import React from 'react'
import CustomGridToolbarButton from './customGridToolbarButton.js'

export default function CustomGridToolbarDeleteButton(props) {
  return <CustomGridToolbarButton title={'Delete'} startIcon={<DeleteIcon/>} {...props}/>
}
