# 그 외 파이썬이라 가능한 풀이들

document = input()
word = input()


# [풀이 1] replace & count 메소드 활용
def solution1() -> int :
    replaced_document = document.replace(word, '*')
    return replaced_document.count('*')

# [풀이 2] count 메소드 활용
def solution2() -> int :
    return document.count(word)

if __name__ == '__main__':
    answer1 = solution1()
    answer2 = solution2()
    print(answer1)
    print(answer2)