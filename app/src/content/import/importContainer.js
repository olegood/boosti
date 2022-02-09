import { Button, Container, styled, Typography } from '@mui/material'
import React, { useState } from 'react'
import ServiceFile from '../../service/serviceFile.js'
import Header from '../components/common/header/header.js'
import './css/import-container.css'
import FileInfo from './fileInfo.js'

const Input = styled('input')({
  display: 'none',
})

export default function ImportContainer() {

  const [selectedFile, setSelectedFile] = useState()
  const [isSelected, setIsSelected] = useState(false)

  const [uploadResult, setUploadResult] = useState('')

  const [isError, setIsError] = useState(false)

  const changeHandler = (event) => {
    setSelectedFile(event.target.files[0])
    setIsSelected(true)
    setUploadResult('')
    setIsError(false)
  }

  const handleSubmission = () => {
    const data = new FormData()
    data.append('file', selectedFile)

    ServiceFile.uploadFile(data)
      .then(() => {
        setIsError(false)
        setUploadResult('Success')
      })
      .catch(err => {
        setIsError(true)
        setUploadResult(err.response.data)
      })
  }

  return <>
    <Container maxWidth="xl">
      <Header text={'Import File'}/>
      <div>
        <label htmlFor="contained-button-file">
          <Input type="file" id="contained-button-file" onChange={changeHandler}/>
          <Button variant="contained" component="span">Browse</Button>
        </label>
        <FileInfo selectedFile={selectedFile}/>
        <Button variant="contained" onClick={handleSubmission} disabled={!isSelected}>Upload</Button>
        {uploadResult && (
          <Typography variant="overline" display="block">
            <b>Result:</b>
            <div className={isError ? 'm-color-failure' : 'm-color-success'}>{uploadResult}</div>
          </Typography>
        )}
      </div>
    </Container>
  </>

}
