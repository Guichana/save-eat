export class User {
	name: string
	email: string
	imageUrl: string
	readonly joinAt: Date

	constructor(data: Pick<User, "name" | "imageUrl" | "email" | "joinAt">) {
		this.name = data.name
		this.email = data.email
		this.imageUrl = data.imageUrl
		this.joinAt = data.joinAt
	}
}
