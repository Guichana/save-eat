import { User } from "@/domain/user"
import apiClient from "@/lib/apiClient"
import axios from "axios"
import { useMutation, useQuery, useQueryClient } from "react-query"

// QueryKey
export const LOGIN_STATUS = "LOGIN_STATUS"
export const USER = "USER"

export function useUserQuery() {
	return useQuery<User | null>({
		queryKey: USER,
		async queryFn() {
			try {
				const { data } = await apiClient.get("user/me")
				return new User({
					name: data["name"],
					email: data["email"],
					imageUrl: data["imageUrl"],
					joinAt: new Date(data["joinAt"]),
				})
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
