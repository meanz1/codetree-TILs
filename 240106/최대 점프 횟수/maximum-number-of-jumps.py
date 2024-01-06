n = int(input())
jumps = list(map(int, input().split()))

dp = [0]*n
flag = n
for i in range(n):
    for j in range(i+1, i+jumps[i]+1):
        if j >= n:
            break
        dp[j] = max(dp[i]+1, dp[j])

new_dp = dp[1:]
for i in range(n-1):
    if new_dp[i] == 0:
        flag = i
        break
if flag != n:
    print(max(new_dp[:i]))
else:
    print(max(new_dp))