import sys
import heapq
input = sys.stdin.readline

INT_MAX = sys.maxsize

n, m = map(int, input().split())
k = int(input())

hq = []

graph = [
    [] for _ in range(n+1)
]

for i in range(m):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

dist = [INT_MAX] * (n+1)
dist[k] = 0

heapq.heappush(hq, (0, k))

while hq:
    min_dist, min_idx = heapq.heappop(hq)
    if dist[min_idx] != min_dist:
        continue
    for target_idx, target_dist in graph[min_idx]:
        new_dist = target_dist + dist[min_idx]
        if dist[target_idx] > new_dist:
            dist[target_idx] = new_dist
            heapq.heappush(hq, (new_dist, target_idx))

for i in range(1, n+1):
    if dist[i] == INT_MAX:
        print(-1)
    else:
        print(dist[i])