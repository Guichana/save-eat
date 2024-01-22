import { ReactNode } from "react"

type LayoutProps = {
	header?: ReactNode,
	footer?: ReactNode,
	content?: ReactNode,
}

export function PageLayout(props: LayoutProps) {
	return <div className="flex h-dvh flex-col">
		{props.header}
		{props.content}
		{props.footer}
	</div>
}
