import { NotFoundIcon } from "@/components/icon"
import { PageLayout } from "@/components/layout/page"
import { Button } from "@/components/ui/button"
import { TriangleAlertIcon } from "lucide-react"
export function ErrorPage() {
	function goHome() {
		location.href = "/"
	}
	return <PageLayout
		content={
			<div className="flex h-screen flex-col items-center justify-center">
				<TriangleAlertIcon size={48} color="grey" />
				<Button variant="outline" className="mt-4" onClick={goHome}>{"홈 화면으로"}</Button>
			</div>
		}
	/>
}
