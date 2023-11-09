import sys
input = sys.stdin.readline

n, k = map(int, input().split())
nums = [0]+list(map(int, input().split()))

prefix_sum = [0]*(n+1)

def get_sum(s, e):
    return prefix_sum[e] - prefix_sum[s-1]

for i in range(1, n+1):
    prefix_sum[i] = prefix_sum[i-1]+nums[i]

cnt = 0

for i in range(1, n+1):
    for j in range(1, n+1):
        if get_sum(i, j) == k:
            cnt += 1

print(cnt)