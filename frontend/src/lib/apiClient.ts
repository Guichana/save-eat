import axios from "axios"

export default axios.create({
	baseURL: "api",
	withCredentials: true,
})

// TODO: csrf interceptor 추가
