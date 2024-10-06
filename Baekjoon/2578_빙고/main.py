bingo = [list(map(int, input().split())) for _ in range(5)]
numbers = []
for _ in range(5):
    numbers.extend(map(int, input().split()))


def idx_of(value):
    global bingo
    for i, row in enumerate(bingo):
        if number in row:
            return i, row.index(value)

    raise Exception(f"{value} not found")

def calc_sum(bingo):
    sum_x = sum(bingo[x])
    sum_y = sum(list(map(list, zip(*bingo)))[y])
    sum_diag1 = sum([bingo[i][i] for i in range(5)])
    sum_diag2 = sum([bingo[i][4 - i] for i in range(5)])
    return sum_x, sum_y, sum_diag1, sum_diag2

bingo_cnt = 0
cnt = 0
for number in numbers:
    cnt += 1
    x, y = idx_of(number)

    before = calc_sum(bingo)
    bingo[x][y] = 0
    after = calc_sum(bingo)

    for i in range(4):
        if before[i] != 0 and after[i] == 0:
            bingo_cnt += 1

    if bingo_cnt >= 3:
        break

print(cnt)

