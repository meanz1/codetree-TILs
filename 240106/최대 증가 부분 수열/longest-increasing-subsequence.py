n = int(input())
seq = list(map(int, input().split()))
dp = [0]*(n)
dp[0] = 1
# 이 전 수가 나보다 작아야해
for i in range(1, n):
    for j in range(i):
        if seq[j] < seq[i]:
            dp[i] = max(dp[i], dp[j]+1)

print(max(dp))