import { isAxiosError } from "axios"
import { FallbackProps } from "react-error-boundary"
import { NotFoundPage } from "./pages/404"
import { ErrorPage } from "./pages/error"

export function FallbackRouter({ error }: FallbackProps) {
	if (isAxiosError(error)) {
		if (error.response?.status === 404) {
			return <NotFoundPage />
		}
	}

	return <ErrorPage />
}
