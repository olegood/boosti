import { List, ListItem } from '@mui/material'
import React from 'react'

export default function FileInfo(props) {

  const { selectedFile } = props

  const showFileInfo = selectedFile => {
    const { name, type, size, lastModifiedDate } = selectedFile
    return (<><List>
      <ListItem>Filename: {name}</ListItem>
      {type && <ListItem>Filetype: {type}</ListItem>}
      <ListItem>Size in bytes: {size}</ListItem>
      <ListItem>Last Modified Date: {lastModifiedDate.toLocaleDateString()}</ListItem>
    </List></>)
  }

  const showNoInfo = () => {
    return <List>
      <ListItem>Select a file to show details</ListItem>
    </List>
  }

  return (<>
    {selectedFile ? showFileInfo(selectedFile) : showNoInfo()}
  </>)
}
