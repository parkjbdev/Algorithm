from math import inf
import heapq

V, E = map(int, input().split())
K = int(input()) - 1

adj = [[] for _ in range(V)]

for _ in range(E):
    x, y, curr_w = map(int, input().split())
    adj[x - 1].append((curr_w, y - 1))

distance = [inf for _ in range(V)]
distance[K] = 0

heap = []
heapq.heappush(heap, (0, K))

while heap:
    # visit
    curr_w, curr_v = heapq.heappop(heap)
    if distance[curr_v] < curr_w:
        continue
    for next_w, next_v in adj[curr_v]:
        new_distance = distance[curr_v] + next_w
        if new_distance < distance[next_v]:
            distance[next_v] = new_distance
            heapq.heappush(heap, (new_distance, next_v))

print(*map(lambda x: str(x).upper(), distance), sep="\n")
