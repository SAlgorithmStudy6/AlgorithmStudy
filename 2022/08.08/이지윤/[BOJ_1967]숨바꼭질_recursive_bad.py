# 무한 루프에 빠지게 되더라.

import sys
sys.setrecursionlimit(300000)

n, k = map(int, input().split())

def dfs(_n, _k, count) :
    global answer
    if _n == _k : answer = min(answer, count)
    if _n < 0 or _n > 1000000 : return
    dfs(_n-1, _k, count+1)
    dfs(_n+1, _k, count+1)
    dfs(2*_n, _k, count+1)


answer = 0
dfs(n, k, 0)
print(answer)