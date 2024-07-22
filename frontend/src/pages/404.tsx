import { NotFoundIcon } from "@/components/icon"
import { PageLayout } from "@/components/layout/page"
import { Button } from "@/components/ui/button"

export function NotFoundPage() {
	function goHome() {
		location.href = "/"
	}
	return <PageLayout
		content={
			<div className="self-center m-auto text-center">
				<NotFoundIcon />
				<Button onClick={goHome}>{"홈 화면으로 이동"}</Button>
			</div>
		}
	/>
}
