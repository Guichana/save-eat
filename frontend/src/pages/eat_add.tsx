import { EatForm } from "@/components/eat/eat_form"
import { HeaderLayout } from "@/components/layout/header"
import { PageLayout } from "@/components/layout/page"
import { useEatCreateMutation } from "@/hook/eat"
import { formatISO } from "date-fns"
import { ChevronLeft } from "lucide-react"

function EatAdd() {
	const { mutate } = useEatCreateMutation()
	return <EatForm
		onSubmit={(data) => {
			mutate({
				...data,
				eatDate: formatISO(data.eatDate, { representation: "date" }),
			}, { onSettled: console.log })
		}}
	/>
}

export function EatWritePage() {
	return <PageLayout
		header={
			<HeaderLayout
				left={<ChevronLeft className="ml-2" size={24} />}
				title={"기록하기"}
			/>
		}
		content={<EatAdd />}
	/>
}
