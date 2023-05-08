import React from 'react'
import ReactDOM from 'react-dom/client'
import Home from './Home.tsx'
import 'bootstrap/dist/css/bootstrap.css';
import AddProjectDataFormPage from './components/AddProjectDataFormPage.tsx';
import ListProjectTable from './components/ListProjectTable.tsx';
import SearchProjectForm from './components/SearchProjectForm.tsx'
import App from './App.tsx';

document.title = 'Welcome | Data Entry';
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider
} from "react-router-dom";

const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/" element={<Home />} />
      <Route path="/home" element={<Home />} />
      <Route path="/addproject" element={<AddProjectDataFormPage />} />
      <Route path="/projectlist" element={<ListProjectTable />} />
      <Route path="/searchprojects" element={<SearchProjectForm />} />
    </>
  )
);

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    {/* <RouterProvider router={router} /> */}
    <App />
  </React.StrictMode>,
)
