import axios from "axios";

export function useApi(baseUrl) {
  // Força a base URL para localhost:8080
  const apiBaseUrl = baseUrl || import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";

  console.log("API Base URL:", apiBaseUrl); // só para checar no console

  const api = axios.create({
    baseURL: apiBaseUrl,
    headers: { "Content-Type": "application/json" }
  });

  return api;
}