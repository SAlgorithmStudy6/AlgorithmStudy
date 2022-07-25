def search_max_candy(n, candy_l):  # 고정된 상황. 최대 캔디 수만 세기.
    count_max_row = 1
    count_max_col = 1
    for x in range(n):
        count_row = 1
        count_col = 1
        for y in range(n - 1):
            if candy_l[x][y] == candy_l[x][y + 1]:  # '우'와 동일 여부 비교.(row) (세로 마지막 열 배제)
                count_row += 1
                if count_max_row < count_row:
                    count_max_row = count_row
            else:
                count_row = 1
            if candy_l[y][x] == candy_l[y + 1][x]:  # '하'와 동일 여부 비교.(col) (가로 마지막 행 배제)
                count_col += 1
                if count_max_col < count_col:
                    count_max_col = count_col
            else:
                count_col = 1
    return max(count_max_row, count_max_col)


input_n = int(input())
candy_list = [list(input()) for _ in range(input_n)]
count_ans = 0
for i in range(input_n):
    for j in range(input_n):
        # '우'와 '하'의 경우만 고려 - 인접한 사탕을 교환
        if i + 1 < input_n:  # '하'의 경우 - 행이 최대 행일 경우, '하' 교환 성립 X. 따라서 해당 경우 배제.
            candy_list[i][j], candy_list[i + 1][j] = candy_list[i + 1][j], candy_list[i][j]
            max_count = search_max_candy(input_n, candy_list)
            if max_count > count_ans:
                count_ans = max_count
            candy_list[i][j], candy_list[i + 1][j] = candy_list[i + 1][j], candy_list[i][j]
        if j + 1 < input_n:  # '우'의 경우 - 행이 최대 행일 경우, '우' 교환 성립 X. 따라서 해당 경우 배제.
            candy_list[i][j], candy_list[i][j + 1] = candy_list[i][j + 1], candy_list[i][j]
            max_count = search_max_candy(input_n, candy_list)
            if max_count > count_ans:
                count_ans = max_count
            candy_list[i][j], candy_list[i][j + 1] = candy_list[i][j + 1], candy_list[i][j]

print(count_ans)

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
