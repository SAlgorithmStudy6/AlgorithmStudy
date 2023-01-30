import sys

sys.stdin = open("01.30/input.txt", "r", encoding="UTF-8")

n = int(input())
numbers = list(map(int, input().split()))
numbers.sort()

max = 1
for num in numbers:
    if max < num:
        break
    max += num

print(max)


# 시간 초과
# def combination(target):
#     resultSet = set()

#     def combi(idx, nowCombi):
#         if len(nowCombi) == target:
#             resultSet.add(sum(nowCombi))
#             return
#         for i in range(idx, n):
#             combi(i+1, nowCombi+[numbers[i]])

#     combi(0, [])
#     return resultSet

# sumSet = set()
# for i in range(1, n+1):
#     tempSet = combination(i)
#     sumSet = sumSet | tempSet

# sumList = list(sumSet)
# sumList.sort()

# length = len(sumList)
# i = 0
# while i < length-1:
    # if sumList[i+1] - sumList[i] == 1:
    #     i += 1
    # else:
    #     print(sumList[i]+1)
    #     break
