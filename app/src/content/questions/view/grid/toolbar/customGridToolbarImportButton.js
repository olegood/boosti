import ImportExportIcon from '@mui/icons-material/ImportExport'
import React from 'react'
import CustomGridToolbarButton from './customGridToolbarButton.js'

export default function CustomGridToolbarImportButton(props) {
  return <CustomGridToolbarButton title={'Import'} startIcon={<ImportExportIcon/>} {...props}/>
}
