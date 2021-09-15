def solution(rows, columns, queries):
    answer = []
    arr = createMap(rows, columns)
    for query in queries:
        query = list(map(lambda x:x-1, query))
        rotateEdgeMap(arr, query)
        answer.append(findEdgeMin(arr, query))
    return answer

def createMap(rows, columns):
    return [[num + rnum for num in range(columns)] for rnum in range(1, rows * columns, columns)]

def rotateEdgeMap(arr, query):
    start_row, start_col, end_row, end_col = query
    top, right, left, bottom = getEdge(arr, query)
    
    for i, element in enumerate(top): arr[start_row][start_col + 1 + i] = element
    for i, element in enumerate(bottom): arr[end_row][end_col - 1 - i] = element
    for i, element in enumerate(left): arr[end_row - 1 - i][start_col] = element
    for i, element in enumerate(right): arr[start_row + 1 + i][end_col] = element
    
def findEdgeMin(arr, query):
    top, right, bottom, left = getEdge(arr, query)
    return min(top + right + bottom + left)

def getEdge(arr, query):
    start_row, start_col, end_row, end_col = query
    
    top = arr[start_row][start_col:end_col]
    bottom = arr[end_row][end_col:start_col:-1]
    left = [m[start_col] for m in arr[end_row:start_row:-1]]
    right = [m[end_col] for m in arr[start_row:end_row]]
    
    return top, right, left, bottom