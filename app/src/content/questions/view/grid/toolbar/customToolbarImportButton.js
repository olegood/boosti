import ImportExportIcon from '@mui/icons-material/ImportExport'
import React from 'react'
import CustomToolbarButton from './customToolbarButton.js'

export default function CustomToolbarImportButton(props) {
  return <CustomToolbarButton title={'Import'} startIcon={<ImportExportIcon/>} {...props}/>
}
