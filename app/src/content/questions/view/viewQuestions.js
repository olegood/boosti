import { Container } from '@mui/material'
import {
  DataGrid,
  GridToolbarColumnsButton,
  GridToolbarContainer,
  GridToolbarDensitySelector,
  GridToolbarExport,
  GridToolbarFilterButton
} from '@mui/x-data-grid'
import FileDownload from 'js-file-download'
import React, { useEffect, useState } from 'react'
import ServiceQuestions from '../../../service/serviceQuestions.js'
import Header from '../../components/common/header/header.js'
import { QUESTIONS_COLUMNS } from './grid/constants.js'
import CustomGridToolbarDeleteButton from './grid/toolbar/customGridToolbarDeleteButton.js'
import CustomGridToolbarExportButton from './grid/toolbar/customGridToolbarExporttButton.js'
import CustomGridToolbarImportButton from './grid/toolbar/customGridToolbarImportButton.js'
import CustomGridToolbarNewButton from './grid/toolbar/customGridToolbarNewButton.js'

export default function ViewQuestions() {

  const [loading, setLoading] = useState(true)

  const [rows, setRows] = useState([])
  const [selected, setSelected] = useState(new Set())

  const [needRefresh, setNeedRefresh] = useState(false)

  useEffect(() => {
    setLoading(true)
    setNeedRefresh(false)
    ServiceQuestions.getAllQuestions()
      .then(resp => {
        setRows(resp.data)
        setLoading(false)
      })
      .catch(err => console.error(err))
  }, [needRefresh])

  const handleExportSelected = () => {
    ServiceQuestions.exportQuestions([...selected])
      .then(resp => FileDownload(resp.data, 'export_' + Date.now().valueOf() + '.txt'))
      .catch(err => console.error(err))
  }

  const handleDeleteSelected = () => {
    ServiceQuestions.deleteQuestions([...selected])
      .then(() => setNeedRefresh(true))
      .catch(err => console.error(err))
  }

  const toolbar = () => {
    const noRowsSelected = !(selected && selected.size > 0)
    return (
      <GridToolbarContainer>
        <GridToolbarColumnsButton/>
        <GridToolbarFilterButton/>
        <GridToolbarDensitySelector/>
        <GridToolbarExport/>

        <CustomGridToolbarNewButton/>
        <CustomGridToolbarImportButton/>

        <CustomGridToolbarDeleteButton onClick={handleDeleteSelected} disabled={noRowsSelected}/>
        <CustomGridToolbarExportButton onClick={handleExportSelected} disabled={noRowsSelected}/>
      </GridToolbarContainer>
    )
  }

  return (
    <Container maxWidth="xl">
      <Header text={'Questions'}/>
      <div style={{ height: 700, width: '100%' }}>
        <DataGrid
          loading={loading}
          rows={rows}
          columns={QUESTIONS_COLUMNS}
          pageSize={10}
          rowsPerPageOptions={[10]}
          checkboxSelection
          disableSelectionOnClick
          components={{
            Toolbar: toolbar,
          }}
          onSelectionModelChange={(ids) => setSelected(new Set(ids))}
        />
      </div>
    </Container>
  )
}
