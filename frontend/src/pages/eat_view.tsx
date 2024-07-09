import { HeaderLayout } from "@/components/layout/header"
import { PageLayout } from "@/components/layout/page"
import { useEatCreateMutation, useEatQuery } from "@/hook/eat"
import { ChevronLeft } from "lucide-react"
import { useParams } from "react-router-dom"
import { EatViewer } from "../components/eat/eat_viewer"
import { NavigationHeader } from "../components/headers"

export function EatViewPage() {
	const { id } = useParams()
	const { isSuccess, data } = useEatQuery(parseInt(id!))
	// pageid
	return <PageLayout
		header={<NavigationHeader />}
		content={isSuccess
			? <EatViewer
				data={{
					date: new Date(data.eatDate),
					price: data.price,
					rating: data.rating,
					comment: data.comment,
					title: data.foodName,
					tags: [],
				}}
			/>
			: null}
	/>
}
