import React from 'react'
import ReactDOM from 'react-dom/client'
import Home from './Home.tsx'
import 'bootstrap/dist/css/bootstrap.css';
import AddProjectDataFormPage from './components/AddProjectDataFormPage.tsx';
import ListFeatures from './components/ListFeatures.tsx';
import SearchForm from './components/SearchForm.tsx'
import AddFeatureDataFormPage from './components/AddFeatureDataFormPage.tsx';

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
      <Route path="/featurelist" element={<ListFeatures />} />
      <Route path="/search" element={<SearchForm />} />
      <Route path="/addfeature" element={<AddFeatureDataFormPage />} />
    </>
  )
);

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
