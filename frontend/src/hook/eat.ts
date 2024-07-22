import apiClient from "@/lib/apiClient"
import axios from "axios"
import { useMutation, useQuery } from "react-query"

type EatCreateDto = {
	placeName: string,
	eatDate: string,
	foodName: string,
	rating: number,
	price: number,
	comment: string,
	tags: string[],
	photos: File[],
}
type EatCreateResponseDto = {
	eatId: number,
}

export function useEatCreateMutation() {
	return useMutation({
		mutationKey: "EAT_CREATE",
		async mutationFn({ photos, ...data }: EatCreateDto): Promise<EatCreateResponseDto> {
			const createResult = await apiClient.post<EatCreateResponseDto>("eat", data)

			for (const photo of photos) {
				var form = new FormData()
				form.append("file", photo)
				await apiClient.postForm(`eat/${createResult.data.eatId}/photo`, form)
			}

			return createResult.data
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
	photos: string[],
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
