# Kruskal Algorithm
# https://www.acmicpc.net/problem/1197


def kruskal(V, E, EDGES):
    # Greedy: Kruskal selects minimum edges
    EDGES.sort(key=lambda x: x[2])
    parent = [i for i in range(V)]
    mst_cost = 0

    def find_parent_recursive(x):
        if parent[x] != x:
            parent[x] = find_parent_recursive(parent[x])
        return parent[x]

    def find_parent(x):
        while parent[x] != x:
            parent[x] = parent[parent[x]]  # 경로 압축
            x = parent[x]
        return x

    def union_parent(a, b):
        a = find_parent(a)
        b = find_parent(b)
        if a < b:
            parent[b] = a
        else:
            parent[a] = b

    for i in range(E):
        a, b, cost = EDGES[i]
        # 부모노드가 다를 경우.. 사이클이 발생하지 않으므로 MST에 포함
        if find_parent(a - 1) != find_parent(b - 1):
            union_parent(a - 1, b - 1)
            mst_cost += cost

    return mst_cost


# Prim Algorithm
from heapq import heappop as pop, heappush as push


def prim(V, E, EDGES):
    ADJ_EDGES = [[] for _ in range(V)]

    for edge in EDGES:
        ADJ_EDGES[edge[0] - 1].append((edge[2], edge[1] - 1))
        ADJ_EDGES[edge[1] - 1].append((edge[2], edge[0] - 1))

    pq = [(0, 0)]  # (cost, node)
    visited = [False] * V
    mst_cost = 0

    while pq:
        cost, vertex = pop(pq)
        if visited[vertex]:
            continue
        visited[vertex] = True
        mst_cost += cost

        for next_c, next_v in ADJ_EDGES[vertex]:
            if not visited[next_v]:
                push(pq, (next_c, next_v))

    return mst_cost


V, E = map(int, input().split())
# edge input: a, b, cost
EDGES = [tuple(map(int, input().split())) for _ in range(E)]

print("Kruskal:", kruskal(V, E, EDGES))
print("Prim", prim(V, E, EDGES))
