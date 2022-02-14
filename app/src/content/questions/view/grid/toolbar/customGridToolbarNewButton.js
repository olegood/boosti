import AddIcon from '@mui/icons-material/Add'
import React from 'react'
import CustomGridToolbarButton from './customGridToolbarButton.js'

export default function CustomGridToolbarNewButton(props) {
  return <CustomGridToolbarButton title={'New'} startIcon={<AddIcon/>} {...props}/>
}
