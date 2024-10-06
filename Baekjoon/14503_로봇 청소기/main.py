N, M = map(int, input().split())
r, c, d = map(int, input().split())

room_map = [list(map(int, input().split())) for _ in range(N)]

answer = 0

def is_movable(x, y):
    global room_map
    if 0 <= x < N and 0 <= y < M:
        return room_map[x][y] != 1
    return False

def is_clean(x, y):
    global room_map
    # 이동할수 없는 곳은 clean 한 것으로 간주
    return not is_movable(x, y) or not room_map[x][y] == 0

def is_nearby_all_clean(x, y):
    global room_map

    return (
        is_clean(x - 1, y)
        and is_clean(x + 1, y)
        and is_clean(x, y - 1)
        and is_clean(x, y + 1)
    )

def move(adder=1):
    global N, M, r, c, d
    backup = r, c, d

    if d == 0:
        r -= adder
    elif d == 1:
        c += adder
    elif d == 2:
        r += adder
    elif d == 3:
        c -= adder

    if is_movable(r, c):
        return True
    else:
        r, c, d = backup
        return False

def clean(x, y):
    global room_map, answer
    room_map[x][y] = -1
    answer += 1

while True:
    if not is_clean(r, c):
        clean(r, c)
        continue

    # From here, current coord is clean
    if is_nearby_all_clean(r, c):
        if not move(-1):
            break
        else:
            continue

    # From here, current coord is clean but nearby is not clean
    d = (d + 3) % 4
    if move():
        if is_clean(r, c):
            move(-1)
            continue

print(answer)
