import { PageLayout } from "@/components/layout/page"
import { LoginView } from "@/view/login"
import { navigateOAuthLogin } from "../lib/urls"

export function LoginPage() {
	return <PageLayout
		content={
			<div className="self-center m-auto">
				<LoginView
					handleGoogleLogin={() => navigateOAuthLogin("google")}
				/>
			</div>
		}
	/>
}
