import { PageLayout } from "@/components/layout/page"
import { useGoogleLogin } from "@/lib/gsi"
import { LoginView } from "@/view/login"
import { useEffect, useRef, useState } from "react"
import { navigateOAuthLogin } from "../lib/urls"

export function LoginPage() {
	const ref = useRef<HTMLDivElement>(null)
	const { openPrompt, renderButton } = useGoogleLogin(() => {})

	useEffect(() => {
		openPrompt()
		renderButton(ref)
	}, [])

	return <PageLayout
		content={
			<div className="flex h-screen items-center justify-center pb-20 flex-col">
				<LoginView
					handleGoogleLogin={() => navigateOAuthLogin("google")}
				/>
				<div ref={ref} />
			</div>
		}
	/>
}
