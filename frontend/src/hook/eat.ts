import apiClient from "@/lib/apiClient"
import { useMutation, useQuery } from "react-query"

type EatCreateDto = {
	placeName: string,
	eatDate: string,
	foodName: string,
	rating: number,
	price: number,
	comment: string,
	tags: string[],
}
type EatCreateResponseDto = {
	eatId: number,
}

export function useEatCreateMutation() {
	return useMutation({
		mutationKey: "EAT_CREATE",
		async mutationFn(data: EatCreateDto): Promise<EatCreateResponseDto> {
			const response = await apiClient.post("eat", data)
			return response.data
		},
	})
}

type EatDataDto = {
	id: number,
	placeName: string,
	eatDate: string,
	foodName: string,
	rating: number,
	price: number,
	comment: string,
	tags: string[],
}

export function useEatQuery(eatId: number) {
	return useQuery({
		queryKey: ["EAT_READ", eatId],
		async queryFn(): Promise<EatDataDto> {
			const { data } = await apiClient.get(`eat/${eatId}`)
			return data
		},
	})
}
