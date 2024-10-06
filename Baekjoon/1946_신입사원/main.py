# 다른 모든 지원자와 비교했을 때,  서류심사 성적과 면접시험 성적 중
# 적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선발한다는 원칙


def solution():
    N = int(input())
    SCORES = sorted(
        [tuple(map(int, input().split())) for _ in range(N)],
        key=lambda x: (x[0], x[1]),
    )

    best_score = SCORES[0][1]

    cnt = 0
    for _, score in SCORES:
        if best_score < score:
            continue
        cnt += 1
        best_score = score

    return cnt


T = int(input())
print(*[solution() for _ in range(T)], sep="\n")
