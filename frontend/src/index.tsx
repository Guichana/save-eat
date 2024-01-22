import "@/assets/styles/global.css"
import "@/assets/styles/font.css"
import { createRoot } from "react-dom/client"
import { QueryClient, QueryClientProvider } from "react-query"
import { HashRouter } from "react-router-dom"
import { RecoilRoot } from "recoil"
import { AppRouter } from "./app"

const app = document.createElement("div")
const queryClient = new QueryClient()

document.body.appendChild(app)
const root = createRoot(app)

root.render(
	<RecoilRoot>
		<QueryClientProvider client={queryClient}>
			<HashRouter>
				<AppRouter />
			</HashRouter>
		</QueryClientProvider>
	</RecoilRoot>,
)
