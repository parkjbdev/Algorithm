str1 = input()
str2 = input()

dp = [[0] * len(str2) for _ in range(len(str1))]


def get_dp(x, y):
    global dp
    if x < 0 or y < 0:
        return 0
    else:
        return dp[x][y]


for i in range(len(str1)):
    for j in range(len(str2)):
        if str1[i] == str2[j]:
            dp[i][j] = get_dp(i - 1, j - 1) + 1
        else:
            dp[i][j] = max(get_dp(i - 1, j), get_dp(i, j - 1))

print(dp[len(str1) - 1][len(str2) - 1])
