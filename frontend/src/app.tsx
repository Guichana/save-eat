import { Route, Routes } from "react-router-dom"
import { useAuthentication } from "./hook/auth"
import { useUserQuery } from "./hook/user"
import { NotFoundPage } from "./pages/404"
import { EatWritePage } from "./pages/eat_add"
import { EatViewPage } from "./pages/eat_view"
import { HomePage } from "./pages/home"
import { LoginPage } from "./pages/login"

export function AppRouter() {
	const { authenticated } = useAuthentication()
	const { isSuccess } = useUserQuery()

	if (!authenticated || !isSuccess) {
		return <LoginPage />
	}

	return <Routes>
		<Route path="/">
			<Route index Component={HomePage} />
			<Route path="/eat">
				<Route path=":id" Component={EatViewPage} />
				<Route path="create" Component={EatWritePage} />
			</Route>
		</Route>
		<Route path="*" Component={NotFoundPage} />
	</Routes>
}
