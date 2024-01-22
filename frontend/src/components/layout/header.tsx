import { ReactNode } from "react"

type HeaderLayoutProps = {
	left?: ReactNode,
	title?: ReactNode,
	right?: ReactNode,
}
export function HeaderLayout(props: HeaderLayoutProps) {
	return <div className="bg-white h-12 border-b-1 relative">
		<div className="absolute h-full inset-0 grid place-content-center ">
			{props.title}
		</div>
		<div className="absolute left-0 h-full">
			{props.left}
		</div>
		<div className="absolute right-0 h-full">
			{props.right}
		</div>
	</div>
}
