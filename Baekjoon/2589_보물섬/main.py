from collections import deque

N, M = map(int, input().split())
A = [list(map(lambda x: 0 if x == "W" else 1, list(input()))) for _ in range(N)]

dxs = [0, 1, 0, -1]
dys = [1, 0, -1, 0]


def bfs(x, y):
    global N, M, A
    global dxs, dys

    q = deque([(x, y, 0)])
    visited = [[False] * M for _ in range(N)]
    visited[x][y] = True

    max_cnt = 0

    i, j = 0, 0

    while q:
        i, j, cnt = q.popleft()
        max_cnt = max(max_cnt, cnt)

        for dx, dy in zip(dxs, dys):
            if (
                0 <= i + dx < N
                and 0 <= j + dy < M
                and not visited[i + dx][j + dy]
                and A[i + dx][j + dy] != 0
            ):
                q.append((i + dx, j + dy, cnt + 1))
                visited[i + dx][j + dy] = True

    return max_cnt


max_len = 0

for i in range(N):
    for j in range(M):
        if A[i][j] == 0:
            continue

        cnt = 0
        for dx, dy in zip(dxs, dys):
            if 0 <= i + dx < N and 0 <= j + dy < M and A[i + dx][j + dy] != 0:
                cnt += 1
                if cnt > 2:
                    break

        if cnt > 2:
            continue

        ret = bfs(i, j)
        max_len = max(max_len, ret)

print(max_len)
