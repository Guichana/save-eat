import { HeaderLayout } from "@/components/layout/header"
import { PageLayout } from "@/components/layout/page"

import { Button } from "@/components/ui/button"
import { Dialog, DialogClose, DialogContent, DialogTrigger } from "@/components/ui/dialog"
import { useEatCreateMutation, useEatDeleteMutation, useEatQuery } from "@/hook/eat"
import { ChevronLeft, EclipseIcon, EllipsisIcon, EllipsisVerticalIcon, Loader2Icon, PencilIcon } from "lucide-react"
import { useNavigate, useParams } from "react-router-dom"
import { EatViewer } from "../components/eat/eat_viewer"
import { NavigationHeader } from "../components/headers"

function useEatId() {
	const { id } = useParams()
	return parseInt(id!)
}

function EatViewDialog() {
	const eat_id = useEatId()
	const navigate = useNavigate()
	const { isPending, mutate } = useEatDeleteMutation()

	function deleteEat() {
		mutate(eat_id, {
			onSuccess: () => {
				navigate(-1)
			},
		})
	}

	return <Dialog>
		<DialogTrigger asChild>
			<Button className="mr-2" size="icon" variant="ghost">
				<EllipsisVerticalIcon />
			</Button>
		</DialogTrigger>
		<DialogContent className="p-3 gap-3">
			<Button variant={"outline"}>{"편집"}</Button>
			<Button disabled={isPending} onClick={deleteEat} variant={"destructive"}>
				{isPending ? <Loader2Icon className="animate-spin mr-1" size={20} /> : null}
				{"삭제"}
			</Button>
		</DialogContent>
	</Dialog>
}

export function EatViewPage() {
	const eat_id = useEatId()
	const { isSuccess, data } = useEatQuery(eat_id)

	return <PageLayout
		header={
			<NavigationHeader
				transparent
				right={<EatViewDialog />}
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
