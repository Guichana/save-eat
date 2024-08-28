import "@/assets/styles/global.css"
import "@/assets/styles/font.css"
import { QueryClient, QueryClientProvider } from "@tanstack/react-query"
import { createRoot } from "react-dom/client"
import { ErrorBoundary } from "react-error-boundary"
import { HashRouter } from "react-router-dom"
import { RecoilRoot } from "recoil"
import { AppRouter } from "./app"
import { FallbackRouter } from "./error"

const app = document.createElement("div")
const queryClient = new QueryClient({
	defaultOptions: {
		queries: {
			retry: false,
			refetchInterval: false,
			throwOnError: true,
			// useErrorBoundary: true,
		},
	},
})

document.body.appendChild(app)
const root = createRoot(app)

root.render(
	<RecoilRoot>
		<QueryClientProvider client={queryClient}>
			<ErrorBoundary FallbackComponent={FallbackRouter}>
				<HashRouter>
					<AppRouter />
				</HashRouter>
			</ErrorBoundary>
		</QueryClientProvider>
	</RecoilRoot>,
)
