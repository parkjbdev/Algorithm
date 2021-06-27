class Pair {
	public x: number
	public y: number

	constructor(x: number, y: number) {
		this.x = x
		this.y = y
	}

	public setNumber(num: number) {
		this.x = this.getX(num)
		this.y = this.getY(num)

	}

	public distanceTo = (num: number): number => {
		return Math.abs(this.getX(num) - this.x)
			+ Math.abs(this.getY(num) - this.y)
	}

	private getX = (num: number): number => {
		return num == 0 ? 1 : (num - 1) % 3
	}

	private getY = (num: number): number => {
		return num == 0 ? 3 : Math.floor((num - 1) / 3)
	}
}

class Solution {
	leftHand: Pair = new Pair(0, 3)
	rightHand: Pair = new Pair(2, 3)
	answer: string = ""
	private readonly leftNum: number[] = [1, 4, 7]
	private readonly rightNum: number[] = [3, 6, 9]

	solution = (numbers: number[], hand: string): string => {
		for (const num of numbers) {
			if (this.leftNum.includes(num)) this.appendAnswer_Left(num)
			else if (this.rightNum.includes(num)) this.appendAnswer_Right(num)
			else {
				const leftDistance = this.leftHand.distanceTo(num)
				const rightDistance = this.rightHand.distanceTo(num)

				if (leftDistance > rightDistance) this.appendAnswer_Right(num)
				else if (leftDistance < rightDistance) this.appendAnswer_Left(num)
				else {
					if (hand === "left") this.appendAnswer_Left(num)
					else if (hand === "right") this.appendAnswer_Right(num)
				}
			}
		}
		return this.answer
	}

	appendAnswer_Right = (num: number): void => {
		this.rightHand.setNumber(num)
		this.answer += "R"
	}

	appendAnswer_Left = (num: number): void => {
		this.leftHand.setNumber(num)
		this.answer += "L"
	}
}

export default Solution