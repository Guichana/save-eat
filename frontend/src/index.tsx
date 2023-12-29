import "@/assets/styles/global.css"
import "@/assets/styles/font.css"
import { createRoot } from "react-dom/client"
import { HashRouter } from "react-router-dom"
import { RecoilRoot } from "recoil"
import { AppRouter } from "./app"

const app = document.createElement("div")
document.body.appendChild(app)

const root = createRoot(app)
root.render(
	<RecoilRoot>
		<HashRouter>
			<AppRouter />
		</HashRouter>
	</RecoilRoot>,
)
