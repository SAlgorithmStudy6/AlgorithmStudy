import itertools

# def solution(_n, _m, _arr) -> int :
#     count = 0
#     for i in  itertools.combinations(range(1, _n+1), 3):
#         for j in _arr:
#             set_i = set(i)
#             if set_i - j != set_i : break
#             else :
#                 print(count)
#                 print(i)
#                 print(j)
#                 count += 1
#     return count
#
# n, m = map(int, input().split())
# arr = [list(map(int, input().split())) for i in range(m)]
# answer = solution(n, m, arr)
# print(answer)