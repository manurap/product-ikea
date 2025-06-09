import React, { useEffect, useState } from "react";
import * as ProductService from "../services/ProductService";
import { PAGE_SIZE } from "../const/productConst";

const ProductList = () => {
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const [pageSize, setPageSize] = useState(PAGE_SIZE); 

    useEffect(() => {
        ProductService.getAllProducts(page, pageSize)
            .then(response => {
                const data = response.data;
                setProducts(data.content || []);
                setTotalPages(data.page?.totalPages || 1);
            })
            .catch(error => console.error("Error fetching products:", error));
    }, [page]);

    const handlePrev = () => {
        if (page > 0) setPage(page - 1);
    };

    const handleNext = () => {
        if (page < totalPages - 1) setPage(page + 1);
    };

    return (
        <div>
            <h2>All Products</h2>
            <ul>
                {products.map(product => (
                    <li key={product.id}>
                        <strong>ID:</strong> {product.id} - <strong>Name:</strong> {product.name}
                        {product.productType?.name && <> - <strong>Type:</strong> {product.productType.name}</>}
                    </li>
                ))}
            </ul>
            <div>
                <button onClick={handlePrev} disabled={page === 0}>Previous</button>
                <span> Page {page + 1} of {totalPages} </span>
                <button onClick={handleNext} disabled={page >= totalPages - 1}>Next</button>
            </div>
        </div>
    );
};

export default ProductList;