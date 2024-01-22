import { PageLayout } from "@/components/layout/page"
import { LogoSqure, LogoText } from "../components/icon"
import { HeaderLayout } from "../components/layout/header"
import { Button } from "../components/ui/button"
import { navigateLogout } from "../lib/urls"

export function HomePage() {
	return <PageLayout
		header={
			<HeaderLayout
				title={<LogoText className="h-8 w-auto" />}
			/>
		}
		content={
			<>
				<div>
					<Button onClick={navigateLogout}>LOGOUT</Button>
				</div>
			</>
		}
	/>
}
