import apiClient from "@/lib/apiClient"
import { useInfiniteQuery, useMutation, useQuery } from "@tanstack/react-query"
import axios from "axios"
import { number, z } from "zod"

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
		mutationKey: ["EAT_CREATE"],
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

const EatListDataDto = z.object({
	list: z.array(
		z.object({
			id: z.number(),
			placeName: z.string(),
			date: z.coerce.date(),
			foodName: z.string(),
			rating: z.number(),
			thumbnail: z.string().nullable(),
		}),
	),
	hasNext: z.boolean(),
})

export function useEatListInfiniteQuery() {
	return useInfiniteQuery({
		queryKey: ["EAT_LIST"],
		async queryFn({ pageParam }) {
			const { data } = await apiClient.get("eat", { params: { page: pageParam } })
			return EatListDataDto.parse(data)
		},
		select(data) {
			return {
				pageParams: data.pageParams,
				pages: data.pages.map(item => item.list),
			}
		},
		initialPageParam: 0,
		getNextPageParam: (lastPage, pages) => lastPage.hasNext ? pages.length : null,
	})
}

export function useEatDeleteMutation() {
	return useMutation({
		mutationKey: ["EAT_DELETE"],
		async mutationFn(eat_id: number) {
			await apiClient.delete("/eat/" + eat_id)
		},
	})
}
