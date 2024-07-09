import { ChevronLeft } from "lucide-react"
import { ReactNode } from "react"
import { useNavigate } from "react-router-dom"
import { HeaderLayout } from "./layout/header"
import { Button } from "./ui/button"

type NavigationHeaderProps = {
	title?: ReactNode,
	right?: ReactNode,
}
export function NavigationHeader(props: NavigationHeaderProps) {
	return <HeaderLayout
		left={<BackButton />}
		{...props}
	/>
}

function BackButton() {
	const navigate = useNavigate()
	function goBack() {
		navigate(-1)
	}
	return <Button variant={"link"} onClick={goBack}>
		<ChevronLeft />
	</Button>
}
