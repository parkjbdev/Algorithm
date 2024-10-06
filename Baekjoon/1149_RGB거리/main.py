N = int(input())
RGB_COST = [list(map(int, input().split())) for _ in range(N)]

for i in range(1, N):
    for j in range(3):
        RGB_COST[i][j] += min(RGB_COST[i - 1][j - 1], RGB_COST[i - 1][j - 2])

print(min(RGB_COST[N - 1]))
