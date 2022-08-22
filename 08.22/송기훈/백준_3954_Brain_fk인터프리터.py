import sys

# https://conak-diary.tistory.com/103
sys.stdin = open("input.txt", "r", encoding="utf-8")

t = int(input())


def runCode(code):
    global pointer, memorySize, inputIndex, inputSize, codeIndex, codeSize, maxCodeIndex
    if code == "+":
        memoryList[pointer] += 1
        if memoryList[pointer] == 256:
            memoryList[pointer] = 0
    elif code == "-":
        memoryList[pointer] -= 1
        if memoryList[pointer] == -1:
            memoryList[pointer] = 255
    elif code == "<":
        pointer -= 1
        if pointer == -1:
            pointer = memorySize - 1
    elif code == ">":
        pointer += 1
        if pointer == memorySize:
            pointer = 0
    elif code == "[":
        if memoryList[pointer] == 0:
            codeIndex = jumpList[codeIndex]
    elif code == "]":
        if memoryList[pointer] != 0:
            codeIndex = jumpList[codeIndex]
    elif code == ".":
        pass
    elif code == ",":
        if inputIndex < inputSize:
            memoryList[pointer] = ord(inputList[inputIndex])
            inputIndex += 1
        else:
            memoryList[pointer] = 255
    codeIndex += 1
    maxCodeIndex = max(maxCodeIndex, codeIndex)


def loopCheck():
    global codeIndex, codeSize, maxCodeIndex
    count = 0
    while codeIndex < codeSize:
        count += 1
        runCode(codeList[codeIndex])
        if count >= 50000000:
            print("Loops", jumpList[maxCodeIndex], maxCodeIndex)
            return
    print("Terminates")
    return


for test_case in range(t):
    memorySize, codeSize, inputSize = map(int, input().split())
    codeList = list(input())
    inputList = list(input())

    memoryList = [0] * memorySize
    jumpList = [0] * codeSize
    saveList = [-1] * (codeSize // 2)
    pointer = 0
    codeIndex = 0
    inputIndex = 0
    maxCodeIndex = 0

    count = 0
    for i in range(len(codeList)):
        if codeList[i] == "[":
            saveList[count] = i
            count += 1
        elif codeList[i] == "]":
            count -= 1
            jumpList[saveList[count]] = i
            jumpList[i] = saveList[count]
            saveList[count] = -1

    loopCheck()
