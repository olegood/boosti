export const QUESTIONS_COLUMNS = [
  { field: 'id', headerName: 'ID', width: 100 },
  {
    field: 'text',
    headerName: 'Text',
    width: 600,
  },
  {
    field: 'category',
    headerName: 'Category',
    width: 150,
    valueGetter: (params) =>
      `${params.row?.category?.name || ''}`,
  },
  {
    field: 'tags',
    headerName: 'Tags',
    width: 200,
    sortable: false,
  },
  {
    field: 'answer',
    headerName: 'Has Answer',
    width: 120,
    valueGetter: (params) =>
      `${params.row?.answer?.length > 0 ? 'Yes' : ''}`,
  },
]
