import { render, screen } from '@testing-library/react'
import App from './App'

test.skip('renders List of Questions', () => {
  render(<App/>)
  const linkElement = screen.getByText(/Questions/i)
  expect(linkElement).toBeInTheDocument()
})
