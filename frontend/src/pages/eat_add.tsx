import { PageLayout } from "@/components/layout/page"
import { ChevronLeft } from "lucide-react"
import { useForm } from "react-hook-form"
import { EatForm } from "../components/form/eatform"
import { HeaderLayout } from "../components/layout/header"

export function EatWritePage() {
	return <PageLayout
		header={
			<HeaderLayout
				// left={<FiArrowLeft className="ml-2" size={24} />}
				left={<ChevronLeft className="ml-2" size={24} />}
				title={"기록하기"}
			/>
		}
		content={
			<>
				<EatForm />
			</>
		}
	/>
}
