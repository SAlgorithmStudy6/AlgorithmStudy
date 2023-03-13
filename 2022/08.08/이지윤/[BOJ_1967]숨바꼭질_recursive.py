# 재귀 방법으로 풀기 - 타 블로그 방식

import sys
sys.setrecursionlimit(300000)

def recursive(_n, _k):
    if _n >= _k:
        return _n - _k
    elif _k == 1:
        return 1
    elif _k % 2:
        return 1 + min(recursive(_n, _k- 1), recursive(_n, _k + 1))
    else:
        return min(_k - _n, 1 + recursive(_n, _k // 2))


n, k = map(int, input().split())
print(recursive(n, k))