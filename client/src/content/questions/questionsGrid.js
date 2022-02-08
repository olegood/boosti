import { Button, Container } from '@mui/material'
import { DataGrid, GridToolbarContainer } from '@mui/x-data-grid'
import FileDownload from 'js-file-download'
import React, { useEffect, useState } from 'react'
import ServiceQuestions from '../../service/serviceQuestions.js'
import Header from '../components/common/header/header.js'

const columns = [
  { field: 'id', headerName: 'ID', width: 90 },
  {
    field: 'topic',
    headerName: 'Topic',
    width: 200,
  },
  {
    field: 'text',
    headerName: 'Text',
    width: 600,
  }
]

export default function QuestionsGrid() {

  const [loading, setLoading] = useState(true)

  const [rows, setRows] = useState([])
  const [selected, setSelected] = useState(new Set())

  const [needRefresh, setNeedRefresh] = useState(false)

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
        <Button onClick={handleExportSelected} disabled={noRowsSelected}>Export Selected</Button>
        <Button onClick={handleDeleteSelected} disabled={noRowsSelected}>Delete Selected</Button>
      </GridToolbarContainer>
    )
  }

  useEffect(() => {
    setLoading(true)
    setNeedRefresh(false)
    ServiceQuestions.getQuestions()
      .then(resp => {
        setRows(resp.data)
        setLoading(false)
      })
      .catch(err => console.error(err))
  }, [needRefresh])

  return (
    <Container maxWidth="xl">
      <Header text={'Questions'}/>
      <div style={{ height: 700, width: '100%' }}>
        <DataGrid
          loading={loading}
          rows={rows}
          columns={columns}
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
