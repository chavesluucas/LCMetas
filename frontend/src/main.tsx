import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './index.css'

//para garantir que não vai dar erro
window.React = React

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
)
