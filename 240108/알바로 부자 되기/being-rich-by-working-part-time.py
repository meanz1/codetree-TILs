n = int(input())
data = []

for _ in range(n):
    a, b, c = map(int, input().split())
    data.append([a, b, c])
dp = [0]*n
for i in range(n):
    a, b, c = data[i]
    dp[i] = c

    for j in range(i):
        d, e, f = data[j]
        if e < a:
            dp[i] = max(dp[i], dp[j]+c)

print(max(dp))