import { setBearerToken } from "@/lib/apiClient"
import { atom, useRecoilState } from "recoil"

const AUTHENTICATED = atom({
	key: "AUTHENTICATED",
	default: false,
})

export function useAuthentication() {
	const [authenticated, setAuthenticated] = useRecoilState(AUTHENTICATED)
	return {
		authenticated,
		setCredentials(token: string) {
			setBearerToken(token)
			setAuthenticated(true)
		},
	}
}
