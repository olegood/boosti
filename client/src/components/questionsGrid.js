import { Button, Container } from '@mui/material'
import { DataGrid, GridToolbarContainer } from '@mui/x-data-grid'
import FileDownload from 'js-file-download'
import React, { useEffect, useState } from 'react'
import QuestionsService from '../service/questionsService.js'
import Header from './common/header/header.js'

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

  const handleExportSelected = () => {
    QuestionsService.exportQuestions([...selected])
      .then(resp => FileDownload(resp.data, 'export_' + Date.now().valueOf() + '.txt'))
      .catch(err => console.error(err))
  }

  const toolbar = () => {
    return (
      <GridToolbarContainer>
        <Button onClick={handleExportSelected} disabled={!(selected && selected.size > 0)}>Export Selected</Button>
      </GridToolbarContainer>
    )
  }

  useEffect(() => {
    setLoading(true)
    QuestionsService.getQuestions().then(resp => {
      setRows(resp.data)
      setLoading(false)
    }).catch(err => console.error(err))
  }, [])

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
