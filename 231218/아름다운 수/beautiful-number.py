import sys
input = sys.stdin.readline

n = int(input())
seq = list()
ans = 0

def is_beatiful():
    i = 0
    while i < n:
        if i + seq[i] - 1 >= n:
            return False
        for j in range(i, i+seq[i]):
            if seq[j]!= seq[i]:
                return False
        i += seq[i]
    return True


def make_seq(cnt):
    global ans
    if cnt == n:
        if is_beatiful():
            ans += 1
        return

    for i in range(1, 5):
        seq.append(i)
        make_seq(cnt+1)
        seq.pop()


make_seq(0)
print(ans)