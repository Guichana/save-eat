import { GoogleIcon, LogoSqure } from "@/components/icon"
import { Button } from "@/components/ui/button"

type LoginProps = {
	handleGoogleLogin(): void,
}

export function LoginView(props: LoginProps) {
	return <div className="max-w-80">
		<LogoSqure width="100%" height="100%" />
		<Button variant="outline" className="w-full" onClick={props.handleGoogleLogin}>
			<GoogleIcon />
			{"Google로 시작하기"}
		</Button>
	</div>
}
