from typing import List


def solution(m: int, n: int, puddles: List[List[int]]):
    dp_map = [[0] * n for i in range(m)]

    isInMap = lambda x, y: 0 <= x < m and 0 <= y < n
    isPuddle = lambda x, y: [x + 1, y + 1] in puddles
    isValid = lambda x, y: isInMap(x, y) and not isPuddle(x, y)
    getDpMap = lambda x, y: dp_map[x][y] if isValid(x, y) else 0

    dp_map[0][0] = 1

    for i in range(m):
        for j in range(n):
            dp_map[i][j] += getDpMap(i - 1, j)
            dp_map[i][j] += getDpMap(i, j - 1)
            dp_map[i][j] %= 1000000007

    return dp_map[m - 1][n - 1]
