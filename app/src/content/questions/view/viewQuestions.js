import { Container } from '@mui/material'
import { DataGrid, GridToolbarContainer } from '@mui/x-data-grid'
import FileDownload from 'js-file-download'
import React, { useEffect, useState } from 'react'
import ServiceQuestions from '../../../service/serviceQuestions.js'
import Header from '../../components/common/header/header.js'
import AddQuestionDialog from '../addQuestionDialog.js'
import { QUESTIONS_COLUMNS } from './grid/constants.js'
import CustomToolbarDelete from './grid/toolbar/customToolbarDelete.js'
import CustomToolbarExport from './grid/toolbar/customToolbarExport.js'
import CustomToolbarImport from './grid/toolbar/customToolbarImport.js'
import CustomToolbarNew from './grid/toolbar/customToolbarNew.js'

export default function ViewQuestions() {

  const [loading, setLoading] = useState(true)

  const [rows, setRows] = useState([])
  const [selected, setSelected] = useState(new Set())

  const [needRefresh, setNeedRefresh] = useState(false)

  const [openNewDialog, setOpenNewDialog] = useState(false)

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

  const handleCloseNewDialog = () => {
    setOpenNewDialog(false)
    setNeedRefresh(true)
  }

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
        {/*TODO: Temporary toolbar buttons*/}
        {/*<GridToolbarColumnsButton/>*/}
        {/*<GridToolbarFilterButton/>*/}
        {/*<GridToolbarDensitySelector/>*/}
        {/*<GridToolbarExport/>*/}

        <CustomToolbarNew onClick={() => setOpenNewDialog(true)}/>
        <CustomToolbarImport/>

        <CustomToolbarDelete onClick={handleDeleteSelected} disabled={noRowsSelected}/>
        <CustomToolbarExport onClick={handleExportSelected} disabled={noRowsSelected}/>
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
      <AddQuestionDialog open={openNewDialog} onSave={handleCloseNewDialog} onCancel={handleCloseNewDialog}/>
    </Container>
  )
}
