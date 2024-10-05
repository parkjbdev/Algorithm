# Dijkstra Algorithm with Adjacent List and Priority Heap
# https://www.acmicpc.net/problem/1753

from math import inf
from heapq import heappush as push, heappop as pop

# 입력
V, E = map(int, input().split())
K = int(input()) - 1 # 시작 정점 번호
EDGES = [tuple(map(int, input().split())) for _ in range(E)]

# Adjacent List
ADJ_EDGES = [[] for _ in range(V)]

for edge in EDGES:
    ADJ_EDGES[edge[0] - 1].append((edge[2], edge[1] - 1))

# Distance
distance = [inf for _ in range(V)]
distance[K] = 0

# Priority Heap: 거리순으로 정렬
pq = []
push(pq, (0, K))

while pq:
    # 노드 방문
    cost, vertex = pop(pq)
    if distance[vertex] < cost:
        continue

    # 가장 작은 weight 가진 곳으로..
    for next_c, next_v in ADJ_EDGES[vertex]:
        cmp_distance = distance[vertex] + next_c
        if cmp_distance < distance[next_v]:
            distance[next_v] = cmp_distance
            push(pq, (cmp_distance, next_v))

print(*map(lambda x: str(x).upper(), distance), sep="\n")
