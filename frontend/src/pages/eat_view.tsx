import { HeaderLayout } from "@/components/layout/header"
import { PageLayout } from "@/components/layout/page"
import { Button } from "@/components/ui/button"
import { useEatCreateMutation, useEatQuery } from "@/hook/eat"
import { ChevronLeft, PencilIcon } from "lucide-react"
import { useParams } from "react-router-dom"
import { EatViewer } from "../components/eat/eat_viewer"
import { NavigationHeader } from "../components/headers"

export function EatViewPage() {
	const { id } = useParams()
	const { isSuccess, data } = useEatQuery(parseInt(id!))

	return <PageLayout
		header={
			<NavigationHeader
				transparent
				right={
					<Button className="mr-2" size="icon" variant="link">
						{/* <PencilIcon size={20} /> */}
						{"편집"}
					</Button>
				}
			/>
		}
		content={isSuccess
			? <EatViewer
				data={{
					date: new Date(data.eatDate),
					place: data.placeName,
					price: data.price,
					rating: data.rating,
					comment: data.comment,
					title: data.foodName,
					tags: data.tags,
					photos: data.photos,
				}}
			/>
			: null}
	/>
}
