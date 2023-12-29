import { useRoutes } from "react-router-dom"
import { NotFoundPage } from "./pages/404"
import { HomePage } from "./pages/home"
import { LoginPage } from "./pages/login"

// TODO: login상태에 따라 router 구성을 다르게하기

export function AppRouter() {
	return useRoutes([
		{
			path: "/",
			Component: HomePage,
		},
		{
			path: "/login",
			Component: LoginPage,
		},
		{
			path: "*",
			Component: NotFoundPage,
		},
	])
}
