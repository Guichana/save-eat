import { NotFoundIcon } from "@/components/icon"
import { PageLayout } from "@/components/layout/page"

export function NotFoundPage() {
	return <PageLayout
		content={
			<div className="self-center m-auto">
				<NotFoundIcon />
			</div>
		}
	/>
}
