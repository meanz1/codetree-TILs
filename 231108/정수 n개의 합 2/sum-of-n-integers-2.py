import sys
input = sys.stdin.readline

n, k = map(int, input().split())
nums = list(map(int, input().split()))
prefix_sum = [0]*n
prefix_sum[0] = nums[0]
for i in range(1, n):
    prefix_sum[i] = prefix_sum[i-1]+nums[i]
result = -100
for i in range(n-k+2):
    result = max(result, prefix_sum[i+5]-prefix_sum[i])
print(result)