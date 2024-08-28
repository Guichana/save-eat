import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Calendar } from "@/components/ui/calendar"
import { Drawer, DrawerContent, DrawerFooter, DrawerHeader, DrawerTitle } from "@/components/ui/drawer"
import {
	DropdownMenu,
	DropdownMenuContent,
	DropdownMenuItem,
	DropdownMenuSeparator,
	DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import { zodResolver } from "@hookform/resolvers/zod"
import { format, startOfToday } from "date-fns"
import { ko } from "date-fns/locale"
import { CalendarIcon, CameraIcon, StarIcon, XIcon } from "lucide-react"
import { ReactNode, useRef, useState } from "react"
import { useForm, useFormContext } from "react-hook-form"
import { Rating } from "react-simple-star-rating"
import { z } from "zod"

const formSchema = z.object({
	foodName: z.string(),
	photos: z.array(z.instanceof(File)),
	rating: z.number(),
	price: z.number(),
	comment: z.string(),
	placeName: z.string(),
	tags: z.array(z.string()),
	eatDate: z.date(),
})
type formSchema = z.infer<typeof formSchema>

function ImageSelect(props: { onSelect(img: File): void, children: ReactNode }) {
	const ref = useRef<HTMLInputElement>(null)

	function openDialog() {
		ref.current?.click()
	}

	return <Button
		className="border h-24 aspect-square flex-col"
		type="button"
		variant="secondary"
		onClick={openDialog}
	>
		<Input
			type="file"
			accept="image/*"
			ref={ref}
			onChange={(e) => {
				const file = e.target.files?.item(0)
				if (file) {
					props.onSelect(file)
					ref.current!.value = ""
				}
			}}
			className="sr-only"
		/>
		{props.children}
	</Button>
}

function EatPhotosField() {
	const form = useFormContext<formSchema>()

	return <FormField
		control={form.control}
		name="photos"
		defaultValue={[]}
		render={({ field: { value, onChange } }) => {
			return <FormItem>
				<FormLabel>사진</FormLabel>
				<FormControl>
					<div className="flex flex-wrap gap-2">
						<ImageSelect
							onSelect={(file) => {
								const modify = Array.from(value)
								modify.push(file)
								onChange(modify)
							}}
						>
							<CameraIcon className="size-6 mb-1" />
							{"사진 추가"}
						</ImageSelect>
						{value.map((img, index) => {
							function remove() {
								const modify = Array.from(value)
								modify.splice(index, 1)
								onChange(modify)
							}
							function swap(goto: number) {
								const modify = Array.from(value)
								;[modify[goto], modify[index]] = [modify[index], modify[goto]]
								onChange(modify)
							}
							const hasPrev = index > 0
							const hasNext = (value.length - index) != 1

							return <DropdownMenu key={index}>
								<DropdownMenuTrigger>
									<img draggable className="rounded-md border h-24" src={URL.createObjectURL(img)} />
								</DropdownMenuTrigger>
								<DropdownMenuContent>
									<DropdownMenuItem disabled={!hasPrev} onClick={() => swap(index - 1)}>앞으로 이동</DropdownMenuItem>
									<DropdownMenuItem disabled={!hasNext} onClick={() => swap(index + 1)}>뒤로 이동</DropdownMenuItem>
									<DropdownMenuSeparator />
									<DropdownMenuItem onClick={remove}>삭제</DropdownMenuItem>
								</DropdownMenuContent>
							</DropdownMenu>
						})}
					</div>
				</FormControl>
				<FormMessage />
			</FormItem>
		}}
	/>
}

function EatDateField() {
	const form = useFormContext<formSchema>()
	return <FormField
		control={form.control}
		name="eatDate"
		render={({ field }) => {
			const [open, setOpen] = useState(false)

			return <FormItem>
				<FormLabel>날짜</FormLabel>
				<FormControl>
					<div>
						<Drawer open={open} onOpenChange={setOpen}>
							<Button type="button" variant="outline" className="px-3 py-2" onClick={() => setOpen(true)}>
								<CalendarIcon className="mr-2 h-4 w-4" />
								{field.value ? format(field.value, "yyyy-MM-dd") : "날짜 선택"}
							</Button>
							<DrawerContent>
								<DrawerHeader>
									<DrawerTitle>{"날짜를 선택해주세요"}</DrawerTitle>
									<Calendar
										className="mx-auto"
										mode="single"
										selected={field.value}
										onSelect={date => {
											field.onChange(date)
											setOpen(false)
										}}
										locale={ko}
									/>
								</DrawerHeader>
								<DrawerFooter>
									<Button
										className="w-full"
										type="button"
										// variant={"outline"}
										onClick={() => {
											field.onChange(startOfToday())
											setOpen(false)
										}}
									>
										오늘
									</Button>
									<Button className="w-full" type="button" variant={"link"} onClick={() => setOpen(false)}>닫기</Button>
								</DrawerFooter>
							</DrawerContent>
						</Drawer>
					</div>
				</FormControl>
				<FormMessage />
			</FormItem>
		}}
	/>
}

function EatPlaceField() {
	const form = useFormContext<formSchema>()
	return <FormField
		control={form.control}
		name="placeName"
		render={({ field }) => (
			<FormItem>
				<FormLabel>가게 정보</FormLabel>
				<FormControl>
					<Input placeholder="가게 이름 입력" {...field} />
				</FormControl>
				<FormMessage />
			</FormItem>
		)}
	/>
}

function EatNameField() {
	const form = useFormContext<formSchema>()
	return <FormField
		control={form.control}
		name="foodName"
		render={({ field }) => (
			<FormItem>
				<FormLabel>음식 정보</FormLabel>
				<FormControl>
					<Input placeholder="음식 이름 입력" {...field} />
				</FormControl>
				<FormMessage />
			</FormItem>
		)}
	/>
}

function EatRatingField() {
	const form = useFormContext<formSchema>()
	return <FormField
		control={form.control}
		name="rating"
		render={({ field }) => (
			<FormItem>
				<FormLabel>별점</FormLabel>
				<FormControl>
					<div>
						<Rating
							onClick={field.onChange}
							onPointerMove={field.onChange}
							fillIcon={<StarIcon fill="currentColor" className="inline size-8" />}
							emptyIcon={<StarIcon fill="currentColor" className="inline size-8" />}
							allowFraction
						/>
						{/* <span className="text-xl font-bold text-slate-400">{field.value}</span> */}
						<Badge className="align-top ml-2" variant="outline">
							{field.value}
						</Badge>
					</div>
				</FormControl>
				<FormMessage />
			</FormItem>
		)}
	/>
}

function EatPriceField() {
	const form = useFormContext<formSchema>()
	return <FormField
		control={form.control}
		name="price"
		render={({ field }) => (
			<FormItem>
				<FormLabel>가격</FormLabel>
				<FormControl>
					<Input
						placeholder="가격"
						type="number"
						{...field}
						onChange={({ target: { value } }) => {
							field.onChange(parseInt(value))
						}}
					/>
				</FormControl>
				<FormMessage />
			</FormItem>
		)}
	/>
}

function EatCommentField() {
	const form = useFormContext<formSchema>()
	return <FormField
		control={form.control}
		name="comment"
		render={({ field }) => (
			<FormItem>
				<FormLabel>한줄평</FormLabel>
				<FormControl>
					<Input placeholder="한줄평 입력" {...field} />
				</FormControl>
				<FormMessage />
			</FormItem>
		)}
	/>
}

function EatTagField() {
	const form = useFormContext<formSchema>()
	return <FormField
		control={form.control}
		name="tags"
		defaultValue={[]}
		render={({ field }) => {
			const [addValue, setAddValue] = useState("")
			function add(v: string) {
				if (field.value.includes(v)) return
				field.onChange(field.value.concat(v))
			}
			function remove(v: string) {
				field.onChange(
					field.value.filter((item: string) => item !== v),
				)
			}
			return <FormItem>
				<FormLabel>태그</FormLabel>
				<FormControl>
					<div className="flex flex-wrap gap-1">
						{field.value.map((value: string) =>
							<Badge key={value} className="h-7" onClick={() => remove(value)}>
								{value}
								<XIcon className="ml-2" size={16} />
							</Badge>
						)}
						<Input
							className="w-fit border-dashed text-xs h-7 p-2 focus-visible:ring-offset-0 focus-visible:border-transparent"
							placeholder="태그 추가"
							value={addValue}
							onChange={({ target: { value } }) => {
								setAddValue(value)
							}}
							onKeyDown={({ key }) => {
								if (key !== "Enter") return
								if (!addValue) return
								add(addValue)
								setAddValue("")
							}}
							onBlur={() => {
								if (!addValue) return
								add(addValue)
								setAddValue("")
							}}
						/>
					</div>
				</FormControl>
				<FormMessage />
			</FormItem>
		}}
	/>
}

export function EatForm(props: { onSubmit(data: formSchema): void }) {
	const form = useForm<formSchema>({
		resolver: zodResolver(formSchema),
	})
	return <Form {...form}>
		<form
			className="space-y-6 p-4"
			onSubmit={form.handleSubmit(props.onSubmit)}
			onKeyDown={event => {
				if (event.key === "Enter") event.preventDefault()
			}}
		>
			<EatDateField />
			<EatPlaceField />
			<EatNameField />
			<EatPhotosField />
			<EatRatingField />
			<EatPriceField />
			<EatCommentField />
			<EatTagField />

			<Button className="w-full" type="submit">저장</Button>
		</form>
	</Form>
}
