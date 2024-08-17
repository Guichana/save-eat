import { RefObject, useEffect, useMemo, useRef } from "react"

export function getGsi() {
	if (window.google?.accounts?.id) {
		return Promise.resolve(google.accounts.id)
	}

	var script = document.getElementById("GSI_LIB")
	if (script) {
		return new Promise<typeof google.accounts.id>(rs => {
			script!.addEventListener("load", () => {
				console.log("asdf")
				rs(google.accounts.id)
			})
		})
	}

	return new Promise<typeof google.accounts.id>(rs => {
		const script = document.createElement("script")
		script.src = "https://accounts.google.com/gsi/client"
		script.id = "GSI_LIB"
		script.addEventListener("load", () => {
			console.log("load")
		})
		script.addEventListener("load", () => {
			console.log("asdf")
			rs(google.accounts.id)
		})
		document.head.appendChild(script)
	})
}

export function useGoogleLogin(onLogin: (credential: string) => void) {
	const loader = useMemo(() =>
		getGsi().then(gsi => {
			gsi.initialize({
				client_id: process.env.APP_OAUTH_CLIENT_GOOGLE_ID!,
				use_fedcm_for_prompt: true,
				auto_select: true,
				callback(response) {
					onLogin(response.credential)
					console.log(response)
				},
				ux_mode: "popup",
			})
			return gsi
		}), [])

	return {
		openPrompt() {
			loader.then(gsi => {
				gsi.prompt()
			})
		},
		renderButton(ref: RefObject<any>) {
			if (ref.current) {
				loader.then(gsi => {
					gsi.renderButton(ref.current!, { type: "standard" })
				})
			}
		},
	}
}

// export function GoogleLoginButton() {
// 	const ref = useRef<HTMLDivElement>(null)
// 	useEffect(() => {
// 		if (ref.current) {
// 			getGsi().then(gsi => gsi.renderButton(ref.current!, { type: "standard" }))
// 		}
// 	}, [ref])

// 	return <div ref={ref} />
// }
