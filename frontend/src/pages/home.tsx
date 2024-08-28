import { EatItem } from "@/components/eat/eat_item"
import { PageLayout } from "@/components/layout/page"
import { useEatListInfiniteQuery } from "@/hook/eat"
import { PenBoxIcon, PlusCircleIcon, SearchIcon, User2Icon } from "lucide-react"
import { useNavigate } from "react-router-dom"
import { LogoSqure, LogoText } from "../components/icon"
import { HeaderLayout } from "../components/layout/header"
import { Button } from "../components/ui/button"
import { navigateLogout } from "../lib/urls"

function EatListView() {
	const navigate = useNavigate()
	const { data, hasNextPage } = useEatListInfiniteQuery()

	return <div className="pt-3">
		{data?.pages.flat().map(data =>
			<EatItem
				key={data.id}
				data={data}
				onClick={() => navigate("eat/" + data.id)}
			/>
		)}
		{hasNextPage ? <Button>{"더보기"}</Button> : null}
	</div>
}

export function HomePage() {
	const navigate = useNavigate()
	return <PageLayout
		header={
			<HeaderLayout
				left={
					<Button variant={"link"}>
						<User2Icon />
					</Button>
				}
				title={<LogoText className="h-6 w-auto" />}
				right={
					<Button variant={"link"}>
						<SearchIcon />
					</Button>
				}
			/>
		}
		content={
			<>
				<EatListView />
				{/* <Button className="m-2" onClick={navigateLogout}>LOGOUT-TEST</Button> */}
				<div className="flex justify-center pb-4 pt-2 sticky bottom-0">
					<Button className="" variant={"outline"} onClick={() => navigate("eat/create")}>
						<PenBoxIcon size={20} className="mr-2" />
						{"Eat 추가하기"}
					</Button>
				</div>
			</>
		}
	/>
}
