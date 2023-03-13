# import sys; input = sys.stdin.readline
import sys; sys.stdin = open("input.txt")
from itertools import combinations

def solution(_n, _arr) -> int:
    comb_min, comb_list = 100000, list(range(_n))
    for start_member in list(combinations(comb_list, _n//2)):
        start_total = link_total = 0
        link_member = list(set(comb_list) - set(start_member))
        for i, j in list(combinations(start_member, 2)):
            start_total += _arr[i][j]
            start_total += _arr[j][i]
        for i, j in list(combinations(link_member, 2)):
            link_total += _arr[i][j]
            link_total += _arr[j][i]
        comb_min = min(comb_min, abs(start_total - link_total))
    return comb_min

n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
answer = solution(n, arr)
print(answer)