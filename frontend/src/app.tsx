import { Route, Routes, useRoutes } from "react-router-dom"
import { useUserQuery } from "./hook/user"
import { NotFoundPage } from "./pages/404"
import { EatWritePage } from "./pages/eat_add"
import { ErrorPage } from "./pages/error"
import { HomePage } from "./pages/home"
import { LoginPage } from "./pages/login"
import { SplashPage } from "./pages/splash"

export function AppRouter() {
	const { data, isLoading } = useUserQuery()

	if (isLoading) return <SplashPage />
	if (data === null) return <LoginPage />

	return <Routes>
		<Route path="/" Component={HomePage} />
		{/* <Route path="/eat/create" Component={EatWritePage} /> */}
		<Route path="eat">
			<Route path="create" Component={EatWritePage} />
		</Route>
		<Route path="*" Component={NotFoundPage} />
	</Routes>
}
