import { format } from "date-fns"
import { HandCoinsIcon, MapPinnedIcon, MessageSquareQuoteIcon, StarIcon, TagIcon } from "lucide-react"
import { ReactNode } from "react"
import { Rating } from "react-simple-star-rating"
import { Badge } from "../ui/badge"

function Item(props: { icon: ReactNode, content: ReactNode }) {
	return <div className="flex flex-row items-center px-2 h-10 bg-white">
		<span className="mr-2">{props.icon}</span>
		<span className="text-base">{props.content}</span>
	</div>
}

type EatViewerProps = {
	data: {
		title: string,
		place: string,
		rating: number,
		date: Date,
		tags: string[],
		price: number,
		comment: string,
		photos: string[],
	},
}
export function EatViewer({ data }: EatViewerProps) {
	return <>
		<div className="flex flex-col items-center py-5 bg-white mb-2">
			<div className="text-sm text-gray-700">{format(data.date, "MM월 dd일")}</div>
			<div className="text-2xl font-bold">{data.title}</div>
			<Rating
				className="mt-2"
				readonly
				initialValue={data.rating}
				allowFraction
				fillIcon={<StarIcon fill="currentColor" className="inline size-6" />}
				emptyIcon={<StarIcon fill="currentColor" className="inline size-6" />}
			/>
		</div>

		<Item
			icon={<MapPinnedIcon className="w-10" />}
			content={data.place}
		/>
		<Item
			icon={<HandCoinsIcon className="w-10" />}
			content={data.price + "원"}
		/>
		<Item
			icon={<TagIcon className="w-10" />}
			content={
				<span>
					{data.tags.map((value) =>
						<Badge className="align-top mr-2" variant="outline">
							{value}
						</Badge>
					)}
				</span>
			}
		/>
		<Item
			icon={<MessageSquareQuoteIcon className="w-10" />}
			content={"\"" + data.comment + "\""}
		/>
		<div className="flex flex-col gap-2 p-2">
			{data.photos.map(photoId =>
				<img
					draggable
					className="rounded-md border snap-center"
					src={"/photo/" + photoId}
				/>
			)}
		</div>
	</>
}
