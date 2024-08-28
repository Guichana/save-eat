import axios from "axios"

const api = axios.create({
	baseURL: "api",
})

export function setBearerToken(token?: string | null) {
	api.defaults.headers.common.Authorization = token ? "Bearer ".concat(token) : undefined
}

export default api
