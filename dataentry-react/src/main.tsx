import React from 'react'
import ReactDOM from 'react-dom/client'
import 'bootstrap/dist/css/bootstrap.css';
import Routers from './Routers.tsx';

document.title = 'Welcome | Data Entry';

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <Routers />
  </React.StrictMode>,
)