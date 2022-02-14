import FileUploadIcon from '@mui/icons-material/FileUpload'
import React from 'react'
import CustomGridToolbarButton from './customGridToolbarButton.js'

export default function CustomGridToolbarExportButton(props) {
  return <CustomGridToolbarButton title={'Export'} startIcon={<FileUploadIcon/>} {...props}/>
}
