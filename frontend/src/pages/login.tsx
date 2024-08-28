import { LogoSqure } from "@/components/icon"
import { PageLayout } from "@/components/layout/page"
import { useAuthentication } from "@/hook/auth"
import { useGoogleLogin } from "@/lib/gsi"
import { useEffect, useRef, useState } from "react"

export function LoginPage() {
	const { setCredentials } = useAuthentication()
	const ref = useRef<HTMLDivElement>(null)
	const { openPrompt, renderButton } = useGoogleLogin((token) => {
		setCredentials(token)
	})

	useEffect(() => {
		openPrompt()
		renderButton(ref)
	}, [])

	return <PageLayout
		content={
			<div className="flex h-screen items-center justify-center pb-20 flex-col">
				<div className="max-w-80">
					<LogoSqure width="100%" height="100%" />
					<div className="flex justify-center" ref={ref} />
				</div>
			</div>
		}
	/>
}
