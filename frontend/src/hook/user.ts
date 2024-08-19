import apiClient from "@/lib/apiClient"
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query"
import axios from "axios"
import { useAuthentication } from "./auth"

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
	const { authenticated } = useAuthentication()
	return useQuery<User | null>({
		queryKey: [USER],
		async queryFn() {
			try {
				const { data } = await apiClient.get("user/me")
				return data
			} catch (error) {
				if (axios.isAxiosError(error)) {
					if (error.response?.status === 404) {
						await apiClient.post<{ userId: number }>("user")
						const { data } = await apiClient.get("user/me")
						return data
					}
				}
				throw error
			}
		},
		refetchOnMount: false,
		refetchOnWindowFocus: false,
		enabled: authenticated,
	})
}
