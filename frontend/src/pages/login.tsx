import { PageLayout } from "@/components/layout/page"
import { LoginView } from "@/view/login"
import { navigateOAuthLogin } from "../lib/urls"

export function LoginPage() {
	return <PageLayout
		content={
			<div className="flex h-screen items-center justify-center pb-20">
				<LoginView
					handleGoogleLogin={() => navigateOAuthLogin("google")}
				/>
			</div>
		}
	/>
}
