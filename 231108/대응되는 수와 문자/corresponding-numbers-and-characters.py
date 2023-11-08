n, m = map(int, input().split())
d = {}

order = 1
for i in range(n):
    a = input()
    d[a] = str(i+1)
    d[str(i+1)] = a
   

for i in range(m):
    t = input()
    print(d[t])