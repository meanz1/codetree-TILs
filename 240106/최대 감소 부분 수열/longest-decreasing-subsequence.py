n = int(input())
seq = [0]+list(map(int, input().split()))
dp = [10001]*(n+1)

for i in range(1, n+1):
    for j in range(i):
        if seq[j] > seq[i]:
            dp[i] = max(dp[i], dp[j]+1)

print(max(dp)-10000)