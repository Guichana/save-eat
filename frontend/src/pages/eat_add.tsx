import { EatForm } from "@/components/eat/eat_form"
import { NavigationHeader } from "@/components/headers"
import { HeaderLayout } from "@/components/layout/header"
import { PageLayout } from "@/components/layout/page"
import { useEatCreateMutation } from "@/hook/eat"
import { formatISO } from "date-fns"
import { ChevronLeft } from "lucide-react"
import { useNavigate } from "react-router-dom"

function EatAdd() {
	const { mutate } = useEatCreateMutation()
	const navigate = useNavigate()

	return <EatForm
		onSubmit={(data) => {
			mutate({
				...data,
				eatDate: formatISO(data.eatDate, { representation: "date" }),
			}, {
				onSettled: console.log,
				onSuccess: ({ eatId }) => {
					navigate("/eat/" + eatId, { replace: true })
				},
			})
		}}
	/>
}

export function EatWritePage() {
	return <PageLayout
		header={<NavigationHeader title={"기록하기"} />}
		content={<EatAdd />}
	/>
}
