n = int(input())

dp = [0]*n
lines = []
for _ in range(n):
    a, b = map(int, input().split())
    lines.append([a, b])

lines.sort()

for i in range(n):
    dp[i] = 1
    for j in range(i):
        a, b = lines[i]
        c, d = lines[j]

        if d< a:
            dp[i] = max(dp[i], dp[j]+1)
ans = 0
print(max(dp))