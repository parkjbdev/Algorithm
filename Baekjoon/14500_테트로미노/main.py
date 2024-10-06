# Brute Force
N, M = map(int, input().split())
MAP = [list(map(int, input().split())) for _ in range(N)]
TETROS = [
    # ㅡ 모양
    [[1, 1, 1, 1]],
    [[1], [1], [1], [1]],
    # ㅁ 모양
    [[1, 1], [1, 1]],
    # ㄴ 모양
    [[1, 0], [1, 0], [1, 1]],
    [[0, 1], [0, 1], [1, 1]],
    [[1, 1], [0, 1], [0, 1]],
    [[1, 1], [1, 0], [1, 0]],
    [[0, 0, 1], [1, 1, 1]],
    [[1, 0, 0], [1, 1, 1]],
    [[1, 1, 1], [1, 0, 0]],
    [[1, 1, 1], [0, 0, 1]],
    # ㄴㄱ 모양
    [[1, 0], [1, 1], [0, 1]],
    [[0, 1, 1], [1, 1, 0]],
    [[0, 1], [1, 1], [1, 0]],
    [[1, 1, 0], [0, 1, 1]],
    # ㅗ 모양
    [[1, 1, 1], [0, 1, 0]],
    [[0, 1, 0], [1, 1, 1]],
    [[0, 1], [1, 1], [0, 1]],
    [[1, 0], [1, 1], [1, 0]],
]

answer = 0

for tetro in TETROS:
    size_x = len(tetro)
    size_y = len(tetro[0])

    for i in range(N):
        if i + size_x > N: break
        for j in range(M):
            if j + size_y > M: break
            result = 0
            for ii in range(size_x):
                for jj in range(size_y):
                    result += tetro[ii][jj] * MAP[i + ii][j + jj]
            answer = max(result, answer)

print(answer)

