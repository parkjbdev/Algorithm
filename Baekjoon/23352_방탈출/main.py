from collections import deque

N, M = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(N)]

dxs = [0, 1, 0, -1]
dys = [1, 0, -1, 0]


def bfs(x, y):
    global N, M, A
    global dxs, dys

    q = deque([(x, y, 0)])
    visited = [[False] * M for _ in range(N)]
    visited[x][y] = True

    max_cnt = 0
    max_end = 0

    i, j = 0, 0

    while q:
        i, j, cnt = q.popleft()

        if cnt > max_cnt:
            max_cnt = cnt
            max_end = A[i][j]
        elif cnt == max_cnt:
            max_end = A[i][j]

        for dx, dy in zip(dxs, dys):
            if (
                0 <= i + dx < N
                and 0 <= j + dy < M
                and not visited[i + dx][j + dy]
                and A[i + dx][j + dy] != 0
            ):
                q.append((i + dx, j + dy, cnt + 1))
                visited[i + dx][j + dy] = True

    return max_cnt, max_end


max_len = 0
answer = 0

for i in range(N):
    for j in range(M):
        if A[i][j] == 0:
            continue

        ret = bfs(i, j)

        if max_len < ret[0]:
            max_len = ret[0]
            answer = ret[1] + A[i][j]
        elif max_len == ret[0]:
            answer = max(answer, ret[1] + A[i][j])

print(answer)
