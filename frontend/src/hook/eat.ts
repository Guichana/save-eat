import apiClient from "@/lib/apiClient"
import { useMutation } from "react-query"

type EatCreateDto = {
	placeName: string,
	eatDate: Date,
	foodName: string,
	rating: number,
	price: number,
	comment: string,
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
