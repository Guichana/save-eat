import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Calendar } from "@/components/ui/calendar"
import { Drawer, DrawerClose, DrawerContent, DrawerFooter, DrawerHeader, DrawerTitle } from "@/components/ui/drawer"
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import { format } from "date-fns"
import { ko } from "date-fns/locale"
import { CalendarIcon, ImageIcon, PlusIcon, StarIcon, XIcon } from "lucide-react"
import { useRef, useState } from "react"
import { useForm, useFormContext } from "react-hook-form"
import { Rating } from "react-simple-star-rating"
import { z } from "zod"

const formSchema = z.object({
	name: z.string(),
	picture: z.instanceof(File),
	rating: z.number(),
	price: z.number(),
	comment: z.string(),
})

function EatPictureField() {
	const form = useFormContext()

	return <FormField
		control={form.control}
		name="picture"
		render={({ field }) => {
			const [image, setImage] = useState<string | null>(null)
			const ref = useRef<HTMLInputElement>(null)
			return <FormItem>
				<FormLabel>사진</FormLabel>
				<FormControl>
					<Input
						type="file"
						accept="image/*"
						// {...field}
						ref={ref}
						onChange={(e) => {
							const file = e.target.files?.item(0)
							setImage(file ? URL.createObjectURL(file) : null)
							field.onChange(file)
						}}
						className="hidden"
					/>
				</FormControl>
				{image
					? <img className="rounded-md border" src={image} />
					: <Button
						className="w-full border h-24"
						type="button"
						variant="secondary"
						onClick={() => {
							ref.current?.click()
						}}
					>
						<ImageIcon className="mr-2 size-4" />
						{"사진 선택"}
					</Button>}
				<FormMessage />
			</FormItem>
		}}
	/>
}

function EatDateField() {
	const form = useFormContext()
	return <FormField
		control={form.control}
		name="date"
		render={({ field }) => {
			const [open, setOpen] = useState(false)

			return <FormItem>
				<FormLabel>날짜</FormLabel>
				<FormControl>
					<div>
						<Drawer open={open} onOpenChange={setOpen}>
							<Button variant="outline" className="px-3 py-2" onClick={() => setOpen(true)}>
								<CalendarIcon className="mr-2 h-4 w-4" />
								{field.value ? format(field.value, "yyyy-MM-dd") : "날짜 선택"}
							</Button>
							<DrawerContent>
								<DrawerHeader>
									<DrawerTitle>{"날짜를 선택해주세요"}</DrawerTitle>
									<Calendar
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
									<DrawerClose>
										<Button className="w-full">닫기</Button>
									</DrawerClose>
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
	const form = useFormContext()
	return <FormField
		control={form.control}
		name="place"
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
	const form = useFormContext()
	return <FormField
		control={form.control}
		name="eat-name"
		render={({ field }) => (
			<FormItem>
				<FormLabel>음식 이름</FormLabel>
				<FormControl>
					<Input placeholder="음식 이름 입력" {...field} />
				</FormControl>
				<FormMessage />
			</FormItem>
		)}
	/>
}

function EatRatingField() {
	const form = useFormContext()
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
	const form = useFormContext()
	return <FormField
		control={form.control}
		name="price"
		render={({ field }) => (
			<FormItem>
				<FormLabel>가격</FormLabel>
				<FormControl>
					<Input placeholder="가격" {...field} />
				</FormControl>
				<FormMessage />
			</FormItem>
		)}
	/>
}

function EatCommentField() {
	const form = useFormContext()
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
	const form = useFormContext()
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
							<Badge className="h-7" onClick={() => remove(value)}>
								{value}
								<XIcon className="ml-2" size={16} />
							</Badge>
						)}
						<Input
							className="w-fit border-dashed text-xs h-7 p-2 focus-visible:ring-offset-0"
							placeholder="+ 태그 추가"
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

export function EatForm() {
	const form = useForm()
	return <Form {...form}>
		<form
			className="space-y-6 p-4"
			onSubmit={form.handleSubmit((value) => {
				console.log("submit", value)
			})}
			onKeyDown={event => {
				if (event.key === "Enter") event.preventDefault()
			}}
		>
			<EatDateField />
			<EatPlaceField />
			<EatNameField />
			<EatPictureField />
			<EatRatingField />
			<EatPriceField />
			<EatCommentField />
			<EatTagField />

			<Button className="w-full" type="submit">저장</Button>
		</form>
	</Form>
}
