import React, { useState } from "react";
import { getProductById } from "../services/ProductService";

const FindProduct = () => {
    const [productId, setProductId] = useState("");
    const [product, setProduct] = useState(null);

    const handleSearch = () => {
        getProductById(productId)
            .then(response => setProduct(response.data))
            .catch(error => {
                setProduct(null);
                console.error("Product not found:", error);
            });
    };

    return (
        <div>
            <input
                type="text"
                placeholder="Enter Product ID"
                value={productId}
                onChange={e => setProductId(e.target.value)}
            />
            <button onClick={handleSearch}>Find Product</button>
            {product && (
                <div style={{ marginTop: "1em" }}>
                    <p><strong>ID:</strong> {product.id}</p>
                    <p><strong>Name:</strong> {product.name}</p>
                    <p><strong>Product Type:</strong> {product.productType?.name}</p>
                    <p><strong>Colours:</strong> {product.colours && product.colours.length > 0
                        ? product.colours.map(c => c.name).join(", ")
                        : "None"}
                    </p>
                </div>
            )}
        </div>
    );
};

export default FindProduct;