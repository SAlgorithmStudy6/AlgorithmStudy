# 고쳐야 한다.

answer = 0
document = input()
word = input()
len_d, len_w = len(document), len(word)

def main():
    solution(document, word, 0, 0)
    print(answer)

def solution(_d, _w, _index, _count) :
    global answer
    if _index < len_d:
        answer = max(answer, _count)
    else:
        return
    for i in range(_index ,len_d):
        if _d[i:i + len_w] == _w:
            solution(_d, _w, i+len_w, _count+1)



if __name__ == '__main__':
    main()
