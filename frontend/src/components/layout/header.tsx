import { ReactNode } from "react"

type HeaderLayoutProps = {
	left?: ReactNode,
	title?: ReactNode,
	right?: ReactNode,
	transparent?: boolean,
}
export function HeaderLayout(props: HeaderLayoutProps) {
	return <div
		className={props.transparent
			? "absolute h-12 inset-x-0"
			: "h-12 relative bg-white border-b-1 border-b-[0.5px] border-slate-100"}
	>
		<div className="absolute h-full inset-0 grid place-content-center">
			{props.title}
		</div>
		<div className="absolute left-0 h-full flex items-center text-lg">
			{props.left}
		</div>
		<div className="absolute right-0 h-full flex items-center">
			{props.right}
		</div>
	</div>
}
