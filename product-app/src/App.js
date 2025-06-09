import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import ProductList from "./components/ProductList";
import ProductForm from "./components/ProductForm";
import FindProduct from "./components/FindProduct";

const App = () => {
    return (
        <Router>
            <nav>
                <Link to="/">Home</Link> | 
                <Link to="/add">Add Product</Link> | 
                <Link to="/find">Find Product</Link>
            </nav>
            <Routes>
                <Route path="/" element={<ProductList />} />
                <Route path="/add" element={<ProductForm />} />
                <Route path="/find" element={<FindProduct />} />
            </Routes>
        </Router>
    );
};

export default App;