from collections import Counter

# MST
V, E = map(int, input().split())
EDGES = [tuple(map(int, input().split())) for _ in range(E)]
EDGES.sort(key=lambda x: x[2])

parent = [i for i in range(V)]
visited = [False] * V
answer = 0

for start, end, weight in EDGES:
    # if sum(visited) == V:
    #     break
    print(parent)
    if len(Counter(parent).keys()) == 1:
        break
    if not visited[start - 1] or not visited[end - 1]:
        visited[start - 1] = True
        visited[end - 1] = True

        before = max(parent[start - 1], parent[end - 1])
        after = min(parent[start - 1], parent[end - 1])
        parent = [after if par == before else par for par in parent]

        answer += weight
        continue



print(answer)
