import { ReactNode } from "react"
import { HeaderLayout } from "./layout/header"
import { Button } from "./ui/button"

type NavigationHeaderProps = {
	title?: ReactNode,
	right?: ReactNode,
}
export function NavigationHeader(props: NavigationHeaderProps) {
	return <HeaderLayout {...props} />
}

function BackButton() {
	return <Button></Button>
}
