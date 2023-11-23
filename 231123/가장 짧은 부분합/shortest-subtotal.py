import sys
input = sys.stdin.readline

n, s = map(int, input().split())
nums = [0]+list(map(int, input().split()))

j = 0
num_sum = 0
ans = sys.maxsize
for i in range(1, n+1):
    while j+1 <= n and num_sum < s:
        num_sum += nums[j+1]
        j+=1
    if num_sum < s:
        break
    ans = min(ans, j-i+1)
    num_sum -= nums[i]

if ans==sys.maxsize:
    ans = -1
print(ans)