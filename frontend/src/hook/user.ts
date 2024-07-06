import apiClient from "@/lib/apiClient"
import axios from "axios"
import { useMutation, useQuery, useQueryClient } from "react-query"

// QueryKey
export const LOGIN_STATUS = "LOGIN_STATUS"
export const USER = "USER"

type User = {
	name: string,
	email: string,
	imageUrl: string,
	joinAt: Date,
}

export function useUserQuery() {
	return useQuery<User | null>({
		queryKey: USER,
		async queryFn() {
			try {
				const { data } = await apiClient.get("user/me")
				return data
			} catch (error) {
				if (axios.isAxiosError(error)) {
					if (error.response?.status === 401) return null
				}
				throw error
			}
		},
		refetchOnMount: false,
		refetchOnWindowFocus: false,
	})
}
