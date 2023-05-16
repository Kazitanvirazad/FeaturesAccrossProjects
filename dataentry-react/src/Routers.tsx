import Home from './Home.tsx'
import AddProjectDataFormPage from './components/AddProjectDataFormPage.tsx';
import ListFeatures from './components/ListFeatures.tsx';
import SearchForm from './components/SearchForm.tsx'
import AddFeatureDataFormPage from './components/AddFeatureDataFormPage.tsx';
import {
    createBrowserRouter,
    createRoutesFromElements,
    Route,
    RouterProvider
} from "react-router-dom";

const Routers = () => {
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

    return (
        <>
            <RouterProvider router={router} />
        </>
    );
};

export default Routers;