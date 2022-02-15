import FileUploadIcon from '@mui/icons-material/FileUpload'
import React from 'react'
import CustomToolbarButton from './customToolbarButton.js'

export default function CustomToolbarExport(props) {
  return <CustomToolbarButton title={'Export'} startIcon={<FileUploadIcon/>} {...props}/>
}
