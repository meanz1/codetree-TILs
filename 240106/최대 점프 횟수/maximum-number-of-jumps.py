n = int(input())
jumps = list(map(int, input().split()))

dp = [0]*n

for i in range(n):
    for j in range(i+1, i+jumps[i]+1):
        if j >= n:
            break
        dp[j] = max(dp[i]+1, dp[j])

print(max(dp))