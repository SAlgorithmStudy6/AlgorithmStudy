
if __name__ == '__main__':
    document = input()
    word = input()
    len_d, len_w = len(document), len(word)
    index, answer = 0, 0
    while index <= len_d:
        if document[index:index+len_w] == word:
            answer += 1
            index += len_w
        else:
            index +=1
    print(answer)


