import { List, ListItem } from '@mui/material'
import PropTypes from 'prop-types'
import React from 'react'

export default function FileInfo({ selectedFile }) {

  const showFileInfo = selectedFile => {
    const { name, type, size, lastModified } = selectedFile
    return (
      <List>
        <ListItem>Filename: {name}</ListItem>
        {type && <ListItem>Filetype: {type}</ListItem>}
        <ListItem>Size in bytes: {size}</ListItem>
        <ListItem>Last Modified Date: {new Date(lastModified).toLocaleDateString()}</ListItem>
      </List>
    )
  }

  const showNoInfo = () => {
    return (
      <List>
        <ListItem>Select a file to show details</ListItem>
      </List>
    )
  }

  return (
    <React.Fragment>
      {selectedFile ? showFileInfo(selectedFile) : showNoInfo()}
    </React.Fragment>
  )
}

FileInfo.displayName = 'FileInfo'

FileInfo.propTypes = {
  selectedFile: PropTypes.shape.isRequired
}
