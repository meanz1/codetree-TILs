import sys

input = sys.stdin.readline

n, k = map(int, input().split())

grid = [[0]*(n+1)]

for _ in range(n):
    temp = [0]+list(map(int, input().split()))
    grid.append(temp)

prefix_sum = [[0]*(n+1) for _ in range(n+1)]

for i in range(1, n+1):
    for j in range(1, n+1):
        prefix_sum[i][j] += prefix_sum[i][j-1] + grid[i][j]

for i in range(1, n+1):
    for j in range(1, n+1):
        prefix_sum[i][j] += prefix_sum[i-1][j]

result = 0

if k == 1:
    for elem in grid:
        result = max(result, *elem)
else:
    for i in range(1, n+2-k):
        for j in range(1, n+2-k):
            result = max(result, prefix_sum[i+k-1][j+k-1]-prefix_sum[i-1][j+k-1]-prefix_sum[j+k-1][i-1]+prefix_sum[i-1][j-1])

print(result)