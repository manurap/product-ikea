import axios from "axios";
import { API_URL } from "../const/productConst";

export const getAllProducts = (page = 0,size=5) => axios.get(`${API_URL}/products?page=${page}&size=${size}`);
export const getProductById = (id) => axios.get(`${API_URL}/products/${id}`);
export const addProduct = (product) => axios.post(`${API_URL}/products`, product);