import { Button, styled, Typography } from '@mui/material'
import FileInfo from './fileInfo'
import Header from './common/header/header'
import QuestionsService from '../service/questionsService'
import React, { useState } from 'react'

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

    QuestionsService.uploadFile(data)
      .then(resp => {
        setIsError(false)
        setUploadResult('Success')
      })
      .catch(err => {
        setIsError(true)
        setUploadResult(err.response.data)
      })
  }

  return <>
    <Header text={'Import File'}/>
    <div>
      <label htmlFor="contained-button-file">
        <Input type="file" id="contained-button-file" onChange={changeHandler}/>
        <Button variant="contained" component="span">
          Browse
        </Button>
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
  </>

}
