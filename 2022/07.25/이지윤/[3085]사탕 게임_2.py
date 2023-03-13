# import sys
#
#
# def check_max_candy(n, lst):  # [Part1] 고정된 2차원 배열에서 최대 사탕의 갯수를 세어주는 함수
#     ans = 1
#     for i in range(n):
#         count = 1
#         for j in range(n - 1):  # same row, dif col 과의 비교
#             if lst[i][j] == lst[i][j + 1]:  # 주의 : 여기서 i,j != row, col
#                 count += 1
#             else:
#                 count = 1
#             if ans < count:
#                 ans = count
#         count = 1
#         for j in range(n - 1):  # same col, dif row 와의 비교
#             if lst[j][i] == lst[j + 1][i]:
#                 count += 1
#             else:
#                 count = 1
#             if ans < count:
#                 ans = count
#     return ans
#
#
# # ---------------------------------------------------------------------------------------------------------------
#
# max_num = int(input())
# candy_list = [list(sys.stdin.readline().rstrip()) for _ in range(max_num)]
# max_candy = 0
#
# for x in range(max_num):  # [Part2] 주어진 2차원 배열에서 변화를 일으켜 모든 경우의 수를 커버하는 코드.
#     for y in range(max_num):
#         if x + 1 < max_num:
#             candy_list[x][y], candy_list[x + 1][y] = candy_list[x + 1][y], candy_list[x][y]
#             temp = check_max_candy(max_num, candy_list)
#             if max_candy < temp:
#                 max_candy = temp
#         if y + 1 < max_num:
#             candy_list[x][y], candy_list[x][y + 1] = candy_list[x][y + 1], candy_list[x][y]
#             temp = check_max_candy(max_num, candy_list)
#             if max_candy < temp:
#                 max_candy = temp
# print(max_candy)
