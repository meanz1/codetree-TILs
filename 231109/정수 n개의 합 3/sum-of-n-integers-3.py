import sys

input = sys.stdin.readline

n, k = map(int, input().split())

grid = [[0]*(n+1)]

for _ in range(n):
    temp = [0]+list(map(int, input().split()))
    grid.append(temp)

prefix_sum = [[0]*(n+1) for _ in range(n+1)]

def get_sum(x1, y1, x2, y2):
    return prefix_sum[x2][y2]-prefix_sum[x1-1][y2]-prefix_sum[x2][y1-1] + prefix_sum[x1-1][y1-1]

for i in range(1, n+1):
    for j in range(1, n+1):
        prefix_sum[i][j] += prefix_sum[i][j-1] + grid[i][j]

for i in range(1, n+1):
    for j in range(1, n+1):
        prefix_sum[i][j] += prefix_sum[i-1][j]

result = 0

for i in range(1, n-k+2):
    for j in range(1, n-k+2):
        result = max(result, get_sum(i, j, i+k-1, j+k-1))


print(result)