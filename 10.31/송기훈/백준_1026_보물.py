n = int(input())

aList = list(map(int, input().split()))
bList = list(map(int, input().split()))
minValue = 10 ** 9


def calculate(tempA):
    global minValue
    result = 0
    for i in range(n):
        result += tempA[i] * bList[i]

    return result

aList.sort()
bList.sort(reverse=True)

print(calculate(aList))