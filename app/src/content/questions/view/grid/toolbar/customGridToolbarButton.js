import { Button } from '@mui/material'
import React from 'react'

export default function CustomGridToolbarButton(props) {
  const { title } = props
  return (
    <Button size={'small'} {...props}>
      {title}
    </Button>
  )
}
