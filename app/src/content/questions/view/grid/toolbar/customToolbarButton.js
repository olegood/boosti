import { Button } from '@mui/material'
import React from 'react'

export default function CustomToolbarButton(props) {
  const { title } = props
  return (
    <Button size={'small'} {...props}>
      {title}
    </Button>
  )
}
