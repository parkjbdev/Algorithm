# Dijkstra Algorithm with Adjacent List and Priority Heap
# https://www.acmicpc.net/problem/1753

from math import inf
from heapq import heappush, heappop

# 입력
V, E = map(int, input().split())
K = int(input()) - 1

# Adjacent List
adj = [[] for _ in range(V)]
for _ in range(E):
    x, y, curr_w = map(int, input().split())
    adj[x - 1].append((curr_w, y - 1))

# Distance
distance = [inf for _ in range(V)]
distance[K] = 0

# Priority Heap: 거리순으로 정렬
heap = []
heappush(heap, (0, K))

while heap:
    # 노드 방문
    curr_w, curr_v = heappop(heap)
    if distance[curr_v] < curr_w:
        continue

    # 가장 작은 weight 가진 곳으로..
    for next_w, next_v in adj[curr_v]:
        new_distance = distance[curr_v] + next_w
        if new_distance < distance[next_v]:
            distance[next_v] = new_distance
            heappush(heap, (new_distance, next_v))

print(*map(lambda x: str(x).upper(), distance), sep="\n")
