import sys
input = sys.stdin.readline

n, k = map(int, input().split())
nums = list(map(int, input().split()))
prefix_sum = [0]*n

for i in range(1, n):
    prefix_sum[i] = nums[i-1]+nums[i]

print(max(prefix_sum))