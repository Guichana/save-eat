export function navigateOAuthLogin(provider: "google") {
	location.href = "/oauth2/authorization/" + provider
}

export function navigateLogout() {
	location.href = "/logout"
}
