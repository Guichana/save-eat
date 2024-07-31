import { format } from "date-fns"
import { StarIcon } from "lucide-react"
import { Rating } from "react-simple-star-rating"

type EatItemProps = {
	data: {
		placeName: string,
		foodName: string,
		date: Date,
		rating: number,
		thumbnail: string | null,
	},
	onClick?(): void,
}
export function EatItem({ data, onClick }: EatItemProps) {
	return <div className="bg-white px-3 py-2 flex items-center" onClick={onClick}>
		<img
			className="h-14 aspect-[4/3] bg-slate-100 rounded-md mr-3"
			src={data.thumbnail ? "/photo/" + data.thumbnail : ""}
		/>
		<div className="flex-1">
			<div className="text-sm text-slate-500">{data.placeName}</div>
			<div className="text-lg">{data.foodName}</div>
		</div>
		<div className="text-right">
			<div className="text-sm">{format(data.date, "MM월 dd일")}</div>
			<Rating
				readonly
				initialValue={data.rating}
				allowFraction
				fillIcon={<StarIcon fill="currentColor" className="inline size-5" />}
				emptyIcon={<StarIcon fill="currentColor" className="inline size-5" />}
			/>
		</div>
	</div>
}
