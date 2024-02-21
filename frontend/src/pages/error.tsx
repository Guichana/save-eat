import { NotFoundIcon } from "@/components/icon"
import { PageLayout } from "@/components/layout/page"
export function ErrorPage() {
	return <PageLayout
		content={
			<div className="self-center m-auto">
				<div className="font-extrabold text-4xl text-slate-600 mb-2">
					{":("}
				</div>
				{"오류발생"}
			</div>
		}
	/>
}
