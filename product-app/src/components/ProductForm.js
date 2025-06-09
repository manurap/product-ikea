import React, { useState, useEffect } from "react";
import { addProduct } from "../services/ProductService";
import axios from "axios";
import { API_URL } from "../const/productConst";
import { useNavigate } from "react-router-dom"; 

const initialColour = { id: "", name: "" };

const ProductForm = () => {
    const [id, setId] = useState("");
    const [name, setName] = useState("");
    const [productType, setProductType] = useState({ id: "", name: "" });
    const [colours, setColours] = useState([{ ...initialColour }]);
    const [message, setMessage] = useState("");
    const navigate = useNavigate(); 
    const [productTypes, setProductTypes] = useState([]);
    const [colorOptions, setColorOptions] = useState([]);

    useEffect(() => {
        axios.get(`${API_URL}/product-types`)
            .then(res => setProductTypes(res.data))
            .catch(() => setProductTypes([]));
        axios.get(`${API_URL}/colors`)
            .then(res => setColorOptions(res.data))
            .catch(() => setColorOptions([]));
    }, []);

    const handleColourSelect = (index, colorId) => {
        const color = colorOptions.find(c => String(c.id) === String(colorId));
        const updatedColours = colours.map((colour, idx) =>
            idx === index
                ? { id: String(colorId), name: color ? color.name : "" }
                : colour
        );
        setColours(updatedColours);
    };

    const addColourField = () => {
        setColours([...colours, { ...initialColour }]);
    };

    const removeColourField = (index) => {
        setColours(colours.filter((_, idx) => idx !== index));
    };

    const handleProductTypeSelect = (typeId) => {
        const type = productTypes.find(t => String(t.id) === String(typeId));
        setProductType(type ? { id: type.id, name: type.name } : { id: "", name: "" });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const product = {
            id,
            name,
            productType,
            colours: colours.filter(c => c.id && c.name)
        };
        addProduct(product)
            .then(() => {
                setMessage("Product added successfully!");
                setId("");
                setName("");
                setProductType({ id: "", name: "" });
                setColours([{ ...initialColour }]);
                setTimeout(() => navigate("/"), 1000); // <-- redirect after 1 second
            })
            .catch((error) => {setMessage(error.response?.data?.Error )});
    };

    return (
        <div>
            <h2>Add Product</h2>
            
            {message && <p style={{"color":"red"}}>{message}</p>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Product ID:</label>
                    <input value={id} onChange={e => setId(e.target.value)} required />
                </div>
                <div>
                    <label>Name:</label>
                    <input value={name} onChange={e => setName(e.target.value)} required />
                </div>
                <div>
                    <label>Product Type:</label>
                    <select
                        value={productType.id}
                        onChange={e => handleProductTypeSelect(e.target.value)}
                        required
                    >
                        <option value="">Select Product Type</option>
                        {productTypes.map(type => (
                            <option key={type.id} value={type.id}>{type.name}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Colours:</label>
                    {colours.map((colour, idx) => (
                        <div key={idx} style={{ marginBottom: "8px" }}>
                            <select
                                value={colour.id}
                                onChange={e => handleColourSelect(idx, e.target.value)}
                                required
                                style={{ marginRight: "4px" }}
                            >
                                <option value="">Select Colour</option>
                                {colorOptions.map(c => (
                                    <option key={c.id} value={c.id}>{c.name}</option>
                                ))}
                            </select>
                            {colours.length > 1 && (
                                <button type="button" onClick={() => removeColourField(idx)}>
                                    Remove
                                </button>
                            )}
                        </div>
                    ))}
                    <button type="button" onClick={addColourField}>Add Colour</button>
                </div>
                <button type="submit">Add Product</button>
            </form>
        </div>
    );
};

export default ProductForm;