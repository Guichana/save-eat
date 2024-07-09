import { ReactNode } from "react"

type LayoutProps = {
	header?: ReactNode,
	footer?: ReactNode,
	content?: ReactNode,
	backgroundColor?: string,
}

export function PageLayout(props: LayoutProps) {
	return <div
		className="flex h-dvh flex-col"
		style={{ backgroundColor: props.backgroundColor ?? "rgb(241 245 249)" }}
	>
		<div className="sticky top-0">
			{props.header}
		</div>
		{props.content}
		{props.footer}
	</div>
}
