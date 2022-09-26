import sys;

sys.stdin = open("input.txt")


def solution() -> int:
    return 0


T = int(input())
for t in range(1, T + 1):
    n = int(input())
    arr = [list(map(int, input().split())) for _ in range(n)]
    answer = solution()
    print(f"#{t} {answer}")
