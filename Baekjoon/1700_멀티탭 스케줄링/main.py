N, K = map(int, input().split())
APPLIANCES = list(map(int, input().split()))

plugged = set()
answer = 0

for i, appl in enumerate(APPLIANCES):
    if len(plugged) < N:
        plugged.add(appl)
        continue

    if appl in plugged:
        continue

    # appl is not plugged and plugs are full
    # 앞에 N개를 더 보고 나중에 쓸거를 남겨두기..
    candidates = plugged.copy()
    for j in range(i + 1, len(APPLIANCES)):
        if len(candidates) == 1:
            break
        if APPLIANCES[j] in candidates:
            candidates.remove(APPLIANCES[j])

    plugged.remove(candidates.pop())
    plugged.add(appl)
    answer += 1

print(answer)
