import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n = int(input())

if (n % 2 == 1):
    print("CY")
else:
    print("SK")

"""
n = 1 상 -> CY
n = 2 상 창 -> SK
n = 3 상 창 상 -> CY
n = 4 상 상 상 창 -> SK
      상 창 상 창
n = 5 상 상 상 창 상 -> CY
      상 창 상 창 상
"""
