import sys
input = sys.stdin.readline

k, n = map(int, input().split())
selected_num = []

def find_per(cnt):
    if cnt == n:
        print_per()
        return
    
    for i in range(1, k+1):
        selected_num.append(i)
        find_per(cnt+1)
        selected_num.pop()

def print_per():
    for num in selected_num:
        print(num, end=" ")
    print()

find_per(0)