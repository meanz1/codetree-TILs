import sys
input = sys.stdin.readline

n, k = map(int, input().split())
nums = list(map(int, input().split()))
prefix_sum = [0]*n
prefix_sum[0] = nums[0]
for i in range(1, n):
    prefix_sum[i] = prefix_sum[i-1]+nums[i]
result = -sys.maxsize
for i in range(n-k):
    result = max(result, prefix_sum[i+k]-prefix_sum[i])
print(result)